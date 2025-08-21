# Configuración de Firebase para Univalle NIC ID

## Pasos para Configurar Firebase

### 1. Crear Proyecto en Firebase Console

1. Ve a [Firebase Console](https://console.firebase.google.com/)
2. Haz clic en "Crear un proyecto"
3. Nombre del proyecto: `univallenicid-app`
4. Habilita Google Analytics (opcional)
5. Selecciona tu cuenta de Google Analytics
6. Haz clic en "Crear proyecto"

### 2. Configurar Android App

1. En la consola de Firebase, haz clic en el ícono de Android
2. Package name: `com.example.univallenicid`
3. Nickname: `Univalle NIC ID`
4. Debug signing certificate SHA-1: (opcional para desarrollo)
5. Haz clic en "Registrar app"

### 3. Descargar google-services.json

1. Descarga el archivo `google-services.json`
2. Colócalo en el directorio `app/` de tu proyecto
3. **IMPORTANTE**: No subas este archivo a Git (ya está en .gitignore)

### 4. Habilitar Firestore Database

1. En la consola de Firebase, ve a "Firestore Database"
2. Haz clic en "Crear base de datos"
3. Selecciona "Comenzar en modo de prueba"
4. Selecciona la ubicación más cercana (ej: `us-central1`)
5. Haz clic en "Listo"

### 5. Configurar Reglas de Seguridad

En Firestore Database > Reglas, configura las siguientes reglas:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Permitir acceso a estudiantes solo si están autenticados
    match /students/{studentId} {
      allow read, write: if request.auth != null && 
        (request.auth.uid == studentId || 
         request.auth.token.role == 'admin');
    }
    
    // Permitir acceso a asignaturas
    match /subjects/{subjectId} {
      allow read: if request.auth != null;
      allow write: if request.auth != null && 
        request.auth.token.role == 'admin';
    }
    
    // Permitir acceso a docentes
    match /teachers/{teacherId} {
      allow read: if request.auth != null;
      allow write: if request.auth != null && 
        request.auth.token.role == 'admin';
    }
  }
}
```

### 6. Habilitar Authentication

1. En la consola de Firebase, ve a "Authentication"
2. Haz clic en "Comenzar"
3. Habilita "Correo electrónico/contraseña"
4. Configura las opciones adicionales según necesites

### 7. Estructura de Datos en Firestore

#### Colección: students
```json
{
  "studentId": "2024-001",
  "fullName": "María José González López",
  "email": "maria.gonzalez@estudiantes.univalle.edu.ni",
  "career": "INGENIERIA_SISTEMAS",
  "modality": "DIURNO",
  "status": "ACTIVO",
  "password": "hashed_password",
  "qrCode": "UNIVALLE|2024-001|María José González López|INGENIERIA_SISTEMAS|ACTIVO",
  "createdAt": "2024-01-01T00:00:00Z",
  "updatedAt": "2024-01-01T00:00:00Z"
}
```

#### Colección: subjects
```json
{
  "id": "subject_id",
  "code": "IS-101",
  "name": "Programación I",
  "credits": 4,
  "career": "INGENIERIA_SISTEMAS",
  "teacherId": "teacher_id",
  "students": ["2024-001", "2024-002"],
  "semester": "2024-1"
}
```

#### Colección: teachers
```json
{
  "id": "teacher_id",
  "name": "Dr. Carlos Mendoza",
  "email": "carlos.mendoza@univalle.edu.ni",
  "specialization": "Programación Avanzada",
  "subjects": ["subject_id_1", "subject_id_2"]
}
```

### 8. Configurar Variables de Entorno (Opcional)

Para mayor seguridad, puedes usar variables de entorno:

1. Crea un archivo `.env` en la raíz del proyecto:
```env
FIREBASE_PROJECT_ID=univallenicid-app
FIREBASE_API_KEY=your_api_key_here
```

2. Agrega `.env` a `.gitignore`

### 9. Configurar ProGuard (Para Release)

En `app/proguard-rules.pro`, agrega:

```proguard
# Firebase
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# ZXing
-keep class com.google.zxing.** { *; }
-keep class com.journeyapps.** { *; }
```

### 10. Verificar Configuración

1. Sincroniza el proyecto en Android Studio
2. Ejecuta la aplicación
3. Verifica que puedas conectarte a Firebase
4. Revisa los logs para errores de conexión

## Solución de Problemas

### Error: "No matching client found for package name"
- Verifica que el package name en `google-services.json` coincida con tu `build.gradle.kts`
- Asegúrate de que el archivo esté en el directorio correcto

### Error: "Permission denied"
- Verifica las reglas de Firestore
- Asegúrate de que el usuario esté autenticado
- Revisa los permisos de la aplicación

### Error: "Network security config"
- Verifica que `network_security_config.xml` esté configurado correctamente
- Asegúrate de que los dominios de Firebase estén incluidos

### Error: "QR Code generation failed"
- Verifica que las dependencias de ZXing estén incluidas
- Asegúrate de que los permisos de cámara estén configurados

## Configuración para Producción

### 1. Cambiar a Modo de Producción en Firestore
1. Ve a Firestore Database > Reglas
2. Cambia las reglas para producción
3. Deshabilita el modo de prueba

### 2. Configurar Analytics
1. Habilita Google Analytics en Firebase
2. Configura eventos personalizados
3. Monitorea el uso de la aplicación

### 3. Configurar Crashlytics
1. Habilita Crashlytics en Firebase
2. Agrega las dependencias necesarias
3. Configura el reporte de crashes

### 4. Configurar Performance Monitoring
1. Habilita Performance Monitoring
2. Monitorea el rendimiento de la aplicación
3. Optimiza según los datos

## Seguridad Adicional

### 1. Implementar Autenticación Biométrica
```kotlin
// Agregar dependencia
implementation("androidx.biometric:biometric:1.1.0")
```

### 2. Implementar Cifrado Local
```kotlin
// Usar EncryptedSharedPreferences para datos sensibles
implementation("androidx.security:security-crypto:1.1.0-alpha06")
```

### 3. Implementar Certificate Pinning
```xml
<!-- En network_security_config.xml -->
<domain-config>
    <domain includeSubdomains="true">firebase.google.com</domain>
    <pin-set expiration="2024-12-31">
        <pin digest="SHA-256">your_certificate_pin_here</pin>
    </pin-set>
</domain-config>
```

## Monitoreo y Mantenimiento

### 1. Logs de Firebase
- Revisa los logs en Firebase Console
- Configura alertas para errores críticos
- Monitorea el uso de recursos

### 2. Actualizaciones de Dependencias
- Mantén las dependencias actualizadas
- Revisa las notas de lanzamiento
- Prueba las actualizaciones en desarrollo

### 3. Backup de Datos
- Configura backups automáticos en Firestore
- Exporta datos regularmente
- Mantén copias de seguridad

---

**Nota**: Esta configuración es para desarrollo. Para producción, implementa medidas de seguridad adicionales y revisa las mejores prácticas de Firebase.
