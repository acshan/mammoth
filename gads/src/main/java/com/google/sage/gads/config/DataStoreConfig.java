package com.google.sage.gads.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@Slf4j
public class DataStoreConfig {

    @Autowired
    AdsConfig adsConfig;

    @Bean
    public Datastore cloudDatastoreService() {
        GoogleCredentials credentials = null;
        if(StringUtils.isEmpty(adsConfig.getCredentialPath())){
            return DatastoreOptions.getDefaultInstance().getService();
        }
        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream(adsConfig.getCredentialPath()));
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
        return DatastoreOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}
