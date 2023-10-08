/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

/**
 *
 * @author ngtro
 */
public class Order {
    private String orderID;
    private Date orderDate;
    private String customerName;
    private TreeMap<String, OrderDetail> orderDetails = new TreeMap<>();

    public Order(String orderID, Date orderDate, String customerName) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerName = customerName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public TreeMap<String, OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(TreeMap<String, OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    public double getOrderTotal() {
        double orderTotal = 0;
        for (OrderDetail orderDetail : orderDetails.values()) {
            orderTotal += orderDetail.getOrderDetailTotal();
        }
        return orderTotal;
    }
    
    public void showOrder() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String msg;
        msg = String.format("%3s|%10s|%20s|%3d|%8.2f", orderID, dateFormat.format(orderDate), customerName, orderDetails.size(), getOrderTotal());
        System.out.println(msg);
    }
    
}
