# Univalle NIC ID - Aplicación de Identificación Estudiantil

## Descripción

Univalle NIC ID es una aplicación Android desarrollada en Kotlin con Jetpack Compose que permite a los estudiantes de la Universidad del Valle de Nicaragua gestionar su identificación digital, ver sus asignaturas inscritas y generar códigos QR únicos para validación de identidad.

## Características Principales

### 🔐 Autenticación Segura
- Inicio de sesión con validación de credenciales institucionales
- Número de carnet y contraseña requeridos
- Validación de estudiantes activos/inactivos

### 📱 Interfaz Moderna
- Diseño Material 3 con Jetpack Compose
- Paleta de colores: Verdes, Azules y Blanco Perla
- Navegación intuitiva con menú inferior
- Modo claro y oscuro

### 🎓 Gestión Académica
- Visualización de información del estudiante
- Lista de asignaturas inscritas con detalles
- Información de docentes por asignatura
- Estado académico (activo/inactivo)

### 📱 Códigos QR
- Generación de códigos QR únicos por estudiante
- Escaneo de códigos QR para validación
- Información cifrada en el código QR
- Validación en tiempo real

### 🔒 Seguridad
- Comunicación HTTPS con Firestore
- Cifrado de datos personales
- Validación de permisos de cámara
- Configuración de seguridad de red

## Estructura del Proyecto

```
app/src/main/java/com/example/univallenicid/
├── data/
│   ├── Models.kt              # Clases de datos
│   └── DataRepository.kt      # Repositorio con datos ficticios
├── ui/
│   ├── components/
│   │   └── BottomNavigation.kt # Navegación inferior
│   ├── screens/
│   │   ├── LoginScreen.kt     # Pantalla de login
│   │   ├── HomeScreen.kt      # Pantalla principal
│   │   ├── SubjectsScreen.kt  # Pantalla de asignaturas
│   │   ├── QRCodeScreen.kt    # Pantalla de código QR
│   │   └── QRScannerScreen.kt # Pantalla de escaneo QR
│   └── theme/
│       ├── Theme.kt           # Tema personalizado
│       └── Type.kt            # Tipografía
├── viewmodel/
│   └── MainViewModel.kt       # ViewModel principal
└── MainActivity.kt            # Actividad principal
```

## Datos de Prueba

La aplicación incluye 20 estudiantes ficticios con datos completos:

### Estudiantes de Prueba
- **Carnet**: 2024-001 a 2024-020
- **Contraseña**: 123456 (para todos)

### Carreras Disponibles
1. Ingeniería en Sistemas
2. Medicina
3. Derecho
4. Administración de Empresas
5. Psicología
6. Arquitectura
7. Economía
8. Ciencias de la Educación
9. Química
10. Biología

### Modalidades
- Diurno
- Sabatino
- Domingo

## Configuración del Proyecto

### Requisitos
- Android Studio Hedgehog | 2023.1.1 o superior
- Kotlin 1.9.0 o superior
- Android SDK 36
- Gradle 8.0 o superior

### Dependencias Principales
```kotlin
// Compose
implementation("androidx.compose.material3:material3")
implementation("androidx.navigation:navigation-compose:2.7.7")

// Firebase
implementation("com.google.firebase:firebase-firestore")
implementation("com.google.firebase:firebase-auth")

// QR Code
implementation("com.google.zxing:core:3.5.3")
implementation("com.journeyapps:zxing-android-embedded:4.3.0")

// ViewModel & Coroutines
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
```

### Configuración de Firebase
1. Crear proyecto en Firebase Console
2. Descargar `google-services.json`
3. Colocar en `app/` directory
4. Habilitar Firestore Database

## Instalación y Uso

### 1. Clonar el Repositorio
```bash
git clone <repository-url>
cd UnivalleNICID
```

### 2. Configurar Firebase
- Crear proyecto en [Firebase Console](https://console.firebase.google.com/)
- Descargar `google-services.json`
- Colocar en directorio `app/`

### 3. Sincronizar Proyecto
```bash
./gradlew build
```

### 4. Ejecutar en Dispositivo
```bash
./gradlew installDebug
```

### 5. Iniciar Sesión
- Usar credenciales de prueba:
  - Carnet: 2024-001
  - Contraseña: 123456

## Funcionalidades por Pantalla

### 🔐 Login
- Validación de credenciales
- Mensajes de error informativos
- Indicador de carga

### 🏠 Inicio
- Información personal del estudiante
- Estado académico
- Acceso rápido a asignaturas y QR

### 📚 Asignaturas
- Lista de materias inscritas
- Información de docentes
- Códigos y créditos

### 📱 Código QR
- Generación de QR único
- Información del estudiante
- Botón de escaneo

### 📷 Escáner QR
- Escaneo de códigos QR
- Validación en tiempo real
- Resultados de validación

## Seguridad Implementada

### 🔒 Protección de Datos
- Cifrado de información en códigos QR
- Validación de permisos de cámara
- Comunicación HTTPS obligatoria

### 🛡️ Configuración de Red
```xml
<network-security-config>
    <domain-config cleartextTrafficPermitted="false">
        <domain includeSubdomains="true">firebase.google.com</domain>
        <domain includeSubdomains="true">googleapis.com</domain>
    </domain-config>
</network-security-config>
```

### 📱 Permisos Requeridos
- `CAMERA`: Para escanear códigos QR
- `INTERNET`: Para comunicación con Firebase
- `ACCESS_NETWORK_STATE`: Para verificar conectividad

## Arquitectura

### MVVM (Model-View-ViewModel)
- **Model**: Clases de datos y repositorio
- **View**: Pantallas Compose
- **ViewModel**: Lógica de negocio y estado

### Patrones Utilizados
- **Repository Pattern**: Para acceso a datos
- **Observer Pattern**: Con StateFlow
- **Dependency Injection**: Con ViewModel

## Personalización

### Colores del Tema
```kotlin
// Verde Principal
primary = Color(0xFF2E7D32)

// Azul Secundario
secondary = Color(0xFF1976D2)

// Blanco Perla
background = Color(0xFFF8F9FA)
```

### Tipografía
- Material 3 Typography
- Pesos personalizados
- Tamaños optimizados para legibilidad

## Próximas Mejoras

### 🚀 Funcionalidades Planificadas
- [ ] Integración completa con Firestore
- [ ] Notificaciones push
- [ ] Historial de escaneos
- [ ] Exportación de datos
- [ ] Modo offline
- [ ] Autenticación biométrica

### 🔧 Mejoras Técnicas
- [ ] Tests unitarios
- [ ] Tests de UI
- [ ] CI/CD pipeline
- [ ] Analytics
- [ ] Crashlytics

## Contribución

1. Fork el proyecto
2. Crear rama para feature (`git checkout -b feature/AmazingFeature`)
3. Commit cambios (`git commit -m 'Add AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## Contacto

- **Desarrollador**: [Tu Nombre]
- **Email**: [tu.email@ejemplo.com]
- **Universidad**: Universidad del Valle de Nicaragua

---

**Nota**: Esta aplicación es un prototipo educativo. Para uso en producción, se recomienda implementar medidas de seguridad adicionales y validación exhaustiva de datos.
