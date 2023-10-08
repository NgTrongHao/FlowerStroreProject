/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import dataloader.FlowerDataLoader;
import dataloader.OrderDataLoader;
import datasaver.FlowerDataSaver;
import datasaver.OrderDataSaver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import util.TryCatch;

/**
 *
 * @author ngtro
 */
public class Store {

    public Set<Flower> flowers = new HashSet<>();
    public HashMap<String, Order> orders = new HashMap<>();

    public boolean loadData() {
        flowers.addAll(FlowerDataLoader.loadFlower("Flower.txt"));
        orders.putAll(OrderDataLoader.loadOrder("Order.txt"));
        System.out.println("Flower data has been loaded");
        for (Flower flower : flowers) {
            flower.showProfile();
        }
        System.out.println("");
        System.out.println("Order data has been loaded!");
        for (Order order : orders.values()) {
            order.showOrder();
        }
        return true;
    }

    public void saveData() {
        FlowerDataSaver.saveFlower(flowers, "Flower.txt");
        OrderDataSaver.saveOrder(orders, "Order.txt");
    }

    public boolean addAFlower() {
        String id;
        String description;
        Date importDate;
        double unitPrice;
        String category;

        Flower existFlower;
        do {
            id = TryCatch.getID("Input flower ID: ", "This field is required!", "^[0-9]{6}$");
            existFlower = findFLowerByID(id);
            if (existFlower != null) {
                System.out.println("This flower ID existed!");
            }
        } while (existFlower != null);
        description = TryCatch.getString("Input flower description: ", "This field is required from 3-50 character!", "^[a-zA-Z0-9\\s]{3,50}$");
        importDate = TryCatch.getDate("Input import date (dd/mm/yyyy): ", "This field is required!");
        unitPrice = TryCatch.getADouble("Input flower unit price: ", "This field is required!");
        category = TryCatch.getString("Input flower's category: ", "This field is required!");
        Flower flower = new Flower(id, description, importDate, unitPrice, category);

        flowers.add(flower);

        return true;
    }

    public void findFlower() {
        String input;
        input = TryCatch.getString("Input flower's name or flower's ID that you want to find: ", "This field is required!");
        if (input.matches("^[0-9]{6}$")) {
            findFLowerByID(input).showProfile();
        } else {
            findFlowerByName(input).forEach(flower -> flower.showProfile());
        }
    }

    public void updateFlower() {
        String flowerID;
        Flower foundFlower;
        do {
            flowerID = TryCatch.getID("Input flower's ID that you want to update: ", "This field is required!", "^[0-9]{6}$");
            foundFlower = findFLowerByID(flowerID);
            if (foundFlower == null) {
                System.out.println("Flower not found!");
            } else {
                foundFlower.showProfile();
            }
        } while (foundFlower == null);
        String input = TryCatch.getString("What field you want to update (ID, Category, Description, ImportDate, UnitPrice): ", "This field is required!");
        if (input.equalsIgnoreCase("ID")) {
            Flower update;
            do {
                String inputID = TryCatch.getID("Input updating ID: ", "This field is required!", "^[0-9]{6}$");
                update = findFLowerByID(inputID);
                if (update != null) {
                    System.out.println("This flower's ID existed!");
                } else {
                    foundFlower.setId(inputID);
                }
            } while (update != null);
        } else if (input.equalsIgnoreCase("Category")) {
            String inputCategory = TryCatch.getString("Input updating flower's category: ", "This field is required!");
            foundFlower.setCategory(inputCategory);
        } else if (input.equalsIgnoreCase("Description")) {
            String inputDescription = TryCatch.getString("Input updating description: ", "This field is required from 3-50 character!", "^[a-zA-Z0-9]{3,50}$");
            foundFlower.setDescription(inputDescription);
        } else if (input.equalsIgnoreCase("UnitPrice")) {
            double inputUnitPrice = TryCatch.getAnInteger("Input updating price: ", "This field is required!");
            foundFlower.setUnitPrice(inputUnitPrice);
        } else {
            System.out.println("Update Fail!");
        }
        foundFlower.showProfile();
    }

