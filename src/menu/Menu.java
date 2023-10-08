/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import java.util.ArrayList;
import util.TryCatch;

/**
 *
 * @author ngtro
 */
public class Menu {

    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList<>();

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public void addNewOption(String optionTitle) {
        optionList.add(optionTitle);
    }

    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("There has not any option in menu");
            return;
        }

        System.out.println("\n---------------" + menuTitle + "---------------");
        for (String option : optionList) {
            System.out.println(option);
        }
    }
    public int maxOption() {
        return optionList.size();
    }
    public int getChoice() {
        int maxOption = optionList.size();
        return TryCatch.getAnInteger("Choose [1.." + maxOption + "]: ", "Just input from 1 to " + maxOption, 1, maxOption);
    }
}
