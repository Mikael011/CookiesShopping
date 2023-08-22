package org.example.Entitati;
public class CasaDeMarcat {
    private static CasaDeMarcat instance;
    private double sumaTotalaBani;
    private double incasariZiCurenta;

    private CasaDeMarcat() {
        this.sumaTotalaBani = 0.0;
        this.incasariZiCurenta = 0.0;
    }

    public static CasaDeMarcat getInstance() {
        if (instance == null) {
            instance = new CasaDeMarcat();
        }
        return instance;
    }

    public void retragereNumerarZi() {
        sumaTotalaBani += incasariZiCurenta;
        incasariZiCurenta = 0.0;
    }

    public void retragereSoldLunar() {
        sumaTotalaBani = 0.0;
    }

    public void incasare(double valoare) {
        incasariZiCurenta += valoare;
    }

    // Metodele getter
    public double getSumaTotalaBani() {
        return sumaTotalaBani;
    }

    public double getIncasariZiCurenta() {
        return incasariZiCurenta;
    }
}
