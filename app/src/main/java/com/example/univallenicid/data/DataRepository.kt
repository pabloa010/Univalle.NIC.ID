package com.example.univallenicid.data

import kotlinx.coroutines.delay

class DataRepository {
    
    // Lista de docentes ficticios
    private val teachers = listOf(
        Teacher(name = "Dr. Carlos Mendoza", email = "carlos.mendoza@univalle.edu.ni", specialization = "Programación Avanzada"),
        Teacher(name = "Dra. Ana Rodríguez", email = "ana.rodriguez@univalle.edu.ni", specialization = "Anatomía Humana"),
        Teacher(name = "Lic. Roberto Silva", email = "roberto.silva@univalle.edu.ni", specialization = "Derecho Civil"),
        Teacher(name = "MSc. María González", email = "maria.gonzalez@univalle.edu.ni", specialization = "Finanzas Corporativas"),
        Teacher(name = "Dr. Luis Pérez", email = "luis.perez@univalle.edu.ni", specialization = "Psicología Clínica"),
        Teacher(name = "Arq. Patricia López", email = "patricia.lopez@univalle.edu.ni", specialization = "Diseño Arquitectónico"),
        Teacher(name = "Dr. Fernando Torres", email = "fernando.torres@univalle.edu.ni", specialization = "Macroeconomía"),
        Teacher(name = "MSc. Carmen Vega", email = "carmen.vega@univalle.edu.ni", specialization = "Pedagogía"),
        Teacher(name = "Dr. Jorge Herrera", email = "jorge.herrera@univalle.edu.ni", specialization = "Química Orgánica"),
        Teacher(name = "Dra. Isabel Morales", email = "isabel.morales@univalle.edu.ni", specialization = "Biología Molecular")
    )
    
