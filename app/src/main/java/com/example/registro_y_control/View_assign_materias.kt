package com.example.registro_y_control

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.registro_y_control.db.OpenHelperDatabase

class View_assign_materias : AppCompatActivity() {
    lateinit var db: OpenHelperDatabase
    var materias = mutableListOf<Map<*, *>>()
    lateinit var sharedPreferences: SharedPreferences
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_assign_materias)

        sharedPreferences = getSharedPreferences("datos_globales", Context.MODE_PRIVATE)
        db = OpenHelperDatabase(this)
        materias = db.getAllMaterias() as MutableList<Map<*, *>>;
        this.findViewById<TextView>(R.id.nameTeacherFalse2).text = sharedPreferences.getString("nameDocente", "NA")
        this.findViewById<TextView>(R.id.nameTeacherFalse3).text = sharedPreferences.getString("lastNameDocente", "NA")

        val items = mutableListOf<Any>("Elija la facultad")

        materias.forEach { materia ->
            items.add("${materia["idMateria"].toString()} ${materia["nameCourse"]}")
        }

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        this.findViewById<AutoCompleteTextView>(R.id.autoComplete).threshold=0
        this.findViewById<AutoCompleteTextView>(R.id.autoComplete).setAdapter(adapter)
    }

    fun assignMaterias(view: View){
        if(validate()){
            if(db.createAssignedSubjects(sharedPreferences.getInt("idDocente", 0),
                    this.findViewById<AutoCompleteTextView>(R.id.autoComplete).text.split(" ")[0].toInt())){
                val intent: Intent = Intent(this, Seleccionar_docente::class.java).also {
                    startActivity(/* intent = */ it)
                }
                Toast.makeText(this, "Se ha asignado la materia", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Ha ocurrido un erro en la base de datos", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Por favor elija una materia", Toast.LENGTH_SHORT).show()
        }
}

    fun validate(): Boolean{
        println("${this.findViewById<AutoCompleteTextView>(R.id.autoComplete).text} soy")
        return this.findViewById<AutoCompleteTextView>(R.id.autoComplete).text.toString() != ""
    }
}