package com.example.registro_y_control

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class View_programs_general : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_programs_general)
    }

    fun newProgramView(view: View){
        val intent: Intent = Intent(this, Nuevo_programa::class.java).also {
            startActivity(/* intent = */ it)
        }
    }

    fun viewProgramsActive(view: View){
        val intent: Intent = Intent(this, View_programas_activos::class.java).also {
            startActivity(/* intent = */ it)
        }
    }

    fun viewProgramsInactive(view: View){
        val intent: Intent = Intent(this, View_programs_inactiv::class.java).also {
            startActivity(/* intent = */ it)
        }
    }
}