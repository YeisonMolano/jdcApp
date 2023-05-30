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

class View_materias_activas : AppCompatActivity() {
    lateinit var tablePrograms : TableLayout
    lateinit var db: OpenHelperDatabase
    lateinit var sharedPreferences: SharedPreferences
    var items = mutableListOf<Map<*, *>>()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_materias_activas)
        sharedPreferences = getSharedPreferences("datos_globales", Context.MODE_PRIVATE)

        db = OpenHelperDatabase(this)
        items = db.getAllMateriasByStudent(sharedPreferences.getInt("numDocument", 0),"ACTIVE") as MutableList<Map<*, *>>;

        tablePrograms = findViewById(R.id.tb_programs_active)
        items.forEach{student ->
            println(student)
            val registro = LayoutInflater.from(this).inflate(R.layout.tb_programs_active_row, null, false)
            val columnName = registro.findViewById<View>(R.id.column_name) as TextView
            val columnFacultad = registro.findViewById<View>(R.id.column_facultad) as TextView
            val edit = registro.findViewById<View>(R.id.butonEditar)
            val delete = registro.findViewById<View>(R.id.butonDlelete)
            columnName.text = student["nameProgram"] as CharSequence?
            columnFacultad.text = student["nameFaculty"] as CharSequence?
            edit.id = student["idProgram"] as Int
            delete.id = student["idProgram"] as Int
            tablePrograms.addView(registro)
        }
    }
}