    // Lista de asignaturas por carrera
    private val subjectsByCareer = mapOf(
        Career.INGENIERIA_SISTEMAS to listOf(
            Subject(code = "IS-101", name = "Programación I", credits = 4, teacher = teachers[0], career = Career.INGENIERIA_SISTEMAS),
            Subject(code = "IS-102", name = "Matemáticas Discretas", credits = 3, teacher = teachers[0], career = Career.INGENIERIA_SISTEMAS),
            Subject(code = "IS-201", name = "Estructuras de Datos", credits = 4, teacher = teachers[0], career = Career.INGENIERIA_SISTEMAS),
            Subject(code = "IS-202", name = "Bases de Datos", credits = 4, teacher = teachers[0], career = Career.INGENIERIA_SISTEMAS)
        ),
        Career.MEDICINA to listOf(
            Subject(code = "MED-101", name = "Anatomía Humana", credits = 6, teacher = teachers[1], career = Career.MEDICINA),
            Subject(code = "MED-102", name = "Fisiología", credits = 5, teacher = teachers[1], career = Career.MEDICINA),
            Subject(code = "MED-201", name = "Bioquímica", credits = 4, teacher = teachers[1], career = Career.MEDICINA),
            Subject(code = "MED-202", name = "Farmacología", credits = 4, teacher = teachers[1], career = Career.MEDICINA)
        ),
        Career.DERECHO to listOf(
            Subject(code = "DER-101", name = "Introducción al Derecho", credits = 3, teacher = teachers[2], career = Career.DERECHO),
            Subject(code = "DER-102", name = "Derecho Civil", credits = 4, teacher = teachers[2], career = Career.DERECHO),
            Subject(code = "DER-201", name = "Derecho Penal", credits = 4, teacher = teachers[2], career = Career.DERECHO),
            Subject(code = "DER-202", name = "Derecho Constitucional", credits = 3, teacher = teachers[2], career = Career.DERECHO)
        ),
        Career.ADMINISTRACION_EMPRESAS to listOf(
            Subject(code = "ADM-101", name = "Introducción a la Administración", credits = 3, teacher = teachers[3], career = Career.ADMINISTRACION_EMPRESAS),
            Subject(code = "ADM-102", name = "Contabilidad I", credits = 4, teacher = teachers[3], career = Career.ADMINISTRACION_EMPRESAS),
            Subject(code = "ADM-201", name = "Finanzas Corporativas", credits = 4, teacher = teachers[3], career = Career.ADMINISTRACION_EMPRESAS),
            Subject(code = "ADM-202", name = "Marketing", credits = 3, teacher = teachers[3], career = Career.ADMINISTRACION_EMPRESAS)
        ),
        Career.PSICOLOGIA to listOf(
            Subject(code = "PSI-101", name = "Introducción a la Psicología", credits = 3, teacher = teachers[4], career = Career.PSICOLOGIA),
            Subject(code = "PSI-102", name = "Psicología del Desarrollo", credits = 4, teacher = teachers[4], career = Career.PSICOLOGIA),
            Subject(code = "PSI-201", name = "Psicología Clínica", credits = 4, teacher = teachers[4], career = Career.PSICOLOGIA),
            Subject(code = "PSI-202", name = "Psicología Social", credits = 3, teacher = teachers[4], career = Career.PSICOLOGIA)
        ),
        Career.ARQUITECTURA to listOf(
            Subject(code = "ARQ-101", name = "Dibujo Arquitectónico", credits = 4, teacher = teachers[5], career = Career.ARQUITECTURA),
            Subject(code = "ARQ-102", name = "Historia de la Arquitectura", credits = 3, teacher = teachers[5], career = Career.ARQUITECTURA),
            Subject(code = "ARQ-201", name = "Diseño Arquitectónico", credits = 5, teacher = teachers[5], career = Career.ARQUITECTURA),
            Subject(code = "ARQ-202", name = "Estructuras", credits = 4, teacher = teachers[5], career = Career.ARQUITECTURA)
        ),
        Career.ECONOMIA to listOf(
            Subject(code = "ECO-101", name = "Introducción a la Economía", credits = 3, teacher = teachers[6], career = Career.ECONOMIA),
            Subject(code = "ECO-102", name = "Microeconomía", credits = 4, teacher = teachers[6], career = Career.ECONOMIA),
            Subject(code = "ECO-201", name = "Macroeconomía", credits = 4, teacher = teachers[6], career = Career.ECONOMIA),
            Subject(code = "ECO-202", name = "Econometría", credits = 4, teacher = teachers[6], career = Career.ECONOMIA)
        ),
        Career.CIENCIAS_EDUCACION to listOf(
            Subject(code = "EDU-101", name = "Fundamentos de la Educación", credits = 3, teacher = teachers[7], career = Career.CIENCIAS_EDUCACION),
            Subject(code = "EDU-102", name = "Psicología Educativa", credits = 4, teacher = teachers[7], career = Career.CIENCIAS_EDUCACION),
            Subject(code = "EDU-201", name = "Metodología de la Enseñanza", credits = 4, teacher = teachers[7], career = Career.CIENCIAS_EDUCACION),
            Subject(code = "EDU-202", name = "Evaluación Educativa", credits = 3, teacher = teachers[7], career = Career.CIENCIAS_EDUCACION)
        ),
        Career.QUIMICA to listOf(
            Subject(code = "QUI-101", name = "Química General", credits = 4, teacher = teachers[8], career = Career.QUIMICA),
            Subject(code = "QUI-102", name = "Química Inorgánica", credits = 4, teacher = teachers[8], career = Career.QUIMICA),
            Subject(code = "QUI-201", name = "Química Orgánica", credits = 4, teacher = teachers[8], career = Career.QUIMICA),
            Subject(code = "QUI-202", name = "Análisis Químico", credits = 4, teacher = teachers[8], career = Career.QUIMICA)
        ),
        Career.BIOLOGIA to listOf(
            Subject(code = "BIO-101", name = "Biología General", credits = 4, teacher = teachers[9], career = Career.BIOLOGIA),
            Subject(code = "BIO-102", name = "Botánica", credits = 4, teacher = teachers[9], career = Career.BIOLOGIA),
            Subject(code = "BIO-201", name = "Zoología", credits = 4, teacher = teachers[9], career = Career.BIOLOGIA),
            Subject(code = "BIO-202", name = "Biología Molecular", credits = 4, teacher = teachers[9], career = Career.BIOLOGIA)
        )
    )
    
