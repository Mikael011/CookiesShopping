package org.example.Entitati.Shapes;

import org.example.Entitati.Shapes.Forma;

import java.util.Scanner;

public class Triunghi extends Forma {

//    public Triunghi(Double lungime, Double latine) {
//        super(lungime, latine);
//    }
Scanner scan = new Scanner(System.in);
    @Override
    public Double calculArie() {
        System.out.println("Introdu latimea: ");
        latine = scan.nextDouble();
        System.out.println("Introdu lungimea: ");
        lungime = scan.nextDouble();
        System.out.print("Aria triunghiului: ");
      return (latine*calculInaltime(lungime))/2;
    }

    @Override
    public double calculPerimetru() {
        System.out.println("Introdu dimensiune latura: ");
        latine = scan.nextDouble();
        System.out.print("Perimetrul triunghiului: ");
        return latine * 3;
    }

    private Double calculInaltime(Double latura){
        Double halfLength = latura/2;
        return Math.sqrt(latura*latura-halfLength*halfLength);
    }
}
