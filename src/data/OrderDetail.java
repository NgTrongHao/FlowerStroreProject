/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author ngtro
 */
public class OrderDetail {
    
    private String orderDetailID;
    private Flower flower;
    private int quantity;
    private double orderDetailTotal;

    public OrderDetail(String orderDetailID, Flower flower, int quantity) {
        this.orderDetailID = orderDetailID;
        this.flower = flower;
        this.quantity = quantity;
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getOrderDetailTotal() {
        return orderDetailTotal = quantity * flower.getUnitPrice();
    }
    
    public void showProfile() {
        String msg;
        msg = String.format("%7s|%6s|%20s|%3d|%6.2f", orderDetailID, flower.getId(), flower.getCategory(), quantity, getOrderDetailTotal());
        System.out.println(msg);
    }
    
    
}
