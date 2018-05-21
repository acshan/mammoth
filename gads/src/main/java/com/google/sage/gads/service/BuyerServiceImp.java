package com.google.sage.gads.service;

import com.google.cloud.datastore.*;
import com.google.sage.gads.model.Buyer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class BuyerServiceImp implements BuyerService {
    private final String nameField="name";
    private final String imageUrlField="imageUrl";
    private final String landingUrlField="langdingUrl";

    private final String dataStoreKind = "AdsBuyer";

    @Autowired
    Datastore datastore;

    private KeyFactory userKeyFactory;

    @PostConstruct
    public void initializeKeyFactories() {
        log.info("Initializing key factories");
        userKeyFactory = datastore.newKeyFactory().setKind(dataStoreKind);
    }


    public void createBuyer(Buyer buyer) {
        datastore.put(createBuyerEntity(buyer));
    }

//    public Batch.Response createUser(BatchUser users) {
//        Batch batch = datastore.newBatch();
//        for (User user : users.getUsers()) {
//            batch.put(createUserEntity(user));
//        }
//
//        return batch.submit();
//    }

    private Entity createBuyerEntity(Buyer buyer) {
        Key key = userKeyFactory.newKey(buyer.getCode());
        return Entity.newBuilder(key)
                .set(imageUrlField, buyer.getImageUrl())
                .set(landingUrlField, buyer.getLandingUrl())
                .set(nameField, buyer.getName())
                .build();
    }


    public Buyer getBuyer(String code) {
        Entity buyerEntity = datastore.get(userKeyFactory.newKey(code));
        return entityToBuyer(buyerEntity);
    }


    @Async
    public void updateBuyer(String id, Buyer buyer) {
        //
    }

    @Async
    public void deleteBuyer(String id) {
        //
    }


    private Buyer entityToBuyer(Entity entity){
        Buyer buyer = new Buyer();
        buyer.setCode(entity.getKey().toString());
        buyer.setImageUrl(entity.getString(imageUrlField));
        buyer.setLandingUrl(entity.getString(landingUrlField));
        buyer.setName(entity.getString(nameField));
        return buyer;
    }


}
