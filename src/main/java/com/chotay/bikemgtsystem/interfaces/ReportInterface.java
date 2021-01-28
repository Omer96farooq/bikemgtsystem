package com.chotay.bikemgtsystem.interfaces;

import com.chotay.bikemgtsystem.entity.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface ReportInterface {
 
    public ObservableList<Invoice> REPORTLIST = FXCollections.observableArrayList();
}
