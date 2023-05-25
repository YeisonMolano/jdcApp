package com.example.registro_y_control

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.registro_y_control.db.OpenHelperDatabase

class Formulario_inscripcion_docentes : AppCompatActivity() {
    lateinit var db: OpenHelperDatabase
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_inscripcion_doc)
        sharedPreferences = getSharedPreferences("datos_globales", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        db = OpenHelperDatabase(this)

        val spinner: Spinner = findViewById(R.id.spinnerPrograma)
        val items = db.getAllProgramas()
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun inscripcionDocente(view: View){
        if(validateForm()){
            var tittle = findViewById<EditText>(R.id.nameStudentFalse).text.toString()
            var experience = findViewById<EditText>(R.id.experience).text.toString().toInt()
            var programa = findViewById<Spinner>(R.id.spinnerPrograma).selectedItem.toString()
            db.createTeacher(tittle, experience, programa, sharedPreferences.getInt("numDocument", 0))
            Toast.makeText(this, "Se ha creado su solicitud con exito.", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this, Bienvenida::class.java).also {
                startActivity(/* intent = */ it)
            }
        }else{
            Toast.makeText(this, "Por favor ingrese todos los datos", Toast.LENGTH_SHORT).show()
        }
    }

    fun validateForm(): Boolean{
        return (!findViewById<EditText>(R.id.nameStudentFalse).text.toString().equals("") && !findViewById<EditText>(R.id.experience).text.toString().equals("")
        && !findViewById<Spinner>(R.id.spinnerPrograma).selectedItem.toString().equals("Seleccione el programa"))
    }
}