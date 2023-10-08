/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ngtro
 */
public class Flower {
    private String id, description;
    private Date importDate;
    private double unitPrice;
    private String category;

    public Flower(String id, String description, Date importDate, double unitPrice, String category) {
        this.id = id;
        this.description = description;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public void showProfile () {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String msg;
        msg = String.format("%6s|%10s|%10s|%8.2f|%-50s|", id, category, dateFormat.format(importDate), unitPrice, description);
        System.out.println(msg);
    }
}
