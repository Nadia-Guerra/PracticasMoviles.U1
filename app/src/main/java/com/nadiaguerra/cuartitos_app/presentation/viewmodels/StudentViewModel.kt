package com.nadiaguerra.cuartitos_app.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.nadiaguerra.cuartitos_app.data.Student

class StudentViewModel : ViewModel() {

    private val _students = mutableStateListOf(
        Student(1L, "Nana", "Traumada", "https://i.pinimg.com/736x/18/d8/6c/18d86cdeb5b0140083078b78d7b99d26.jpg"),
        Student(2L, "Hachi", "Traumada rosa", "https://i.pinimg.com/736x/87/7e/d7/877ed77f44e98c69c567c3fe7b69ab49.jpg"),
        Student(3L, "Yasu", "abogado pelon","https://preview.redd.it/i-think-we-can-all-agree-that-yasu-is-the-best-male-v0-pk1br3ek6lde1.jpeg"),
        Student(4L, "Ren", "novio de la traumada","https://static.wikia.nocookie.net/nana/images/1/12/Ren-H.jpg"),
        Student(5L, "Reira", "me cae mal","https://static.wikia.nocookie.net/nana/images/6/6f/Layla-S.jpg"),
        Student(6L, "Takumi", "me cae mal","https://i.pinimg.com/1200x/86/24/e0/8624e09e7586c97d417e5e6c69957039.jpg"),
        Student(7L, "Shoji", "me cae mal","https://i.pinimg.com/1200x/d8/28/c5/d828c533d73d86c093898165168a823c.jpg"),
        Student(8L, "Sachiko", "me cae mal","https://i.pinimg.com/736x/81/73/ec/8173ec0362361827985d8f9e84c943e4.jpg"),
        Student(9L, "Juno", "mala amiga", "https://i.pinimg.com/1200x/c4/ae/0e/c4ae0e5bc8adfff5847010eef1bdc186.jpg"),
        Student(10L, "No se", "no se","https://ichef.bbci.co.uk/ace/ws/800/cpsprodpb/12DF4/production/_120600377_gettyimages-637743724.jpg.webp")
    )
    val students: List<Student>
        get() {
            return _students
        }

    fun addStudent(name: String, description: String, image: String) {
        var newId = 1L
        if (_students.isNotEmpty()) {
            val lastStudent = _students.last()
            newId = lastStudent.id + 1
        }

        val newStudent = Student(newId, name, description, image)
        _students.add(newStudent)
    }

    fun deleteStudent(id: Long) {
        for (student in _students) {
            if (student.id == id) {
                _students.remove(student)
                break
            }
        }
    }
    fun getStudentById(id: Long): Student? {
        for (student in _students) {
            if (student.id == id) {
                return student
            }
        }
        return null
    }
}
