package com.example.registro_y_control

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.registro_y_control.db.OpenHelperDatabase

class Nuevo_curso : AppCompatActivity() {
    lateinit var db: OpenHelperDatabase
    private lateinit var builder: AlertDialog.Builder
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nuevo_curso)
        db = OpenHelperDatabase(this)
        sharedPreferences = getSharedPreferences("datos_globales", Context.MODE_PRIVATE)

        builder = AlertDialog.Builder(this)
    }

    fun createNewCourse(view: View){
        if(db.createCourse(this.findViewById<EditText>(R.id.nameCourseCreate).text.toString(), this.findViewById<EditText>(R.id.locationSemesterCreate).text.toString().toInt(), this.findViewById<EditText>(R.id.numCreditsOfCourseCreate).text.toString().toInt(), sharedPreferences.getInt("idProgramNew", 0))){
            Toast.makeText(this, "El curso se ha creado correctamente", Toast.LENGTH_SHORT).show()
            builder.setTitle("¡Alerta!").setMessage("¿Desea crear un nuevo curso?").setCancelable(true).setPositiveButton("Si"){dialogInterface, it -> dialogInterface.cancel() }
                .setNegativeButton("Terminar"){dialogInterface, it -> val intent: Intent = Intent(this, View_programs_general::class.java).also {
                    startActivity(/* intent = */ it)
                }}.show()
        }else{
            Toast.makeText(this, "Ha habido un error en la base de datos", Toast.LENGTH_SHORT).show()
        }
    }
}