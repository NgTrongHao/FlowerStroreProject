/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataloader;

import java.util.HashMap;
import data.Order;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import data.OrderDetail;
import java.util.Set;
import data.Flower;

/**
 *
 * @author ngtro
 */
public class OrderDataLoader {

    public static HashMap<String, Order> loadOrder(String fileName) {
        HashMap<String, Order> orders = new HashMap<>();
        Set<Flower> flowers = dataloader.FlowerDataLoader.loadFlower("Flower.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String [] arr;
            String line = br.readLine();
            String orderIDData = null;

            while (line != null) {
                arr = line.split(", ");
                if (line.isEmpty()) {
                    orderIDData = null;
                } else {
                    Order order;
                    OrderDetail orderDetail;
                    Flower foundFlower;
                    if (arr.length == 3 && orderIDData == null) {
                        String orderID = arr[0].trim();
                        Date orderDate = format.parse(arr[1].trim());
                        String customerName = arr[2].trim();
                        order = new Order(orderID, orderDate, customerName);
                        orders.put(orderID, order);
                        orderIDData = orderID;
                    } else if (arr.length == 3 && orderIDData != null) {
                        
                        String orderDetailID = arr[0].trim();
                        String flowerID = arr[1].trim();
                        foundFlower = flowers.stream()
                                .filter(flower -> flower.getId().equalsIgnoreCase(flowerID))
                                .findFirst()
                                .orElse(null);

                        int quantity = Integer.parseInt(arr[2].trim());
                        orderDetail = new OrderDetail(orderDetailID, foundFlower, quantity);
                        orders.get(orderIDData).getOrderDetails().put(orderDetailID, orderDetail);
                    }
                }
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return orders;
    }
}
