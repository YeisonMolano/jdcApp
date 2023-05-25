package com.example.registro_y_control

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class View_programs_general : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_programs_general)
    }

    fun newProgramView(){
        val intent: Intent = Intent(this, View_create_program::class.java).also {
            startActivity(/* intent = */ it)
        }
    }
}