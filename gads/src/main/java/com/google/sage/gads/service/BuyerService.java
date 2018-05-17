package com.google.sage.gads.service;

import com.google.sage.gads.model.Buyer;

public interface BuyerService {

    Buyer getBuyer(String code);

    void createBuyer(Buyer buyer);

    void deleteBuyer(String code);

    void updateBuyer(String code, Buyer buyer);
}
