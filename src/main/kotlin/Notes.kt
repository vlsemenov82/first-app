data class Notes(
    var notesId: Int,
    val title: String,
    val text: String,
    var notesComments: NotesComments?

) {
    operator fun set(index: Int, value: Notes) {
    }

    operator fun get(index: Int) {
    }
}

data class NotesComments(
    var commentId: Int,
    var isDelete: Boolean = false,
    val massage: String
) {
    operator fun get(index: Int) {
    }
}

object NotesService {
    private val notes = mutableListOf<Notes>()
    private val notesComments = mutableListOf<NotesComments>()
    private var countNotes = 0
    private var countComment = 0

    fun addNotes(newNotes: Notes): MutableList<Notes> {
        notes.add(newNotes.copy(notesId = ++countNotes))
        return notes
    }

    fun deleteNotes(newNotes: Notes): MutableList<Notes> {
        notes.remove(newNotes)
        notesComments.remove(newNotes.notesComments)
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
                notesComments.add(comment)
                notes[index].notesComments = comment
                return true
            }
        }
        return false
    }

    fun deleteComment(notesId: Int, comment: NotesComments): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.notesId == notesId && !note.notesComments!!.isDelete) {
                notes[index].notesComments = null
                notesComments.remove(comment)
                comment.isDelete = true
                notesComments.add(comment)
                return true
            }
        }
        return false
    }

    fun editComment(newComment: NotesComments): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.notesComments!!.commentId == newComment.commentId && !note.notesComments!!.isDelete) {
                notesComments.remove(notes[index].notesComments)
                notes[index].notesComments = newComment
                notesComments.add(newComment)
                return true
            }
        }
        return false
    }

    fun getComments(): MutableList<NotesComments> {
        val nonDeleteNotesComments = mutableListOf<NotesComments>()
        for ((index, noteComment) in notesComments.withIndex()) {
            if (noteComment.isDelete == false) {
                nonDeleteNotesComments.add(notesComments[index])
            }
        }
        return nonDeleteNotesComments
    }

    fun restoreComment(notesId: Int, comment: NotesComments): Boolean {
        for ((index, noteComment) in notesComments.withIndex()) {
            if (notesComments[index].commentId == comment.commentId && notesComments[index].isDelete) {
                notesComments[index] = comment
                notesComments[index].isDelete = false
                comment.isDelete = false
                for ((index, note) in notes.withIndex()) {
                    if (note.notesId == notesId) {
                        notes[index].notesComments = comment
                        return true
                    }
                }
            }
        }
        return false
    }

    fun clear() {
        notes.clear()
        notesComments.clear()
        countNotes = 0
        countComment = 0
    }
}