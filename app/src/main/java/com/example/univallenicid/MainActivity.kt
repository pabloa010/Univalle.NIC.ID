package com.example.univallenicid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.univallenicid.ui.components.BottomNavigation
import com.example.univallenicid.ui.screens.*
import com.example.univallenicid.ui.theme.UnivalleNICIDTheme
import com.example.univallenicid.viewmodel.MainViewModel
import com.example.univallenicid.viewmodel.UIState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnivalleNICIDTheme {
                UnivalleNICIDApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnivalleNICIDApp() {
    val viewModel: MainViewModel = viewModel()
    val currentScreen by viewModel.uiState.collectAsState()
    val currentStudent by viewModel.currentStudent.collectAsState()
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            // Mostrar navegación solo si el usuario está autenticado
            if (currentStudent != null && currentScreen !is UIState.Login && currentScreen !is UIState.QRScanner) {
                BottomNavigation(
                    viewModel = viewModel
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                is UIState.Login -> {
                    LoginScreen(viewModel = viewModel)
                }
                is UIState.Home -> {
                    HomeScreen(viewModel = viewModel)
                }
                is UIState.Subjects -> {
                    SubjectsScreen(viewModel = viewModel)
                }
                is UIState.QRCode -> {
                    QRCodeScreen(viewModel = viewModel)
                }
                is UIState.QRScanner -> {
                    QRScannerScreen(
                        viewModel = viewModel,
                        onNavigateBack = { viewModel.navigateTo(UIState.QRCode) }
                    )
                }
            }
        }
    }
}