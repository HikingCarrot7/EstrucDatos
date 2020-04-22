package com.sw.controller;

import com.sw.model.ArbolBB;
import com.sw.model.Egresado;
import com.sw.util.LinkedList;
import com.sw.view.Vista;
import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author Nicolás
 */
public class VistaController
{

    private final Vista vista;
    private final Egresado[] egresados;

    private final Comparador<LinkedList<Integer>, Egresado> COMPARADOR_POR_NOMBRE;
    private final Comparador<LinkedList<Integer>, Egresado> COMPARADOR_POR_PROFESION;
    private final Comparador<LinkedList<Integer>, Egresado> COMPARADOR_POR_PROMEDIO;

    public VistaController(Vista vista, Egresado[] egresados)
    {
        this.vista = vista;
        this.egresados = egresados;

        COMPARADOR_POR_NOMBRE = (lista, egresado) -> egresados[lista.first()].getNombre().compareTo(egresado.getNombre());
        COMPARADOR_POR_PROFESION = (lista, egresado) -> egresados[lista.first()].getProfesion().compareTo(egresado.getProfesion());
        COMPARADOR_POR_PROMEDIO = (lista, egresado) -> egresados[lista.first()].getPromedio().compareTo(egresado.getPromedio());

        initComponents();
        crearArbol();
    }

    private void initComponents()
    {
        setPanelEnabled(vista.getPanelLateralIzq(), false);
        setPanelEnabled(vista.getPanelLateralDer(), false);
        setPanelEnabled(vista.getPanelSuperior(), false);
    }

    private void crearArbol()
    {
        ArbolBB<LinkedList<Integer>, Egresado> arbolBB = new ArbolBB<>(COMPARADOR_POR_NOMBRE);

        for (int i = 0; i < egresados.length; i++)
            arbolBB.insertar(i, egresados[i]);

        System.out.println("Resultados");
        arbolBB.inorder();

        LinkedList<Integer> resultados = arbolBB.buscar(egresados[0]);
        System.out.println("\n\n\n");
        System.out.println(resultados);
    }

    private void setPanelEnabled(JPanel panel, boolean isEnabled)
    {
        panel.setEnabled(isEnabled);

        Component[] components = panel.getComponents();

        for (Component component : components)
        {
            if (component instanceof JPanel)
                setPanelEnabled((JPanel) component, isEnabled);

            component.setEnabled(isEnabled);
        }
    }
}
