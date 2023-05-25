package com.example.registro_y_control.db

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class OpenHelperDatabase(context: Context) : SQLiteOpenHelper(context, "REGISTRO_USUARIOS.db", null, 1) {
    private val TABLE_ROLE = "ROLE";
    private val TABLE_AUTHORITIES = "AUTHORITIES"
    private val TABLE_USER = "USER";
    private val TABLE_FACULTY = "FACULTY"
    private val TABLE_PROGRAM = "PROGRAM";
    private val TABLE_COURSE = "COURSE";
    private val TABLE_TEACHER = "TEACHER";
    private val TABLE_ASSIGNED_SUBJECTS = "ASSINGED_SUBJECTS";
    private val TABLE_INSCRIPTION = "INSCRIPTION";
    private val TABLE_STUDENT = "STUDENT";
    private val TABLE_CURRENT_SEMESTER = "CURRENT_SEMESTER"

    override fun onCreate(db: SQLiteDatabase?) {
        var createTableRole = "CREATE TABLE " + TABLE_ROLE + "(" +
                "idRole integer primary key," +
                "nameRole varchar(30) not null)";
        val createTableAuthorities = "CREATE TABLE $TABLE_AUTHORITIES (" +
                "  idAuthority INTEGER PRIMARY KEY," +
                "  username VARCHAR(60)," +
                "  password VARCHAR(60)," +
                "  idRole INTEGER REFERENCES $TABLE_ROLE (idRole)" +
                ");"
        val createTableUser = "CREATE TABLE " + TABLE_USER + " (" +
                "  numDocument INTEGER PRIMARY KEY," +
                "  name VARCHAR(60)," +
                "  lastname VARCHAR(60)," +
                "  email VARCHAR(50)," +
                "  idAuthority INTEGER," +
                "  FOREIGN KEY (idAuthority) REFERENCES $TABLE_AUTHORITIES (idAuthority)" +
                ");"
        val createTableFaculty = "CREATE TABLE " + TABLE_FACULTY + " (" +
                "  idFaculty INTEGER PRIMARY KEY," +
                "  nameFaculty VARCHAR(50)" +
                ");"
        val createTableProgram = "CREATE TABLE " + TABLE_PROGRAM + " (" +
                "  idProgram INTEGER PRIMARY KEY," +
                "  nameProgram VARCHAR(60)," +
                "  numSemester INTEGER," +
                "  numCredits INTEGER" +
                ");"
        val createTableCourse = "CREATE TABLE " + TABLE_COURSE + " (" +
                "  idCourse INTEGER PRIMARY KEY," +
                "  nameCourse VARCHAR(60)," +
                "  semesterLocation INTEGER," +
                "  numCredits INTEGER" +
                ");"
        val createTableTeacher = "CREATE TABLE " + TABLE_TEACHER + " (" +
                "  idTeacher INTEGER PRIMARY KEY," +
                "  tittle VARCHAR(60)," +
                "  experience INTEGER," +
                "  status VARCHAR(30)," +
                "  numDocument integer," +
                "  idProgram INTEGER," +
                "  FOREIGN KEY (idProgram) REFERENCES $TABLE_PROGRAM (idProgram)" +
                "  FOREIGN KEY (numDocument) REFERENCES $TABLE_USER (numDocument)" +
                ");"
        val createTableAssignedSubjects = "CREATE TABLE " + TABLE_ASSIGNED_SUBJECTS + " (" +
                "  idAssignedSubjects INTEGER PRIMARY KEY," +
                "  idTeacher INTEGER," +
                "  idCourse INTEGER," +
                "  FOREIGN KEY (idTeacher) REFERENCES $TABLE_TEACHER (idTeacher)," +
                "  FOREIGN KEY (idCourse) REFERENCES $TABLE_COURSE (idCourse)" +
                ");"
        val createTableInscription = "CREATE TABLE " + TABLE_INSCRIPTION + " (" +
                "  idInscription INTEGER PRIMARY KEY," +
                "  status VARCHAR(30)," +
                "  typeDocument VARCHAR(30)," +
                "  gender VARCHAR(30)," +
                "  birdthdate VARCHAR(30)," +
                "  numDocument INTEGER," +
                "  phone VARCHAR(10)," +
                "  idProgram INTEGER," +
                "  FOREIGN KEY (numDocument) REFERENCES $TABLE_USER (numDocument)," +
                "  FOREIGN KEY (idProgram) REFERENCES $TABLE_PROGRAM (idProgram)" +
                ");"
        val createTableStudent = "CREATE TABLE " + TABLE_STUDENT + " (" +
                "  idStudent INTEGER PRIMARY KEY," +
                "  semester NUMERIC," +
                "  statusStudent VARCHAR(30)," +
                "  numDocument INTEGER," +
                "  idProgram INTEGER," +
                "  FOREIGN KEY (numDocument) REFERENCES $TABLE_USER (numDocument)," +
                "  FOREIGN KEY (idProgram) REFERENCES $TABLE_PROGRAM (idProgram)" +
                ");"
        val createTableCurrentSemester = "CREATE TABLE " + TABLE_CURRENT_SEMESTER + " (" +
                "  idCurrentSemester INTEGER PRIMARY KEY," +
                "  status VARCHAR(30)," +
                "  idCourse INTEGER," +
                "  idStudent INTEGER," +
                "  FOREIGN KEY (idCourse) REFERENCES $TABLE_COURSE (idCourse)," +
                "  FOREIGN KEY (idStudent) REFERENCES $TABLE_STUDENT (idStudent)" +
                ");"

        if (db != null) {
            db.execSQL(createTableRole)
            db.execSQL(createTableAuthorities)
            db.execSQL(createTableUser)
            db.execSQL(createTableFaculty)
            db.execSQL(createTableProgram)
            db.execSQL(createTableCourse)
            db.execSQL(createTableTeacher)
            db.execSQL(createTableAssignedSubjects)
            db.execSQL(createTableInscription)
            db.execSQL(createTableStudent)
            db.execSQL(createTableCurrentSemester)
        }

        var insertFirstProgram = "INSERT INTO $TABLE_PROGRAM VALUES (1, 'Ingenieria de sistemas', 10, 1);"
        var insertFirst = "INSERT INTO $TABLE_AUTHORITIES values (1049658454, 'Yeison', 'Molano', 1);"
        var createAdmin = "INSERT INTO $TABLE_USER values (1049658454, 'Yeison Eduardo', 'Molano Lopez', 'kolokololoco79@gmail.com', 1049658454);"
        var insertRol = "INSERT INTO $TABLE_ROLE VALUES (1, 'ADMIN');"
        var insertRolUser = "INSERT INTO $TABLE_ROLE VALUES (2, 'USER');"
        var insertRolStudent = "INSERT INTO $TABLE_ROLE VALUES (3, 'STUDENT');"
        var insertRolTeacher = "INSERT INTO $TABLE_ROLE VALUES (4, 'TEACHER');"
        if (db != null) {
            db.execSQL(insertRol)
            db.execSQL(insertFirst)
            db.execSQL(createAdmin)
            db.execSQL(insertRolUser)
            db.execSQL(insertRolStudent)
            db.execSQL(insertRolTeacher)
            db.execSQL(insertFirstProgram)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
        if (db != null) {
            var cursor = db.rawQuery("PRAGMA foreign_keys", null)
            if (cursor.moveToFirst()) {
                val foreignKeysEnabled = cursor.getInt(0)
            }
            cursor.close()
            db.execSQL("PRAGMA foreign_keys = ON;")
            var cursor1 = db.rawQuery("PRAGMA foreign_keys", null)
            if (cursor1.moveToFirst()) {
                val foreignKeysEnabled1 = cursor1.getInt(0)
            }
            cursor.close()
        }
    }

    fun createRol(){
        var insertRol = "INSERT INTO " + TABLE_ROLE + " VALUES (1, 'ADMINSITATIVO');"
        val db = this.writableDatabase
        db.execSQL(insertRol)
    }

    fun createUser(numIdentificacion: Int, name: String, lastName: String, email: String, username: String, password: String){
        var createUser = "INSERT INTO " + TABLE_USER + " values ($numIdentificacion, '$name', '$lastName', '$email', ${createLogin(numIdentificacion, username, password)});"
        val db = this.writableDatabase
        db.execSQL(createUser)
    }

    private fun createLogin(numDocument: Int, username: String, password: String): Int{
        var createLogin = "INSERT INTO $TABLE_AUTHORITIES (idAuthority, username, password, idRole) values ($numDocument, '$username', '$password', 2);"
        val db = this.writableDatabase
        db.execSQL(createLogin)
        val dbRead = this.readableDatabase
        createLogin = "SELECT * FROM $TABLE_AUTHORITIES;"
        val user = dbRead.rawQuery(createLogin, null)
        if(user.moveToFirst()){
            do {
                if (user.getString(1).equals(username) && user.getString(2).equals(password)){
                    return user.getInt(0)
                }
            }while (user.moveToNext())
        }else{
            println("La tabla esta vacía")
        }
        return -1
    }

    fun login(username: String, password: String): Any{
        val getUsers = "SELECT * FROM $TABLE_AUTHORITIES WHERE username = '$username' AND password = '$password';"
        val db = this.readableDatabase
        val allUsers = db.rawQuery(getUsers, null)
        var user : Any
        if(allUsers.moveToFirst()){
            do {
                    user = findUserById(allUsers.getInt(0), allUsers.getInt(3))
                    return user;
            }while (allUsers.moveToNext())
        }else{
            println("La tabla esta vacía")
        }
        return "El usuario no exite";
    }

    fun findUserById(numDocument: Int, idRole: Int): Map<*, *>{
        var findUser = "SELECT * FROM $TABLE_USER WHERE numDocument = $numDocument"
        val db = this.readableDatabase
        val user = db.rawQuery(findUser, null)
        val userFind = if(user.moveToNext()){
            val numDocumentIndex = user.getColumnIndex("numDocument")
            val nameIndex = user.getColumnIndex("name")
            val lastNameIndex = user.getColumnIndex("lastname")
            val emailIndex = user.getColumnIndex("email")

            val numDocument = user.getInt(numDocumentIndex)
            val name = user.getString(nameIndex)
            val lastName = user.getString(lastNameIndex)
            val email = user.getString(emailIndex)

            mapOf("numDocument" to numDocument, "name" to name, "lastName" to lastName, "email" to email, "role" to findNameRoleById(idRole))
        }else{
            emptyMap()
        }
        return userFind
    }

    fun findNameRoleById(idRole: Int): String{
        val findRole = "SELECT nameRole FROM $TABLE_ROLE WHERE idRole = $idRole"
        val db = this.readableDatabase
        var role = db.rawQuery(findRole, null)
        if(role.moveToFirst()){
            do {
                return role.getString(0)
            }while (role.moveToNext())
        }else{
            return "La tabla esta vacía"
        }
    }

    fun getAllProgramas(): List<Any>{
        var getAllPrograms = "SElECT * FROM $TABLE_PROGRAM;"
        val db = this.readableDatabase
        val items = mutableListOf("Seleccione el programa")
        var programs = db.rawQuery(getAllPrograms, null)
        if(programs.moveToFirst()){
            do {
                items.add(programs.getString(1))
            }while (programs.moveToNext())
        }else{
            println("La tabla esta vacía")
        }
        return items;
    }

    fun createAuthorities(username: String, password: String, idRole: Int){
        val createAuthority = "INSERT INTO $TABLE_AUTHORITIES (username, password, idRole)" +
                "VALUES ('$username', '$password', $idRole);"
        val db = this.writableDatabase
        db.execSQL(createAuthority)
    }

    fun createFaculty(nameFaculty: String){
        val createFaculty = "INSERT INTO $TABLE_FACULTY (nameFaculty)" +
                "VALUES ('$nameFaculty');"
        val db = this.writableDatabase
        db.execSQL(createFaculty)
    }

    fun createProgram(idProgram: Int, nameProgram: String, numSemester: Int, numCredits: Int){
        val createProgram = "INSERT INTO  $TABLE_PROGRAM VALUES ($idProgram, '$nameProgram', $numSemester, $numCredits);"
        val db = this.writableDatabase
        db.execSQL(createProgram)
    }

    fun createCourse(idCourse: Int, nameCourse: String, semesterLocation: Int, numCredits: Int){
        val createCourse = "INSERT INTO Course VALUES ($idCourse, '$nameCourse', $semesterLocation, $numCredits);"
        val db = this.writableDatabase
        db.execSQL(createCourse)
    }

    fun createTeacher(tittle: String, experience: Int, program: String, numDocument: Int){
        val createTeacher = "INSERT INTO $TABLE_TEACHER (tittle, experience, idProgram, status, numDocument)" +
                "VALUES ('$tittle', $experience, ${findIdProgramByName(program)}, 'PENDING', $numDocument);"
        val db = this.writableDatabase
        db.execSQL(createTeacher)
    }

    fun createAssignedSubjects(idTeacher: Int, idCourse: Int){
        val assignedSubject = "INSERT INTO $TABLE_ASSIGNED_SUBJECTS (idTeacher, idCourse)" +
                "VALUES ($idTeacher, $idCourse);"
        val db = this.writableDatabase
        db.execSQL(assignedSubject)
    }

    fun createInscription(typeDocument: String, gender: String, birdthdate: String, numDocument: Int, idProgram: String, phone: String){
        val createInscription = "INSERT INTO $TABLE_INSCRIPTION (typeDocument, gender, birdthdate, status, numDocument, idProgram, phone)" +
                "VALUES ('$typeDocument', '$gender', '$birdthdate', 'PENDING', $numDocument, ${findIdProgramByName(idProgram)}, '$phone');"
        val db = this.writableDatabase
        db.execSQL(createInscription)
    }

    fun createStudent(semester: Int, statusStudent: String, numDocumento: Int, idPrograma: Int){
        val createStudent = "INSERT INTO $TABLE_STUDENT (semester, statusStudent, numDocumento, idPrograma)\n" +
                "VALUES ($semester, '$statusStudent', $numDocumento, $idPrograma);"
        val db = this.writableDatabase
        db.execSQL(createStudent)
    }

    fun createCurrentSemester(status: String, idCourse: Int, idStudent: Int){
        val createCurrentSemester = "INSERT INTO $TABLE_CURRENT_SEMESTER (status, idCourse, idStudent)\n" +
                "VALUES ('$status', $idCourse, $idStudent);"
        val db = this.writableDatabase
        db.execSQL(createCurrentSemester)
    }

    @SuppressLint("Range")
    fun findIdProgramByName(name: String): Int{
        val findProgram = "SELECT idProgram FROM $TABLE_PROGRAM WHERE nameProgram = '$name'"
        val db = this.readableDatabase
        var idProgram = db.rawQuery(findProgram, null)

        if(idProgram.moveToFirst()){
            do {
                return idProgram.getInt(idProgram.getColumnIndex("idProgram"))
            }while (idProgram.moveToNext())
        }else{
            return -1
        }
    }

    fun getAllStudentsPending(): List<Map<*, *>> {
        var getAllPrograms = "SElECT * FROM $TABLE_INSCRIPTION;"
        val db = this.readableDatabase
        val items = mutableListOf<Map<*, *>>()
        var programs = db.rawQuery(getAllPrograms, null)
        if(programs.moveToFirst()){
            do {
                items.add(findUserById(programs.getInt(5), 2))
            }while (programs.moveToNext())
        }else{
            println("La tabla esta vacía")
        }
        return items;
    }

    fun findUserInsrciption(numDocument: Int): Map<*, *>{
        var findUserInsricption = "SELECT $TABLE_USER.name, $TABLE_USER.lastName, $TABLE_USER.email," +
                "$TABLE_INSCRIPTION.typeDocument, $TABLE_INSCRIPTION.gender, $TABLE_INSCRIPTION.birdthdate," +
                "$TABLE_INSCRIPTION.phone, $TABLE_INSCRIPTION.idProgram FROM $TABLE_USER JOIN $TABLE_INSCRIPTION " +
                "ON $TABLE_USER.numDocument = $TABLE_INSCRIPTION.numDocument WHERE $TABLE_INSCRIPTION.numDocument = " +
                "$numDocument"
        val db = this.readableDatabase
        var userFind = db.rawQuery(findUserInsricption, null)
        var inscription = if(userFind.moveToFirst()){
                mapOf("numDocument" to numDocument, "name" to "${userFind.getString(0)} ${userFind.getString(1)}", "email" to userFind.getString(2), "phone" to userFind.getString(6), "birthdate" to userFind.getString(5), "gender" to userFind.getString(4), "program" to findNameProgramById(userFind.getInt(7)))
        }else{
            emptyMap()
        }
        println(inscription)
        return inscription
    }

    fun findNameProgramById(idProgram: Int): String{
        var findNameProgram = "SELECT nameProgram FROM $TABLE_PROGRAM WHERE idProgram = $idProgram"
        val db = this.readableDatabase
        var userFind = db.rawQuery(findNameProgram, null)
        if(userFind.moveToFirst()){
            do {
                return userFind.getString(0)
            }while (userFind.moveToNext())
        }else{
            return "Este id no existe en la base de datos"
        }
    }

    fun dropInsription(numDocument: Int){
        var dropTupla = "DELETE FROM $TABLE_INSCRIPTION WHERE numDocument = $numDocument"
        val db = this.writableDatabase
        db.execSQL(dropTupla)
    }

    fun updateRolToStudent(numDocument: Int){
        var changeRole = "UPDATE $TABLE_AUTHORITIES SET idRole = 3 WHERE idAuthority = $numDocument;"
        val db = this.writableDatabase
        db.execSQL(changeRole)
    }
}