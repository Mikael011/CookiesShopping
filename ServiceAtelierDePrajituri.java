package org.example.AtelierDePrajituriApp.Service;

import org.example.Entitati.CasaDeMarcat;
import org.example.Entitati.ComenziPrajituri.Comanda;
import org.example.Entitati.Prajitura.Prajitura;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

public class ServiceAtelierDePrajituri {
    ArrayList<Prajitura> stocInitialPrajituri = new ArrayList<>();
    private ArrayList<Prajitura> listaTotalaPrajituri = new ArrayList<>();
    private ArrayList<Prajitura> listaCosLaur = new ArrayList<>();
    private ArrayList<Prajitura> cosCumparaturi = new ArrayList<>();
    private ArrayList<Comanda> listaComenzi = new ArrayList<>();

    private double vanzariZiCurenta = 0.0;
    private double sumaTotalaBani = 0.0;
    private int ziuaCurentaLuna = 1;
    private HashMap<Integer, Double> incasariPeZi = new HashMap<>();

    public ServiceAtelierDePrajituri() {
        cosCumparaturi = new ArrayList<>();
        this.ziuaCurentaLuna = 0;

        Calendar calendar = Calendar.getInstance();
        this.ziuaCurentaLuna = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void afiseazaMeniuInteractiv() {
        Scanner scanner = new Scanner(System.in);
        int optiune;
        initializareListaPredefinitaProduse();
        do {
            System.out.println("Meniu principal:");
            System.out.println("1. Adauga prajitura");
            System.out.println("2. Adauga comanda in cos");
            System.out.println("3. Sterge comanda din cos");
            System.out.println("4. Cauta produs");
            System.out.println("5. Produse");
            System.out.println("6. Retragere numerar la final de zi");
            System.out.println("7. Retragere sold lunar");
            System.out.println("8. Incasare la soldul de zi");
            System.out.println("9. Produse finale");
            System.out.println("0. Iesire");
            System.out.println("============");
            System.out.println("Alege o optiune: ");
            optiune = scanner.nextInt();
            scanner.nextLine(); // Consumam newline

            switch (optiune) {
                case 1:
                    adaugaPrajituraInteractiv();
                    break;
                case 2:
                    adaugaComandaInteractiv();
                    break;
                case 3:
                    int idProdus = getIdProdusFromUser();
                    if (idProdus == 0) {
                        return; // If the user enters 0, return to the main menu without performing any action.
                    }
                    for (Prajitura prajitura : cosCumparaturi) {
                        if (prajitura.getId() == idProdus) {
                            stergeDinCos();
                            break;
                        }
                    }
                    break;
                case 4:
                    cautaProdus();
                    break;
                case 5:
                    afiseazaProduse();
                    break;
                case 6:
                    retragereNumerarLaFinalDeZi();
                    break;
                case 7:
                    retragereSoldLunar();
                case 8:
                    double sumaPlata = getSumaPlataFromUser();
                    incasareLaSoldulDeZi(sumaPlata);
                    break;
                case 9:
                    afiseazaProduseAlese();
                    break;
                case 0:
                    System.out.println("La revedere!");
                    break;
                default:
                    System.out.println("Optiune invalida!");
            }
        } while (optiune != 0);
    }

    private void adaugaPrajituraInteractiv() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introdu datele pentru prajitura:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumam newline

        System.out.print("Ingrediente: ");
        String ingrediente = scanner.nextLine();

        System.out.print("Pret: ");
        double pret = scanner.nextDouble();
        scanner.nextLine(); // Consumam newline

        System.out.print("Forma: ");
        String forma = scanner.nextLine();

        System.out.println("Cantitate: ");
        int cantitate = scanner.nextInt();
        scanner.nextLine();

        Prajitura prajitura = new Prajitura(id, ingrediente, pret, forma);
        prajitura.setCantitate(cantitate);

        listaTotalaPrajituri.add(prajitura);
        System.out.println("Prajitura a fost adaugata cu succes!");
    }

