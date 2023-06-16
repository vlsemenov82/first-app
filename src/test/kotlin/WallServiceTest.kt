import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addExisting() {
        val likes = Likes(0, true, true, true);
        val repost = Reposts(0, true);
        val geo = Geo("Страна", "0.0.0.0", "Россия")
        val post = Post(1, 0, 0, 0, 1685464427, "Hello, Netology", 0, 0,
            "Post", true, true, true, true, "BBC news", 0, "post",
            2, original = null, true, true, true, 1, comments = null, likes, repost, geo)
        WallService.add(post)
        val result = post.id
        assertEquals(1, result)
    }
    @Test
    fun updateExistingTrue() {
        val likes = Likes(0, true, true, true);
        val repost = Reposts(0, true);
        val geo = Geo("Страна", "0.0.0.0", "Россия")
        val post = Post(1, 0, 0, 0, 1685464429, "Hello, me", 0, 0,
            "Post", true, true, true, true, "BBC news", 0, "post",
            2, original = null, true, true, true, 1, comments = null, likes, repost, geo)
        val post2 = Post(1, 0, 0, 0, 1685464430, "Hello, sun", 0, 0,
            "Post", true, true, true, true, "BBC news", 0, "post",
            2, original = null, true, true, true, 1, comments = null, likes, repost, geo)

        WallService.add(post)
        val result =  WallService.update(post2)
        assertEquals(true, result)
    }

    @Test
    fun updateExistingFalse() {
        val likes = Likes(0, true, true, true);
        val repost = Reposts(0, true);
        val geo = Geo("Страна", "0.0.0.0", "Россия")
        val post = Post(1, 0, 0, 0, 1685464430, "Hello, sun", 0, 0,
            "Post", true, true, true, true, "BBC news", 0, "post",
            2, original = null, true, true, true, 1, comments = null, likes, repost, geo)

        val result =  WallService.update(post)
        assertEquals(false, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldIdThrow() {
        val commentOne = Comment(1, 1, 168546441, "Good Post")
        val likes = Likes(0, true, true, true);
        val repost = Reposts(0, true);
        val geo = Geo("Страна", "0.0.0.0", "Россия")
        val post = Post(
            1, 0, 0, 0, 1685464427, "Hello, Netology", 0, 0,
            "Photo", true, true, true, true, "BBC news", 0, "post",
            2, original = null, true, true, true, 1, comments = null, likes, repost, geo
        )
        WallService.add(post)
        WallService.creatComment(2, commentOne)
    }

    @Test
    fun shouldIdNoThrow() {
        val commentOne = Comment(1, 1, 168546441, "Good Post")
        val likes = Likes(0, true, true, true);
        val repost = Reposts(0, true);
        val geo = Geo("Страна", "0.0.0.0", "Россия")
        val post = Post(
            1, 0, 0, 0, 1685464427, "Hello, Netology", 0, 0,
            "Photo", true, true, true, true, "BBC news", 0, "post",
            2, original = null, true, true, true, 1, comments = null, likes, repost, geo
        )
        WallService.add(post)
        val result = WallService.creatComment(1, commentOne)
        assertEquals(commentOne, result)

    }

    @Test(expected = CommentNotFoundException::class)
    fun shouldIdCommentThrow() {
        val commentOne = Comment(1, 1, 168546441, "Good Post")
        val reportComment = ReportComments(1,1, 1)
        val likes = Likes(0, true, true, true);
        val repost = Reposts(0, true);
        val geo = Geo("Страна", "0.0.0.0", "Россия")
        val post = Post(
            1, 0, 0, 0, 1685464427, "Hello, Netology", 0, 0,
            "Photo", true, true, true, true, "BBC news", 0, "post",
            2, original = null, true, true, true, 1, comments = null, likes, repost, geo
        )
        WallService.add(post)
        WallService.creatComment(1, commentOne)
        WallService.addReportComment(2, reportComment)
    }

    @Test(expected = ReportCommentNotFoundException::class)
    fun shouldIdReportCommentThrow() {
        val commentOne = Comment(1, 1, 168546441, "Good Post")
        val reportComment = ReportComments(1,1, 9)
        val likes = Likes(0, true, true, true);
        val repost = Reposts(0, true);
        val geo = Geo("Страна", "0.0.0.0", "Россия")
        val post = Post(
            1, 0, 0, 0, 1685464427, "Hello, Netology", 0, 0,
            "Photo", true, true, true, true, "BBC news", 0, "post",
            2, original = null, true, true, true, 1, comments = null, likes, repost, geo
        )
        WallService.add(post)
        WallService.creatComment(1, commentOne)
        WallService.addReportComment(1, reportComment)
    }

}