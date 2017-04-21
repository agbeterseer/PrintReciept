/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.response;

/**
 *
 * @author terseer
 */
public class PdfFileResponse {

    String invoice_date;
    String invoice_number;
    String tracking_id;
    CustomerResponse customer_data;
    Float total_order;

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getTracking_id() {
        return tracking_id;
    }

    public void setTracking_id(String tracking_id) {
        this.tracking_id = tracking_id;
    }

    public CustomerResponse getCustomer_data() {
        return customer_data;
    }

    public void setCustomer_data(CustomerResponse customer_data) {
        this.customer_data = customer_data;
    }

    public Float getTotal_order() {
        return total_order;
    }

    public void setTotal_order(Float total_order) {
        this.total_order = total_order;
    }

 
   

 
}
