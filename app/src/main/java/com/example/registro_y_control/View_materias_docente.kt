package com.example.registro_y_control

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import com.example.registro_y_control.db.OpenHelperDatabase

class View_materias_docente : AppCompatActivity() {
    lateinit var tableMaterias : TableLayout
    lateinit var db: OpenHelperDatabase
    var items = mutableListOf<Map<*, *>>()
    lateinit var sharedPreferences: SharedPreferences
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_materias_docente)

        sharedPreferences = getSharedPreferences("datos_globales", Context.MODE_PRIVATE)

        db = OpenHelperDatabase(this)
        items = db.getAllMateriasByDocent(sharedPreferences.getInt("numDocument", 0),"ACTIVE").toMutableList()

        tableMaterias = findViewById(R.id.tb_materias_actuales)
        items.forEach{materia ->
            println(materia)
            val registro = LayoutInflater.from(this).inflate(R.layout.vista_materias_estudiantes, null, false)
            val columnNameCourse = registro.findViewById<View>(R.id.nameCourse) as TextView
            val columnSemesterLocatcion = registro.findViewById<View>(R.id.semesterLocation) as TextView
            val columnCredistos = registro.findViewById<View>(R.id.numCreditos) as TextView
            val columnNameTeacher = registro.findViewById<View>(R.id.nameTeacher) as TextView
            columnNameCourse.text = materia["nameCourse"].toString()
            columnSemesterLocatcion.text = "Ubicación semestral ${materia["semesterLocation"].toString()}"
            columnCredistos.text = materia["numCredits"].toString()
            columnNameTeacher.text = materia["nameTeacher"].toString()
            tableMaterias.addView(registro)
        }
    }
}