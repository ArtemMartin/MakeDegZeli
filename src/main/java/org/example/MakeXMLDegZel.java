package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MakeXMLDegZel {
    private final Map<String, DegZel> mapNameOSZel;
    private final String[] nameADn = new String[]{"Aksai1", "Aksai2", "Aksai3", "Aksai4", "Aksai5", "Aksai6"
            , "Kalitva1", "Kalitva2", "Kalitva3", "Kalitva4", "Kalitva5", "Kalitva6"
            , "Oskol1", "Oskol2", "Oskol3", "Oskol4", "Oskol5", "Oskol6"
            , "Elan1", "Elan2", "Elan3", "Elan4"};
    private final String[] nameGSADn = new String[]{"Desna1", "Desna2", "Desna3", "Desna4", "Desna5", "Desna6"
            , "Samara1", "Samara2", "Samara3", "Samara4", "Samara5", "Samara6"
            , "Koren1", "Koren2", "Koren3", "Koren4", "Koren5", "Koren6"};
    private final String[] nameREADn = new String[]{"Morava1", "Morava2", "Morava3", "Morava4", "Morava5", "Morava6"
            , "Tisa1", "Tisa2", "Tisa3", "Tisa4", "Tisa5", "Tisa6"
            , "Izvor1", "Izvor2", "Izvor3", "Izvor4", "Izvor5", "Izvor6"};
    private Map<String, DegZel> mapADn = new HashMap<>();
    private Map<String, DegZel> mapGSADn = new HashMap<>();
    private Map<String, DegZel> mapREADn = new HashMap<>();

    public MakeXMLDegZel() {
        ManagerDegZeli managerDegZeli = new ManagerDegZeli();
        this.mapNameOSZel = managerDegZeli.getMapPozZel();
        razdelitPoDivizion();
        cleanDirectory("D:\\YO_NA\\MakeDegZeli\\ADn\\");
        createFileKML(mapADn, "D:\\YO_NA\\MakeDegZeli\\ADn\\", "ADn.png");
        System.out.println("АДн отработано...");
        cleanDirectory("D:\\YO_NA\\MakeDegZeli\\GSADn\\");
        createFileKML(mapGSADn, "D:\\YO_NA\\MakeDegZeli\\GSADn\\", "GSADn.png");
        System.out.println("ГСАДн отработано...");
        cleanDirectory("D:\\YO_NA\\MakeDegZeli\\READn\\");
        createFileKML(mapREADn, "D:\\YO_NA\\MakeDegZeli\\READn\\", "READn.png");
        System.out.println("РЕАДн отработано...");
        System.out.println("Успешно!!!");
        pausa();
    }

    private void pausa() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            myLogger(e.getMessage());
        }
    }

    private static void cleanDirectory(String dirName) {
        Path pathToDelete = Paths.get(dirName);
        try {
            Files.walkFileTree(pathToDelete, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (exc != null) throw exc;
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException ex) {
            myLogger(ex.getMessage());
        }
    }

    private static void myLogger(String e) {
        Logger.getLogger(MakeXMLDegZel.class.getName()).info("Шляпа: " + e);
    }

    private void razdelitPoDivizion() {
        vuclenitYkazanDivizion(mapADn, nameADn);
        vuclenitYkazanDivizion(mapGSADn, nameGSADn);
        vuclenitYkazanDivizion(mapREADn, nameREADn);
    }

    private void vuclenitYkazanDivizion(Map<String, DegZel> mapDivizion, String[] masName) {
        for (String nameOS : mapNameOSZel.keySet()) {
            for (String name : masName) {
                if (name.equals(nameOS)) mapDivizion.put(name, mapNameOSZel.get(name));
            }
        }
    }

    public void createFileKML(Map<String, DegZel> mapDivizion, String path, String pngFile) {
        for (String name : mapDivizion.keySet()) {
            File file = new File(path + name + ".kml");
            try {
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<kml xmlns=\"http://earth.google.com/kml/2.2\">\n" +
                        "  <Document>\n" +
                        "    <Placemark>\n");
                writer.write("<name>" + name + "</name>\n");
                writer.write(" <description></description>\n" +
                        "      <Style>\n" +
                        "        <LabelStyle>\n" +
                        "          <color>FF00FFFF</color>\n" +
                        "          <scale>1.36363636363636</scale>\n" +
                        "        </LabelStyle>\n" +
                        "        <IconStyle>\n" +
                        "          <scale>0.625</scale>\n" +
                        "          <Icon>\n");
                writer.write("<href>files/" + pngFile + "</href>\n");
                writer.write("</Icon>\n" +
                        "          <hotSpot x=\"0.5\" y=\"0.5\" xunits=\"fraction\" yunits=\"fraction\"/>\n" +
                        "        </IconStyle>\n" +
                        "      </Style>\n" +
                        "      <Point>\n" +
                        "        <extrude>1</extrude>\n");
                writer.write("<coordinates>" + mapDivizion.get(name).getShirota()
                        + "," + mapDivizion.get(name).getDolgota()
                        + "," + 0 + " </coordinates>\n");
                writer.write(" </Point>\n" +
                        "        </Placemark>\n" +
                        "      </Document>\n" +
                        "    </kml>");
                writer.close();
            } catch (IOException e) {
                myLogger(e.getMessage());
            }
        }
    }


}
