package com.chotay.bikemgtsystem.interfaces;

import com.chotay.bikemgtsystem.entity.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface PurchaseInterface {
    
    public ObservableList<Purchase> PURCHASELIST = FXCollections.observableArrayList();
}
