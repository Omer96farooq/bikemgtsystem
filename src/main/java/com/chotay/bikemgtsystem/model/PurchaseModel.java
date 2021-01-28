package com.chotay.bikemgtsystem.model;

import com.chotay.bikemgtsystem.HibernateUtil;
import com.chotay.bikemgtsystem.dao.PurchaseDao;
import com.chotay.bikemgtsystem.entity.Purchase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

public class PurchaseModel implements PurchaseDao {

    private static Session session;
    
    @Override
    public ObservableList<Purchase> getPurchases() {
        
        ObservableList<Purchase> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Purchase> products = session.createQuery("from Purchase").list();
        session.beginTransaction().commit();
        products.stream().forEach(list::add);

        return list;
    }

    @Override
    public Purchase getPurchase(long id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Purchase purchase = session.get(Purchase.class, id);
        session.getTransaction().commit();

        return purchase;
    }

    @Override
    public void savePurchase(Purchase purchase) {
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String date =dtf.format(now);
        purchase.setDate(date);
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(purchase);
        session.getTransaction().commit();
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String date =dtf.format(now);
        purchase.setDate(date);
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Purchase p = session.get(Purchase.class, purchase.getId());
        p.setProduct(purchase.getProduct());
        p.setSupplier(purchase.getSupplier());
        p.setQuantity(purchase.getQuantity());
        p.setDate(purchase.getDate());
        session.getTransaction().commit();
    }

    @Override
    public void deletePurchase(Purchase purchase) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Purchase p = session.get(Purchase.class, purchase.getId());
        session.delete(p);
        session.getTransaction().commit();
    }
    
}
