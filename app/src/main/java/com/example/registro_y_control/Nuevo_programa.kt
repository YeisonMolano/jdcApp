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

class Nuevo_programa : AppCompatActivity() {
    lateinit var db: OpenHelperDatabase
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var facultys: List<Map<*, *>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nuevo_programa)

        sharedPreferences = getSharedPreferences("datos_globales", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        db = OpenHelperDatabase(this)
        facultys = db.getAllFacultades();
        val items = mutableListOf<Any>("Elija la facultad")

        facultys.forEach { facultad ->
            items.add(facultad["nameFaculty"].toString())
        }

        val spinner: Spinner = this.findViewById(R.id.spinnerFacultades)

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun createNewProgram(view: View){
        if(isValid()){
            if(db.createProgram(this.findViewById<EditText>(R.id.nameProgramCreate).text.toString(),
                    this.findViewById<EditText>(R.id.numSemesterOfProgram).text.toString().toInt(),
                    this.findViewById<EditText>(R.id.numCreditsCreate).text.toString().toInt(),
                    db.findIdFacultyByName(findViewById<Spinner>(R.id.spinnerFacultades).selectedItem.toString()))){
                editor.putInt("idProgramNew", db.findIdProgramByName(this.findViewById<EditText>(R.id.nameProgramCreate).text.toString()))
                editor.apply()
                val intent: Intent = Intent(this, Nuevo_curso::class.java).also {
                    startActivity(/* intent = */ it)
                }
            }else{
                Toast.makeText(this, "Error en la base de datos", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Por favor rellena todos los datos", Toast.LENGTH_SHORT).show()
        }
    }

    fun isValid(): Boolean{
        return !this.findViewById<EditText>(R.id.nameProgramCreate).text.toString().equals("") && !this.findViewById<EditText>(R.id.numCreditsCreate).text.toString().equals("")
                && !this.findViewById<EditText>(R.id.numSemesterOfProgram).text.toString().equals("") && !this.findViewById<Spinner>(R.id.spinnerFacultades).selectedItem.toString().equals("Elija la facultad")
    }
}