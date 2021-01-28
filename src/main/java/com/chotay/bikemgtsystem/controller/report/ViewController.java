package com.chotay.bikemgtsystem.controller.report;

import com.chotay.bikemgtsystem.entity.Invoice;
import com.chotay.bikemgtsystem.pdf.PrintInvoice;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

public class ViewController implements Initializable {
    
    @FXML
    private TextField employeeField, totalField, vatField, discountField, payableField, paidField, returnedField;
    @FXML
    private Label serialLabel, dateLabel;
    private Invoice invoice;
    private PrintInvoice printInvoice;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void setReport(Invoice invoice){
        this.invoice = invoice;
        setData();
    }
    
    private void setData() {
        serialLabel.setText("Transcation ID# " + invoice.getId());
        dateLabel.setText("Date: " + invoice.getDate());
        employeeField.setText(invoice.getEmployee().getUserName());
        totalField.setText(String.valueOf(invoice.getTotal()));
        vatField.setText(String.valueOf(invoice.getVat()));
        discountField.setText(String.valueOf(invoice.getDiscount()));
        payableField.setText(String.valueOf(invoice.getPayable()));
        paidField.setText(String.valueOf(invoice.getPaid()));
        returnedField.setText(String.valueOf(invoice.getReturned()));
    }
    
    @FXML
    public void handlePrint(ActionEvent event) {
      try {
          PrintService mPrinter = null;
          Boolean bFoundPrinter = false;

          PrintService[] printServices = PrinterJob.lookupPrintServices();

          for (PrintService printService : printServices) {
              String sPrinterName = printService.getName();
              if (sPrinterName.equals("Black Cobra")) {
                  mPrinter = printService;
                  bFoundPrinter = true;
              }
          }
          String testData = invoice+"\f";
          try (InputStream is = new ByteArrayInputStream(testData.getBytes())) {
              DocFlavor flavor =  DocFlavor.INPUT_STREAM.AUTOSENSE   ;
              
              PrintService service = PrintServiceLookup.lookupDefaultPrintService();
              System.out.println(service);
              
              DocPrintJob job = service.createPrintJob();
              Doc doc= new SimpleDoc(is, flavor, null);
              
              PrintJobWatcher pjDone = new PrintJobWatcher(job);
              
              job.print(doc, null);
              
              pjDone.waitForDone();
          }
      } catch (PrintException | IOException e) {
          e.printStackTrace();
      } 
    }
    
    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    static class PrintJobWatcher {
      boolean done = false;

      PrintJobWatcher(DocPrintJob job) {
          // Add a listener to the print job
          job.addPrintJobListener(new PrintJobAdapter() {
              @Override
              public void printJobCanceled(PrintJobEvent pje) {
                  allDone();
              }
              @Override
              public void printJobCompleted(PrintJobEvent pje) {
                  allDone();
              }
              @Override
              public void printJobFailed(PrintJobEvent pje) {
                  allDone();
              }
              @Override
              public void printJobNoMoreEvents(PrintJobEvent pje) {
                  allDone();
              }
              void allDone() {
                  synchronized (PrintJobWatcher.this) {
                      done = true;
                      PrintJobWatcher.this.notify();
                  }
              }
          });
      }
      public synchronized void waitForDone() {
          try {
              while (!done) {
                  wait();
              }
          } catch (InterruptedException e) {
          }
      }
  }

}
