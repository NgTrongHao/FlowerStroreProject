/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package flowerstroreproject;

import data.Store;
import menu.Menu;
import util.TryCatch;
/**
 *
 * @author ngtro
 */
public class FlowerStroreProject {
    
    public static void main(String[] args) {
        FlowerStrore();
    }
    
    
    public static void FlowerStrore () {
        
        Store store = new Store();
        
        Menu flowerStore = new Menu("FLOWER STORE MENU");
        flowerStore.addNewOption("1. Manage Flower");
        flowerStore.addNewOption("2. Manage Order");
        flowerStore.addNewOption("3. Print Flower List");
        flowerStore.addNewOption("4. Print Order List");
        flowerStore.addNewOption("5. Load Data From File");
        flowerStore.addNewOption("6. Save Data To File");
        flowerStore.addNewOption("7. EXIT!");
        
        int choice;
        do {            
            flowerStore.printMenu();
            choice = flowerStore.getChoice();
            switch (choice) {
                case 1:
                    manageFlower(store);
                    break;
                case 2:
                    manageOrder(store);
                    break;
                case 3:
                    store.flowers.forEach(flower -> flower.showProfile());
                    break;
                case 4:
                    store.orders.values().forEach(order -> order.showOrder());
                    break;
                case 5:
                    if(store.loadData() == true) {
                        System.out.println("Load Data Success");
                    } else {
                        System.out.println("Load Data Fail");
                    }
                    break;
                case 6:
                    store.saveData();
                    break;
                
            }
        } while (choice != flowerStore.maxOption());
    }
    
    public static void manageFlower (Store store) {
        
        Menu manageFlowerMenu = new Menu("Manage Flower");
        manageFlowerMenu.addNewOption("1. Add a flower");
        manageFlowerMenu.addNewOption("2. Find a flower");
        manageFlowerMenu.addNewOption("3. Update a flower");
        manageFlowerMenu.addNewOption("4. Delete a flower");
        manageFlowerMenu.addNewOption("5. List flower by import date");
        manageFlowerMenu.addNewOption("6. Back to Flower Store Menu");
        
        int flowerChoice;
        do {            
            manageFlowerMenu.printMenu();
            flowerChoice = manageFlowerMenu.getChoice();
            switch (flowerChoice) {
                case 1:
                    if(store.addAFlower() == true) {
                        System.out.println("Add success!");
                    } else {
                        System.out.println("Add fail!!!");
                    }
                    break;
                case 2:
                    store.findFlower();
                    break;
                case 3:
                    store.updateFlower();
                    break;
                case 4:
                    if(store.deleteAFlower() == true) {
                        System.out.println("Delete success!");
                    } else {
                        System.out.println("Delete fail!!!");
                    }
                    break;
                case 5:
                    store.findFlowerListByImportDate();
                    break;
            }
        } while (flowerChoice != manageFlowerMenu.maxOption());
    }
    
    public static void manageOrder(Store store) {
        
        Menu manageOrderMenu = new Menu("Manage Order");
        manageOrderMenu.addNewOption("1. Add an order");
        manageOrderMenu.addNewOption("2. Display orders");
        manageOrderMenu.addNewOption("3. Sort order");
        manageOrderMenu.addNewOption("4. Back to Flower Store Menu");
        
        int orderChoice;
        do {            
            manageOrderMenu.printMenu();
            orderChoice = manageOrderMenu.getChoice();
            switch (orderChoice) {
                case 1:
                    store.addAnOrder();
                    break;
                case 2:
                    store.displayOrder();
                    break;
                case 3:
                    String input = TryCatch.getString("Input your choice (ASC|DESC)", "This field is required!");
                    if (input.equalsIgnoreCase("ASC")) {
                        store.sortOrderByAsc();
                    } else if (input.equalsIgnoreCase("DESC")) {
                        store.sortOrderByDesc();
                    }
                    break;
            }
        } while (orderChoice != manageOrderMenu.maxOption());
    }
    
}
