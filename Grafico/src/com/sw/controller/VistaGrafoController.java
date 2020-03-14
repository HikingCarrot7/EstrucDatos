package com.sw.controller;

import com.sw.model.Grafico;
import com.sw.model.Vertice;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HikingCarrot7
 */
public class VistaGrafoController implements Initializable
{

    @FXML
    private AnchorPane panel;

    private Grafico grafico;
    private AristaHandler aristaHandler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        grafico = new Grafico();
        aristaHandler = new AristaHandler(grafico);
        panel.setOnMouseMoved(aristaHandler);
        panel.addEventHandler(MouseEvent.MOUSE_CLICKED, aristaHandler);
    }

    @FXML
    private void ponerVertice(MouseEvent e)
    {
        if (e.getButton().equals(MouseButton.SECONDARY))
            return;

        double x = e.getSceneX();
        double y = e.getSceneY();

        Platform.runLater(() ->
        {
            if (!VerticeHandler.existeVerticeEnPosicion(grafico, x, y))
            {
                Vertice v = new Vertice(x, y);
                VerticeHandler.setEvents(v);
                aristaHandler.accionPonerArista(this, v);
                grafico.addVertice(v);
                actualizarGrafico();
            }
        });

    }

    public void actualizarGrafico()
    {
        panel.getChildren().clear();
        ObservableList<Node> children = FXCollections.observableArrayList(grafico.getVertices());
        children.addAll(grafico.getVertices().stream().map(v -> v.getNombre()).collect(Collectors.toCollection(ArrayList::new)));
        children.addAll(grafico.getAristas());
        panel.getChildren().addAll(children);
        grafico.getAristas().forEach(Node::toBack);
    }

}
