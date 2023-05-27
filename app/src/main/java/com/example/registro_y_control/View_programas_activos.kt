package com.example.registro_y_control

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import com.example.registro_y_control.db.OpenHelperDatabase

class View_programas_activos : AppCompatActivity() {
    lateinit var tablePrograms : TableLayout
    lateinit var db: OpenHelperDatabase
    var items = mutableListOf<Map<*, *>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_programas_activos)
        db = OpenHelperDatabase(this)
        items = db.getAllProgramas() as MutableList<Map<*, *>>;

        tablePrograms = findViewById(R.id.tb_programs_active)
        items.forEach{student ->
            println(student)
        }
    }
}