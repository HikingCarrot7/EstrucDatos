package com.sw.controller;

import com.sw.model.Arista;
import com.sw.model.Grafico;
import com.sw.model.Punto;
import com.sw.model.Vertice;
import java.util.Observable;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Aquí se encuentra la lógica principal del grafo.
 *
 * @author HikingCarrot7
 */
public final class GraphLogic extends Observable implements EventHandler<MouseEvent>
{

    private VistaGrafoController controller;
    private Grafico grafico;
    private MouseMovement mouseMovement;

    private boolean poniendoArista;
    private boolean moviendoVertice;

    private Arista aristaCursor;
    private Vertice verticeSeleccionado;

    public GraphLogic(VistaGrafoController controller, Grafico grafico)
    {
        this.grafico = grafico;
        this.controller = controller;
        mouseMovement = new MouseMovement();
        controller.getPanel().setOnMouseMoved(mouseMovement);
    }

    /**
     * Añade un {@link Vertice} al grafo.
     *
     * @param x La coordenada x del nuevo {@link Vertice}.
     * @param y La coordenada y del nuevo {@link Vertice}.
     */
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

    public void eliminarVertice(Vertice v)
    {
        grafico.eliminarVertice(v);
        notificar("Se ha eliminado el vértice: " + v.getNombre().getText());
        actualizarGrafico();
    }

    private void moverVertice(Vertice v, double x, double y)
    {
        Label nombre = v.getNombre();

        v.setCenterX(x);
        v.setCenterY(y);

        nombre.setTranslateX(v.getCenterX() - nombre.getWidth() / 2);
        nombre.setTranslateY(v.getCenterY() - nombre.getHeight() / 2);
        moviendoVertice = true;
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

    public void manejarAristaCursor(Vertice vInicio, double x, double y)
    {
        if (!grafico.existenVerticesSuficientesParaCrearArista())
        {
            notificar("No hay vértices suficientes para crear una arista");
            return;
        }

        if (vInicio.isSelected())
        {
            eliminarArista(aristaCursor);
            vInicio.setSelected(false);
            poniendoArista = false;

        } else if (!poniendoArista)
        {
            verticeSeleccionado = vInicio;
            vInicio.setSelected(true);
            mouseMovement.ponerCoordenadasCursor(x, y);
            aristaCursor = anadirArista(vInicio, mouseMovement.getCursor());
            poniendoArista = true;
            notificar("Poniendo arista. Vértice de origen: " + vInicio.getNombre().getText());
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
        notificar("Se ha cancelado la operación");
    }

    @Override
    public void handle(MouseEvent e)
    {
        double x = e.getSceneX();
        double y = e.getSceneY();

        anadirVertice(x, y);

        if (clicDerechoPresionado(e) && poniendoArista)
            eliminarSeleccionActual();
    }

    /**
     * Eventos de los {@link Vertice}.
     *
     * @param v
     */
    private void setVerticeEvents(Vertice v)
    {
        v.setOnMouseClicked(e ->
        {
            if (clicIzquierdoPresionado(e))
                if (!poniendoArista)
                    manejarAristaCursor(v, e.getSceneX(), e.getSceneY());

                else
                {
                    eliminarSeleccionActual();
                    anadirArista(verticeSeleccionado, v);
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

            v.requestFocus();
        });

        v.setOnMouseExited(e ->
        {
            if (!v.isSelected())
                v.setFill(Vertice.DEFAULT_SKIN);

            controller.getPanel().requestFocus();
        });

        v.setOnMouseDragged(e ->
        {
            moverVertice(v, e.getSceneX(), e.getSceneY());
        });

        v.setOnMouseReleased(e ->
        {
            if (moviendoVertice)
                notificar("Ha dejado de mover al vértice: " + v.getNombre().getText());

            moviendoVertice = false;
        });

        v.setOnKeyReleased(e ->
        {
            if (eliminarPresionado(e) && !poniendoArista)
                eliminarVertice(v);
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
        controller.actualizarGrafico();
    }

    private boolean clicDerechoPresionado(MouseEvent e)
    {
        return e.getButton().equals(MouseButton.SECONDARY);
    }

    private boolean clicIzquierdoPresionado(MouseEvent e)
    {
        return e.getButton().equals(MouseButton.PRIMARY);
    }

    private boolean eliminarPresionado(KeyEvent e)
    {
        return e.getCode().equals(KeyCode.BACK_SPACE) || e.getCode().equals(KeyCode.DELETE);
    }

}
