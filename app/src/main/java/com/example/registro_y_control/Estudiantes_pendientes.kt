package com.example.registro_y_control

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.registro_y_control.db.OpenHelperDatabase

class Estudiantes_pendientes : AppCompatActivity() {
    lateinit var db: OpenHelperDatabase
    lateinit var studentsPending: List<Map<*, *>>
    var items = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.estudiantes_pendientes)
        db = OpenHelperDatabase(this)

        //Textiew info estudiante pendiente
        this.findViewById<ImageView>(R.id.imageView5).visibility = View.GONE
        findViewById<TextView>(R.id.email_estudiante_pendiente).visibility = View.GONE
        findViewById<TextView>(R.id.program_estudiante_pendiente).visibility = View.GONE
        findViewById<TextView>(R.id.birthdate_estudiante_pendiente).visibility = View.GONE
        findViewById<TextView>(R.id.gender_estudiante_pendiente).visibility = View.GONE
        findViewById<TextView>(R.id.nombres_estudiante_pendiente).visibility = View.GONE
        findViewById<TextView>(R.id.numIdentificacion_estudiante_pendiente).visibility = View.GONE
        findViewById<TextView>(R.id.telefono_estudiante_pendiente).visibility = View.GONE
        this.findViewById<Button>(R.id.find_other).visibility = View.GONE
        this.findViewById<Button>(R.id.accept_teacher).visibility = View.GONE
        this.findViewById<Button>(R.id.rejected_teacher).visibility = View.GONE
        this.findViewById<TextView>(R.id.nameStudentPending).visibility = View.GONE
        this.findViewById<TextView>(R.id.birdhtadeStudentPending).visibility = View.GONE
        this.findViewById<TextView>(R.id.emailStudentPending).visibility = View.GONE
        this.findViewById<TextView>(R.id.genderStudentPending).visibility = View.GONE
        this.findViewById<TextView>(R.id.phoneStudentPending).visibility = View.GONE
        this.findViewById<TextView>(R.id.identificationStudentPending).visibility = View.GONE
        this.findViewById<TextView>(R.id.programStudentPending).visibility = View.GONE

        chargeList()
    }

    fun chargeList(){
        items.clear()
        items.add("Ver estudiantes pendientes")
        studentsPending = db.getAllStudentsPending()
        studentsPending.forEach{student ->
            items.add("${student["numDocument"]} ${student["name"]}")
        }
        val spinner: Spinner = this.findViewById(R.id.spinner_studenst_pending)
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun showUser(view: View) =
        if(!findViewById<Spinner>(R.id.spinner_studenst_pending).selectedItem.toString().equals("Ver estudiantes pendientes")){
            println(findViewById<Spinner>(R.id.spinner_studenst_pending).selectedItem)
            var student =  db.findUserInsrciption(findViewById<Spinner>(R.id.spinner_studenst_pending).selectedItem.toString().split(" ")[0].toInt(), "STUDENT")
            findViewById<Button>(R.id.assign_materia).visibility = View.GONE

            this.findViewById<ImageView>(R.id.imageView5).visibility = View.VISIBLE

            findViewById<TextView>(R.id.email_estudiante_pendiente).visibility = View.VISIBLE
            findViewById<TextView>(R.id.program_estudiante_pendiente).visibility = View.VISIBLE
            findViewById<TextView>(R.id.birthdate_estudiante_pendiente).visibility = View.VISIBLE
            findViewById<TextView>(R.id.gender_estudiante_pendiente).visibility = View.VISIBLE
            findViewById<TextView>(R.id.nombres_estudiante_pendiente).visibility = View.VISIBLE
            findViewById<TextView>(R.id.numIdentificacion_estudiante_pendiente).visibility = View.VISIBLE
            findViewById<TextView>(R.id.telefono_estudiante_pendiente).visibility = View.VISIBLE

            this.findViewById<TextView>(R.id.nameStudentPending).text = (student?.get("name").toString())
            this.findViewById<TextView>(R.id.birdhtadeStudentPending).text = (student?.get("birthdate").toString())
            this.findViewById<TextView>(R.id.emailStudentPending).text = (student?.get("email").toString())
            this.findViewById<TextView>(R.id.genderStudentPending).text = (student?.get("gender").toString())
            this.findViewById<TextView>(R.id.phoneStudentPending).text = (student?.get("phone").toString())
            this.findViewById<TextView>(R.id.identificationStudentPending).text = (student?.get("numDocument").toString())
            this.findViewById<TextView>(R.id.programStudentPending).text = (student?.get("program").toString())

            this.findViewById<TextView>(R.id.nameStudentPending).visibility = View.VISIBLE
            this.findViewById<TextView>(R.id.birdhtadeStudentPending).visibility = View.VISIBLE
            this.findViewById<TextView>(R.id.emailStudentPending).visibility = View.VISIBLE
            this.findViewById<TextView>(R.id.genderStudentPending).visibility = View.VISIBLE
            this.findViewById<TextView>(R.id.phoneStudentPending).visibility = View.VISIBLE
            this.findViewById<TextView>(R.id.identificationStudentPending).visibility = View.VISIBLE
            this.findViewById<TextView>(R.id.programStudentPending).visibility = View.VISIBLE

            this.findViewById<Button>(R.id.find_other).visibility = View.VISIBLE
            this.findViewById<Button>(R.id.accept_teacher).visibility = View.VISIBLE
            this.findViewById<Button>(R.id.rejected_teacher).visibility = View.VISIBLE
        }else{
            Toast.makeText(this, "Por favor elija un estudiante", Toast.LENGTH_SHORT).show()
        }

    fun recharge(view: View){
        chargeList()
        findViewById<TextView>(R.id.email_estudiante_pendiente).visibility = View.GONE
        findViewById<TextView>(R.id.program_estudiante_pendiente).visibility = View.GONE
        findViewById<TextView>(R.id.birthdate_estudiante_pendiente).visibility = View.GONE
        findViewById<TextView>(R.id.gender_estudiante_pendiente).visibility = View.GONE
        findViewById<TextView>(R.id.nombres_estudiante_pendiente).visibility = View.GONE
        findViewById<TextView>(R.id.numIdentificacion_estudiante_pendiente).visibility = View.GONE
        findViewById<TextView>(R.id.telefono_estudiante_pendiente).visibility = View.GONE
        this.findViewById<Button>(R.id.find_other).visibility = View.GONE
        this.findViewById<Button>(R.id.accept_teacher).visibility = View.GONE
        this.findViewById<Button>(R.id.rejected_teacher).visibility = View.GONE
        this.findViewById<TextView>(R.id.nameStudentPending).visibility = View.GONE
        this.findViewById<TextView>(R.id.birdhtadeStudentPending).visibility = View.GONE
        this.findViewById<TextView>(R.id.emailStudentPending).visibility = View.GONE
        this.findViewById<TextView>(R.id.genderStudentPending).visibility = View.GONE
        this.findViewById<TextView>(R.id.phoneStudentPending).visibility = View.GONE
        this.findViewById<TextView>(R.id.identificationStudentPending).visibility = View.GONE
        this.findViewById<TextView>(R.id.programStudentPending).visibility = View.GONE
        this.findViewById<ImageView>(R.id.imageView5).visibility = View.GONE
        findViewById<Button>(R.id.assign_materia).visibility = View.VISIBLE
    }

    fun acceptStudent(view:View){
        db.updateRolTo(findViewById<Spinner>(R.id.spinner_studenst_pending).selectedItem.toString().split(" ")[0].toInt(), "STUDENT")
        if(db.createFirstCurrentSemester(db.findIdProgramByName(findViewById<TextView>(R.id.programStudentPending).text.toString()),
                findViewById<TextView>(R.id.identificationStudentPending).text.toString().toInt())){
            chargeList()
            findViewById<TextView>(R.id.email_estudiante_pendiente).visibility = View.GONE
            findViewById<TextView>(R.id.program_estudiante_pendiente).visibility = View.GONE
            findViewById<TextView>(R.id.birthdate_estudiante_pendiente).visibility = View.GONE
            findViewById<TextView>(R.id.gender_estudiante_pendiente).visibility = View.GONE
            findViewById<TextView>(R.id.nombres_estudiante_pendiente).visibility = View.GONE
            findViewById<TextView>(R.id.numIdentificacion_estudiante_pendiente).visibility = View.GONE
            findViewById<TextView>(R.id.telefono_estudiante_pendiente).visibility = View.GONE
            this.findViewById<Button>(R.id.find_other).visibility = View.GONE
            this.findViewById<Button>(R.id.accept_teacher).visibility = View.GONE
            this.findViewById<Button>(R.id.rejected_teacher).visibility = View.GONE
            this.findViewById<TextView>(R.id.nameStudentPending).visibility = View.GONE
            this.findViewById<TextView>(R.id.birdhtadeStudentPending).visibility = View.GONE
            this.findViewById<TextView>(R.id.emailStudentPending).visibility = View.GONE
            this.findViewById<TextView>(R.id.genderStudentPending).visibility = View.GONE
            this.findViewById<TextView>(R.id.phoneStudentPending).visibility = View.GONE
            this.findViewById<TextView>(R.id.identificationStudentPending).visibility = View.GONE
            this.findViewById<TextView>(R.id.programStudentPending).visibility = View.GONE
            this.findViewById<ImageView>(R.id.imageView5).visibility = View.GONE
            findViewById<Button>(R.id.assign_materia).visibility = View.VISIBLE
        }else{
            Toast.makeText(this, "Error en la base de datos", Toast.LENGTH_SHORT).show()
        }
    }

    fun reject(view: View){
        db.dropInsription(findViewById<Spinner>(R.id.spinner_studenst_pending).selectedItem.toString().split(" ")[0].toInt())
        recharge(view)
        items.clear()
        chargeList()
    }
}