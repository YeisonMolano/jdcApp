package com.example.registro_y_control

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class View_docentes_general : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_docentes_general)
    }

    fun viewDocentesPendientes(view: View){
        val intent: Intent = Intent(this, View_docentes_pendientes::class.java).also {
            startActivity(/* intent = */ it)
        }
    }

    fun assignMaterias(view: View){
        val intent: Intent = Intent(this, Seleccionar_docente::class.java).also {
            startActivity(/* intent = */ it)
        }
    }

    fun viewDodentes(view: View){
        val intent: Intent = Intent(this, View_docentes::class.java).also {
            startActivity(/* intent = */ it)
        }
    }
}