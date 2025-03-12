package org.example;

import org.osgeo.proj4j.*;

import java.util.ArrayList;
import java.util.List;

public class DegZel {
    private final String nameOgnSreds;
    private final String nameZel;
    private final double dolgota;
    private final double shirota;

    public DegZel(String nameOgnSreds, String nameZel, double x, double y) {
        this.nameOgnSreds = nameOgnSreds;
        this.nameZel = nameZel;
        List<Double> mas = refactorXYtoBL(x, y);
        this.dolgota = mas.get(0);
        this.shirota = mas.get(1);
    }

    public static List<Double> refactorXYtoBL(double x, double y) {
        List<Double> list = new ArrayList();
        x += 5300000;
        if (y > 50000) {
            y += 7300000;
        } else {
            y += 7400000;
        }
// Создаем исходную и целевую системы координат
        CRSFactory factory = new CRSFactory();
        CoordinateReferenceSystem srcCRS = factory.createFromName("EPSG:28407");
        CoordinateReferenceSystem dstCRS = factory.createFromName("EPSG:4326");

        // Создаем объект для преобразования координат
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CoordinateTransform transform = ctFactory.createTransform(srcCRS, dstCRS);

        // Преобразуем координаты
        //сначала вводим долготу потом широту
        ProjCoordinate srcCoord = new ProjCoordinate(y, x);
        ProjCoordinate dstCoord = new ProjCoordinate();
        transform.transform(srcCoord, dstCoord);

        // Выводим результат(наоборот x->y)
        //System.out.println("Преобразованные координаты: " + dstCoord.x + ", " + dstCoord.y);
        //возвращаем масив b, l
        list.add(dstCoord.y);
        list.add(dstCoord.x);
        return list;
    }

    public String getNameOgnSreds() {
        return nameOgnSreds;
    }

    public String getNameZel() {
        return nameZel;
    }

    public double getDolgota() {
        return dolgota;
    }

    public double getShirota() {
        return shirota;
    }
}
