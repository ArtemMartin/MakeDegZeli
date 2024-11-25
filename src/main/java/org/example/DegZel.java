package org.example;

public class DegZel {
    private final String nameOgnSreds;
    private final String nameZel;
    private final double dolgota;
    private final double shirota;

    public DegZel(String nameOgnSreds, String nameZel, double x, double y) {
        this.nameOgnSreds = nameOgnSreds;
        this.nameZel = nameZel;
        double[] mas = getBLPoint(x, y);
        this.dolgota = mas[0];
        this.shirota = mas[1];
    }

    public double[] getBLPoint(double x, double y) {
        x += 5300000;
        if (y > 99999) {
            y += 7400000;
        } else {
            y += 7300000;
        }
        x += getDX(y);
        y += -117.0;
        int nZonu = (int) Math.round(y * Math.pow(10.0, -6.0));
        double b = x / 6367558.4968;
        double B0 = b + Math.sin(2.0 * b) * (0.00252588685 - 1.49186E-5 * Math.pow(Math.sin(b), 2.0) + 1.1904E-7 * Math.pow(Math.sin(b), 4.0));
        double z0 = (y - (double) (10 * nZonu + 5) * Math.pow(10.0, 5.0)) / (6378245.0 * Math.cos(B0));
        double dB = -Math.pow(z0, 2.0) * Math.sin(2.0 * B0) * (0.251684631 - 0.003369263 * Math.pow(Math.sin(B0), 2.0) + 1.1276E-5 * Math.pow(Math.sin(B0), 4.0) - Math.pow(z0, 2.0) * 0.10500614 - 0.04559916 * Math.pow(Math.sin(B0), 2.0) + 0.00228901 * Math.pow(Math.sin(B0), 4.0) - 2.987E-5 * Math.pow(Math.sin(B0), 6.0) - Math.pow(z0, 2.0) * (0.042858 - 0.025318 * Math.pow(Math.sin(B0), 2.0) + 0.014346 * Math.pow(Math.sin(B0), 4.0) - 0.001264 * Math.pow(Math.sin(B0), 6.0) - Math.pow(z0, 2.0) * (0.01672 - 0.0063 * Math.pow(Math.sin(B0), 2.0) + 0.01188 * Math.pow(Math.sin(B0), 4.0) - 0.00328 * Math.pow(Math.sin(B0), 6.0))));
        double l = z0 * (1.0 - 0.0033467108 * Math.pow(Math.sin(B0), 2.0) - 5.6002E-6 * Math.pow(Math.sin(B0), 4.0) - 1.87E-8 * Math.pow(Math.sin(B0), 6.0) - Math.pow(z0, 2.0) * (0.16778975 + 0.16273586 * Math.pow(Math.sin(B0), 2.0) - 5.249E-4 * Math.pow(Math.sin(B0), 4.0) - 8.46E-6 * Math.pow(Math.sin(B0), 6.0) - Math.pow(z0, 2.0) * (0.0420025 + 0.1487407 * Math.pow(Math.sin(B0), 2.0) + 0.005942 * Math.pow(Math.sin(B0), 4.0) - 1.5E-5 * Math.pow(Math.sin(B0), 6.0) - Math.pow(z0, 2.0) * (0.01225 + 0.09477 * Math.pow(Math.sin(B0), 2.0) + 0.03282 * Math.pow(Math.sin(B0), 4.0) - 3.4E-4 * Math.pow(Math.sin(B0), 6.0) - Math.pow(z0, 2.0) * (0.0038 + 0.0524 * Math.pow(Math.sin(B0), 2.0) + 0.0482 * Math.pow(Math.sin(B0), 4.0) + 0.0032 * Math.pow(Math.sin(B0), 6.0))))));
        double B = (B0 + dB) * 180.0 / Math.PI;
        double L = (6.0 * ((double) nZonu - 0.5) / 57.29577951 + l) * 180.0 / Math.PI;

        return new double[]{B, L};
    }

    public double getDX(double y) {
        double srY = 7387540.0;
        double koef = 279.0 / 101588.0;
        double dX = (y - srY) * koef + (-116.0);
        return dX;
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
