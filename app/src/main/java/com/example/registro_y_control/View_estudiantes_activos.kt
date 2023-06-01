package com.example.registro_y_control

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.registro_y_control.db.OpenHelperDatabase

class View_estudiantes_activos : AppCompatActivity() {
    lateinit var tableStudents : TableLayout
    lateinit var db: OpenHelperDatabase
    var items = mutableListOf<Map<*, *>>()
    private lateinit var builder: AlertDialog.Builder
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_estudiantes_activos)
        db = OpenHelperDatabase(this)
        items = db.getAllStudentsByStatus("ACTIVE") as MutableList<Map<*, *>>;
        builder = AlertDialog.Builder(this)

        tableStudents = findViewById(R.id.tb_programs_active)
        items.forEach{student ->
            val registro = LayoutInflater.from(this).inflate(R.layout.tb_programs_active_row, null, false)
            val columnName = registro.findViewById<View>(R.id.column_name) as TextView
            val columnFacultad = registro.findViewById<View>(R.id.column_facultad) as TextView
            val edit = registro.findViewById<View>(R.id.butonEditar)
            val delete = registro.findViewById<View>(R.id.butonDlelete)
            columnName.text = student["name"].toString()
            columnFacultad.text = student["name"].toString()
            println(student["idStudent"])
            edit.id = student["idStudent"].toString().toInt()
            delete.id = student["idStudent"].toString().toInt()
            tableStudents.addView(registro)
        }

        this.findViewById<EditText>(R.id.nameUserNew).visibility = View.GONE
        this.findViewById<EditText>(R.id.lastNameUserNew).visibility = View.GONE
        this.findViewById<EditText>(R.id.statusNew).visibility = View.GONE
        this.findViewById<EditText>(R.id.semesterNew).visibility = View.GONE
        this.findViewById<EditText>(R.id.type_document).visibility = View.GONE
        this.findViewById<EditText>(R.id.genderNew).visibility = View.GONE
        this.findViewById<EditText>(R.id.birthdateNew).visibility = View.GONE
        this.findViewById<EditText>(R.id.phoneNew).visibility = View.GONE
        this.findViewById<Button>(R.id.cancelar).visibility = View.GONE
        this.findViewById<Button>(R.id.editar).visibility = View.GONE

        this.findViewById<TextView>(R.id.textView21).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView22).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView23).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView25).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView26).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView27).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView28).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView29).visibility = View.GONE
    }

    fun terminarEditar(){

    }

    fun edit(view: View){
        var persona = db.findStudentById(view.id)

        this.findViewById<EditText>(R.id.nameUserNew).setText(persona["name"].toString())
        this.findViewById<EditText>(R.id.lastNameUserNew).setText(persona["lastName"].toString())
        this.findViewById<EditText>(R.id.semesterNew).setText(persona["semester"].toString())
        this.findViewById<EditText>(R.id.type_document).setText(persona["typeDocument"].toString())
        this.findViewById<EditText>(R.id.genderNew).setText(persona["gender"].toString())
        this.findViewById<EditText>(R.id.birthdateNew).setText(persona["birthdate"].toString())
        this.findViewById<EditText>(R.id.phoneNew).setText(persona["phone"].toString())

        this.findViewById<TextView>(R.id.textView).visibility = View.GONE
        this.findViewById<TableLayout>(R.id.tb_programs_active).visibility = View.GONE
        this.findViewById<EditText>(R.id.nameUserNew).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.lastNameUserNew).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.statusNew).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.semesterNew).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.type_document).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.genderNew).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.birthdateNew).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.phoneNew).visibility = View.VISIBLE
        this.findViewById<Button>(R.id.cancelar).visibility = View.VISIBLE
        this.findViewById<Button>(R.id.editar).visibility = View.VISIBLE

        this.findViewById<TextView>(R.id.textView21).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView22).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView23).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView25).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView26).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView27).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView28).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView29).visibility = View.VISIBLE
    }

    fun inactive(view: View){
        Toast.makeText(this, "Usuario No existente ${view.id.toString()}", Toast.LENGTH_SHORT).show()
    }
}