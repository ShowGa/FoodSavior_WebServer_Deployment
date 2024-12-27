package com.foodwastesavior.webapp.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

@Configuration
public class FirebaseInitialization {
    @Value("${FIREBASE_CREDENTIALS}")
    private String firebaseCredentialsJson;

    @PostConstruct
    public void initialization() {
        try {
            InputStream serviceAccount = new ByteArrayInputStream(firebaseCredentialsJson.getBytes());

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
