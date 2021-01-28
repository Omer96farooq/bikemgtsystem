package com.chotay.bikemgtsystem.dao;

import com.chotay.bikemgtsystem.entity.Sale;
import javafx.collections.ObservableList;

public interface SaleDao {

    public ObservableList<Sale> getSales();
    public Sale getSale(long id);
    public ObservableList<Sale> getSaleByProductId(long id);
    public void saveSale(Sale sale);
    public void updateSale(Sale sale);
    public void deleteSale(Sale sale);
}
