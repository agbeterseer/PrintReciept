/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.controller;

import hg.print.recipt.model.Address;
import hg.print.recipt.repository.AddressRepository;
import hg.print.recipt.model.AuditTrail;
import hg.print.recipt.model.Billfrom;
import hg.print.recipt.repository.BillfromRepository;
import hg.print.recipt.model.Customer;
import hg.print.recipt.repository.CustomerAddressMapperRepository;
import hg.print.recipt.repository.CustomerOrderMapperRepository;
import hg.print.recipt.repository.CustomerRepository;
import hg.print.recipt.model.Customer_Address_Mapper;
import hg.print.recipt.model.Customer_Order_Mapper;
import hg.print.recipt.model.Invoice;
import hg.print.recipt.repository.InvoiceRepository;
import hg.print.recipt.model.TrackingOrderDataMapper;
import hg.print.recipt.model.Orderdata;
import hg.print.recipt.repository.OrderdataRepository;
import hg.print.recipt.model.State;
import hg.print.recipt.repository.StateRepository;
import hg.print.recipt.model.TrackOrder;
import hg.print.recipt.request.BillFromRequest;
import hg.print.recipt.request.BillToRequest;
import hg.print.recipt.request.CustomerRequest;
import hg.print.recipt.request.OrderRequest;
import hg.print.recipt.request.PdfFileRequest;
import hg.print.recipt.request.StateRequest;
import hg.print.recipt.response.CustomerResponse;
import hg.print.recipt.route.UrlRoutes;
import hg.print.recipt.service.PdfFileCreatorService;
import hg.print.recipt.util.ErrorCodes;
import hg.print.recipt.response.MessageResponse;
import hg.print.recipt.response.OrderResponse;
import hg.print.recipt.response.PdfFileResponse;
import hg.print.recipt.response.StaticResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import hg.print.recipt.repository.TrackingOrderDataMapperRepository;
import java.util.HashMap;
import java.util.Map;
import hg.print.recipt.repository.TrackOrderRepository;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author terseer
 */
@Controller
public class PrintReceiptController {

    private final PdfFileCreatorService pdfFileCreatorService;
    @Autowired
    OrderdataRepository orderDataRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerOrderMapperRepository customerOrderMapperRepository;

    @Autowired
    CustomerAddressMapperRepository customerAddressMapperRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    BillfromRepository billfromRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    TrackingOrderDataMapperRepository trackingOrderDataMapperRepository;

    @Autowired
    TrackOrderRepository trackOrderRepository;

    @Autowired
    public PrintReceiptController(PdfFileCreatorService pdfFileCreator) {
        this.pdfFileCreatorService = pdfFileCreator;
    }

 
    @RequestMapping(value =UrlRoutes.HD_GENERATE_RECEIPT, method = RequestMethod.GET)
    public String generatePdf(@PathVariable("tracking_id") String tracking_id, Model model) {
        StaticResponse sresponse = new StaticResponse();
        MessageResponse messageResponse = new MessageResponse();
      //  ResponseEntity<Object> olks = new ResponseEntity<>();
           ResponseEntity<Object> expResult = new ResponseEntity<>(HttpStatus.OK);
        TrackOrder trackOrder;
        try {
            trackOrder = trackOrderRepository.findByTrackingId(tracking_id);
         
        } catch (Exception ex) {

            sresponse.setStatus(ErrorCodes.UN_SUCCESSFUL.getDesciption());
            sresponse.setMessage("Tracking ID not Found!!");
            sresponse.setCode(500);
               return "500 Tracking ID not Found!!";
        }
        try{
                 Invoice invoice = invoiceRepository.findByTrackingId(tracking_id);
                String createdDate = invoice.getInvoice_date().toString();
            
            List<TrackingOrderDataMapper> trackingOrderDataMapperList = trackingOrderDataMapperRepository.findAllByTracking(trackOrder);

            for (int i = 0; i < trackingOrderDataMapperList.size(); i++) {
                TrackingOrderDataMapper orb = trackingOrderDataMapperList.get(i);
                Orderdata orderdata = orb.getOrderdata();

                // get tracking_id
           
                //date secion
                model.addAttribute("invoice_num", invoice.getInvoice_number());
                model.addAttribute("created", createdDate);
                model.addAttribute("trackingid", tracking_id);

                // get Customer
                Customer_Order_Mapper customerID = customerOrderMapperRepository.findByOrderdata(orderdata);
                Customer customer = customerID.getCustomer();

                model.addAttribute("first_name", customer.getFirst_name());
                model.addAttribute("last_name", customer.getLast_name());
                model.addAttribute("phone_number", customer.getPhone_number());
                model.addAttribute("email", customer.getEmail());

                Customer_Address_Mapper customer_Address_Mapper = customerAddressMapperRepository.findByCustomer(customer);
                Address address = addressRepository.findOne(customer_Address_Mapper.getId());

                model.addAttribute("address_name", address.getAddress_name());
                model.addAttribute("line_one", address.getStreet_one());
                model.addAttribute("town", address.getCity());
                model.addAttribute("country", address.getCountry_name());
                model.addAttribute("city", address.getState());
                model.addAttribute("postalcode", address.getPostal_code());
            }
            model.addAttribute("orders", getOrders(tracking_id));
            model.addAttribute("total", invoice.getTotal());
           
          
        return "invoice";
        }catch(Exception ex){
        
        return "Error";
        }

    }

