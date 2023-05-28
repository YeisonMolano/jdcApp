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
                "  numCredits INTEGER," +
                "  statusProgram VARCHAR(30)," +
                "  idFaculty INTEGER," +
                "  FOREIGN KEY (idFaculty) REFERENCES $TABLE_FACULTY (idFaculty)" +
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
                "  typeDocument VARCHAR(30)," +
                "  gender VARCHAR(30)," +
                "  birdthdate VARCHAR(30)," +
                "  phone VARCHAR(10)," +
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
        }
        val createFaculty = "INSERT INTO $TABLE_FACULTY (nameFaculty)" +
                "VALUES ('FACULTAD DE CIENCIAS AGRARIAS Y AMBIENTALES');"
        val createFaculty2 = "INSERT INTO $TABLE_FACULTY (nameFaculty)" +
                "VALUES ('FACULTAD DE CIENCIAS SOCIALES Y ECONOMICAS');"
        val createFaculty3 = "INSERT INTO $TABLE_FACULTY (nameFaculty)" +
                "VALUES ('FACULTAD DE CIENCIAS DE LA EDUCACION, HUMANIDADES Y ARTE');"
        val createFaculty4 = "INSERT INTO $TABLE_FACULTY (nameFaculty)" +
                "VALUES ('FACULTAD DE CIENCIAS JURIDICAS Y POLITICAS INTERNACIONALES');"
        val createFaculty5 = "INSERT INTO $TABLE_FACULTY (nameFaculty)" +
                "VALUES ('FACULTAD DE INGENIERIA Y CIENCIAS BASICAS');"
        if (db != null) {
            db.execSQL(createFaculty)
            db.execSQL(createFaculty2);
            db.execSQL(createFaculty3);
            db.execSQL(createFaculty4);
            db.execSQL(createFaculty5);
        };
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

    fun getAllFacultades(): List<Map<*,*>>{
        var getAllFaculty = "SElECT * FROM $TABLE_FACULTY;"
        val db = this.readableDatabase
        val items = mutableListOf<Map<*, *>>()
        var item : Map<*, *>
        var faculty = db.rawQuery(getAllFaculty, null)
        if(faculty.moveToFirst()){
            do {
                item = mapOf("idFaculty" to faculty.getInt(0), "nameFaculty" to faculty.getString(1))
                items.add(item)
            }while (faculty.moveToNext())
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

    fun createProgram(nameProgram: String, numSemester: Int, numCredits: Int, idFaculty: Int): Boolean{
        try {
            val createProgram = "INSERT INTO  $TABLE_PROGRAM (nameProgram, numSemester, numCredits, idFaculty, statusProgram) VALUES ('$nameProgram', $numSemester, $numCredits, $idFaculty, 'ACTIVE');"
            println(createProgram)
            val db = this.writableDatabase
            db.execSQL(createProgram)
            return true;
        }catch (error: Exception){
            return false;
        }
    }



    fun createCourse(nameCourse: String, semesterLocation: Int, numCredits: Int): Boolean{
        try {
            val createCourse = "INSERT INTO $TABLE_COURSE (nameCourse, semesterLocation, numCredits) VALUES ('$nameCourse', $semesterLocation, $numCredits);"
            val db = this.writableDatabase
            db.execSQL(createCourse)
            return true;
        }catch (e: Exception){
            return false;
        }
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

    fun createInscription(idInscription: Int, typeDocument: String, gender: String, birdthdate: String, numDocument: Int, idProgram: String, phone: String){
        val createInscription = "INSERT INTO $TABLE_INSCRIPTION (idInscription, typeDocument, gender, birdthdate, status, numDocument, idProgram, phone)" +
                "VALUES ($idInscription, '$typeDocument', '$gender', '$birdthdate', 'PENDING', $numDocument, ${findIdProgramByName(idProgram)}, '$phone');"
        val db = this.writableDatabase
        db.execSQL(createInscription)
    }

    fun createStudent(idStudent: Int, semester: Int, statusStudent: String, numDocument: Int, idProgram: Int, typeDocument: String, gender: String, birthdate: String, phone: String): Boolean{
        try {
            val createStudent = "INSERT INTO $TABLE_STUDENT VALUES ($idStudent, $semester, '$statusStudent', $numDocument, $idProgram, '$typeDocument', '$gender', '$birthdate', '$phone');"
            println(createStudent)
            val db = this.writableDatabase
            db.execSQL(createStudent)
            return true;
        }catch (e: Exception){
            return false;
        }
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
            println("La tabla esta vacía ñero")
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
        println("Aca")
        var changeRole = "UPDATE $TABLE_AUTHORITIES SET idRole = 3 WHERE idAuthority = $numDocument;"
        val db = this.writableDatabase
        db.execSQL(changeRole)
        findInscription(numDocument)
        dropInsription(numDocument)
    }

    fun findInscription(numDocument: Int): Boolean{
        var findInscription = "SELECT * FROM $TABLE_INSCRIPTION WHERE idInscription = $numDocument"
        val db = this.readableDatabase
        var faculty = db.rawQuery(findInscription, null)
        if(faculty.moveToFirst()){
            println("La creacion")
            return createStudent(faculty.getInt(0), 1, "ACTIVE", faculty.getInt(0), faculty.getInt(7),
                faculty.getString(2), faculty.getString(3), faculty.getString(4), faculty.getString(6))
        }else{
            return false;
        }
    }

    fun findIdFacultyByName(name: String): Int{
        val findFaculty = "SELECT idFaculty FROM $TABLE_FACULTY WHERE nameFaculty = '$name'"
        val db = this.readableDatabase
        var faculty = db.rawQuery(findFaculty, null)
        if(faculty.moveToFirst()){
            do {
                return faculty.getInt(0)
            }while (faculty.moveToNext())
        }else{
            return -1
        }
    }

    fun findNameFacultyById(idFaculty: Int): String{
        println(idFaculty)
        var findNameFaculty = "SELECT nameFaculty FROM $TABLE_FACULTY WHERE idFaculty = $idFaculty"
        val db = this.readableDatabase
        var facultyFind = db.rawQuery(findNameFaculty, null)
        if(facultyFind.moveToFirst()){
            do {
                return facultyFind.getString(0)
            }while (facultyFind.moveToNext())
        }else{
            return "Este id no existe en la base de datos"
        }
    }

    fun getStudentsActive(): List<Map<*, *>>{
        var getStudentsActive = "SElECT * FROM $TABLE_STUDENT WHERE statusStudent = 'ACTIVE';"
        val db = this.readableDatabase
        val items = mutableListOf<Map<*, *>>()
        var item : Map<*, *>
        var students = db.rawQuery(getStudentsActive, null)
        if(students.moveToFirst()){
            do {
                item = mapOf("idStudent" to students.getInt(0), "semester" to 1, "statusStudent" to "ACTIVE",
                    "numDocument" to students.getInt(0), "idProgram" to students.getInt(7),
                    "typeDocument" to students.getString(2), "gender" to students.getString(3),
                    "birthdate" to students.getString(4), "phone" to students.getString(6), "name" to findUserById(students.getInt(0), 1)["name"] )
                println(item)
                items.add(item)
            }while (students.moveToNext())
        }else{
            println("La tabla esta vacía")
        }
        return items;
    }


    //Metodos UPDATE
    fun updateUser(numIdentificacion: Int, name: String, lastName: String, email: String, username: String, password: String){
        var createUser = "UPDATE $TABLE_USER (name, lastName, email) values ('$name', '$lastName', '$email' WHERE numDocument = $numIdentificacion;"
        val db = this.writableDatabase
        db.execSQL(createUser)
    }

    fun updateStudent(idStudent: Int, semester: Int, statusStudent: String, numDocument: Int, idProgram: Int, typeDocument: String, gender: String, birthdate: String, phone: String): Boolean{
        try {
            val createStudent = "UPDATE $TABLE_STUDENT VALUES ($semester, '$statusStudent', $numDocument, $idProgram, '$typeDocument', '$gender', '$birthdate', '$phone') WHERE idStudent = $idStudent;"
            println(createStudent)
            val db = this.writableDatabase
            db.execSQL(createStudent)
            return true;
        }catch (e: Exception){
            return false;
        }
    }

    fun updateProgram(){

    }

    //Metodos getAll
    fun getAllProgramas(): List<Map<*, *>>{
        var getAllPrograms = "SElECT * FROM $TABLE_PROGRAM;"
        val db = this.readableDatabase
        val items = mutableListOf<Map<*, *>>()
        var item : Map<*, *>
        var programs = db.rawQuery(getAllPrograms, null)
        if(programs.moveToFirst()){
            do {
                item = mapOf("idProgram" to programs.getInt(0), "nameProgram" to programs.getString(1), "numSemester" to programs.getInt(2),
                    "numCredits" to programs.getInt(3), "nameFaculty" to findNameFacultyById(programs.getInt(5)))
                items.add(item)
            }while (programs.moveToNext())
        }else{
            println("La tabla esta vacía")
        }
        return items;
    }

    fun getAllProgramasByStatus(status: String): List<Map<*, *>>{
        var getAllPrograms = ""
        if(status.equals("ACTIVE")){
            getAllPrograms = "SElECT * FROM $TABLE_PROGRAM WHERE statusProgram = 'ACTIVE';"
        }else if(status.equals("INACTIVE")){
            getAllPrograms = "SElECT * FROM $TABLE_PROGRAM WHERE statusProgram = 'INACTIVE';"
        }else{
            return emptyList();
        }
        val db = this.readableDatabase
        val items = mutableListOf<Map<*, *>>()
        var item : Map<*, *>
        var programs = db.rawQuery(getAllPrograms, null)
        if(programs.moveToFirst()){
            do {
                println("${programs.getInt(0)} ${programs.getString(1)} ${programs.getInt(2)} ${programs.getInt(3)} ${programs.getInt(4)}")
                item = mapOf("idProgram" to programs.getInt(0), "nameProgram" to programs.getString(1), "numSemester" to programs.getInt(2),
                    "numCredits" to programs.getInt(3), "nameFaculty" to findNameFacultyById(programs.getInt(5)))
                items.add(item)
            }while (programs.moveToNext())
        }else{
            println("La tabla esta vacía")
        }
        return items;
    }
}