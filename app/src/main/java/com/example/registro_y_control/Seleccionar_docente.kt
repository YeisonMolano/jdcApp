package com.example.registro_y_control

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.registro_y_control.db.OpenHelperDatabase

class Seleccionar_docente : AppCompatActivity() {
    lateinit var db: OpenHelperDatabase
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seleccionar_docente)

        val items = mutableListOf("Seleccione un programa")
        val programs = db.getAllProgramas()
        programs.forEach { program ->
            items.add(program["nameProgram"].toString())
        }

        val spinner: Spinner = this.findViewById(R.id.spinnerPrograma)
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun assign(){
        if(validateForm()){

        }
    }

    fun validateForm(): Boolean {
        return !this.findViewById<Spinner>(R.id.spinner_docents).selectedItem.toString().equals("Seleccione un docente")
    }
}