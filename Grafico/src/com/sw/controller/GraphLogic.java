package com.sw.controller;

import com.sw.model.Arista;
import com.sw.model.Grafico;
import com.sw.model.Punto;
import com.sw.model.Vertice;
import java.util.Observable;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Aquí se encuentra la lógica principal del grafo.
 *
 * @author HikingCarrot7
 */
public final class GraphLogic extends Observable implements EventHandler<MouseEvent>
{

    private VistaGrafoController vgc;
    private Grafico grafico;
    private MouseMovement mouseMovement;

    private boolean poniendoArista;
    private Arista aristaCursor;
    private Vertice verticeSeleccionado;

    public GraphLogic(VistaGrafoController vgc, Grafico grafico)
    {
        this.grafico = grafico;
        this.vgc = vgc;
        mouseMovement = new MouseMovement();
        vgc.getPanel().setOnMouseMoved(mouseMovement);
    }

    public void anadirVertice(double x, double y)
    {
        if (VerticeUtils.existeVerticeEnPosicion(grafico, x, y))
            return;

        if (!poniendoArista)
        {
            Vertice v = new Vertice(x, y);
            setVerticeEvents(v);
            grafico.addVertice(v);
            notificar("Se ha añadido el véritce: " + v.getNombre().getText());
            actualizarGrafico();
        }
    }

    public void eliminarVertice()
    {

    }

    private void moverVertice(Vertice v, double x, double y)
    {
        Label nombre = v.getNombre();

        v.setCenterX(x);
        v.setCenterY(y);

        nombre.setTranslateX(v.getCenterX() - nombre.getWidth() / 2);
        nombre.setTranslateY(v.getCenterY() - nombre.getHeight() / 2);
        notificar("Moviendo vértice: " + v.getNombre().getText());
    }

    public Arista anadirArista(Vertice vInicio, Vertice vFinal)
    {
        Arista arista = new Arista(vInicio, vFinal);
        grafico.addArista(arista);
        notificar("Se ha creado la conexión entre el vértice: " + vInicio.getNombre().getText() + " y el vértice: " + vFinal.getNombre().getText());
        actualizarGrafico();
        return arista;
    }

    public Arista anadirArista(Vertice vInicio, Punto pFinal)
    {
        Arista arista = new Arista(vInicio, pFinal);
        grafico.addArista(arista);
        actualizarGrafico();
        return arista;
    }

    public void manejarAristaCursor(Vertice v, double x, double y)
    {
        if (!grafico.existenVerticesSuficientesParaCrearArista())
        {
            notificar("No hay vértices suficientes para crear una arista");
            return;
        }

        if (v.isSelected())
        {
            eliminarArista(aristaCursor);
            v.setSelected(false);
            poniendoArista = false;

        } else if (!poniendoArista)
        {
            verticeSeleccionado = v;
            v.setSelected(true);
            mouseMovement.ponerCoordenadasCursor(x, y);
            aristaCursor = anadirArista(v, mouseMovement.getCursor());
            poniendoArista = true;
            notificar("Poniendo arista. Vértice de origen: " + v.getNombre().getText());
        }

        actualizarGrafico();
    }

    public void eliminarArista(Arista arista)
    {
        grafico.eliminarArista(arista);
        actualizarGrafico();
    }

    private void eliminarSeleccionActual()
    {
        eliminarArista(aristaCursor);
        verticeSeleccionado.setSelected(false);
        poniendoArista = false;
    }

    @Override
    public void handle(MouseEvent e)
    {
        double x = e.getSceneX();
        double y = e.getSceneY();
        anadirVertice(x, y);

        if (e.getButton().equals(MouseButton.SECONDARY) && poniendoArista)
            eliminarSeleccionActual();
    }

    private void setVerticeEvents(Vertice v)
    {
        v.setOnMouseClicked(e ->
        {
            if (!e.getButton().equals(MouseButton.SECONDARY) && poniendoArista)
            {
                anadirArista(verticeSeleccionado, v);
                eliminarSeleccionActual();
            }
        });

        v.setOnMouseEntered(e ->
        {
            if (!v.isSelected())
            {
                v.toFront();
                v.getNombre().toFront();
                v.setFill(Vertice.HOVER_SKIN);
            }
        });

        v.setOnMouseExited(e ->
        {
            if (!v.isSelected())
                v.setFill(Vertice.DEFAULT_SKIN);
        });

        v.setOnMouseDragged(e ->
        {
            moverVertice(v, e.getSceneX(), e.getSceneY());
        });

        v.setOnContextMenuRequested(e ->
        {
            manejarAristaCursor(v, e.getSceneX(), e.getSceneY());
        });

    }

    private void notificar(String mensaje)
    {
        setChanged();
        notifyObservers(mensaje);
        clearChanged();
    }

    private void actualizarGrafico()
    {
        vgc.actualizarGrafico();
    }

}
