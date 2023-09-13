package br.ada.customer.crud.model;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

public class Sale {
    private Customer customer;
    private List<Product> products;
    private Integer idSelling;
    private DateFormat dateOfRequest;
    private String paymentMethod;
    private Boolean payment;

    public Sale(String paymentMethod,List<Product> products, Customer customer) {
        this.paymentMethod = paymentMethod;
        this.products = products;
        this.customer = customer;
        this.idSelling = getId() + 1;
        this.dateOfRequest = DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt","BR"));
    }

    public Integer getId() {
        return idSelling;
    }

    public void setId(Integer idSelling) {
        this.idSelling = idSelling;
    }

    public DateFormat getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(DateFormat dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }
}
