package com.chotay.bikemgtsystem.interfaces;

import com.chotay.bikemgtsystem.entity.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface SupplierInterface {
    
    public ObservableList<Supplier> SUPPLIERLIST = FXCollections.observableArrayList();
}
