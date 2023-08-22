package org.example.Entitati.Shapes;

import org.example.Entitati.Shapes.Forma;

import java.util.Scanner;

public class Patrat extends Forma {
//    public Patrat(Double lungime, Double latine) {
//        super(lungime, latine);
//    }
Scanner scan = new Scanner(System.in);
    @Override
    public Double calculArie() {
        System.out.println("Introdu latimea: ");
        latine = scan.nextDouble();
        System.out.println("Introdu lungimea: ");
        lungime = scan.nextDouble();
        System.out.print("Aria patratului: ");
        return lungime * latine;
    }

    @Override
    public double calculPerimetru() {
        System.out.println("Introdu dimensiune latura: ");
        latine = scan.nextDouble();
        System.out.print("Perimetrul patratului: ");
        return latine * 4;
    }
}
