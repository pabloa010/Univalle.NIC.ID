package com.example.univallenicid.data

import java.util.UUID

// Enum para modalidades
enum class Modality {
    DIURNO, SABATINO, DOMINGO
}

// Enum para estado del estudiante
enum class StudentStatus {
    ACTIVO, INACTIVO
}

// Enum para carreras
enum class Career {
    INGENIERIA_SISTEMAS,
    MEDICINA,
    DERECHO,
    ADMINISTRACION_EMPRESAS,
    PSICOLOGIA,
    ARQUITECTURA,
    ECONOMIA,
    CIENCIAS_EDUCACION,
    QUIMICA,
    BIOLOGIA
}

// Data class para Docente
data class Teacher(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String,
    val specialization: String
)

// Data class para Asignatura
data class Subject(
    val id: String = UUID.randomUUID().toString(),
    val code: String,
    val name: String,
    val credits: Int,
    val teacher: Teacher,
    val career: Career
)

// Data class para Estudiante
data class Student(
    val id: String = UUID.randomUUID().toString(),
    val studentId: String, // Número de carnet
    val fullName: String,
    val email: String,
    val career: Career,
    val modality: Modality,
    val status: StudentStatus,
    val subjects: List<Subject> = emptyList(),
    val qrCode: String = ""
) {
    // Generar código QR único basado en información del estudiante
    fun generateQRCode(): String {
        return "UNIVALLE|$studentId|$fullName|$career|$status"
    }
}

// Data class para respuesta de autenticación
data class AuthResponse(
    val success: Boolean,
    val student: Student? = null,
    val message: String = ""
)

// Data class para resultado de escaneo QR
data class QRScanResult(
    val isValid: Boolean,
    val student: Student? = null,
    val message: String = ""
)
