/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataloader;

import java.util.Set;
import data.Flower;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author ngtro
 */
public class FlowerDataLoader {
    public static Set <Flower> loadFlower (String fileName) {
        Set <Flower> flowers = new HashSet<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String line = br.readLine();
            String arr [];
            while (line != null) {
                arr = line.split(", ");
                String id = arr[0].trim();
                String category = arr[1].trim();
                Date importDate = format.parse(arr[2].trim());
                double unitPrice = Double.parseDouble(arr[3].trim());
                String description = arr[4].trim();
                
                Flower flower = new Flower(id, description, importDate, unitPrice, category);
                
                flowers.add(flower);
                
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return flowers;
    }
}
