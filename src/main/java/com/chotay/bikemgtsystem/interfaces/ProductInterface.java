package com.chotay.bikemgtsystem.interfaces;

import com.chotay.bikemgtsystem.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface ProductInterface {
    
    public ObservableList<Product> PRODUCTLIST = FXCollections.observableArrayList();   
}