    public List<OrderResponse> getOrders(String tracking_id) {

        TrackOrder trackingid = trackOrderRepository.findByTrackingId(tracking_id);
        List<OrderResponse> list = new ArrayList<>();
        List<TrackingOrderDataMapper> order_Data_Mapper = (List<TrackingOrderDataMapper>) trackingOrderDataMapperRepository.findAllByTracking(trackingid);
        List<OrderResponse> orderResponsesList = new ArrayList<>();
        for (int i = 0; i < order_Data_Mapper.size(); i++) {
            //
            OrderResponse orderResponse = new OrderResponse();
            TrackingOrderDataMapper trackOrder = order_Data_Mapper.get(i);

            Orderdata orderData = orderDataRepository.findOne(trackOrder.getOrderdata().getId());
            orderResponse.setDateOfOrder(pdfFileCreatorService.formatTimestamp(orderData.getDateoforder()));
            orderResponse.setDescription(orderData.getDescription());
            orderResponse.setItem(orderData.getItem());
            orderResponse.setLine_total(orderData.getLineTotal());
            orderResponse.setQuantity(orderData.getQuantity());
            orderResponse.setSub_total(orderData.getSubTotal());
            orderResponse.setTax(orderData.getTax());
            orderResponse.setUnit_cost(orderData.getUnitCost());

            orderResponsesList.add(orderResponse);

        }

        return orderResponsesList;
    }

