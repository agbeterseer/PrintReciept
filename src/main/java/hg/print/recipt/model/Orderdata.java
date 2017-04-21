/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author terseer
 */
@Entity
@Table(name="orderdata")
public class Orderdata {

//    @OneToOne
//    private Customer_Order_Mapper customer_order_mapper;
 private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String item;
    @Column
    private String description;
    @Column
    private Integer quantity;
    @Column
    private Float unitCost;
    @Column
    private Float lineTotal;
    @Column
    private Float subTotal;
    @Column
    private Float tax;
    @Column
    private Float total;
     
    @Column(unique = true)
    private String trackingId;
    
    @Column
    private Timestamp dateoforder;

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

    public Float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Float unit_cost) {
        this.unitCost = unit_cost;
    }

    public Float getLineTotal() {
        return lineTotal;
    }

    public void setLine_total(Float lineTotal) {
        this.lineTotal = lineTotal;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float sub_total) {
        this.subTotal = sub_total;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

 

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public Timestamp getDateoforder() {
        return dateoforder;
    }

    public void setDateoforder(Timestamp dateoforder) {
        this.dateoforder = dateoforder;
    }
   
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}