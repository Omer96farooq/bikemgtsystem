package com.chotay.bikemgtsystem.model;

import com.chotay.bikemgtsystem.HibernateUtil;
import com.chotay.bikemgtsystem.dao.InvoiceDao;
import com.chotay.bikemgtsystem.entity.Invoice;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

public class InvoiceModel implements InvoiceDao {

    private static Session session;

    @Override
    public ObservableList<Invoice> getInvoices() {

        ObservableList<Invoice> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Invoice> products = session.createQuery("from Invoice").list();
        session.beginTransaction().commit();
        products.stream().forEach(list::add);

        return list;
    }

    @Override
    public Invoice getInvoice(String id) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Invoice invoice = session.get(Invoice.class, id);
        session.getTransaction().commit();

        return invoice;
    }

    @Override
    public void saveInvoice(Invoice invoice) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String date =dtf.format(now);
        invoice.setDate(date);
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(invoice);
        session.getTransaction().commit();
    }

    @Override
    public void deleteCategory(Invoice invoice) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Invoice i = session.get(Invoice.class, invoice.getId());
        session.delete(i);
        session.getTransaction().commit();
    }

}
