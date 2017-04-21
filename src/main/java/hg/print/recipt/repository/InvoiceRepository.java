/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.repository;

import hg.print.recipt.model.Invoice;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author terseer
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Long>{
        Invoice findByTrackingId(String tracking_id);
}
