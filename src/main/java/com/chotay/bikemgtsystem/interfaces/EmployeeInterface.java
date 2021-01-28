package com.chotay.bikemgtsystem.interfaces;

import com.chotay.bikemgtsystem.entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface EmployeeInterface {
    
    public ObservableList<Employee> EMPLOYEELIST = FXCollections.observableArrayList();
}
