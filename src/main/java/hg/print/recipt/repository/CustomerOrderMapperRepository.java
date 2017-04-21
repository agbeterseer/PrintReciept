/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.repository;

import hg.print.recipt.model.Customer;
import hg.print.recipt.model.Customer_Order_Mapper;
import hg.print.recipt.model.Orderdata;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author terseer
 */
public interface CustomerOrderMapperRepository extends CrudRepository<Customer_Order_Mapper, Long> {

    public Customer_Order_Mapper findByCustomerId(Customer customer);

    public Customer_Order_Mapper findByOrderdata(Orderdata orderdate);
}
