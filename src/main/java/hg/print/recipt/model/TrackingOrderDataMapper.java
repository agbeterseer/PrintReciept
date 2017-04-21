/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.model;

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
@Table(name="tracking_order_mapper")
public class TrackingOrderDataMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Orderdata orderdata;
    
    @OneToOne
    private TrackOrder tracking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orderdata getOrderdata() {
        return orderdata;
    }

    public void setOrderdata(Orderdata orderdata) {
        this.orderdata = orderdata;
    }

    public TrackOrder getTracking() {
        return tracking;
    }

    public void setTracking(TrackOrder tracking) {
        this.tracking = tracking;
    }
 
    
    
}