    public boolean deleteAFlower() {
        String id = TryCatch.getID("Input flower's ID that you want to delete: ", "This field is required!", "^[0-9]{6}$");
        Flower foundFlower = findFLowerByID(id);

        if (foundFlower == null) {
            System.out.println("This flower does not exit!");
            return false;
        } else {
            Set<OrderDetail> existFlowerInOrder = new HashSet<>();
            for (Order order : orders.values()) {
                for (OrderDetail orderDetail : order.getOrderDetails().values()) {
                    if (orderDetail.getFlower().getId().equalsIgnoreCase(id)) {
                        existFlowerInOrder.add(orderDetail);
                    }
                }
            }
            if (existFlowerInOrder.isEmpty()) {
                foundFlower.showProfile();
                String confirmation = TryCatch.getString("Are you sure to delete this flower? (YES | NO): ", "This field is required!");
                if (confirmation.equalsIgnoreCase("YES")) {
                    flowers.remove(foundFlower);
                    return true;
                }
            } else {
                System.out.println("Cannot Delete - This flower has existed in:");
                for (OrderDetail x : existFlowerInOrder) {
                    x.showProfile();
                }
                return false;
            }
        }
        return false;
    }

    public void addAnOrder() {
        String orderID;
        Date orderDate;
        String customerName;
        String input;
        Order order;

        Order foundOrder;
        do {
            orderID = TryCatch.getID("Input order ID (xxx): ", "This field is required!(xxx)", "^[0-9]{3}$");
            foundOrder = findOrderByID(orderID);
        } while (foundOrder != null);
        orderDate = TryCatch.getDate("Input order date: ", "This field is required!");
        customerName = TryCatch.getString("Input customer's name: ", "This field is required!");
        order = new Order(orderID, orderDate, customerName);
        orders.put(orderID, order);
        do {
            String orderDetailID;
            String flowerID;
            Flower flower;
            int quantity;
            boolean existOrderDetail;
            do {
                orderDetailID = TryCatch.getID("Input order detail ID (" + orderID + "-xxx): ", "This field is required! (" + orderID + "-xxx)", "^" + orderID + "-\\d{3}$");
                existOrderDetail = order.getOrderDetails().containsKey(orderDetailID);
                if (existOrderDetail == true) {
                    System.out.println("This order detail ID is existed!");
                }
            } while (existOrderDetail == true);
            flowers.forEach((Flower flowerInList) -> {
                flowerInList.showProfile();
            });
            do {
                flowerID = TryCatch.getID("Input flower ID: ", "This field is required!", "^[0-9]{6}$");
                flower = findFLowerByID(flowerID);
            } while (flower == null);

            quantity = TryCatch.getAnInteger("Input " + flower.getCategory() + "'s quantity: ", "This field is required!");

            OrderDetail orderDetail = new OrderDetail(orderDetailID, flower, quantity);
            order.getOrderDetails().put(orderDetailID, orderDetail);
            order.showOrder();
            order.getOrderDetails().values().forEach(OrderDetail::showProfile);

            input = TryCatch.getString("Do you want to add more? (YES|NO): ", "This field is required!");
        } while (input.equalsIgnoreCase("YES"));
    }

    public void displayOrder() {
        Date startDate = TryCatch.getDate("Input start date order of sorting (dd/mm/yyyy): ", "This field is required!");
        Date endDate = TryCatch.getDate("Input end date order of sorting (dd/mm/yyyy): ", "This field is required!");
        orders.values().forEach((Order order) -> {
            if (order.getOrderDate().compareTo(startDate) >= 0 && order.getOrderDate().compareTo(endDate) <= 0) {
                order.showOrder();
            }
        });

    }

