package com.google.sage.gads.test;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.sage.gads.config.AdsConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class DataStoreTester {

    @Autowired
    private AdsConfig adsConfig;


    @Autowired
    private Datastore dataStore;




    @Test
    public void test_hello() throws Exception {
        System.out.print(adsConfig.getCredentialPath());
    }

    @Test
    public void test_datastore() throws Exception {
        // The kind for the new entity
        String kind = "Task";
        // The name/ID for the new entity
        String name = "sampletask1";
        // The Cloud Datastore key for the new entity
        Key taskKey = dataStore.newKeyFactory().setKind(kind).newKey(name);

        // Prepares the new entity
        Entity task = Entity.newBuilder(taskKey).set("description", "Buy milk").build();

        // Saves the entity
        dataStore.put(task);

        System.out.printf("Saved %s: %s%n", task.getKey().getName(), task.getString("description"));

        // Retrieve entity
        Entity retrieved = dataStore.get(taskKey);

        System.out.printf("Retrieved %s: %s%n", taskKey.getName(), retrieved.getString("description"));
    }


    @Test
    public void testCreateBuyer() throws Exception{


    }
}
