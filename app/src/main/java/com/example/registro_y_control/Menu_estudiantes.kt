package com.example.registro_y_control

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Menu_estudiantes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_estud)
    }

    fun view_materias_actuales(view: View){
        val intent: Intent = Intent(this, View_materias_actuales::class.java).also {
            startActivity(/* intent = */ it)
        }
    }

    fun view_horario_actual(view: View){
        val intent: Intent = Intent(this, View_horario_actual::class.java).also {
            startActivity(/* intent = */ it)
        }
    }

    fun view_liquidacion(view: View){
        val intent: Intent = Intent(this, View_liquidacion::class.java).also {
            startActivity(/* intent = */ it)
        }
    }
}