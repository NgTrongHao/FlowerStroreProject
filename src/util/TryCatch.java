/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ngtro
 */
public class TryCatch {

    private static Scanner sc = new Scanner(System.in);

    public static int getAnInteger(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n;
        if (lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;

        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n >= lowerBound && n <= upperBound) {
                    return n;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getADouble(String inputMsg, String errorMsg) {
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getID(String inputMsg, String errorMsg, String format) {
        String id;
        boolean match;
        while (true) {
            try {
                System.out.print(inputMsg);
                id = sc.nextLine().toUpperCase();
                match = id.matches(format);
                if (id.length() == 0 || id.isEmpty() || match == false) {
                    throw new Exception();
                } else {
                    return id;
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg) {
        String s;
        while (true) {
            try {
                System.out.print(inputMsg);
                s = sc.nextLine().trim();
                if (s.length() == 0 || s.isEmpty()) {
                    throw new Exception();
                } else {
                    return s;
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg, String format) {
        String s;
        boolean match;
        while (true) {
            try {
                System.out.print(inputMsg);
                s = sc.nextLine().trim().toUpperCase();
                match = s.matches(format);
                if (s.length() == 0 || s.isEmpty() || match == false) {
                    throw new Exception();
                } else {
                    return s;
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static Date getDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        while (true) {
            try {
                Date date = format.parse(sc.nextLine().trim());
                return date;
            } catch (Exception e) {
                System.out.println("Invalid date format");
            }
        }
    }

    public static Date getDate(String inputMsg, String errorMsg) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        while (true) {
            try {
                System.out.print(inputMsg);
                Date date = format.parse(sc.nextLine().trim());
                return date;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

}
