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
import java.util.Calendar

class Inscripcion_estudiantes : AppCompatActivity() {
    lateinit var db: OpenHelperDatabase
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_est)
        sharedPreferences = getSharedPreferences("datos_globales", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        db = OpenHelperDatabase(this)

        findViewById<EditText>(R.id.nameStudentFalse).setText(sharedPreferences.getString("name", "name"))
        findViewById<EditText>(R.id.lastNameStudentFalse).setText(sharedPreferences.getString("lastName", "lastName"))

        val items = mutableListOf("Seleccione un programa")
        val programs = db.getAllProgramas()
        programs.forEach { program ->
            items.add(program["nameProgram"].toString())
        }

        val spinner: Spinner = this.findViewById(R.id.spinnerPrograma)
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val spinnerTypeDocument: Spinner = this.findViewById(R.id.spinner_typeDocument)
        val itemsTypeDocument = listOf("Seleccione un tipo de documento", "C.C.", "Tarjeta de indentidad", "Otro...")
        var adapterTypeDocument = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsTypeDocument)
        adapterTypeDocument.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTypeDocument.adapter = adapterTypeDocument

        val spinnerGender: Spinner = this.findViewById(R.id.spinner_genderStudent)
        val itemsGender = listOf("Seleccione su genero", "Masculino", "Femenino", "Otro...")
        var adapterGender = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsGender)
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapterGender
    }

    fun inscriptionStudent(view: View){
        if(validateForm()){
            var typeDocument = findViewById<Spinner>(R.id.spinner_typeDocument).selectedItem.toString()
            var gender = findViewById<Spinner>(R.id.spinner_genderStudent).selectedItem.toString()
            var program = findViewById<Spinner>(R.id.spinnerPrograma).selectedItem.toString()
            var birtday = findViewById<EditText>(R.id.birthday).text.toString()
            var phone = findViewById<EditText>(R.id.phoneInscriptionStudent).text.toString()
            println("${sharedPreferences.getInt("numDocument", 0)}, $typeDocument, $gender, $birtday, ${sharedPreferences.getInt("numDocument", 0)}, $program, $phone")
            db.createInscription(sharedPreferences.getInt("numDocument", 0), typeDocument, gender, birtday, sharedPreferences.getInt("numDocument", 0), program, phone)
            Toast.makeText(this, "Se ha creado su solicitud con exito.", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this, Bienvenida::class.java).also {
                startActivity(/* intent = */ it)
            }
        }else{
            Toast.makeText(this, "Por favor ingrese todos los datos", Toast.LENGTH_SHORT).show()
        }
    }

    fun validateForm(): Boolean{
        return (!findViewById<Spinner>(R.id.spinner_typeDocument).selectedItem.toString().equals("Selecciona un tipo de documento") && !findViewById<Spinner>(R.id.spinner_genderStudent).selectedItem.toString().equals("Seleccione su genero")
                && !findViewById<Spinner>(R.id.spinnerPrograma).selectedItem.toString().equals("Seleccione un programa") && !findViewById<EditText>(R.id.birthday).text.equals("") && !findViewById<EditText>(R.id.phoneInscriptionStudent).equals(""))
    }

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