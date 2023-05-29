package com.example.registro_y_control

import android.app.DatePickerDialog
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
import java.util.*

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

        val items = mutableListOf("Seleccione un programa")
        val programs = db.getAllProgramas()
        programs.forEach { program ->
            items.add(program["nameProgram"].toString())
        }

        val spinner: Spinner = this.findViewById(R.id.spinnerPrograma)
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val spinnerGender: Spinner = this.findViewById(R.id.spinner_genderStudent)
        val itemsGender = listOf("Seleccione su genero", "Masculino", "Femenino", "Otro...")
        var adapterGender = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsGender)
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapterGender
    }

    fun inscripcionDocente(view: View){
        if(validateForm()){
            println("ACEPTED")
            var tittle = this.findViewById<EditText>(R.id.tittleCreateTeacher).text.toString()
            var experience = findViewById<EditText>(R.id.experienceCreateTeacher).text.toString().toInt()
            var programa = findViewById<Spinner>(R.id.spinnerPrograma).selectedItem.toString()
            var gender = this.findViewById<Spinner>(R.id.spinner_genderStudent).selectedItem.toString()
            var birtdate = this.findViewById<EditText>(R.id.birthday).text.toString()
            var phone = this.findViewById<EditText>(R.id.phoneInscriptionStudent).text.toString()
            db.createTeacherInscription(sharedPreferences.getInt("numDocument", 0), gender, birtdate, sharedPreferences.getInt("numDocument", 0), phone, experience, tittle, programa,)
            Toast.makeText(this, "Se ha creado su solicitud con exito.", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this, Bienvenida::class.java).also {
                startActivity(/* intent = */ it)
            }
        }else{
            Toast.makeText(this, "Por favor ingrese todos los datos", Toast.LENGTH_SHORT).show()
        }
    }

    fun validateForm(): Boolean{
        return (!findViewById<EditText>(R.id.tittleCreateTeacher).text.toString().equals("") && !findViewById<EditText>(R.id.experienceCreateTeacher).text.toString().equals("")
                && !findViewById<Spinner>(R.id.spinner_genderStudent).selectedItem.toString().equals("Seleccione su genero")
                && !findViewById<Spinner>(R.id.spinnerPrograma).selectedItem.toString().equals("Seleccione un programa") &&
                !findViewById<EditText>(R.id.birthday).text.equals("") &&
                !findViewById<EditText>(R.id.phoneInscriptionStudent).equals(""))
    }



    //

    fun mostrarDatePickerDialog(view: View) {
        val editTextFecha = findViewById<EditText>(R.id.birthday)
        val calendar = Calendar.getInstance()
        val año = calendar.get(Calendar.YEAR)
        val mes = calendar.get(Calendar.MONTH)
        val día = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val fechaSeleccionada = "$dayOfMonth/${month + 1}/$year"
            editTextFecha.setText(fechaSeleccionada)
        }, año, mes, día)

        datePickerDialog.show()
    }
}