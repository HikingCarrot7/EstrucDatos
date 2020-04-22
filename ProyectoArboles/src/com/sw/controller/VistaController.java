package com.sw.controller;

import com.sw.model.ArbolBB;
import com.sw.model.Egresado;
import com.sw.view.Vista;

/**
 *
 * @author Nicolás
 */
public class VistaController
{

    public final Vista vista;

    public VistaController(Vista vista)
    {
        this.vista = vista;

        Egresado[] egresados = new Egresado[10];
        egresados[0] = new Egresado("Nicolás", "LIS", 20);
        egresados[1] = new Egresado("Nicolás", "FIQ", 20);
        egresados[2] = new Egresado("Nicolás", "LCC", 20);
        egresados[3] = new Egresado("Emmanuel", "LIS", 20);
        egresados[4] = new Egresado("Eusebio", "LIC", 20);
        egresados[5] = new Egresado("Carlos", "LCC", 20);
        egresados[6] = new Egresado("Antonio", "LIC", 20);

        ArbolBB arbolBB = new ArbolBB();

        arbolBB.insertar(0, egresados[0], (lista, egresado) -> egresados[lista.first()].getProfesion().compareTo(egresado.getProfesion()));
        arbolBB.insertar(1, egresados[1], (lista, egresado) -> egresados[lista.first()].getProfesion().compareTo(egresado.getProfesion()));
        arbolBB.insertar(2, egresados[2], (lista, egresado) -> egresados[lista.first()].getProfesion().compareTo(egresado.getProfesion()));
        arbolBB.insertar(3, egresados[3], (lista, egresado) -> egresados[lista.first()].getProfesion().compareTo(egresado.getProfesion()));
        arbolBB.insertar(4, egresados[4], (lista, egresado) -> egresados[lista.first()].getProfesion().compareTo(egresado.getProfesion()));
        arbolBB.insertar(5, egresados[5], (lista, egresado) -> egresados[lista.first()].getProfesion().compareTo(egresado.getProfesion()));
        arbolBB.insertar(6, egresados[6], (lista, egresado) -> egresados[lista.first()].getProfesion().compareTo(egresado.getProfesion()));

        System.out.println("Resultados");
        arbolBB.inorder();
    }
}
