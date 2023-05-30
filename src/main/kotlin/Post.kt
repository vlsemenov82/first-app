data class Post(
    var id: Int,
    val ownerId: Int,
    val fromID: Int,
    val createdBy: Int,
    val date: Int,
    val text: String,
    val postType: String,
    val friendsOnly: Boolean,
    val canPin: Boolean,
    val canDelete: Boolean,
    val comments: Comments
) {
    class Comments(
        val count: Int = 20,
        val canPost: Boolean = true,
        val groupsCanPost: Boolean = true,
        val canClose: Boolean = true,
        val canOpen: Boolean = true
    )
}

object WallService {
    private var posts = emptyArray<Post>()
    private var count = 0
    fun add(post: Post): Post {
        posts += post
        count++
        post.id = count
        posts[count - 1] = post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (index + 1 == post.id) {
                posts[index] = post.copy()
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        count = 0
    }
}



