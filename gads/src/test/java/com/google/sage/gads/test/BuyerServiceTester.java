package com.google.sage.gads.test;


import com.google.sage.gads.model.Buyer;
import com.google.sage.gads.service.BuyerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class BuyerServiceTester {

    @Autowired
    BuyerService buyerService;


    @Test
    public void createBuyer() throws Exception {

        Buyer buyer = new Buyer();
        buyer.setCode("KFC");
        buyer.setLandingUrl("http://www.google.com");
        buyer.setImageUrl("kfc.png");
        buyer.setName("KFC");
        buyerService.createBuyer(buyer);
    }


    @Test
    public void getBuyer() throws Exception{
        Buyer buyer = buyerService.getBuyer("KFC");
        log.info(buyer.toString());
    }
}
