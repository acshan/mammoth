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
        buyer.setCode("JD");
        buyer.setLandingUrl("https://union-click.jd.com/sem.php?source=google-union&unionId=262767352&siteId=googleunion_270088008894&to=https://pro.m.jd.com/mall/active/2HGPbaLYUZczDBViCnvfhPGcNqDq/index.html");
        buyer.setImageUrl("jd618.png");
        buyer.setName("jd");
        buyerService.createBuyer(buyer);
    }


    @Test
    public void getBuyer() throws Exception{
        Buyer buyer = buyerService.getBuyer("KFC");
        log.info(buyer.toString());
    }
}
