package org.example.Entitati.ComenziPrajituri;

import org.example.Entitati.Prajitura.Prajitura;

import java.util.ArrayList;

// Clasa Comanda
public class Comanda {
    private int id;
    private double sumaPlata;
    private String numeClient;
    private double valoarePlatita;
    private ArrayList<Prajitura> prajituri;

    public Comanda(int id, double sumaPlata, String numeClient, ArrayList<Prajitura> prajituri) {
        this.id = id;
        this.sumaPlata = sumaPlata;
        this.numeClient = numeClient;
        this.prajituri = prajituri;
        this.valoarePlatita = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSumaPlata() {
        return sumaPlata;
    }

    public void setSumaPlata(double sumaPlata) {
        this.sumaPlata = sumaPlata;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public ArrayList<Prajitura> getPrajituri() {
        return prajituri;
    }

    public void setPrajituri(ArrayList<Prajitura> prajituri) {
        this.prajituri = prajituri;
    }

    // Metodele getter si setter pentru valoarePlatita
    public double getValoarePlatita() {
        return valoarePlatita;
    }

    public void setValoarePlatita(double valoarePlatita) {
        this.valoarePlatita = valoarePlatita;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", sumaPlata=" + sumaPlata +
                ", numeClient='" + numeClient + '\'' +
                ", valoarePlatita=" + valoarePlatita +
                ", prajituri=" + prajituri +
                '}';
    }
}
