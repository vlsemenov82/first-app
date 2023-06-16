import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NotesServiceTest {
    @Before
    fun clearBeforeTest() {
        NotesService.clear()
    }

    @Test
    fun addNotesTest() {
        val notesComment = NotesComments(1, 1, false, "Hello Kotlin")
        val note = Notes(1, "My", "Hello, Kotlin", mutableListOf())
        val result = NotesService.add(note)
        Assert.assertEquals(mutableListOf(note), result)
    }

    @Test
    fun deleteNotesTest() {
        val notesComment = NotesComments(1, 1, false, "Hello Kotlin")
        val note1 = Notes(1, "My", "Hello, Kotlin", mutableListOf())
        val note2 = Notes(2, "My", "Hello, Kotlin", mutableListOf())
        NotesService.add(note1)
        NotesService.add(note2)
        val result = NotesService.delete(note2)
        Assert.assertEquals(mutableListOf(note1), result)
    }

    @Test
    fun getNotesTest() {
        val notesComment = NotesComments(1, 1, false, "Hello Kotlin")
        val note = Notes(1, "My", "Hello, Kotlin", mutableListOf())
        NotesService.add(note)
        val result = NotesService.getNotes()
        Assert.assertEquals(NotesService.add(note), result)
    }

    @Test
    fun getByIdTest() {
        val notesComment = NotesComments(1, 1, false, "Hello Kotlin")
        val note = Notes(1, "My", "Hello, Kotlin", mutableListOf())
        NotesService.add(note)
        val result = NotesService.getById(1)
        Assert.assertEquals(note, result)
    }

    @Test
    fun editNotesTest() {
        val notesComment = NotesComments(1, 1, false, "Hello Kotlin")
        val note = Notes(1, "My", "Hello, Kotlin", mutableListOf())
        val note2 = Notes(1, "notMy", "Hello, Kotlin", mutableListOf())
        NotesService.add(note)
        Assert.assertEquals(mutableListOf(note2), NotesService.editNotes(note2))
    }
    @Test
    fun addCommentTest() {
        val notesComment = NotesComments(1, 1, false,"Good note")
        val notesComment2 = NotesComments(1, 1, false, "Very good note")
        val note = Notes(1, "My", "Hello, Kotlin", mutableListOf())
        NotesService.add(note)
        Assert.assertEquals(true, NotesService.addComment(1, notesComment2))
    }

    @Test
    fun deleteCommentTest() {
        val notesComment = NotesComments(1, 1, false, "Hello Kotlin")
        val note = Notes(1, "My", "Hello, Kotlin", mutableListOf())
        NotesService.add(note)
        Assert.assertEquals(true, NotesService.deleteComment(1, notesComment))
    }

    @Test
    fun editCommentTest() {
        val notesComment = NotesComments(1, 1, false, "Hello Kotlin")
        val notesComment2 = NotesComments(1, 1, false, "Hello my Kotlin")
        val note = Notes(1, "My", "Hello, Kotlin", mutableListOf())
        NotesService.add(note)
        NotesService.addComment(1, notesComment)
        Assert.assertEquals(true, NotesService.editComment(notesComment2))
    }

    @Test
    fun getCommentTest() {
        val notesComment = NotesComments(1, 1, false, "Hello Kotlin")
        val notesComment2 = NotesComments(1, 1, false, "Hello my Kotlin")
        val note = Notes(1, "My", "Hello, Kotlin", mutableListOf())
        NotesService.add(note)
        NotesService.addComment(1, notesComment)
        NotesService.addComment(1, notesComment2)
        Assert.assertEquals(mutableListOf(notesComment, notesComment2), NotesService.getComments(1))
    }

    @Test
    fun restoreCommentTest() {
        val notesComment = NotesComments(1, 1, false, "Hello Kotlin")
        val notesComment2 = NotesComments(1, 1, false, "Hello my Kotlin")
        val note = Notes(1, "My", "Hello, Kotlin", mutableListOf())
        NotesService.add(note)
        NotesService.addComment(1, notesComment2)
        NotesService.deleteComment(1, notesComment2)
        Assert.assertEquals(true, NotesService.restoreComment(1, notesComment2))
    }
}