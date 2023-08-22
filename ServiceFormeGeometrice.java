package org.example.AtelierDePrajituriApp.Service;

import org.example.Entitati.Shapes.Forma;
import org.example.Entitati.Shapes.Dreptunghi;
import org.example.Entitati.Shapes.Patrat;
import org.example.Entitati.Shapes.Triunghi;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiceFormeGeometrice {

    Scanner scan = new Scanner(System.in);
    Dreptunghi dreptunghi = new Dreptunghi();
    Triunghi triunghi = new Triunghi();

    Patrat patrat = new Patrat();

    double perimetruTriunghi = 0.0;
    double perimetruDreptunghi = 0.0;
    double perimetruPatrat = 0.0;



    public void meniu() {
        ArrayList<Forma> formeGeometrice = new ArrayList<>();
        formeGeometrice.add(patrat);
        formeGeometrice.add(triunghi);
        formeGeometrice.add(dreptunghi);
        boolean condition = true;
        do {
            int cnt ;
            System.out.println("1.Calcul perimetru");
            System.out.println("2.Suma perimetre");
            System.out.println("3.Calcul arie");
            System.out.println("Alege o optiune: ");
            cnt = scan.nextInt();
            switch (cnt) {
                case 1 : {
                    System.out.println("Alege forma: ");

                    String numeForma = scan.next();

                    System.out.println(numeForma);

                    if ( numeForma.equalsIgnoreCase("dreptunghi")){
                        System.out.println(dreptunghi.calculPerimetru());

                    }else if (numeForma.equalsIgnoreCase("triunghi")){
                        System.out.println(triunghi.calculPerimetru());
                    }
                    else if (numeForma.equalsIgnoreCase("patrat")){
                        System.out.println(patrat.calculPerimetru());
                    }
                    else {
                        System.out.println("Forma incorecta!");
                    }
                    break;
                }
                case 2 :{
                    System.out.println("Suma perimetrelor este: ");
                    sumaPerimetre();
                    break;
                }
                case 3 : {
                    System.out.println("Alege forma: ");

                    String numeForma = scan.next();

                    System.out.println(numeForma);

                    if ( numeForma.equalsIgnoreCase("dreptunghi")){
                        System.out.println(dreptunghi.calculArie());

                    }else if (numeForma.equalsIgnoreCase("triunghi")){
                        System.out.println(triunghi.calculArie());
                    }
                    else if (numeForma.equalsIgnoreCase("patrat")){
                        System.out.println(patrat.calculArie());
                    }
                    else {
                        System.out.println("Forma incorecta!");
                    }
                    break;
                }
            }
        } while(condition);
    }

    public void sumaPerimetre (){
        Double sumaPerimetreFormeGeometrice;

        perimetruPatrat = patrat.calculPerimetru();

        perimetruDreptunghi = dreptunghi.calculPerimetru();

        perimetruTriunghi = triunghi.calculPerimetru();

        sumaPerimetreFormeGeometrice = perimetruPatrat+perimetruDreptunghi+perimetruTriunghi;

        System.out.println(sumaPerimetreFormeGeometrice);
    }
}