    public void sortOrderByAsc() {
        ArrayList<Order> orderList = new ArrayList(orders.values());
        String input = TryCatch.getString("What field you want to sort? (OrderID, OrderDate, CustomerName, OrderTotal): ", "This field is required!");

        if (input.equalsIgnoreCase("OrderID")) {
            Comparator<Order> orderIDComparator = (Order order1, Order order2) -> order1.getOrderID().compareTo(order2.getOrderID());
            Collections.sort(orderList, orderIDComparator);
            System.out.println("The ascending sorting list by order ID:");
            for (Order order : orderList) {
                order.showOrder();
            }

        } else if (input.equalsIgnoreCase("OrderDate")) {
            Comparator<Order> orderDateComparator = (Order order1, Order order2) -> order1.getOrderDate().compareTo(order2.getOrderDate());
            Collections.sort(orderList, orderDateComparator);
            System.out.println("The asceding sorting list by order date:");
            for (Order order : orderList) {
                order.showOrder();
            }

        } else if (input.equalsIgnoreCase("CustomerName")) {
            Comparator<Order> orderCustomerNameComparator = (Order order1, Order order2) -> order1.getCustomerName().compareTo(order2.getCustomerName());
            Collections.sort(orderList, orderCustomerNameComparator);
            System.out.println("The ascending sorting list by customer's name:");
            for (Order order : orderList) {
                order.showOrder();
            }

        } else if (input.equalsIgnoreCase("OrderTotal")) {
            Comparator<Order> orderTotalComparator = (Order order1, Order order2) -> Double.compare(order1.getOrderTotal(), order2.getOrderTotal());
            Collections.sort(orderList, orderTotalComparator);
            System.out.println("The ascending sorting list by order's total:");
            for (Order order : orderList) {
                order.showOrder();
            }
        }
    }

    public void sortOrderByDesc() {
        ArrayList<Order> orderList = new ArrayList(orders.values());
        String input = TryCatch.getString("What field you want to sort? (OrderID, OrderDate, CustomerName, OrderTotal): ", "This field is required!");

        if (input.equalsIgnoreCase("OrderID")) {
            Comparator<Order> orderIDComparator = (Order order1, Order order2) -> order2.getOrderID().compareTo(order1.getOrderID());
            Collections.sort(orderList, orderIDComparator);
            System.out.println("The descending sorting list by order ID:");
            for (Order order : orderList) {
                order.showOrder();
            }

        } else if (input.equalsIgnoreCase("OrderDate")) {
            Comparator<Order> orderDateComparator = (Order order1, Order order2) -> order2.getOrderDate().compareTo(order1.getOrderDate());
            Collections.sort(orderList, orderDateComparator);
            System.out.println("The descending sorting list by order date:");
            for (Order order : orderList) {
                order.showOrder();
            }

        } else if (input.equalsIgnoreCase("CustomerName")) {
            Comparator<Order> orderCustomerNameComparator = (Order order1, Order order2) -> order2.getCustomerName().compareTo(order1.getCustomerName());
            Collections.sort(orderList, orderCustomerNameComparator);
            System.out.println("The descending sorting list by customer's name:");
            for (Order order : orderList) {
                order.showOrder();
            }

        } else if (input.equalsIgnoreCase("OrderTotal")) {
            Comparator<Order> orderTotalComparator = (Order order1, Order order2) -> Double.compare(order2.getOrderTotal(), order1.getOrderTotal());
            Collections.sort(orderList, orderTotalComparator);
            System.out.println("The descending sorting list by order's total:");
            for (Order order : orderList) {
                order.showOrder();
            }
        }
    }

    public Flower findFLowerByID(String input) {
        Flower foundFlower = flowers.stream()
                .filter((flower) -> flower.getId().equalsIgnoreCase(input))
                .findFirst()
                .orElse(null);

        return foundFlower;
    }

    public Set<Flower> findFlowerByName(String input) {
        Set<Flower> foundFlowerSet = flowers.stream()
                .filter(flower -> flower.getCategory().toUpperCase().contains(input.toUpperCase()))
                .collect(Collectors.toSet());

        return foundFlowerSet;
    }

    public Order findOrderByID(String input) {
        Order foundOrder = orders.get(input);
        return foundOrder;
    }
    
    public Set <Flower> findFlowerByImportDate (Date input) {
        Set <Flower> foundFLower = flowers.stream()
                .filter(flower -> flower.getImportDate().equals(input))
                .collect(Collectors.toSet());
        return foundFLower;
    }
    
    public void findFlowerListByImportDate() {
        Date input = TryCatch.getDate("Input ImportDate: ", "This field is required!");
        Set <Flower> flowersByOrderDate = findFlowerByImportDate(input);
        flowersByOrderDate.forEach(flower -> {
            flower.showProfile();
        });
    }

}
