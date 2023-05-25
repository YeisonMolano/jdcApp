package com.example.registro_y_control

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.registro_y_control.db.OpenHelperDatabase

class MainActivity : AppCompatActivity() {
    lateinit var db: OpenHelperDatabase
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("datos_globales", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        db = OpenHelperDatabase(this)

        val btn: Button = findViewById(R.id.boton_crear_cuenta)
        btn.setOnClickListener{
            val intent: Intent = Intent(this, Registro:: class.java)
            startActivity(intent)
        }
    }

    fun login(view: View){
        var username = findViewById<EditText>(R.id.username)
        var password = findViewById<EditText>(R.id.password)
        if(db.login(username.text.toString(), password.text.toString()) != ("El usuario no exite")){
            val userDetails = db.login(username.text.toString(), password.text.toString()) as Map<*, *>
            editor.putString("authority", userDetails["role"].toString())
            editor.putString("name", userDetails["name"].toString())
            editor.putString("lastName", userDetails["lastName"].toString())
            editor.putInt("numDocument", userDetails["numDocument"].toString().toInt())
            editor.putString("email", userDetails["email"].toString())
            editor.apply()
            println(userDetails["role"].toString())
            when(userDetails["role"].toString()){
                "ADMIN" -> {
                    val intent: Intent = Intent(this, Menu_admin::class.java).also {
                        startActivity(/* intent = */ it)
                    }
                }
                "USER" -> {
                    val intent: Intent = Intent(this, Bienvenida::class.java).also {
                        startActivity(/* intent = */ it)
                    }
                }
                "STUDENT" -> {
                    val intent: Intent = Intent(this, Menu_estudiantes::class.java).also {
                        startActivity(/* intent = */ it)
                    }
                }
                "TEACHER" -> {
                    val intent: Intent = Intent(this, Bienvenida::class.java).also {
                        startActivity(/* intent = */ it)
                    }
                }
                "SUPPORT" -> {
                    val intent: Intent = Intent(this, Menu_apoyo::class.java).also {
                        startActivity(/* intent = */ it)
                    }
                }
            }
        }else{
            Toast.makeText(this, "Usuario No existente", Toast.LENGTH_SHORT).show()
        }
    }
}