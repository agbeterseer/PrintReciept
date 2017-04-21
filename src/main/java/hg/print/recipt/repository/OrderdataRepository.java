/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.repository;

import hg.print.recipt.model.Orderdata;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author terseer
 */
@Transactional
public interface OrderdataRepository extends CrudRepository<Orderdata, Long> {

    public Orderdata findByTrackingId(String tracking_id);
    
    public List<Orderdata> findAllByTrackingId(String tracking_id);

}
