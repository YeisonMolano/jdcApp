package com.example.registro_y_control

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
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

        val editText = findViewById<EditText>(R.id.email)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val email = s.toString().trim()

                if (!email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editText.error = "Ingresa un correo electrónico válido"
                } else {
                    editText.error = null
                }
            }
        })
    }

    fun createUser(view: View){
        if(isValid()){
            if(validatePassword()){
                println("ajaaaa")
                var name = findViewById<EditText>(R.id.nameUser)
                var lastName = findViewById<EditText>(R.id.lastNameNewUser)
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
            }else{
                Toast.makeText(this, "La contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Por favor rellena todos los datos", Toast.LENGTH_SHORT).show()
        }
    }

    fun isValid(): Boolean {
        return !this.findViewById<EditText>(R.id.nameUser).text.equals("") && !this.findViewById<EditText>(R.id.lastNameNewUser).text.equals("") && !this.findViewById<EditText>(R.id.numIdentificacion).text.equals("")
                && !this.findViewById<EditText>(R.id.email).text.equals("") && !this.findViewById<EditText>(R.id.username).text.equals("") && !this.findViewById<EditText>(R.id.passwordUser).text.equals("")
                && !this.findViewById<EditText>(R.id.passwordUser2).text.equals("")
    }

    fun validatePassword(): Boolean{
        println("${this.findViewById<EditText>(R.id.passwordUser).text} ${this.findViewById<EditText>(R.id.passwordUser2).text}")
        return this.findViewById<EditText>(R.id.passwordUser).text.toString().equals(this.findViewById<EditText>(R.id.passwordUser2).text.toString())
    }
}