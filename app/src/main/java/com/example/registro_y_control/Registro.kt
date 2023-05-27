package com.example.registro_y_control

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.registro_y_control.db.OpenHelperDatabase

class Registro : AppCompatActivity() {
    lateinit var db: OpenHelperDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro)
        db = OpenHelperDatabase(this)
    }

    fun createUser(view: View){
        var name = findViewById<EditText>(R.id.nameUser)
        var lastName = findViewById<EditText>(R.id.numCreditsOfCourseCreate)
        var numIdentificacion = findViewById<EditText>(R.id.numIdentificacion)
        var email = findViewById<EditText>(R.id.email)
        var username = findViewById<EditText>(R.id.username)
        var password = findViewById<EditText>(R.id.passwordUser)
        db.createUser(numIdentificacion.text.toString().toInt(), name.text.toString(),
            lastName.text.toString(), email.text.toString(), username.text.toString(),
            password.text.toString())
        Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
        val intent: Intent = Intent(this, MainActivity::class.java).also {
            startActivity(/* intent = */ it)
        }
    }
}