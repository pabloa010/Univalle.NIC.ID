package com.example.univallenicid.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Colores profesionales y mate para la aplicación
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2E5D31), // Verde oliva mate
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE8F5E8), // Verde muy claro
    onPrimaryContainer = Color(0xFF1B3A1E),
    
    secondary = Color(0xFF34495E), // Azul pizarra mate
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE4E9F2), // Azul muy claro
    onSecondaryContainer = Color(0xFF2C3E50),
    
    tertiary = Color(0xFF6C7B7F), // Gris azulado
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFF0F2F3), // Gris muy claro
    onTertiaryContainer = Color(0xFF454545),
    
    background = Color(0xFFF8F9FA), // Fondo neutro
    onBackground = Color(0xFF1C1B1F),
    surface = Color.White,
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFF1F3F4), // Fondo secundario sutil
    onSurfaceVariant = Color(0xFF49454F),
    
    error = Color(0xFFD32F2F), // Rojo mate
    onError = Color.White,
    errorContainer = Color(0xFFFFF2F2),
    onErrorContainer = Color(0xFFB71C1C),
    
    outline = Color(0xFFE8EAED),
    outlineVariant = Color(0xFFDADCE0),
    
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFF313033),
    inverseOnSurface = Color(0xFFF4EFF4),
    inversePrimary = Color(0xFF4F7942),
    
    surfaceDim = Color(0xFFF1F3F4),
    surfaceBright = Color.White,
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFFAFBFC),
    surfaceContainer = Color(0xFFF1F3F4),
    surfaceContainerHigh = Color(0xFFEBEDEE),
    surfaceContainerHighest = Color(0xFFE4E7E9)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4F7942), // Verde mate para modo oscuro
    onPrimary = Color(0xFF1B3A1E),
    primaryContainer = Color(0xFF2E5D31), // Verde principal mate
    onPrimaryContainer = Color(0xFFE8F5E8),
    
    secondary = Color(0xFF5D6D7E), // Azul pizarra para modo oscuro
    onSecondary = Color(0xFF2C3E50),
    secondaryContainer = Color(0xFF34495E), // Azul principal mate
    onSecondaryContainer = Color(0xFFE4E9F2),
    
    tertiary = Color(0xFF90A4AE), // Gris azulado para modo oscuro
    onTertiary = Color(0xFF454545),
    tertiaryContainer = Color(0xFF6C7B7F), // Gris principal mate
    onTertiaryContainer = Color(0xFFF0F2F3),
    
    background = Color(0xFF1C1B1F), // Fondo oscuro profesional
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),
    
    error = Color(0xFFFFB4AB), // Rojo suave para modo oscuro
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    
    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454F),
    
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFFE6E1E5),
    inverseOnSurface = Color(0xFF1C1B1F),
    inversePrimary = Color(0xFF2E5D31),
    
    surfaceDim = Color(0xFF1C1B1F),
    surfaceBright = Color(0xFF413F43),
    surfaceContainerLowest = Color(0xFF16151A),
    surfaceContainerLow = Color(0xFF1C1B1F),
    surfaceContainer = Color(0xFF201F25),
    surfaceContainerHigh = Color(0xFF2A292F),
    surfaceContainerHighest = Color(0xFF35343A)
)

@Composable
fun UnivalleNICIDTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Desactivado para usar nuestros colores personalizados
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // status bar color
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}