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
        val commentOne = Post.Comments()
        val post = Post(0, 0, 0, 0, 1685464427, "Hello, Netology", "Post", true, true, true, commentOne)
        WallService.add(post)
        val result = post.id
        assertEquals(1, result)
    }

    @Test
    fun updateExistingFalse() {
        val commentOne = Post.Comments()
        val post = Post(2, 0, 0, 0, 1685464428, "Hello, sun", "Post", true, true, true, commentOne)
        WallService.update(post)
        val result = false
        assertEquals(false, result)
    }

    @Test
    fun updateExistingTrue() {
        val commentOne = Post.Comments()
        val post = Post(1, 0, 0, 0, 1685464428, "Hello, me", "Post", true, true, true, commentOne)
        WallService.update(post)
        val result = true
        assertEquals(true, result)
    }

}