/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.request;

import java.util.List;

/**
 *
 * @author terseer
 */
public class PdfFileRequest {

    String tracking_id;
    Float total_order;
    CustomerRequest customer_data;
  
    public PdfFileRequest() {
    }

    public String getTracking_id() {
        return tracking_id;
    }

    public void setTracking_id(String tracking_id) {
        this.tracking_id = tracking_id;
    }


    public CustomerRequest getCustomer_data() {
        return customer_data;
    }

    public void setCustomer_data(CustomerRequest customer_data) {
        this.customer_data = customer_data;
    }

    public Float getTotal_order() {
        return total_order;
    }

    public void setTotal_order(Float total_order) {
        this.total_order = total_order;
    }
 
 


     

}
