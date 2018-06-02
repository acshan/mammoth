package com.google.sage.gads.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@Slf4j
public class FirebaseConfig {


    @Autowired
    private AdsConfig config;


    private final String fbServiceAccountPath = "/Users/shanq/Work/Personal/Cloud/credential/sage-fb-01-7915d5ad00c4.json";

    @Bean
    public FirebaseApp getFirebaseOptions() {
        GoogleCredentials googleCredentials = null;
        try {

            InputStream serviceAccount = new FileInputStream(fbServiceAccountPath);
            googleCredentials = GoogleCredentials.fromStream(serviceAccount);
//           googleCredentials = GoogleCredentials.getApplicationDefault();
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(googleCredentials)
                .setProjectId(config.getFirebaseProjectId())
                .build();

        return FirebaseApp.initializeApp(options);
//        return FirestoreClient.getFirestore();
    }

}
