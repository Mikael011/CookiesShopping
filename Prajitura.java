package org.example.Entitati.Prajitura;

public class Prajitura {
    private int id;
    private String ingrediente;
    private Double pretDeVanzare;
    private String forma ;
    private int cantitate;


    public Prajitura(int id, String ingrediente, double pretVanzare, String forma) {
        this.id = id;
        this.ingrediente = ingrediente;
        this.pretDeVanzare = pretVanzare;
        this.forma = forma;
        this.cantitate = 0;
    }

    // Metodele getter
    public int getId() {
        return id;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public double getPretVanzare() {
        return pretDeVanzare;
    }

    public String getForma() {
        return forma;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "Prajitura{" +
                "id=" + id +
                ", ingrediente='" + ingrediente + '\'' +
                ", pretDeVanzare=" + pretDeVanzare +
                ", forma='" + forma + '\'' +
                ", cantitate=" + cantitate +
                '}';
    }
}
