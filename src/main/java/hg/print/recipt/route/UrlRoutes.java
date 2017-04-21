/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.route;

/**
 *
 * @author terseer
 */
public class UrlRoutes {

    public static final String HD_CREATE_PDF = "/api/v1/receipt/create";
    public static final String HD_GENERATE_RECEIPT = "/api/v1/generate/receipt/{tracking_id}";
    public static final String HD_GET_ORDER = "/api/v1/receipt/{tracking_id}";
    
}

























