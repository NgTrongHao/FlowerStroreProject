/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datasaver;

import java.util.Set;
import data.Flower;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

/**
 *
 * @author ngtro
 */
public class FlowerDataSaver {

    public static void saveFlower(Set<Flower> flowers, String fileName) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        flowers.forEach(flower -> flower.showProfile());
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (Flower flower : flowers) {
                StringBuilder stringBuilder = new StringBuilder();
                bw.write(stringBuilder.append(flower.getId()).append(", ").append(flower.getCategory()).append(", ").append(dateFormat.format(flower.getImportDate())).append(", ").append(flower.getUnitPrice()).append(", ").append(flower.getDescription()).toString());
                bw.newLine();
            }
            bw.close();
            System.out.println("Flower data has been saved!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
