package com.example.registro_y_control

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.registro_y_control.db.OpenHelperDatabase

class Seleccionar_docente : AppCompatActivity() {
    lateinit var db: OpenHelperDatabase
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seleccionar_docente)
        db = OpenHelperDatabase(this)
        sharedPreferences = getSharedPreferences("datos_globales", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val items = mutableListOf("Seleccione un docente")
        val teachers = db.getAllTeachersByStatus("ACTIVE")
        teachers.forEach { teacher ->
            items.add("${teacher["idTeacher"]} ${teacher["name"].toString()}")
        }

        val spinner: Spinner = this.findViewById(R.id.spinner_seleccionar_docente)
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun assign(view: View){
        if(validateForm()){
            editor.putString("nameDocente", db.findUserById(this.findViewById<Spinner>(R.id.spinner_seleccionar_docente).selectedItem.toString()
                .split(" ")[0].toInt(), 1)["name"].toString())
            editor.putString("lastNameDocente", db.findUserById(this.findViewById<Spinner>(R.id.spinner_seleccionar_docente).selectedItem.toString()
                .split(" ")[0].toInt(), 1)["lastName"].toString())
            editor.putInt("idDocente", db.findUserById(this.findViewById<Spinner>(R.id.spinner_seleccionar_docente).selectedItem.toString()
                .split(" ")[0].toInt(), 1)["numDocument"].toString().toInt())
            editor.apply()
            val intent: Intent = Intent(this, View_assign_materias::class.java).also {
                startActivity(/* intent = */ it)
            }
        }else{
            Toast.makeText(this, "Por favor elija un docente", Toast.LENGTH_SHORT).show()
        }
    }

    fun validateForm(): Boolean {
        return !this.findViewById<Spinner>(R.id.spinner_seleccionar_docente).selectedItem.toString().equals("Seleccione un docente")
    }
}