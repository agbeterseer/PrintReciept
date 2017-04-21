/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.repository;

import hg.print.recipt.model.TrackOrder;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author terseer
 */
public interface TrackOrderRepository extends CrudRepository<TrackOrder, Long>{
       TrackOrder findByTrackingId(String trackingid);
}
 