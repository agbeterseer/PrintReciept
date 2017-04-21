/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.repository;

import hg.print.recipt.model.Customer;
import hg.print.recipt.model.Customer_Address_Mapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author terseer
 */
@Transactional
public interface CustomerAddressMapperRepository extends CrudRepository<Customer_Address_Mapper, Long>{
    
      public Customer_Address_Mapper findByCustomer(Customer customer);
}
