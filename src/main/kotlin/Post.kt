import java.lang.RuntimeException

data class Post(
    var id: Int,
    val ownerId: Int,
    val fromID: Int,
    val createdBy: Int,
    val date: Int,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val postType: String,
    val friendsOnly: Boolean,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val copyright: String,
    val views: Int?,
    val postSource: String,
    val signerId: Int,
    val original: Post?,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val postPonedId: Int,
    val comments: Comments?,
    val likes: Likes,
    val reposts: Reposts,
    val geo: Geo,
    val attachments: Array<Attachment> = arrayOf(AudioAttachment(1, 1, "Audio", Audio()),
        PhotoAttachment(1,1,"Photo", Photo()))
)

data class Comments(
    val count: Int,
    val text: String,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true
)

data class Comment(
    val id: Int,
    val fromId: Int,
    val date: Int,
    val text: String
)

data class ReportComments(
    val ownerId: Int,
    val commentId: Int,
    val reason: Int
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = true,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

data class Reposts(
    val count: Int = 0,
    val userReposts: Boolean = true
)

data class Geo(
    val type: String,
    val coordinates: String,
    val place: String
)

interface Attachment {
    val id: Int
    val ownerId: Int
    val type: String
}

data class AudioAttachment(
    override val id: Int,
    override val ownerId: Int,
    override val type: String,
    val audio: Audio
) : Attachment

data class Audio(
    val artist: String = "",
    val title: String = "",
    val duration: Int = 0,
    val url: String = "",
    val lyricsID: Int = 0,
    val albumID: Int = 0,
    val genreId: Int = 0,
    val noSearch: Boolean = true,
    val isHq: Boolean = true
)

data class PhotoAttachment(
    override val id: Int,
    override val ownerId: Int,
    override val type: String,
    val photo: Photo
) : Attachment

data class Photo(
    val albumId: Int = 0,
    val userId: Int = 0,
    val text: String = "",
    val date: Int = 0,
    val width: Int = 0,
    val height: Int = 0
)

data class PostedPhotoAttachment(
    override val id: Int,
    override val ownerId: Int,
    override val type: String,
    val photo: PostedPhoto
) : Attachment

data class PostedPhoto(
    val photo130: String = "",
    val photo604: String = ""
)

data class GraffityAttachment(
    override val id: Int,
    override val ownerId: Int,
    override val type: String,
    val graffity: Graffity
) : Attachment

data class Graffity(
    val photo130: String = "",
    val photo604: String = ""
)

data class HistoryAttachment(
    override val id: Int,
    override val ownerId: Int,
    override val type: String,
    val history: History
) : Attachment

data class History(
    val date: Int = 0,
    val expiresAt: Int = 0,
    val isExpired: Boolean = false,
    val isDelited: Boolean = false,
    val canSee: Boolean = false
)

class PostNotFoundException(massage: String): RuntimeException(massage)
class CommentNotFoundException(massage: String): RuntimeException(massage)
class ReportCommentNotFoundException(massage: String): RuntimeException(massage)


object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var reportComments = emptyArray<ReportComments>()
    private var count = 0

    fun addPost(newPost: Post): Post {
        posts += newPost.copy(id = ++count)
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[index] = newPost.copy()
                return true
            }
        }
        return false
    }

    fun creatComment(postId: Int, comment: Comment): Comment {
        for ((index, post) in posts.withIndex()) {
            if (post.id == postId) {
                comments += comment
                return comments.last()
            }
        }
        throw PostNotFoundException("no comment with id $postId")
    }

    fun addReportComment(commentId: Int, reportComment: ReportComments): ReportComments {
        for ((index, comment) in comments.withIndex()) {
            if (comment.id == commentId) {
                reportComments += reportComment
                if (reportComment.reason < 0 || reportComment.reason > 8) {
                    throw ReportCommentNotFoundException("no reason with id ${reportComment.reason}")
                }
                return reportComments.last()
            }
        }
        throw CommentNotFoundException("no comment with id $commentId")
    }

    fun clear() {
        posts = emptyArray()
        count = 0
    }
}


