package com.example.registro_y_control

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Bienvenida : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)
        sharedPreferences = getSharedPreferences("datos_globales", Context.MODE_PRIVATE)
        val authority = sharedPreferences.getString("authority", "auth")

        //Menu de permisos segun rol
        //Rol Admin


        //Rol user
        if (authority.equals("USER")) {
            findViewById<Button>(R.id.boton_trabajador).visibility = View.VISIBLE
            findViewById<Button>(R.id.boton_estudiante).visibility = View.VISIBLE
            findViewById<ImageView>(R.id.imageView4).visibility = View.VISIBLE
            findViewById<TextView>(R.id.textView_titulo_es2).visibility = View.VISIBLE
        } else {
            findViewById<Button>(R.id.boton_trabajador).visibility = View.GONE
            findViewById<Button>(R.id.boton_estudiante).visibility = View.GONE
            findViewById<ImageView>(R.id.imageView4).visibility = View.GONE
            findViewById<TextView>(R.id.textView_titulo_es2).visibility = View.GONE
        }

        //Rol Estudiante

        //Rol docente

        val btnDocente: Button = findViewById(R.id.boton_trabajador)
        btnDocente.setOnClickListener{
            val intent: Intent = Intent(this, Formulario_inscripcion_docentes:: class.java)
            startActivity(intent)
        }
        val btnEstudiante: Button = findViewById(R.id.boton_estudiante)
        btnEstudiante.setOnClickListener{
            val intent: Intent = Intent(this, Inscripcion_estudiantes:: class.java)
            startActivity(intent)
        }
    }
}