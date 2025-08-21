# 🔥 Guía Detallada: Conectar Firebase a tu App Android

## 📋 **Prerrequisitos**
- Tener una cuenta de Google
- Tener Android Studio instalado
- Tener tu proyecto Android funcionando

---

## 🚀 **Paso 1: Crear Proyecto en Firebase Console**

### 1.1 Ir a Firebase Console
1. Abre tu navegador web
2. Ve a: https://console.firebase.google.com/
3. Inicia sesión con tu cuenta de Google

### 1.2 Crear Nuevo Proyecto
1. Haz clic en **"Crear un proyecto"** o **"Add project"**
2. Escribe el nombre: `UnivalleNICID` (o el que prefieras)
3. Haz clic en **"Continuar"**
4. **Desactiva** Google Analytics por ahora (opcional)
5. Haz clic en **"Crear proyecto"**
6. Espera a que se cree el proyecto
7. Haz clic en **"Continuar"**

---

## 📱 **Paso 2: Agregar App Android al Proyecto**

### 2.1 Agregar App Android
1. En la página principal de Firebase, haz clic en el ícono de **Android** (🟢)
2. En **"Nombre del paquete Android"** escribe: `com.example.univallenicid`
3. En **"Apodo de la app"** escribe: `UnivalleNICID`
4. **Deja vacío** "Certificado de firma de depuración SHA-1" por ahora
5. Haz clic en **"Registrar app"**

### 2.2 Descargar google-services.json
1. Firebase te mostrará un archivo `google-services.json`
2. **IMPORTANTE**: Haz clic en **"Descargar google-services.json"**
3. Guarda este archivo en tu computadora
4. Haz clic en **"Continuar"**

### 2.3 Colocar el archivo en tu proyecto
1. Abre tu proyecto en Android Studio
2. En el panel izquierdo, cambia la vista a **"Project"** (no Android)
3. Navega a: `app/` (carpeta)
4. **Copia y pega** el archivo `google-services.json` en la carpeta `app/`
5. **IMPORTANTE**: El archivo debe estar en: `app/google-services.json`

---

## 🔧 **Paso 3: Configurar Gradle**

### 3.1 Configurar build.gradle (Project)
1. En Android Studio, abre el archivo `build.gradle` (el del proyecto, no el de app)
2. En la sección `plugins`, agrega esta línea:
```gradle
id("com.google.gms.google-services") version "4.4.0" apply false
```
3. Haz clic en **"Sync Now"** cuando aparezca

### 3.2 Configurar build.gradle (App)
1. Abre el archivo `app/build.gradle.kts`
2. En la sección `plugins`, agrega:
```kotlin
id("com.google.gms.google-services")
```
3. En la sección `dependencies`, agrega:
```kotlin
// Firebase
implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
implementation("com.google.firebase:firebase-firestore")
implementation("com.google.firebase:firebase-auth")
implementation("com.google.firebase:firebase-analytics")
```
4. Haz clic en **"Sync Now"**

---

## 🗄️ **Paso 4: Configurar Firestore Database**

### 4.1 Crear Base de Datos
1. En Firebase Console, en el menú izquierdo, haz clic en **"Firestore Database"**
2. Haz clic en **"Crear base de datos"**
3. Selecciona **"Comenzar en modo de prueba"** (para desarrollo)
4. Selecciona la ubicación más cercana (ej: `us-central1`)
5. Haz clic en **"Habilitar"**