    // Lista de 20 estudiantes ficticios
    private val students = listOf(
        Student(
            studentId = "2024-001",
            fullName = "María José González López",
            email = "maria.gonzalez@estudiantes.univalle.edu.ni",
            career = Career.INGENIERIA_SISTEMAS,
            modality = Modality.DIURNO,
            status = StudentStatus.ACTIVO,
            subjects = subjectsByCareer[Career.INGENIERIA_SISTEMAS]?.take(3) ?: emptyList()
        ),
        Student(
            studentId = "2024-002",
            fullName = "Carlos Alberto Ramírez Mendoza",
            email = "carlos.ramirez@estudiantes.univalle.edu.ni",
            career = Career.MEDICINA,
            modality = Modality.DIURNO,
            status = StudentStatus.ACTIVO,
            subjects = subjectsByCareer[Career.MEDICINA]?.take(3) ?: emptyList()
        ),
        Student(
            studentId = "2024-003",
            fullName = "Ana Patricia Silva Vega",
            email = "ana.silva@estudiantes.univalle.edu.ni",
            career = Career.DERECHO,
            modality = Modality.SABATINO,
            status = StudentStatus.ACTIVO,
            subjects = subjectsByCareer[Career.DERECHO]?.take(3) ?: emptyList()
        ),
        Student(
            studentId = "2024-004",
            fullName = "Roberto Daniel Torres Herrera",
            email = "roberto.torres@estudiantes.univalle.edu.ni",
            career = Career.ADMINISTRACION_EMPRESAS,
            modality = Modality.DIURNO,
            status = StudentStatus.ACTIVO,
            subjects = subjectsByCareer[Career.ADMINISTRACION_EMPRESAS]?.take(3) ?: emptyList()
        ),
        Student(
            studentId = "2024-005",
            fullName = "Isabella Carmen Morales Pérez",
            email = "isabella.morales@estudiantes.univalle.edu.ni",
            career = Career.PSICOLOGIA,
            modality = Modality.DIURNO,
            status = StudentStatus.ACTIVO,
            subjects = subjectsByCareer[Career.PSICOLOGIA]?.take(3) ?: emptyList()
        ),
        Student(
            studentId = "2024-006",
            fullName = "Fernando José López Rodríguez",
            email = "fernando.lopez@estudiantes.univalle.edu.ni",
            career = Career.ARQUITECTURA,
            modality = Modality.DIURNO,
            status = StudentStatus.ACTIVO,
            subjects = subjectsByCareer[Career.ARQUITECTURA]?.take(3) ?: emptyList()
        ),
        Student(
            studentId = "2024-007",
            fullName = "Carmen Elena Vega Mendoza",
            email = "carmen.vega@estudiantes.univalle.edu.ni",
            career = Career.ECONOMIA,
            modality = Modality.SABATINO,
            status = StudentStatus.ACTIVO,
            subjects = subjectsByCareer[Career.ECONOMIA]?.take(3) ?: emptyList()
        ),
        Student(
            studentId = "2024-008",
            fullName = "Jorge Luis Herrera Silva",
            email = "jorge.herrera@estudiantes.univalle.edu.ni",
            career = Career.CIENCIAS_EDUCACION,
            modality = Modality.DOMINGO,
            status = StudentStatus.ACTIVO,
            subjects = subjectsByCareer[Career.CIENCIAS_EDUCACION]?.take(3) ?: emptyList()
        ),
        Student(
            studentId = "2024-009",
            fullName = "Patricia Isabel Pérez González",
            email = "patricia.perez@estudiantes.univalle.edu.ni",
            career = Career.QUIMICA,
            modality = Modality.DIURNO,
            status = StudentStatus.ACTIVO,
            subjects = subjectsByCareer[Career.QUIMICA]?.take(3) ?: emptyList()
        ),
        Student(
            studentId = "2024-010",
            fullName = "Luis Miguel Rodríguez Torres",
            email = "luis.rodriguez@estudiantes.univalle.edu.ni",
            career = Career.BIOLOGIA,
            modality = Modality.DIURNO,
            status = StudentStatus.ACTIVO,
            subjects = subjectsByCareer[Career.BIOLOGIA]?.take(3) ?: emptyList()
        ),
        Student(
            studentId = "2024-011",
            fullName = "María Fernanda Mendoza Vega",
            email = "maria.mendoza@estudiantes.univalle.edu.ni",
            career = Career.INGENIERIA_SISTEMAS,
            modality = Modality.SABATINO,
            status = StudentStatus.INACTIVO,
            subjects = subjectsByCareer[Career.INGENIERIA_SISTEMAS]?.take(2) ?: emptyList()
        ),
        Student(
            studentId = "2024-012",
            fullName = "Carlos Eduardo Silva López",
            email = "carlos.silva@estudiantes.univalle.edu.ni",
            career = Career.MEDICINA,
            modality = Modality.DIURNO,
            status = StudentStatus.INACTIVO,
            subjects = subjectsByCareer[Career.MEDICINA]?.take(2) ?: emptyList()
        ),
        Student(
            studentId = "2024-013",
            fullName = "Ana Lucía Torres Herrera",
            email = "ana.torres@estudiantes.univalle.edu.ni",
            career = Career.DERECHO,
            modality = Modality.DOMINGO,
            status = StudentStatus.INACTIVO,
            subjects = subjectsByCareer[Career.DERECHO]?.take(2) ?: emptyList()
        ),
        Student(
            studentId = "2024-014",
            fullName = "Roberto Antonio Vega Morales",
            email = "roberto.vega@estudiantes.univalle.edu.ni",
            career = Career.ADMINISTRACION_EMPRESAS,
            modality = Modality.SABATINO,
            status = StudentStatus.INACTIVO,
            subjects = subjectsByCareer[Career.ADMINISTRACION_EMPRESAS]?.take(2) ?: emptyList()
        ),
        Student(
            studentId = "2024-015",
            fullName = "Isabella Patricia Herrera Pérez",
            email = "isabella.herrera@estudiantes.univalle.edu.ni",
            career = Career.PSICOLOGIA,
            modality = Modality.DIURNO,
            status = StudentStatus.INACTIVO,
            subjects = subjectsByCareer[Career.PSICOLOGIA]?.take(2) ?: emptyList()
        ),
        Student(
            studentId = "2024-016",
            fullName = "Fernando Carlos López Silva",
            email = "fernando.lopez.silva@estudiantes.univalle.edu.ni",
            career = Career.ARQUITECTURA,
            modality = Modality.SABATINO,
            status = StudentStatus.INACTIVO,
            subjects = subjectsByCareer[Career.ARQUITECTURA]?.take(2) ?: emptyList()
        ),
        Student(
            studentId = "2024-017",
            fullName = "Carmen Patricia Vega Rodríguez",
            email = "carmen.vega.rodriguez@estudiantes.univalle.edu.ni",
            career = Career.ECONOMIA,
            modality = Modality.DIURNO,
            status = StudentStatus.INACTIVO,
            subjects = subjectsByCareer[Career.ECONOMIA]?.take(2) ?: emptyList()
        ),
        Student(
            studentId = "2024-018",
            fullName = "Jorge Alberto Herrera Mendoza",
            email = "jorge.herrera.mendoza@estudiantes.univalle.edu.ni",
            career = Career.CIENCIAS_EDUCACION,
            modality = Modality.DOMINGO,
            status = StudentStatus.INACTIVO,
            subjects = subjectsByCareer[Career.CIENCIAS_EDUCACION]?.take(2) ?: emptyList()
        ),
        Student(
            studentId = "2024-019",
            fullName = "Patricia Elena Pérez Vega",
            email = "patricia.perez.vega@estudiantes.univalle.edu.ni",
            career = Career.QUIMICA,
            modality = Modality.SABATINO,
            status = StudentStatus.INACTIVO,
            subjects = subjectsByCareer[Career.QUIMICA]?.take(2) ?: emptyList()
        ),
        Student(
            studentId = "2024-020",
            fullName = "Luis Antonio Rodríguez Herrera",
            email = "luis.rodriguez.herrera@estudiantes.univalle.edu.ni",
            career = Career.BIOLOGIA,
            modality = Modality.DIURNO,
            status = StudentStatus.INACTIVO,
            subjects = subjectsByCareer[Career.BIOLOGIA]?.take(2) ?: emptyList()
        )
    )
    
