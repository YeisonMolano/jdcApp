package com.example.registro_y_control

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Ver_estudiantes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver_estudiantes)
    }

    fun viewStudents(view: View){
        val intent: Intent = Intent(this, Estudiantes_pendientes::class.java).also {
            startActivity(/* intent = */ it)
        }
    }
}