    private void cautaProdus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecteaza criteriul de cautare:");
        System.out.println("1. ID");
        System.out.println("2. Ingrediente");
        System.out.println("3. Pret");
        System.out.println("4. Forma");
        int criteriu = scanner.nextInt();
        scanner.nextLine(); // Consumam newline

        System.out.println("Introdu valoarea criteriului de cautare:");
        String valoareCautata = scanner.nextLine();

        ArrayList<Prajitura> rezultateCautare = new ArrayList<>();

        switch (criteriu) {
            case 1: // Cautare dupa ID
                for (Prajitura prajitura : listaTotalaPrajituri) {
                    if (String.valueOf(prajitura.getId()).equals(valoareCautata)) {
                        rezultateCautare.add(prajitura);
                    }
                }
                break;
            case 2: // Cautare dupa Ingrediente
                for (Prajitura prajitura : listaTotalaPrajituri) {
                    if (prajitura.getIngrediente().contains(valoareCautata)) {
                        rezultateCautare.add(prajitura);
                    }
                }
                break;
            case 3: // Cautare dupa Pret
                for (Prajitura prajitura : listaTotalaPrajituri) {
                    if (String.valueOf(prajitura.getPretVanzare()).equals(valoareCautata)) {
                        rezultateCautare.add(prajitura);
                    }
                }
                break;
            case 4: // Cautare dupa Forma
                for (Prajitura prajitura : listaTotalaPrajituri) {
                    if (prajitura.getForma().equalsIgnoreCase(valoareCautata)) {
                        rezultateCautare.add(prajitura);
                    }
                }
                break;
            default:
                System.out.println("Criteriul de cautare nu este valid!");
                return;
        }

