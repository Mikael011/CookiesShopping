package org.example.FormeGeometriceApp;

import org.example.AtelierDePrajituriApp.Service.ServiceFormeGeometrice;

public class Main {
    public static void main(String[] args) {
   //TODO Se da o clasa abstracta Forma cu fieldurile lungime,latime si metodele calculPerimetru si calculArie.
        ///TODO Sa se construiasca alte 3 clase pe baza acesteia: patrat,dreptunghi,triungi si o clasa main ce va contine urmatorul meinu:
        ///TODO Calcul perimetru(aici se va cere inca un imput cu numele formei al carei perimetru se calculeaza)
        ///TODO Suma de perimetre(aici se va genera suma tuturor perimetrelor formelor geometrice) (cerinta interpretabila,vedem ce faci)
        ///TODO Arie forma geometrica la fel ca la punctul 1
        ///TODO Nota* se poate defini un array la inceput de service in care sa existe deja cate o forma geometrica din fiecare categorie
        ///TODO Nota** este obligatorie structura proiectului pe pachete si clase in conformitate cu principiile de OOP si clean code
        ///TODO De acum,pentru fiecare flow de calcul perimetru/arie al unei forme,dimensiunile acesteia se vor citi de la tastatura


        /*
        * ATELIER DE PRAJITURI
        *Sa se simuleze un magazin de prajituri ce functioneaza pe baza de comenzi
        *Clasa noua:  Prajitura(formata din id, ingrediente(un string mare),pret de vanzare si Forma)
        *Clasa noua:  Comanda (fomata din id,suma de plata,nume client,valoare de platit si un array de Prajitura)
        *Clasa noua SINGLETON:  Casa de marcat(formata din suma totala de bani,incazari pe ziua curenta)
        *       Metode: retragere de numerar la final de zi(scadem banii din ziua curenta si ii adaugam la cei totali)
        *               retragere sold lunar(ca si cum luam toti banii si ii scoatem cash,doar scadem tot ce e in soldul lunar)
        *               incasare  la soldul pe zi se va adauga valoarea oricarei comenzi procesate
        * */
        ServiceFormeGeometrice service = new ServiceFormeGeometrice();
        service.meniu();
    }

}