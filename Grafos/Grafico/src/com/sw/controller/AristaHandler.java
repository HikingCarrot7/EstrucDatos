package com.sw.controller;

import com.sw.model.Arista;
import com.sw.model.Grafico;
import com.sw.model.Punto;
import com.sw.model.Vertice;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author HikingCarrot7
 */
public class AristaHandler implements EventHandler<MouseEvent>
{

    private Grafico grafico;

    private boolean poniendoArista;
    private Punto cursor;
    private Arista aristaCursor;
    private Vertice verticeSeleccionado;

    public AristaHandler(Grafico grafico)
    {
        this.grafico = grafico;
    }

    public void accionPonerArista(VistaGrafoController vgc, Vertice v)
    {
        v.setOnContextMenuRequested(e ->
        {
            if (!poniendoArista)
            {
                verticeSeleccionado = v;
                v.setSelected(!v.isSelected());
                cursor = new Punto(e.getSceneX(), e.getSceneY());
                aristaCursor = new Arista(v, cursor);
                grafico.addArista(aristaCursor);
                poniendoArista = true;

            } else if (v.isSelected())
            {
                poniendoArista = false;
                cursor = null;
                grafico.eliminarArista(aristaCursor);
                v.setSelected(false);
            }

            vgc.actualizarGrafico();
        });
    }

    @Override
    public void handle(MouseEvent e)
    {
        if (e.getButton().equals(MouseButton.SECONDARY) && poniendoArista)
        {
            poniendoArista = false;
            cursor = null;
            grafico.eliminarArista(aristaCursor);
            verticeSeleccionado.setSelected(false);
        }

        if (poniendoArista)
        {
            cursor.setX(e.getSceneX());
            cursor.setY(e.getSceneY());
        }
    }

}
