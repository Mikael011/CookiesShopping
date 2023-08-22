package org.example.Entitati.Shapes;

import org.example.Entitati.Shapes.Forma;

import java.util.Scanner;

public class Dreptunghi extends Forma {

//    public Dreptunghi(Double lungime, Double latine) {
//        super(lungime, latine);
//    }
Scanner scan = new Scanner(System.in);
    @Override
    public Double calculArie() {
        System.out.println("Introdu latimea: ");
        latine = scan.nextDouble();
        System.out.println("Introdu lungimea: ");
        lungime = scan.nextDouble();
        System.out.print("Aria dreptunghiului: ");
        return lungime * latine;
    }

    @Override
    public double calculPerimetru() {
        System.out.println("Introdu latimea: ");
        latine = scan.nextDouble();
        System.out.println("Introdu lungimea: ");
        lungime = scan.nextDouble();
        System.out.print("Perimetrul dreptunghiului: ");
        return 2 * lungime + 2 * latine;
    }
}
