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
    private val TABLE_INSCRIPTION_TEACHER = "INSCRIPTION_TEACHER"

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
                "  numCredits INTEGER," +
                "  idProgram INTEGER," +
                "  FOREIGN KEY (idProgram) REFERENCES $TABLE_PROGRAM (idProgram)" +
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
        val createTableInscriptionTeacher = "CREATE TABLE " + TABLE_INSCRIPTION_TEACHER + " (" +
                "  idInscriptionTeacher INTEGER PRIMARY KEY," +
                "  typeDocument VARCHAR(30)," +
                "  gender VARCHAR(30)," +
                "  birdthdate VARCHAR(30)," +
                "  numDocument INTEGER," +
                "  phone VARCHAR(10)," +
                "  experience VARCHAR(10)," +
                "  tittle VARCHAR(30)," +
                "  idProgram INTEGER," +
                "  FOREIGN KEY (numDocument) REFERENCES $TABLE_USER (numDocument)," +
                "  FOREIGN KEY (idProgram) REFERENCES $TABLE_PROGRAM (idProgram)" +
                ");"
        val createTableTeacher = "CREATE TABLE " + TABLE_TEACHER + " (" +
                "  idTeacher INTEGER PRIMARY KEY," +
                "  typeDocument VARCHAR(30)," +
                "  gender VARCHAR(30)," +
                "  birdthdate VARCHAR(30)," +
                "  tittle VARCHAR(60)," +
                "  experience INTEGER," +
                "  phone VARCHAR(10)," +
                "  status VARCHAR(30)," +
                "  numDocument integer," +
                "  idProgram INTEGER," +
                "  FOREIGN KEY (idProgram) REFERENCES $TABLE_PROGRAM (idProgram)" +
                "  FOREIGN KEY (numDocument) REFERENCES $TABLE_USER (numDocument)" +
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
            db.execSQL(createTableInscriptionTeacher)
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

    //Metodo que permite hacer la creación de un nuevo rol
    fun createRol(){
        var insertRol = "INSERT INTO " + TABLE_ROLE + " VALUES (1, 'ADMINSITATIVO');"
        val db = this.writableDatabase
        db.execSQL(insertRol)
    }


    //Metodo que me permite crea un nuevo usuario en la bd
    //Pharams: {numero de identificación, nombre del usuario, apellidos del usuario,
    // correo electronico, nombre de usuario y contraseña para acceder a la app}
    fun createUser(numIdentificacion: Int, name: String, lastName: String, email: String, username: String, password: String){
        var createUser = "INSERT INTO " + TABLE_USER + " values ($numIdentificacion, '$name', '$lastName', '$email', ${createLogin(numIdentificacion, username, password)});"
        val db = this.writableDatabase
        db.execSQL(createUser)
    }

    //Metodo que me permite crear las credenciales del usuario para el ingreso a la app
    //Pharams: {numero de identificación al que se van a relacionas las credenciales, nombre de usuario y password}
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

    //Metodo que permite realizar la primera inserción de materias de un usuario nuevo con las materias de
    //primer semestre
    //Pharams: {identificador del programa al que va a pertenencer el estudiante, y numero de identificación}
    fun createFirstCurrentSemester(idProgram: Int, numDocument: Int): Boolean{
        try {
            var findMateriasOfFirstSemester = "SELECT idCourse FROM $TABLE_COURSE WHERE semesterLocation = 1 and idProgram = $idProgram"
            val db = this.readableDatabase
            val materias = db.rawQuery(findMateriasOfFirstSemester, null)
            println(findMateriasOfFirstSemester)
            if(materias.moveToFirst()){
                do {
                    createCurrentSemester("ACTIVE", materias.getInt(0), numDocument)
                }while (materias.moveToNext())
            }else{
                println("La tabla esta vacía")
            }
            return true;
        }catch (e: Exception){
            return false;
        }
    }

    //Permite crear una nueva inscripcion de estudiante a una materia
    //Pharams: {Estado en el que va a quedar la materia, id del curso que se va a relacionar y el id del estudiante con el que se va a
    // relacionar}
    fun createCurrentSemester(status: String, idCourse: Int, idStudent: Int){
        val createCurrentSemester = "INSERT INTO $TABLE_CURRENT_SEMESTER (status, idCourse, idStudent)" +
                "VALUES ('$status', $idCourse, $idStudent);"
        println(createCurrentSemester)
        val db = this.writableDatabase
        db.execSQL(createCurrentSemester)
    }

    //Acceso a la plataforma segun las credenciales ingresadas
    //Pharams: {nombre de usuario y contraseña}
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

    //Metodo que me permite hacer la busqueda de un usuario mediante el identificador
    //Pharams: {numero de identificación del usuario, posible rol al que pertenece}
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

    //Metodo que me busca el nombre de un rol mediante el identificador
    //Pharams: {identificador del rol}
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

    //Metodo que me permite hacer la creación de un nuevo programa
    //Pharams: {nombre programa, numero de semestres que tiene el programa, numero de creditos totales que
    // tiene el programa, identificador de la facultad a la que va a pertenecer el programa}
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

    //Metodo que me permite crear un nuevo curso
    //Pharams: {nombre del curso, ubicacion semestral del curso, numero de creditos que tiene el curso,
    //identificador del programa al que va a pertenecer el curso o materia}
    fun createCourse(nameCourse: String, semesterLocation: Int, numCredits: Int, idProgram: Int): Boolean{
        try {
            val createCourse = "INSERT INTO $TABLE_COURSE (nameCourse, semesterLocation, numCredits, idProgram) VALUES ('$nameCourse', $semesterLocation, $numCredits, $idProgram);"
            val db = this.writableDatabase
            db.execSQL(createCourse)
            return true;
        }catch (e: Exception){
            return false;
        }
    }

    //Metodo que me permite registrar un nuevo docente al sistema
    //Pharams: {numero de identificación del docente, tipo de documento del docente, genero al que pertenece
    // el docente, fecha de nacimiento, titulo profesional con el que cuenta, experiencia en años con la
    // que cuenta, telefono de contacto, llave foranea que lo relaciona a la tabla Authorities para el ingreso
    // a la app, programa al que va a pertenecer el docente}
    fun createTeacher(idTeacher: Int, typeDocument: String, gender: String, birthdate: String, tittle: String,
                      experience: Int, phone: String, numDocument: Int, program: Int): Boolean{
        try {
            val createTeacher = "INSERT INTO $TABLE_TEACHER (idTeacher, typeDocument, gender, birdthdate, tittle, " +
                    "experience, phone, status, numDocument, idProgram)" +
                    "VALUES ($idTeacher, '$typeDocument', '$gender', '$birthdate', '$tittle', $experience, " +
                    "'$phone', 'ACTIVE', $numDocument, $program);"
            val db = this.writableDatabase
            db.execSQL(createTeacher)
            return true;
        }catch (e: Exception){
            return false;
        }
    }

    //Metodo que me permite registrar la asignación de una materia a un docente
    //Pharams: {identificador del docente, identificador del curso o materia que se le va a asignar}
    fun createAssignedSubjects(idTeacher: Int, idCourse: Int): Boolean{
        try {
            val assignedSubject = "INSERT INTO $TABLE_ASSIGNED_SUBJECTS (idTeacher, idCourse)" +
                    "VALUES ($idTeacher, $idCourse);"
            val db = this.writableDatabase
            db.execSQL(assignedSubject)
            return true;
        }catch (e: Exception){
            return false;
        }
    }

    //Metodo que me permite crear una inscripcion a la tabla docentes, en donde permanecerá hasta que el
    //admin lo acepte o lo rechace
    //Pharams: {identificador del docente, que va a ser el mismo nuemero de documento, genero al que pertenece
    // fecha de nacimiento, llave foranea que lo realaciona a la tabla authorities,  telefono de contacto,
    // experiencia en años, titulo profesional con el que cuenta, id del programa al que se va a postular}
    fun createTeacherInscription(idInscriptionTeacher: Int, gender: String, birdthdate: String, numDocument: Int, phone: String,
    experience: Int, tittle: String, idProgram: String){
        println("soy el id $idInscriptionTeacher")
        val createInscription = "INSERT INTO $TABLE_INSCRIPTION_TEACHER (idInscriptionTeacher, typeDocument, gender, birdthdate, numDocument, idProgram, phone, experience, tittle)" +
                "VALUES ($idInscriptionTeacher, 'C.C.', '$gender', '$birdthdate', $numDocument, ${findIdProgramByName(idProgram)}, '$phone', $experience, '$tittle');"
    val db = this.writableDatabase
    db.execSQL(createInscription)
    }

    //Metodo que me permite hacer la inscripcion de un estudiante a un programa
    //Pharams: {numero de identificacion de la inscripcion, tipo de documento, genero al que pertenece,
    // fecha de nacimiento, relacion a la tabla authorities, id del programa al que se va a postular,
    // telefono de contacto}
    fun createInscription(idInscription: Int, typeDocument: String, gender: String, birdthdate: String, numDocument: Int, idProgram: String, phone: String){
        val createInscription = "INSERT INTO $TABLE_INSCRIPTION (idInscription, typeDocument, gender, birdthdate, status, numDocument, idProgram, phone)" +
                "VALUES ($idInscription, '$typeDocument', '$gender', '$birdthdate', 'PENDING', $numDocument, ${findIdProgramByName(idProgram)}, '$phone');"
        val db = this.writableDatabase
        db.execSQL(createInscription)
    }

    //Metodo que me permite hacer el registro de un nuevo estudiante, esto lo puede hacer solamente el administrador
    //Pharams: {identificacion del estdudiante la cual va a ser llave foranea, semestre al que va a pertenecer,
    // estado en el que va a quedarr el estudiante, llave relacion con la tabla authorities, id del programa
    // al que va a pertenecer, tipo de documento, genero, fecha de nacimiento, telefono de contacto}
    fun createStudent(idStudent: Int, semester: Int, statusStudent: String, numDocument: Int, idProgram: Int, typeDocument: String, gender: String, birthdate: String, phone: String): Boolean{
        try {
            val createStudent = "INSERT INTO $TABLE_STUDENT VALUES ($idStudent, $semester, '$statusStudent', $numDocument, $idProgram, '$typeDocument', '$gender', '$birthdate', '$phone');"
            val db = this.writableDatabase
            db.execSQL(createStudent)
            return true;
        }catch (e: Exception){
            return false;
        }
    }

    //Metodo que me permite buscar el id de un programa por el nombre del mismo
    //Pharams: {nombre del programa a buscar}
    //Return: {id del programa, si se encuentra, sino devuelve un -1}
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

    //Metodo que me permite buscar una inscripcion de estudiante pendiente
    //Pharams: {numero de indentificacion del usuario pendiente, tipo de inscripcion que se desea conocer
    // si la de un estudiante o la de un docente}
    //Return: {mapa de = Inscripcion tipo docente, o inscripcion tipo estduiante, si no se ecuentra la
    // inscripcion retorna un mapa vacio}
    fun findUserInsrciption(numDocument: Int, type: String): Map<*, *>{
        var findUserInsricption = ""
        if(type.equals("STUDENT")){
            findUserInsricption = "SELECT $TABLE_USER.name, $TABLE_USER.lastName, $TABLE_USER.email," +
                    "$TABLE_INSCRIPTION.typeDocument, $TABLE_INSCRIPTION.gender, $TABLE_INSCRIPTION.birdthdate," +
                    "$TABLE_INSCRIPTION.phone, $TABLE_INSCRIPTION.idProgram FROM $TABLE_USER JOIN $TABLE_INSCRIPTION " +
                    "ON $TABLE_USER.numDocument = $TABLE_INSCRIPTION.numDocument WHERE $TABLE_INSCRIPTION.numDocument = " +
                    "$numDocument"
        }else if(type.equals("TEACHER")){
            findUserInsricption = "SELECT $TABLE_USER.name, $TABLE_USER.lastName, $TABLE_USER.email," +
                    "$TABLE_INSCRIPTION_TEACHER.typeDocument, $TABLE_INSCRIPTION_TEACHER.gender, $TABLE_INSCRIPTION_TEACHER.birdthdate," +
                    "$TABLE_INSCRIPTION_TEACHER.phone, $TABLE_INSCRIPTION_TEACHER.idProgram FROM $TABLE_USER JOIN $TABLE_INSCRIPTION_TEACHER " +
                    "ON $TABLE_USER.numDocument = $TABLE_INSCRIPTION_TEACHER.numDocument WHERE $TABLE_INSCRIPTION_TEACHER.numDocument = " +
                    "$numDocument"
        }
        val db = this.readableDatabase
        var userFind = db.rawQuery(findUserInsricption, null)
        var inscription = if(userFind.moveToFirst()){
                mapOf("numDocument" to numDocument, "name" to "${userFind.getString(0)} ${userFind.getString(1)}", "email" to userFind.getString(2), "phone" to userFind.getString(6), "birthdate" to userFind.getString(5), "gender" to userFind.getString(4), "program" to findNameProgramById(userFind.getInt(7)))
        }else{
            emptyMap()
        }
        return inscription
    }

    //Metodo que me permite buscar el nombre de un programa por el id
    //Pharams: {identificador del programa}
    //Return: {nombre del programa encontrado, si no lo encuentra devuelve mensaje no encontrado}
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

    //Metodos drop
    //Metodo que me permite eliminar un registro de la tabla inscripcion teacher mediante el identificador
    //Pharams: {numero de identificacion de la inscripción}
    //Void
    fun dropInsriptionTeacher(numDocument: Int){
        var dropTupla = "DELETE FROM $TABLE_INSCRIPTION_TEACHER WHERE numDocument = $numDocument"
        val db = this.writableDatabase
        db.execSQL(dropTupla)
    }

    //Metodo que me permite eliminar un registro de la tabla inscripcion estudiante mediante el identificador
    //Pharams: {numero de identificacion de la inscripción}
    //Void
    fun dropInsription(numDocument: Int){
        var dropTupla = "DELETE FROM $TABLE_INSCRIPTION WHERE numDocument = $numDocument"
        val db = this.writableDatabase
        db.execSQL(dropTupla)
    }

    //Metodo que me permite editar los campos de un registro en la tabla Authorities para hacer la asignacion
    //de docente o estudiante
    //Pharams: {identificador del usuario, el tipo de rol que se va a actualiza, puede ser docente o estudiante}
    //Void
    fun updateRolTo(numDocument: Int, type: String){
        var changeRole = ""
        if(type.equals("STUDENT")){
            changeRole = "UPDATE $TABLE_AUTHORITIES SET idRole = 3 WHERE idAuthority = $numDocument;"
        }else if(type.equals("TEACHER")){
            changeRole = "UPDATE $TABLE_AUTHORITIES SET idRole = 4 WHERE idAuthority = $numDocument;"
        }
        val db = this.writableDatabase
        db.execSQL(changeRole)
        if(type.equals("STUDENT")){
            findInscription(numDocument)
            dropInsription(numDocument)
        }else if(type.equals("TEACHER")){
            findInscriptionTeacher(numDocument)
            dropInsriptionTeacher(numDocument)
        }
    }

    //Metodo que me permite encontrar una inscripcion de un docente y llama la funcion de creacion del nuevo docente
    //Pharams: {numero de identificacion del usuario que se va a actualizar}
    //Return: {Me retorna true si el proceso termina bien, false si hay algun problema al ingresar los datos}
    fun findInscriptionTeacher(numDocument: Int): Boolean{
        var findInscription = "SELECT * FROM $TABLE_INSCRIPTION_TEACHER WHERE idInscriptionTeacher = $numDocument"
        val db = this.readableDatabase
        var inscription = db.rawQuery(findInscription, null)
        if(inscription.moveToFirst()){
            return createTeacher(inscription.getInt(0), inscription.getString(1),inscription.getString(2),
                inscription.getString(3),inscription.getString(7),inscription.getInt(6), inscription.getString(5),
                inscription.getInt(0), inscription.getInt(8))
        }else{
            return false;
        }
    }

    //Metodo que me permite encontrar una inscripcion de un estudiante y llama la funcion de creacion del nuevo estudiante
    //Pharams: {numero de identificacion del usuario que se va a actualizar}
    //Return: {Me retorna true si el proceso termina bien, false si hay algun problema al ingresar los datos}
    fun findInscription(numDocument: Int): Boolean{
        var findInscription = "SELECT * FROM $TABLE_INSCRIPTION WHERE idInscription = $numDocument"
        val db = this.readableDatabase
        var faculty = db.rawQuery(findInscription, null)
        if(faculty.moveToFirst()){
            return createStudent(faculty.getInt(0), 1, "ACTIVE", faculty.getInt(0), faculty.getInt(7),
                faculty.getString(2), faculty.getString(3), faculty.getString(4), faculty.getString(6))
        }else{
            return false;
        }
    }

    //Metodo que me permite encontrar el identificador de una facultad mediante el nombre de la misma
    //Pharams: {nombre de la facultad a encontrar}
    //Return: {identificador de la facultad si se encuentra registrada, si no se encuentra retorna -1}
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

    //Metodo qee me permite buscar el nombre de una facultad mediante el identificador
    //Pharams: {identificador de la facultad a encontrar}
    //Return: {Nombre de la facultad encontrada, en caso de que no la encuentre retorna mensaje no existe}
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

    //Metodo que me permite encontrar una materia mediante el identificador
    //Pharams: {idenificador de la materia}
    //Retorn: {Mapa con los datos de la materia, si no lo encuentra retorna un mapa vacío}
    fun findMateriaById(idMateria: Int): Map<*, *>{
        var findMateria = "SELECT * FROM $TABLE_COURSE WHERE idCourse = $idMateria"
        val db = this.readableDatabase
        var materiaFind = db.rawQuery(findMateria, null)
        var materia = if(materiaFind.moveToFirst()){
                 mapOf("nameCourse" to materiaFind.getString(1), "semesterLocation" to materiaFind.getInt(2),
                 "numCredits" to materiaFind.getInt(3))
        }else {
            emptyMap()
        }
        return materia
    }


    //Metodos UPDATE
    //Metodo que me permite hacer actualización de datos a la tabla user
    //Pharams: {numero de dentificación del usuario a editar, nombres nuevos, apellidos nuevos, correo electronico nuevo}
    //Void
    fun updateUser(numIdentificacion: Int, name: String, lastName: String, email: String){
        var createUser = "UPDATE $TABLE_USER (name, lastName, email) values ('$name', '$lastName', '$email' WHERE numDocument = $numIdentificacion;"
        val db = this.writableDatabase
        db.execSQL(createUser)
    }

    //Metodos UPDATE
    //Metodo que me permite hacer actualización de datos a la tabla user
    //Pharams: {numero de dentificación del usuario a editar, nombres nuevos, apellidos nuevos, correo electronico nuevo}
    //Return: {retorna true si el proceso se hace bien, false si hay algun problema al inserta los datos}
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
    //Metodo que me permite encontrar todos los programas que existen
    //Return: {Lista de Maps con cada uno de los programas}
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

    //Metodo que me permite encontrar todos los programas que tienen cierto estado
    //Pharams: {estado que se desea buscar}
    //Return: {Lista de Maps con cada uno de los programas, de caso contrario retorna una lista vacia}
    fun getAllProgramasByStatus(status: String): List<Map<*, *>>{
        var getAllPrograms = "SElECT * FROM $TABLE_PROGRAM WHERE statusProgram = '$status';"
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

    //Metodo que me permite buscar tosos los estudiantes por estado especifico
    //Pharams: {estado a buscar}
    //Return: {Lista de Maps con cada uno de los estudiantes, si los encuentra, de caso contrario retorna una lista vacia}
    fun getAllStudentsByStatus(status: String): List<Map<*, *>>{
        var getStudentsActive = "SElECT * FROM $TABLE_STUDENT WHERE statusStudent = '$status';"
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

    //Metodo que me permite buscar todos los registros de estudiantes pendientes
    //Return: {Lista de Maps con cada uno de los programas, si los encuentra, de caso contrario retorna una lista vacia}
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

    //Metodo que me permite buscar todos los registros de docentes pendientes
    //Return: {Lista de Maps con cada uno de los docentes, si los encuentra, de caso contrario retorna una lista vacia}
    fun getAllDocentsPending(): List<Map<*, *>> {
        var getAllTeachers = "SElECT * FROM $TABLE_INSCRIPTION_TEACHER;"
        val db = this.readableDatabase
        val items = mutableListOf<Map<*, *>>()
        var teachers = db.rawQuery(getAllTeachers, null)
        if(teachers.moveToFirst()){
            do {
                println()
                items.add(findUserById(teachers.getInt(4), 2))
            }while (teachers.moveToNext())
        }else{
            println("La tabla esta vacía ñero")
        }
        return items;
    }

    //Metodo que me permite buscar docentes segun el estado
    //Pharams: {estado que se desea buscar}
    //Return: {Lista de Maps con cada uno de los docentes, si los encuentra, de caso contrario retorna una lista vacia}
    fun getAllTeachersByStatus(status: String): MutableList<Map<*, *>> {
        var getTeachersActive = "SElECT * FROM $TABLE_TEACHER WHERE status = '$status';"
        val db = this.readableDatabase
        val items = mutableListOf<Map<*, *>>()
        var item : Map<*, *>
        var teacher = db.rawQuery(getTeachersActive, null)
        if(teacher.moveToFirst()){
            do {
                item = mapOf("idTeacher" to teacher.getInt(0), "typeDocument" to teacher.getString(1), "gender" to teacher.getString(2),
                    "birdthdate" to teacher.getString(3), "tittle" to teacher.getString(4),
                    "experience" to teacher.getInt(5), "phone" to teacher.getString(6),
                    "status" to teacher.getString(7), "numDocument" to teacher.getInt(8), "program" to findNameProgramById(teacher.getInt(9)), "name" to findUserById(teacher.getInt(8), 1)["name"])
                items.add(item)
            }while (teacher.moveToNext())
        }else{
            println("La tabla esta vacía aca")
        }
        return items;
    }

    //Metodo que me permite buscar todas las materias que hallan disponibles
    //Retorn: {Lista de Maps con cada una de las materias, si los encuentra, de caso contrario retorna una lista vacia}
    fun getAllMaterias(): List<Map<*, *>>{
        var getAllMaterias = "SElECT * FROM $TABLE_COURSE;"
        val db = this.readableDatabase
        val items = mutableListOf<Map<*, *>>()
        var item : Map<*, *>
        var teacher = db.rawQuery(getAllMaterias, null)
        if(teacher.moveToFirst()){
            do {
                item = mapOf("idMateria" to teacher.getInt(0), "nameCourse" to teacher.getString(1), "semesterLocation" to teacher.getString(2),
                    "numCredits" to teacher.getString(3))
                items.add(item)
            }while (teacher.moveToNext())
        }else{
            println("La tabla esta vacía aca")
        }
        return items;
    }

    //Metodo que me permite
    fun getAllMateriasByStudent(numDocument: Int, status: String): List<Map<*, *>>{
        var getAllMaterias = "SElECT * FROM $TABLE_CURRENT_SEMESTER WHERE idStudent = $numDocument and status = '$status';"
        val db = this.readableDatabase
        val items = mutableListOf<Map<*, *>>()
        var item : Map<*, *>
        var materia = db.rawQuery(getAllMaterias, null)
        if(materia.moveToFirst()){
            do {
                item = findMateriaById(materia.getInt(2))
                println(item)
                items.add(item)
            }while (materia.moveToNext())
        }else{
            println("Tabla vacia")
        }
        return items;
    }

    fun getAllMateriasByDocent(numDocument: Int, status: String): List<Map<*, *>>{
        var getAllMaterias = "SElECT * FROM $TABLE_CURRENT_SEMESTER WHERE idStudent = $numDocument and status = '$status';"
        val db = this.readableDatabase
        val items = mutableListOf<Map<*, *>>()
        var item : Map<*, *>
        var materia = db.rawQuery(getAllMaterias, null)
        if(materia.moveToFirst()){
            do {
                item = findMateriaById(materia.getInt(2))
                println(item)
                items.add(item)
            }while (materia.moveToNext())
        }else{
            println("Tabla vacia")
        }
        return items;
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
}