    @RequestMapping(value = UrlRoutes.HD_CREATE_PDF, method = RequestMethod.POST)
    public ResponseEntity<Object> createPdf(@RequestBody String data) {

        StaticResponse sresponse = new StaticResponse();
        MessageResponse messageResponse = new MessageResponse();
        Timestamp dateCreated = new Timestamp(System.currentTimeMillis());
        PdfFileRequest pdfRequest;
        System.out.println("data" + data);
        try {
            pdfRequest = pdfFileCreatorService.fromJson(data, PdfFileRequest.class);

        } catch (Exception ex) {
            ex.printStackTrace();
            sresponse.setStatus(ErrorCodes.UN_SUCCESSFUL.getDesciption());
            sresponse.setMessage("Wrong JSON format");
            sresponse.setCode(ErrorCodes.UN_SUCCESSFUL_MESSAGE.getCode());
            return new ResponseEntity<>(sresponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
      
      //generate Invoice
       
            Customer customer = new Customer();
            CustomerRequest customerRequest = pdfRequest.getCustomer_data();

            if (customerRequest != null) {

                customer.setEmail(customerRequest.getEmail());
                customer.setFirst_name(customerRequest.getFirst_name());
                customer.setLast_name(customerRequest.getLast_name());
                customer.setPhone_number(customerRequest.getPhone_number());
                customerRepository.save(customer);
            }
            // create TrackOrder IDS
            TrackOrder tracking = new TrackOrder();
            tracking.setTrackingId(pdfRequest.getTracking_id());
            trackOrderRepository.save(tracking);
            List<OrderRequest> orderList = pdfRequest.getCustomer_data().getOrder_data();

            List<Orderdata> orderListNew = new ArrayList<>();
            for (OrderRequest order : orderList) {
                Orderdata orderData = new Orderdata();

                orderData.setDescription(order.getDescription());
                orderData.setItem(order.getItem());
                orderData.setLine_total(order.getLine_total());
                orderData.setQuantity(order.getQuantity());
                orderData.setSubTotal(order.getSub_total());
                orderData.setTax(order.getTax());
                //orderData.setTotal(order.getTotal());
                // orderData.setTrackingId(pdfRequest.getTracking_id());
                orderData.setUnitCost(order.getUnit_cost());
                orderData.setDateoforder(new Timestamp(System.currentTimeMillis()));
                orderListNew.add(orderData);
                orderDataRepository.save(orderListNew);

                Customer_Order_Mapper custmerOrderMapper = new Customer_Order_Mapper();
                custmerOrderMapper.setCustomer(customer);
                custmerOrderMapper.setOrderdata(orderData);
                customerOrderMapperRepository.save(custmerOrderMapper);
     
                // mapp order to Invoice
                //Aim : this is to generate one invoice for a customer if he has multiple order
                // with this you can have one item to customer and many items to a customer
                // on one invoice
                TrackingOrderDataMapper trackingorderDataMapper = new TrackingOrderDataMapper();

                trackingorderDataMapper.setTracking(tracking);
                trackingorderDataMapper.setOrderdata(orderData);
                trackingOrderDataMapperRepository.save(trackingorderDataMapper);

                  
            }
            Invoice invoiceGenerate = new Invoice();
            Invoice insd = invoiceRepository.findByTrackingId(pdfRequest.getTracking_id());

            if (insd == null) {
                invoiceGenerate.setInvoice_date(new Timestamp(System.currentTimeMillis()));
                invoiceGenerate.setInvoice_number(pdfFileCreatorService.randomString(10));
                invoiceGenerate.setTrackingId(pdfRequest.getTracking_id());
                invoiceGenerate.setTotal(pdfRequest.getTotal_order());
                invoiceRepository.save(invoiceGenerate);
            } else {
            Invoice remove = invoiceRepository.findByTrackingId(pdfRequest.getTracking_id());
            invoiceRepository.delete(remove);

                sresponse.setStatus(ErrorCodes.UN_SUCCESSFUL.getDesciption());
                sresponse.setMessage("duplicate Tracking ID in Invoice");
                sresponse.setCode(500);
                return new ResponseEntity<>(sresponse, HttpStatus.INTERNAL_SERVER_ERROR);

            }

            BillToRequest billTo = pdfRequest.getCustomer_data().getBill_to();
            System.out.println("BillTo = " + billTo);
            if (billTo != null) {

                Address address = new Address();
                address.setAddress_name(billTo.getAddress_name());
                address.setCity(billTo.getCity());
                address.setCountry_code(billTo.getCountry_code());
                address.setCountry_name(billTo.getCountry_name());
                address.setLatitude(billTo.getLatitude());
                address.setLongitude(billTo.getLongitude());
                address.setPostal_code(billTo.getPostal_code());
                address.setState(billTo.getState());
                address.setStreet_one(billTo.getStreet_one());
                address.setStreet_two(billTo.getStreet_two());

                addressRepository.save(address);

                Customer_Address_Mapper customerAddressMapper = new Customer_Address_Mapper();
                customerAddressMapper.setAddress(address);
                customerAddressMapper.setCustomer(customer);

                customerAddressMapperRepository.save(customerAddressMapper);

            }
 
            messageResponse.setSuccess(Boolean.TRUE);
            messageResponse.setCode(103200);
            messageResponse.setMessage("Successful");
            messageResponse.setData(pdfRequest);
            return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        sresponse.setStatus(ErrorCodes.UN_SUCCESSFUL.getDesciption());
        sresponse.setMessage("Filed to Create Order");
        sresponse.setCode(500);
        return new ResponseEntity<>(sresponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @RequestMapping(value = UrlRoutes.HD_GET_ORDER, method = RequestMethod.GET)
    public ResponseEntity<Object> getOrder(@PathVariable("tracking_id") String tracking_id) {

        StaticResponse sresponse = new StaticResponse();
        MessageResponse messageResponse = new MessageResponse();
        PdfFileResponse pdfFileOrderResponse = new PdfFileResponse();
        try {
            CustomerResponse customerRespnose = new CustomerResponse();
            pdfFileOrderResponse.setTracking_id(tracking_id);

            //get invoice number
            Invoice invoice = invoiceRepository.findByTrackingId(tracking_id);
            pdfFileOrderResponse.setInvoice_date(invoice.getInvoice_date().toString());
            pdfFileOrderResponse.setInvoice_number(invoice.getInvoice_number());
            pdfFileOrderResponse.setTotal_order(invoice.getTotal());

            TrackOrder trackOrder = trackOrderRepository.findByTrackingId(tracking_id);
            List<TrackingOrderDataMapper> trackingOrderDataMapperList = trackingOrderDataMapperRepository.findAllByTracking(trackOrder);

            List<OrderResponse> orderResponsesList = new ArrayList<>();
            for (int i = 0; i < trackingOrderDataMapperList.size(); i++) {

                TrackingOrderDataMapper trackOrderMapper = trackingOrderDataMapperList.get(i);

                Orderdata order = orderDataRepository.findOne(trackOrderMapper.getOrderdata().getId());
                OrderResponse orderResponse = new OrderResponse();

                orderResponse.setDescription(order.getDescription());
                orderResponse.setDateOfOrder(pdfFileCreatorService.formatTimestamp(order.getDateoforder()));
                orderResponse.setItem(order.getItem());
                orderResponse.setLine_total(order.getLineTotal());
                orderResponse.setQuantity(order.getQuantity());
                orderResponse.setSub_total(order.getSubTotal());
                orderResponse.setTax(order.getTax());
                orderResponse.setUnit_cost(order.getUnitCost());

                orderResponsesList.add(orderResponse);
                //add order list to customer
                customerRespnose.setOrder_data(orderResponsesList);

                System.out.println("Pass 2===" + order.getId());

                //add TrackOrder ID
                // get customer  cusotmer ID from Customer_Order_Mapper
                try {

                    Customer_Order_Mapper customerOrderMapper = customerOrderMapperRepository.findByOrderdata(order);

                    Customer customer = customerOrderMapper.getCustomer();

                    System.out.println(customer.getId());
                    System.out.println("Pass 22");
                    Customer customerData = customerRepository.findOne(customerOrderMapper.getCustomer().getId());
                    if (customerData != null) {

                        customerRespnose.setEmail(customerData.getEmail());
                        customerRespnose.setFirst_name(customerData.getFirst_name());
                        customerRespnose.setLast_name(customerData.getLast_name());
                        customerRespnose.setPhone_number(customerData.getPhone_number());
                        
                        // get customer Address
                        Customer_Address_Mapper customerAddressMapper = customerAddressMapperRepository.findByCustomer(customerOrderMapper.getCustomer());
                        System.out.println("Pass 3");
                        Address address = addressRepository.findOne(customerAddressMapper.getAddress().getId());

                        BillToRequest billToRequest = new BillToRequest();

                        billToRequest.setAddress_name(address.getAddress_name());
                        billToRequest.setCity(address.getCity());
                        billToRequest.setCountry_code(address.getCountry_code());
                        billToRequest.setCountry_name(address.getCountry_name());
                        billToRequest.setLatitude(address.getLatitude());
                        billToRequest.setLongitude(address.getLongitude());
                        billToRequest.setPostal_code(address.getPostal_code());
                        billToRequest.setState(address.getState());
                        billToRequest.setStreet_one(address.getStreet_one());
                        billToRequest.setStreet_two(address.getStreet_two());

                        customerRespnose.setBill_to(billToRequest);

                        pdfFileOrderResponse.setCustomer_data(customerRespnose);
                        System.out.println("Pass 333");
                       
                        
                    }
                    
       
                    
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }

            messageResponse.setSuccess(Boolean.TRUE);
            messageResponse.setMessage("successful");
            messageResponse.setCode(103200);
            messageResponse.setData(pdfFileOrderResponse);

            return new ResponseEntity<>(messageResponse, HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        sresponse.setStatus(ErrorCodes.UN_SUCCESSFUL.getDesciption());
        sresponse.setMessage("Filed to Created Order");
        sresponse.setCode(500);
        return new ResponseEntity<>(sresponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    //@ResponseBody
    public String helloRest() {
        return "invoice";
    }
}
