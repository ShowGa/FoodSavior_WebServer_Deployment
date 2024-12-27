package com.foodwastesavior.webapp.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Configuration
public class FirebaseInitialization {
    @PostConstruct
    public void initialization() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/foodwastesavior-firebase-adminsdk-fiu5y-46643389df.json");

            System.out.println("see if the firebase json file was loaded : " + serviceAccount);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

            FirebaseApp.initializeApp(options);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