### 4.2 Configurar Reglas de Seguridad
1. En Firestore Database, ve a la pestaña **"Reglas"**
2. Reemplaza las reglas existentes con:
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Permitir lectura y escritura para usuarios autenticados
    match /{document=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```
3. Haz clic en **"Publicar"**

---

## 🔐 **Paso 5: Configurar Authentication**

### 5.1 Habilitar Authentication
1. En Firebase Console, en el menú izquierdo, haz clic en **"Authentication"**
2. Haz clic en **"Comenzar"**
3. En la pestaña **"Sign-in method"**, haz clic en **"Email/Password"**
4. **Activa** "Email/Password"
5. Haz clic en **"Guardar"**

### 5.2 Crear Usuarios de Prueba
1. En Authentication, ve a la pestaña **"Users"**
2. Haz clic en **"Add user"**
3. Crea algunos usuarios de prueba:
   - Email: `2024-001@univalle.edu.ni`, Password: `123456`
   - Email: `2024-002@univalle.edu.ni`, Password: `123456`
   - etc.

---

## 📊 **Paso 6: Crear Estructura de Datos en Firestore**

### 6.1 Crear Colecciones
1. En Firestore Database, ve a la pestaña **"Datos"**
2. Haz clic en **"Iniciar colección"**
3. Crea las siguientes colecciones:

#### Colección: `students`
- Documento ID: `2024-001`
- Campos:
  - `fullName`: `María José González López`
  - `email`: `maria.gonzalez@estudiantes.univalle.edu.ni`
  - `career`: `INGENIERIA_SISTEMAS`
  - `modality`: `DIURNO`
  - `status`: `ACTIVO`
  - `studentId`: `2024-001`

#### Colección: `subjects`
- Documento ID: `SIS-101`
- Campos:
  - `code`: `SIS-101`
  - `name`: `Programación I`
  - `credits`: `4`
  - `career`: `INGENIERIA_SISTEMAS`
  - `teacherName`: `Dr. Carlos Mendoza`

#### Colección: `teachers`
- Documento ID: `T001`
- Campos:
  - `name`: `Dr. Carlos Mendoza`
  - `email`: `carlos.mendoza@univalle.edu.ni`
  - `specialty`: `Ingeniería de Software`

---

## 💻 **Paso 7: Modificar el Código para Usar Firebase**

### 7.1 Crear FirebaseRepository
Crea un nuevo archivo: `app/src/main/java/com/example/univallenicid/data/FirebaseRepository.kt`

```kotlin
package com.example.univallenicid.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseRepository {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    
    // Autenticación
    suspend fun authenticate(studentId: String, password: String): AuthResponse {
        return try {
            val email = "$studentId@univalle.edu.ni"
            val result = auth.signInWithEmailAndPassword(email, password).await()
            
            if (result.user != null) {
                val student = getStudentById(studentId)
                if (student != null) {
                    AuthResponse(success = true, student = student)
                } else {
                    AuthResponse(success = false, message = "Estudiante no encontrado")
                }
            } else {
                AuthResponse(success = false, message = "Credenciales inválidas")
            }
        } catch (e: Exception) {
            AuthResponse(success = false, message = "Error de autenticación: ${e.message}")
        }
    }
    
    // Obtener estudiante
    suspend fun getStudentById(studentId: String): Student? {
        return try {
            val document = firestore.collection("students")
                .document(studentId)
                .get()
                .await()
            
            if (document.exists()) {
                document.toObject(Student::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
    
    // Cerrar sesión
    fun logout() {
        auth.signOut()
    }
}
```

### 7.2 Modificar MainViewModel
En `MainViewModel.kt`, cambia la línea:
```kotlin
private val repository = DataRepository()
```
Por:
```kotlin
private val repository = FirebaseRepository()
```

---

## 🧪 **Paso 8: Probar la Conexión**

### 8.1 Compilar y Ejecutar
1. En Android Studio, haz clic en **"Build"** → **"Make Project"**
2. Si no hay errores, haz clic en **"Run"** (botón verde)
3. Selecciona tu dispositivo o emulador
4. La app debería instalarse y ejecutarse

### 8.2 Probar Login
1. Usa las credenciales que creaste en Firebase
2. Ejemplo: `2024-001` y contraseña `123456`
3. Deberías poder hacer login exitosamente

---

## 🔍 **Paso 9: Verificar en Firebase Console**

### 9.1 Verificar Authentication
1. En Firebase Console → Authentication → Users
2. Deberías ver el usuario que acabas de crear

### 9.2 Verificar Firestore
1. En Firebase Console → Firestore Database → Datos
2. Deberías ver las colecciones y documentos que creaste

---

## ⚠️ **Solución de Problemas Comunes**

### Error: "google-services.json not found"
- Asegúrate de que el archivo esté en `app/google-services.json`
- Sincroniza el proyecto (File → Sync Project with Gradle Files)

### Error: "Firebase not initialized"
- Verifica que el plugin de Google Services esté en ambos build.gradle
- Limpia y reconstruye el proyecto (Build → Clean Project)

### Error: "Permission denied"
- Verifica las reglas de Firestore
- Asegúrate de que el usuario esté autenticado

### Error: "Network error"
- Verifica tu conexión a internet
- Asegúrate de que Firebase esté disponible en tu región

---

## 📚 **Recursos Adicionales**

- [Documentación oficial de Firebase](https://firebase.google.com/docs)
- [Guía de Android](https://firebase.google.com/docs/android/setup)
- [Firestore Tutorial](https://firebase.google.com/docs/firestore/quickstart)

---

## 🎯 **Próximos Pasos**

1. **Implementar más funcionalidades** con Firebase
2. **Configurar reglas de seguridad** más específicas
3. **Agregar más tipos de autenticación** (Google, Facebook, etc.)
4. **Implementar notificaciones push** con Firebase Cloud Messaging
5. **Configurar Analytics** para monitorear el uso de la app

---

## 💡 **Consejos para Principiantes**

1. **Empieza simple**: No intentes implementar todo de una vez
2. **Prueba cada paso**: Verifica que cada configuración funcione antes de continuar
3. **Usa el modo de prueba**: Para desarrollo, usa reglas permisivas
4. **Lee los logs**: Android Studio te mostrará errores útiles
5. **Haz copias de seguridad**: Guarda tu código en Git

¡Con estos pasos tendrás Firebase completamente configurado en tu app! 🚀
