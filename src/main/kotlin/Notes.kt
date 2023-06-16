import java.util.Objects

data class Notes(
    var notesId: Int,
    val title: String,
    val text: String,
    val notesComments: MutableList<NotesComments>
)

data class NotesComments(
    var commentId: Int,
    var notesId: Int,
    var isDelete: Boolean = false,
    var massage: String
)

interface CrudService<E> {
    fun add(obj: E): Any
}

object NotesService : CrudService<Notes> {
    private val notes = mutableListOf<Notes>()
    private var countNotes = 0
    private var countComment = 0



    override fun add(newNotes: Notes): MutableList<Notes> {
        notes.add(newNotes.copy(notesId = ++countNotes))
        return notes
    }

    fun delete(newNotes: Notes): MutableList<Notes> {
        notes.remove(newNotes)
        newNotes.notesComments.clear()
        return notes
    }

    fun editNotes(newNotes: Notes): MutableList<Notes> {
        for ((index, note) in notes.withIndex()) {
            if (note.notesId == newNotes.notesId) {
                notes[index] = newNotes.copy()
            }
        }
        return notes
    }

    fun getNotes(): MutableList<Notes> {
        return notes
    }

    fun getById(id: Int): Any {
        for ((index, notes) in notes.withIndex()) {
            if (notes.notesId == id) {
                return notes
            }
        }
        return false
    }

    fun addComment(notesId: Int, comment: NotesComments): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.notesId == notesId) {
                comment.commentId = ++countComment
                comment.notesId = notesId
                note.notesComments.add(comment)
                return true
            }
        }
        return false
    }

    fun deleteComment(notesId: Int, comment: NotesComments): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.notesId == notesId) {
                for ((index, notesComments) in note.notesComments.withIndex()) {
                    if (note.notesComments[index].commentId == comment.commentId) {
                        note.notesComments[index].isDelete = true
                    }
                }
                return true
            }
        }
        return false
    }

    fun editComment(newComment: NotesComments): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.notesComments[index].commentId == newComment.commentId && !note.notesComments[index].isDelete) {
                note.notesComments[index].massage = newComment.massage
                return true
            }
        }
        return false
    }

    fun getComments(notesId: Int): MutableList<NotesComments> {
        val nonDeleteNotesComments = mutableListOf<NotesComments>()
        for ((index, note) in notes.withIndex()) {
            if (notes[index].notesId == notesId) {
                for ((index, notesComments) in note.notesComments.withIndex()) {
                    if (!note.notesComments[index].isDelete) {
                        nonDeleteNotesComments.add(note.notesComments[index])
                    }
                }
            }
        }
        return nonDeleteNotesComments
    }

    fun restoreComment(notesId: Int, comment: NotesComments): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (notes[index].notesId == notesId) {
                for ((index, notesComments) in note.notesComments.withIndex()) {
                    if (note.notesComments[index].commentId == comment.commentId && note.notesComments[index].isDelete) {
                        note.notesComments[index].isDelete = false
                        return true
                    }
                }
            }
        }
        return false
    }

    fun clear() {
        notes.clear()
        countNotes = 0
        countComment = 0
    }
}