        if (rezultateCautare.isEmpty()) {
            System.out.println("Nu s-a gasit niciun produs conform criteriilor de cautare.");
        } else {
            System.out.println("Rezultatele cautarii:");
            for (Prajitura prajitura : rezultateCautare) {
                System.out.println(prajitura);
            }
        }
    }

    public void proceseazaComanda(String numeClient, double sumaPlata, ArrayList<Prajitura> listaPrajituriComanda) {

        double valoareComanda = 0;
        for (Prajitura prajitura : cosCumparaturi) {
            valoareComanda += prajitura.getPretVanzare();
        }

        Comanda comanda = new Comanda(cosCumparaturi.size() + 1, sumaPlata, numeClient, cosCumparaturi);
        comanda.setValoarePlatita(valoareComanda);

        // Încasare la casa de marcat
        CasaDeMarcat casaDeMarcat = CasaDeMarcat.getInstance();
        casaDeMarcat.incasare(comanda.getValoarePlatita());

        // Afisare comanda
        System.out.println("Comanda a fost procesata:");
        System.out.println(comanda);

        // Resetare cos cumparaturi
        cosCumparaturi.clear();
    }

    private int getIdProdusFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti ID-ul produsului pe care doriti sa-l stergeti din cos (0 pentru a iesi din meniu): ");
        return scanner.nextInt();
    }

    private void adaugaPrajituraCos(Prajitura prajitura) {
        if (stocInitialPrajituri.contains(prajitura)) {
            // Verificăm dacă produsul se află în stoc
            int indexProdus = stocInitialPrajituri.indexOf(prajitura);
            Prajitura produsStoc = stocInitialPrajituri.get(indexProdus);
            if (produsStoc.getCantitate() > 0) {
                // Dacă produsul este în stoc și are cantitate disponibilă
                cosCumparaturi.add(prajitura); // Adăugăm produsul în coșul de cumpărături
                produsStoc.setCantitate(produsStoc.getCantitate() - 1); // Scădem cantitatea din stoc
                System.out.println("Produsul a fost adaugat in cosul de cumparaturi.");
            } else {
                System.out.println("Ne pare rau, produsul nu mai este disponibil in stoc.");
            }
        } else {
            System.out.println("Produsul nu exista in stoc.");
        }
    }

    // Metoda pentru stergerea unui produs din cosul de cumparaturi
    private void stergeDinCos() {
        if (cosCumparaturi.isEmpty()) {
            System.out.println("Cosul de cumparaturi este gol.");
            return;
        }

        System.out.println("Produsele din cosul de cumparaturi:");

        for (Prajitura prajitura : cosCumparaturi) {
            System.out.println("ID: " + prajitura.getId() + ", Ingrediente: " + prajitura.getIngrediente() + ", Pret articol: "
                    + prajitura.getPretVanzare() + ", Forma: " + prajitura.getForma() + ", Cantitate: "
                    + prajitura.getCantitate() + ".");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdu ID-ul produsului pe care doresti sa il stergi din cos (0 pentru a iesi din meniu):");
        int idProdus = scanner.nextInt();
        scanner.nextLine();

        if (idProdus == 0) {
            return;
        }

        boolean produsGasit = false;
        for (Prajitura prajitura : cosCumparaturi) {
            if (prajitura.getId() == idProdus) {
                cosCumparaturi.remove(prajitura);
                prajitura.setCantitate(prajitura.getCantitate() + 1);
                System.out.println("Produsul a fost sters din cosul de cumparaturi.");
                produsGasit = true;
                break;
            }
        }

        if (!produsGasit) {
            System.out.println("Produsul cu ID-ul specificat nu se afla in cosul de cumparaturi.");
        }
    }

    private int generareIdComanda() {
        return listaComenzi.size();
    }

    private double calculeazaSumaPlata(ArrayList<Prajitura> listaProduse) {
        double sumaPlata = 0;
        for (Prajitura prajitura : listaProduse) {
            sumaPlata += prajitura.getPretVanzare();
        }
        return sumaPlata;
    }

    private ArrayList<Prajitura> adaugaProduseInComanda() {
        ArrayList<Prajitura> listaProduseComanda = new ArrayList<>();
        afiseazaProduse();

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Introdu ID-ul produsului pe care vrei sa il adaugi in comanda (0 pentru a finaliza comanda):");
            int idProdus = scanner.nextInt();
            scanner.nextLine(); // Consumam newline

            if (idProdus == 0) {
                break;
            }

            Prajitura produsGasit = cautaPrajituraDupaIDPentruAdaugaPrajituraInCos(idProdus);

            if (produsGasit != null) {
                listaProduseComanda.add(produsGasit);
            } else {
                System.out.println("Produsul cu ID-ul specificat nu exista in stoc.");
            }
        } while (true);

        return listaProduseComanda;
    }

    private void afisareDetaliiProduse(ArrayList<Prajitura> listaProduse) {
        for (Prajitura prajitura : listaProduse) {
            System.out.println("ID: " + prajitura.getId());
            System.out.println("Ingrediente: " + prajitura.getIngrediente());
            System.out.println("Pret: " + prajitura.getPretVanzare());
            System.out.println("Forma: " + prajitura.getForma());
            System.out.println("Cantitate: 1"); // Cantitate presupusă pentru fiecare produs
        }
    }

    private void finalizareComanda(String numeClient, double sumaPlata, ArrayList<Prajitura> listaProduse) {
        proceseazaComanda(numeClient, sumaPlata, listaProduse);
        actualizareCantitateProduseInStoc(listaProduse);

        System.out.println("Suma totala de achitat: " + sumaPlata);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Doriti sa finalizati comanda? (da/nu)");
        String raspuns = scanner.nextLine();

        if (raspuns.equalsIgnoreCase("da")) {
            int idComanda = generareIdComanda();
            Comanda comanda = new Comanda(idComanda, sumaPlata, numeClient, listaProduse);
            listaComenzi.add(comanda);
            vanzariZiCurenta += sumaPlata;
            System.out.println("Comanda a fost adaugata cu succes!");
        } else if (raspuns.equalsIgnoreCase("nu")) {
            eliminareProduseDinComanda();
        } else {
            System.out.println("Optiunea introdusa nu este valida. Comanda nu a fost finalizata.");
        }
    }

    private void eliminareProduseDinComanda() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Introdu ID-ul produsului pe care doresti sa il elimini din comanda (0 pentru a finaliza eliminarea):");
            int idProdusEliminat = scanner.nextInt();
            scanner.nextLine(); // Consumam newline

            if (idProdusEliminat == 0) {
                break;
            }

            boolean produsEliminat = false;
            for (Prajitura prajitura : listaCosLaur) {
                if (prajitura.getId() == idProdusEliminat) {
                    listaCosLaur.remove(prajitura);
                    produsEliminat = true;
                    System.out.println("Produsul a fost eliminat din comanda.");
                    break;
                }
            }

            if (!produsEliminat) {
                System.out.println("Produsul cu ID-ul specificat nu exista in comanda.");
            }
        } while (true);
    }

    private void actualizareCantitateProduseInStoc(ArrayList<Prajitura> listaProduse) {
        for (Prajitura prajitura : listaProduse) {
            for (Prajitura produsStoc : stocInitialPrajituri) {
                if (prajitura.getId() == produsStoc.getId()) {
                    produsStoc.setCantitate(produsStoc.getCantitate() - 1);
                    break;
                }
            }
        }
    }

    private void adaugaComandaInteractiv() {
        Scanner scanner = new Scanner(System.in);

        int idComanda = generareIdComanda();
        double sumaPlata = 0;
        ArrayList<Prajitura> listaProduseComanda = new ArrayList<>();
        String numeClient;

        System.out.print("Nume client: ");
        numeClient = scanner.nextLine();

        listaProduseComanda = adaugaProduseInComanda();

        if (listaProduseComanda.isEmpty()) {
            System.out.println("Nu ati adaugat niciun produs in comanda. Comanda nu a fost finalizata.");
        } else {
            afisareDetaliiProduse(listaProduseComanda);
            sumaPlata = calculeazaSumaPlata(listaProduseComanda);
            finalizareComanda(numeClient, sumaPlata, listaProduseComanda);
        }
    }

    private Prajitura cautaPrajituraDupaIDPentruAdaugaPrajituraInCos(int idProdus) {
        for (Prajitura prajitura : stocInitialPrajituri) {
            if (prajitura.getId() == idProdus) {
                return prajitura;
            }
        }
        return null;
    }

    private void afiseazaProduseAlese() {
        if (listaCosLaur.isEmpty()) {
            System.out.println("Nu exista produse in cosul de cumparaturi.");
        }

        System.out.println("Produsele alese:");

        for (Prajitura prajitura : listaCosLaur) {
            System.out.println("ID: " + prajitura.getId() + ", Ingrediente: " + prajitura.getIngrediente()
                    + ", Pret articol: " + prajitura.getPretVanzare() + ", Forma: " + prajitura.getForma()
                    + ", Cantitate: " + prajitura.getCantitate() + ".");
        }
    }

    private void retragereNumerarLaFinalDeZi() {
        if (vanzariZiCurenta == 0) {
            System.out.println("Nu s-au inregistrat vanzari pe ziua curenta.");
            return;
        }

        System.out.println("Suma totala de bani disponibila la finalul zilei este: " + sumaTotalaBani);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Doriti sa retrageti numerarul? (da/nu)");
        String raspuns = scanner.nextLine();

        if (raspuns.equalsIgnoreCase("da")) {
            sumaTotalaBani -= vanzariZiCurenta;
            vanzariZiCurenta = 0;
            System.out.println("Suma de numerar a fost retrasa cu succes.");
        } else {
            System.out.println("Retragerea numerarului a fost anulata.");
        }
    }

    private void retragereSoldLunar() {
        System.out.println("Suma totala de bani disponibila este: " + sumaTotalaBani);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Doriti sa retrageti soldul lunar? (da/nu)");
        String raspuns = scanner.nextLine();
        if (raspuns.equalsIgnoreCase("da")) {
            afiseazaIncasariPeZi();
            double sumaRetrasa = sumaTotalaBani;
            sumaTotalaBani = 0;
            System.out.println("Ai retras suma de " + sumaRetrasa + " lei ca sold lunar.");
            System.out.println("Soldul lunar a fost retras cu succes.");
        } else {
            System.out.println("Retragerea soldului lunar a fost anulata.");
        }
    }

    private void afiseazaIncasariPeZi() {
        System.out.println("Suma incasata pe zi:");

        // Parcurgem toate sumele încasate pe zi și le afișăm
        for (int zi = 1; zi <= ziuaCurentaLuna; zi++) {
            System.out.println("Ziua " + zi + ": " + incasariPeZi.get(zi) + " lei");
        }
    }

    private void incasareLaSoldulDeZi(double sumaPlata) {
        // Verificăm dacă ziua curentă a lunii s-a schimbat
        Calendar calendar = Calendar.getInstance();
        int ziuaCurentaNoua = calendar.get(Calendar.DAY_OF_MONTH);
        if (ziuaCurentaNoua != ziuaCurentaLuna) {
            // Dacă ziua curentă a lunii s-a schimbat, afișăm suma pentru ziua anterioară
            System.out.println("Suma incasata pentru ziua " + ziuaCurentaLuna + ": " + vanzariZiCurenta);
            // Resetăm suma încasată pentru ziua curentă a lunii
            vanzariZiCurenta = 0;
            // Actualizăm ziua curentă a lunii cu ziua curentă nouă
            ziuaCurentaLuna = ziuaCurentaNoua;
        }

        // Înregistrăm încasarea pentru ziua curentă
        vanzariZiCurenta += sumaPlata;

        // Actualizăm suma totală de bani
        sumaTotalaBani += sumaPlata;
        System.out.println("Incasare inregistrata pentru ziua " + ziuaCurentaLuna + " cu valoarea de " + sumaPlata + " lei.");
    }

    private double getSumaPlataFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti suma de bani platita: ");
        return scanner.nextDouble();
    }

    private void afiseazaProdusePredefinite() {
        System.out.println("Produse disponibile in stoc:");
        for (Prajitura prajitura : listaTotalaPrajituri) {
            System.out.println("ID: " + prajitura.getId() + ", Ingrediente: " + prajitura.getIngrediente() + ", Pret articol: " + prajitura.getPretVanzare() + ", Forma: " + prajitura.getForma() + ", Cantitate: " + prajitura.getCantitate() + ".");
        }
    }

    private void afiseazaProduseAdaugate() {
        if (cosCumparaturi.isEmpty()) {
            System.out.println("Cosul de cumparaturi este gol.");
        } else {
            System.out.println("Produse alese in cosul de cumparaturi:");
            for (Prajitura prajitura : cosCumparaturi) {
                System.out.println("ID: " + prajitura.getId() + ", Ingrediente: " + prajitura.getIngrediente() + ", Pret articol: " + prajitura.getPretVanzare() + ", Forma: " + prajitura.getForma() + ", Cantitate: " + prajitura.getCantitate() + ".");
            }
        }
    }

    private void afiseazaProduse() {
        afiseazaProduseAdaugate();
        afiseazaProdusePredefinite();
    }

    public void initializareListaPredefinitaProduse() {

        Prajitura prajitura1 = new Prajitura(1, "Ingrediente1", 10.0, "triunghi");
        prajitura1.setCantitate(5);
        stocInitialPrajituri.add(prajitura1);

        Prajitura prajitura2 = new Prajitura(2, "Ingrediente2", 15.0, "dreptunghi");
        prajitura2.setCantitate(10);
        stocInitialPrajituri.add(prajitura2);

        Prajitura prajitura3 = new Prajitura(3, "Ingrediente3", 12.5, "patrat");
        prajitura3.setCantitate(6);
        stocInitialPrajituri.add(prajitura3);

        listaTotalaPrajituri.addAll(stocInitialPrajituri);
    }
}
