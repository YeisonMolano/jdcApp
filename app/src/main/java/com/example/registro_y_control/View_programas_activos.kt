package com.example.registro_y_control

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import com.example.registro_y_control.db.OpenHelperDatabase

class View_programas_activos : AppCompatActivity() {
    lateinit var tablePrograms : TableLayout
    lateinit var db: OpenHelperDatabase
    var items = mutableListOf<Map<*, *>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_programas_activos)
        db = OpenHelperDatabase(this)
        items = db.getAllProgramasByStatus("ACTIVE") as MutableList<Map<*, *>>;

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

    fun edit(view: View){
        Toast.makeText(this, "Usuario No existente ${view.id.toString()}", Toast.LENGTH_SHORT).show()
    }

    fun inactive(view: View){
        Toast.makeText(this, "Usuario No existente ${view.id.toString()}", Toast.LENGTH_SHORT).show()
    }
}