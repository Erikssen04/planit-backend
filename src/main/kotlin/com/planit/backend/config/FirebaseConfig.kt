package com.planit.backend.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Configuration
import java.io.ByteArrayInputStream
import java.io.FileInputStream

@Configuration
class FirebaseConfig {
    init {

        /*val credentialsPath = System.getenv("FIREBASE_CREDENTIALS_PATH")
            ?: throw IllegalStateException("FIREBASE_CREDENTIALS_PATH environment variable is not set")

        val serviceAccount = FileInputStream(credentialsPath)

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()*/

        val firebaseConfig = System.getenv("FIREBASE_CREDENTIALS_JSON")
            ?: throw IllegalStateException("FIREBASE_CREDENTIALS_JSON environment variable is not set")

        val credentials = GoogleCredentials.fromStream(
            ByteArrayInputStream(firebaseConfig.toByteArray(Charsets.UTF_8))
        )

        val options = FirebaseOptions.builder()
            .setCredentials(credentials)
            .build()

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options) // inicializa Firebase solo si no está ya inicializado
        }
    }
}
