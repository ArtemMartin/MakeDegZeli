package org.example;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ManagerDegZeli {
    private final Map<String, String> mapDegZeli = new HashMap<>();
    private final Map<String, XY> mapZeli = new HashMap<>();
    private final Map<String, DegZel> mapPozZel = new HashMap<>();

    public ManagerDegZeli() {
        //читаем файл деж целей
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\YO_NA\\degZeli.txt")))) {
            String line;
            String[] mass;
            while ((line = reader.readLine()) != null) {
                mass = line.split(",");
                mapDegZeli.put(StringUtils.strip(mass[0], "\"").trim()
                        , StringUtils.strip(mass[1], "\"").trim());
            }
            System.out.println("Файл деж цели прочитан...");
        } catch (IOException ex) {
            myLogger(ex.getMessage());
        }
        //читаем файл цели
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\YO_NA\\Zeli")))) {
            String line;
            String[] mas;
            while ((line = reader.readLine()) != null) {
                mas = line.split(",");
                mapZeli.put(StringUtils.strip(mas[0], "\"")
                        , new XY(Double.parseDouble(StringUtils.strip(mas[1], "\""))
                                , Double.parseDouble(StringUtils.strip(mas[2], "\""))));
            }
            System.out.println("Файл Zeli прочитан");
        } catch (IOException e) {
            myLogger(e.getMessage());
        }
        sformirDegZelSKoord();
    }

    private static void myLogger(String e) {
        Logger.getLogger(ManagerDegZeli.class.getName()).info("Шляпа: " + e);
    }

    public Map<String, DegZel> getMapPozZel() {
        return mapPozZel;
    }

    public void sformirDegZelSKoord() {
        String nZeli;
        for (String name : mapDegZeli.keySet()) {
            try {
                nZeli = mapDegZeli.get(name);
                mapPozZel.put(name, new DegZel(name
                        , nZeli
                        , mapZeli.get(nZeli).getX()
                        , mapZeli.get(nZeli).getY()));
            } catch (NullPointerException ex) {
                myLogger(ex.getMessage());
            }
        }
    }

}
