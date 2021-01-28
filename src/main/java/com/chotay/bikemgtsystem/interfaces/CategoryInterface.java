package com.chotay.bikemgtsystem.interfaces;

import com.chotay.bikemgtsystem.entity.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface CategoryInterface {
    
    public ObservableList<Category> CATEGORYLIST = FXCollections.observableArrayList();   
}
