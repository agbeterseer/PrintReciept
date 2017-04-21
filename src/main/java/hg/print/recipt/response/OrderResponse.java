/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.response;

import java.sql.Timestamp;

/**
 *
 * @author terseer
 */
public class OrderResponse {

    String item;
    String description;
    Integer quantity;
    Float unit_cost;
    Float line_total;
    Float sub_total;
    Float tax;
   // Float total;
    private String date_of_order;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(Float unit_cost) {
        this.unit_cost = unit_cost;
    }

    public Float getLine_total() {
        return line_total;
    }

    public void setLine_total(Float line_total) {
        this.line_total = line_total;
    }

    public Float getSub_total() {
        return sub_total;
    }

    public void setSub_total(Float sub_total) {
        this.sub_total = sub_total;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }
//
//    public Float getTotal() {
//        return total;
//    }
//
//    public void setTotal(Float total) {
//        this.total = total;
//    }

    public String getDateOfOrder() {
        return date_of_order;
    }

    public void setDateOfOrder(String date_of_order) {
        this.date_of_order = date_of_order;
    }

}
