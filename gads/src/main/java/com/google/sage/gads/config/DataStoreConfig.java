package com.google.sage.gads.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class DataStoreConfig {

    @Autowired
    AdsConfig adsConfig;

    @Bean
    public Datastore cloudDatastoreService() {
        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream(adsConfig.getCredentialPath()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return DatastoreOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}
