package com.sw.controller;

import com.sw.model.Grafico;
import com.sw.model.Vertice;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HikingCarrot7
 */
public class VistaGrafoController implements Initializable, Observer
{

    @FXML
    private AnchorPane panel;
    @FXML
    private Label estado;

    private Grafico grafico;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        grafico = new Grafico();
        GraphLogic graphLogic = new GraphLogic(this, grafico);
        panel.setOnMouseClicked(graphLogic);
        graphLogic.addObserver(this);
    }

    public void actualizarGrafico()
    {
        panel.getChildren().clear();
        ObservableList<Node> children = FXCollections.observableArrayList(grafico.getVertices());
        children.addAll(grafico.getVertices().stream().map(Vertice::getNombre).collect(Collectors.toCollection(ArrayList::new)));
        children.addAll(grafico.getAristas());
        panel.getChildren().addAll(children);
        grafico.getAristas().forEach(Node::toBack);
    }

    @Override
    public void update(Observable o, Object mensaje)
    {
        Platform.runLater(() ->
        {
            estado.setText(mensaje.toString());
        });
    }

    public AnchorPane getPanel()
    {
        return panel;
    }

}
