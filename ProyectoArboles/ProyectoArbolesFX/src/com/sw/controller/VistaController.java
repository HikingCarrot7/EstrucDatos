package com.sw.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Nicolás
 */
public class VistaController implements Initializable
{

    @FXML
    private ToggleGroup grupoRadioButtons;
    @FXML
    private BorderPane panelPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Platform.runLater(() ->
        {
            Rectangle r = new Rectangle(250, 100);

            r.setTranslateX(panelPrincipal.getWidth());
            r.setTranslateY(panelPrincipal.getHeight() - r.getHeight() - 10);

            TranslateTransition t = new TranslateTransition(Duration.millis(500), r);
            t.setInterpolator(Interpolator.EASE_BOTH);
            t.setToX(r.getTranslateX() - r.getWidth());

            FadeTransition f = new FadeTransition(Duration.millis(1000), r);
            f.setToValue(0.0);

            PauseTransition p = new PauseTransition(Duration.millis(3000));

            SequentialTransition s = new SequentialTransition(t, p, f);
            s.play();

            s.setOnFinished(e -> panelPrincipal.getChildren().remove(r));

            panelPrincipal.widthProperty().addListener(l ->
            {
                r.setTranslateX(panelPrincipal.getWidth() - r.getWidth());
            });

            panelPrincipal.heightProperty().addListener(l ->
            {
                r.setTranslateY(panelPrincipal.getHeight() - r.getHeight() - 10);
            });

            panelPrincipal.getChildren().add(r);
        });
    }

}