    // Simular autenticación
    suspend fun authenticate(studentId: String, password: String): AuthResponse {
        delay(1000) // Simular delay de red
        
        val student = students.find { it.studentId == studentId }
        return if (student != null && password == "123456") { // Contraseña por defecto
            AuthResponse(
                success = true,
                student = student.copy(qrCode = student.generateQRCode())
            )
        } else {
            AuthResponse(
                success = false,
                message = "Credenciales inválidas"
            )
        }
    }
    
    // Obtener estudiante por ID
    fun getStudentById(studentId: String): Student? {
        return students.find { it.studentId == studentId }
    }
    
    // Validar código QR
    suspend fun validateQRCode(qrData: String): QRScanResult {
        delay(500) // Simular delay de red
        
        val parts = qrData.split("|")
        if (parts.size >= 4 && parts[0] == "UNIVALLE") {
            val studentId = parts[1]
            val student = students.find { it.studentId == studentId }
            
            return when {
                student == null -> QRScanResult(
                    isValid = false,
                    message = "Estudiante no encontrado"
                )
                student.status == StudentStatus.ACTIVO -> QRScanResult(
                    isValid = true,
                    student = student,
                    message = "Estudiante válido y activo"
                )
                else -> QRScanResult(
                    isValid = false,
                    student = student,
                    message = "Estudiante inactivo"
                )
            }
        }
        
        return QRScanResult(
            isValid = false,
            message = "Código QR inválido"
        )
    }
    
    // Obtener todas las carreras
    fun getAllCareers(): List<Career> {
        return Career.values().toList()
    }
    
    // Obtener todas las modalidades
    fun getAllModalities(): List<Modality> {
        return Modality.values().toList()
    }
}
