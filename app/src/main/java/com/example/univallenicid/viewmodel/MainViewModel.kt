package com.example.univallenicid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.univallenicid.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    
    private val repository = DataRepository()
    
    // Estados de la aplicación
    private val _uiState = MutableStateFlow<UIState>(UIState.Login)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()
    
    private val _currentStudent = MutableStateFlow<Student?>(null)
    val currentStudent: StateFlow<Student?> = _currentStudent.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _qrScanResult = MutableStateFlow<QRScanResult?>(null)
    val qrScanResult: StateFlow<QRScanResult?> = _qrScanResult.asStateFlow()
    
    // Función de autenticación
    fun login(studentId: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            try {
                val response = repository.authenticate(studentId, password)
                if (response.success) {
                    _currentStudent.value = response.student
                    _uiState.value = UIState.Home
                } else {
                    _errorMessage.value = response.message
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error de conexión: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    // Función para cerrar sesión
    fun logout() {
        _currentStudent.value = null
        _uiState.value = UIState.Login
        _errorMessage.value = null
        _qrScanResult.value = null
    }
    
    // Función para navegar a diferentes pantallas
    fun navigateTo(screen: UIState) {
        _uiState.value = screen
    }
    
    // Función para validar código QR
    fun validateQRCode(qrData: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.validateQRCode(qrData)
                _qrScanResult.value = result
            } catch (e: Exception) {
                _errorMessage.value = "Error al validar QR: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    // Función para limpiar mensajes de error
    fun clearError() {
        _errorMessage.value = null
    }
    
    // Función para limpiar resultado de QR
    fun clearQRResult() {
        _qrScanResult.value = null
    }
    
    // Función para obtener el nombre de la carrera
    fun getCareerName(career: Career): String {
        return when (career) {
            Career.INGENIERIA_SISTEMAS -> "Ingeniería en Sistemas"
            Career.MEDICINA -> "Medicina"
            Career.DERECHO -> "Derecho"
            Career.ADMINISTRACION_EMPRESAS -> "Administración de Empresas"
            Career.PSICOLOGIA -> "Psicología"
            Career.ARQUITECTURA -> "Arquitectura"
            Career.ECONOMIA -> "Economía"
            Career.CIENCIAS_EDUCACION -> "Ciencias de la Educación"
            Career.QUIMICA -> "Química"
            Career.BIOLOGIA -> "Biología"
        }
    }
    
    // Función para obtener el nombre de la modalidad
    fun getModalityName(modality: Modality): String {
        return when (modality) {
            Modality.DIURNO -> "Diurno"
            Modality.SABATINO -> "Sabatino"
            Modality.DOMINGO -> "Domingo"
        }
    }
    
    // Función para obtener el estado del estudiante
    fun getStatusName(status: StudentStatus): String {
        return when (status) {
            StudentStatus.ACTIVO -> "Activo"
            StudentStatus.INACTIVO -> "Inactivo"
        }
    }
}

// Estados de la UI
sealed class UIState {
    object Login : UIState()
    object Home : UIState()
    object Subjects : UIState()
    object QRCode : UIState()
    object QRScanner : UIState()
}
