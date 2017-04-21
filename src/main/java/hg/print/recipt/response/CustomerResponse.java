/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.response;

import hg.print.recipt.request.BillToRequest;
import java.util.List;

/**
 *
 * @author terseer
 */
public class CustomerResponse {
    String first_name;
    String last_name;
    String phone_number;
    String email;
    //OrderResponse  order_data;
    List<OrderResponse>  order_data;
    BillToRequest bill_to;
   

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BillToRequest getBill_to() {
        return bill_to;
    }

    public void setBill_to(BillToRequest bill_to) {
        this.bill_to = bill_to;
    }
    
    

    public List<OrderResponse> getOrder_data() {
        return order_data;
    }

    public void setOrder_data(List<OrderResponse> order_data) {
        this.order_data = order_data;
    }

//    public OrderResponse getOrder_data() {
//        return order_data;
//    }
//
//    public void setOrder_data(OrderResponse order_data) {
//        this.order_data = order_data;
//    }

//    public Float getTotal() {
//        return total;
//    }
//
//    public void setTotal(Float total) {
//        this.total = total;
//    }

  
}
