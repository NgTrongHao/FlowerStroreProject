/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datasaver;

import java.util.HashMap;
import data.Order;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import data.OrderDetail;

/**
 *
 * @author ngtro
 */
public class OrderDataSaver {

    public static void saveOrder(HashMap<String, Order> orders, String fileName) {

        ArrayList<Order> ordersList = new ArrayList<>(orders.values());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            ordersList.forEach(order -> {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    bw.write(stringBuilder.append(order.getOrderID()).append(", ").append(dateFormat.format(order.getOrderDate())).append(", ").append(order.getCustomerName()).toString());
                    bw.newLine();
                    if (!order.getOrderDetails().isEmpty()) {
                        ArrayList<OrderDetail> orderDetailList = new ArrayList<>(order.getOrderDetails().values());
                        stringBuilder.setLength(0);
                        orderDetailList.forEach(orderDetail -> {
                            try {
                                
                                bw.write(stringBuilder.append(orderDetail.getOrderDetailID()).append(", ").append(orderDetail.getFlower().getId()).append(", ").append(orderDetail.getQuantity()).toString());
                                bw.newLine();
                                stringBuilder.setLength(0);
                            } catch (IOException ex) {
                                Logger.getLogger(OrderDataSaver.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                    bw.newLine();
                } catch (IOException ex) {
                    Logger.getLogger(OrderDataSaver.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            bw.close();
            System.out.println("Order data has been saved!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
