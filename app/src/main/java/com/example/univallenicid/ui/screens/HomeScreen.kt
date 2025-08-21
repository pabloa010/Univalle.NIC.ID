package com.example.univallenicid.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.univallenicid.R
import com.example.univallenicid.viewmodel.MainViewModel
import com.example.univallenicid.viewmodel.UIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentStudent by viewModel.currentStudent.collectAsState()
    
    currentStudent?.let { student ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            // Header profesional con información del estudiante
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = getCareerColor(student.career)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Avatar del estudiante profesional
                    Card(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = getCareerColor(student.career)
                            )
                        }
                    }
                    
                    // Nombre del estudiante
                    Text(
                        text = student.fullName,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    // Número de carnet
                    Text(
                        text = "Carnet: ${student.studentId}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f)
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            // Información detallada profesional
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Título de la sección
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = getCareerColor(student.career),
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = stringResource(R.string.home_student_info),
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = getCareerColor(student.career)
                            )
                        )
                    }
                    
                    // Carrera
                    InfoRow(
                        icon = Icons.Default.School,
                        label = stringResource(R.string.home_career),
                        value = viewModel.getCareerName(student.career),
                        valueColor = getCareerColor(student.career)
                    )
                    
                    // Modalidad
                    InfoRow(
                        icon = Icons.Default.Schedule,
                        label = stringResource(R.string.home_modality),
                        value = viewModel.getModalityName(student.modality),
                        valueColor = MaterialTheme.colorScheme.secondary
                    )
                    
                    // Estado
                    InfoRow(
                        icon = if (student.status.name == "ACTIVO") Icons.Default.CheckCircle else Icons.Default.Cancel,
                        label = stringResource(R.string.home_status),
                        value = viewModel.getStatusName(student.status),
                        valueColor = if (student.status.name == "ACTIVO") 
                            MaterialTheme.colorScheme.primary 
                        else 
                            MaterialTheme.colorScheme.error
                    )
                    
                    // Email
                    InfoRow(
                        icon = Icons.Default.Email,
                        label = "Email",
                        value = student.email,
                        valueColor = MaterialTheme.colorScheme.tertiary
                    )
                    
                    // Asignaturas inscritas
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Book,
                            contentDescription = null,
                            tint = getCareerColor(student.career),
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Asignaturas Inscritas",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                )
                            )
                            Text(
                                text = "${student.subjects.size} asignaturas",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = getCareerColor(student.career)
                                )
                            )
                        }
                        // Badge con número de asignaturas
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = getCareerColor(student.career)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = "${student.subjects.size}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary
                                ),
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
            
            // Botones de acción profesionales
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Botón Ver Asignaturas
                Button(
                    onClick = { viewModel.navigateTo(UIState.Subjects) },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Book,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.nav_subjects),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                
                // Botón Código QR
                Button(
                    onClick = { viewModel.navigateTo(UIState.QRCode) },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = getCareerColor(student.career)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCode,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.nav_qr_code),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun InfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    valueColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = valueColor
                )
            )
        }
    }
}

// Función para obtener colores profesionales y mate por carrera
private fun getCareerColor(career: com.example.univallenicid.data.Career): Color {
    return when (career) {
        com.example.univallenicid.data.Career.MEDICINA -> Color(0xFFB71C1C) // Rojo granate
        com.example.univallenicid.data.Career.INGENIERIA_SISTEMAS -> Color(0xFF1A237E) // Azul índigo profundo
        com.example.univallenicid.data.Career.DERECHO -> Color(0xFF4A148C) // Púrpura profundo
        com.example.univallenicid.data.Career.PSICOLOGIA -> Color(0xFF880E4F) // Rosa profundo
        com.example.univallenicid.data.Career.ARQUITECTURA -> Color(0xFFE65100) // Naranja profundo
        com.example.univallenicid.data.Career.ECONOMIA -> Color(0xFF1B5E20) // Verde profundo
        com.example.univallenicid.data.Career.CIENCIAS_EDUCACION -> Color(0xFF004D40) // Verde azulado profundo
        com.example.univallenicid.data.Career.QUIMICA -> Color(0xFF1A237E) // Índigo profundo
        com.example.univallenicid.data.Career.BIOLOGIA -> Color(0xFF33691E) // Verde lima profundo
        com.example.univallenicid.data.Career.ADMINISTRACION_EMPRESAS -> Color(0xFF3E2723) // Marrón profundo
    }
}