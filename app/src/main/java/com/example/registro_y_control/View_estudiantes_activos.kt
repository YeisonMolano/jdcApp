package com.example.registro_y_control

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.registro_y_control.db.OpenHelperDatabase

class View_estudiantes_activos : AppCompatActivity() {
    lateinit var tableStudents : TableLayout
    lateinit var db: OpenHelperDatabase
    var items = mutableListOf<Map<*, *>>()
    private lateinit var builder: AlertDialog.Builder
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_estudiantes_activos)
        db = OpenHelperDatabase(this)
        items = db.getAllStudentsByStatus("ACTIVE") as MutableList<Map<*, *>>;
        builder = AlertDialog.Builder(this)
        sharedPreferences = getSharedPreferences("datos_globales", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        tableStudents = findViewById(R.id.tb_programs_active)
        items.forEach{student ->
            val registro = LayoutInflater.from(this).inflate(R.layout.tb_programs_active_row, null, false)
            val columnName = registro.findViewById<View>(R.id.column_name) as TextView
            val columnFacultad = registro.findViewById<View>(R.id.column_facultad) as TextView
            val edit = registro.findViewById<View>(R.id.butonEditar)
            val delete = registro.findViewById<View>(R.id.butonDlelete)
            columnName.text = student["name"].toString()
            columnFacultad.text = student["lastName"].toString()
            println(student["idStudent"])
            edit.id = student["idStudent"].toString().toInt()
            delete.id = student["idStudent"].toString().toInt()
            tableStudents.addView(registro)
        }

        this.findViewById<EditText>(R.id.nameUserNew).visibility = View.GONE
        this.findViewById<EditText>(R.id.lastNameUserNew).visibility = View.GONE
        this.findViewById<EditText>(R.id.statusNew).visibility = View.GONE
        this.findViewById<EditText>(R.id.semesterNew).visibility = View.GONE
        this.findViewById<EditText>(R.id.type_document).visibility = View.GONE
        this.findViewById<EditText>(R.id.genderNew).visibility = View.GONE
        this.findViewById<EditText>(R.id.birthdateNew).visibility = View.GONE
        this.findViewById<EditText>(R.id.phoneNew).visibility = View.GONE
        this.findViewById<Button>(R.id.cancelar).visibility = View.GONE
        this.findViewById<Button>(R.id.editar).visibility = View.GONE

        this.findViewById<TextView>(R.id.textView21).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView22).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView23).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView25).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView26).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView27).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView28).visibility = View.GONE
        this.findViewById<TextView>(R.id.textView29).visibility = View.GONE
    }

    fun terminarEditar(view: View){
        if(isValid()){
            if(db.updateStudent(
                sharedPreferences.getString("documentPerson", "none")!!.toInt(), this.findViewById<EditText>(R.id.semesterNew).text.toString(),
                this.findViewById<EditText>(R.id.statusNew).text.toString(), this.findViewById<EditText>(R.id.type_document).text.toString(),
                this.findViewById<EditText>(R.id.genderNew).text.toString(), this.findViewById<EditText>(R.id.birthdateNew).text.toString(),
                    this.findViewById<EditText>(R.id.nameUserNew).text.toString(), this.findViewById<EditText>(R.id.lastNameUserNew).text.toString())){
                Toast.makeText(this, "Se ha actualizado al estudiante ${view.id.toString()}", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, View_estudiantes_activos::class.java).also {
                    startActivity(/* intent = */ it)
                }
            }

        }else{
            Toast.makeText(this, "Por favor ingrese todos los campos ${view.id.toString()}", Toast.LENGTH_SHORT).show()
        }
    }

    fun edit(view: View){
        editor.putString("documentPerson", "${view.id}")
        editor.apply()
        var persona = db.findStudentById(view.id)

        this.findViewById<EditText>(R.id.nameUserNew).setText(persona["name"].toString())
        this.findViewById<EditText>(R.id.lastNameUserNew).setText(persona["lastName"].toString())
        this.findViewById<EditText>(R.id.semesterNew).setText(persona["semester"].toString())
        this.findViewById<EditText>(R.id.type_document).setText(persona["typeDocument"].toString())
        this.findViewById<EditText>(R.id.genderNew).setText(persona["gender"].toString())
        this.findViewById<EditText>(R.id.statusNew).setText(persona["status"].toString())
        this.findViewById<EditText>(R.id.birthdateNew).setText(persona["birthdate"].toString())

        this.findViewById<TextView>(R.id.textView).visibility = View.GONE
        this.findViewById<TableLayout>(R.id.tb_programs_active).visibility = View.GONE
        this.findViewById<EditText>(R.id.nameUserNew).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.lastNameUserNew).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.statusNew).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.semesterNew).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.type_document).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.genderNew).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.birthdateNew).visibility = View.VISIBLE
        this.findViewById<EditText>(R.id.phoneNew).visibility = View.VISIBLE
        this.findViewById<Button>(R.id.cancelar).visibility = View.VISIBLE
        this.findViewById<Button>(R.id.editar).visibility = View.VISIBLE

        this.findViewById<TextView>(R.id.textView21).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView22).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView23).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView25).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView26).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView27).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView28).visibility = View.VISIBLE
        this.findViewById<TextView>(R.id.textView29).visibility = View.VISIBLE
    }

    fun inactive(view: View){
        db.deleteStudent(view.id)
        items = db.getAllStudentsByStatus("ACTIVE") as MutableList<Map<*, *>>;
    }

    fun cancelar(view: View){
        val intent: Intent = Intent(this, Ver_estudiantes::class.java).also {
            startActivity(/* intent = */ it)
        }
    }

    fun isValid(): Boolean{
        return sharedPreferences.getString("documentPerson", "none")!!.toInt() != 0 && !this.findViewById<EditText>(R.id.semesterNew).text.equals("") &&
        !this.findViewById<EditText>(R.id.statusNew).text.toString().equals("") && !this.findViewById<EditText>(R.id.type_document).text.toString().equals("") &&
        !this.findViewById<EditText>(R.id.genderNew).text.toString().equals("") && !this.findViewById<EditText>(R.id.birthdateNew).text.equals("")
    }
}