package com.example.registro_y_control

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Menu_admin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_adm)
    }

    fun viewOptionsStudents(view: View){
        val intent: Intent = Intent(this, Ver_estudiantes::class.java).also {
            startActivity(/* intent = */ it)
        }
    }

    fun viewProgramsGeneral(view: View){
        val intent: Intent = Intent(this, View_programs_general::class.java).also {
            startActivity(/* intent = */ it)
        }
    }

    fun view_docentes_general(view: View){

    }
}