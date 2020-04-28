package com.sw.controller;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 *
 * @author Nicolás
 */
public class Notificacion extends BorderPane
{

    private final int RECT_WIDTH = 250;
    private final int RECT_HEIGHT = 100;

    private final BorderPane panelPrincipal;
    private final Button close;
    private final Label title;
    private final Label content;

    public Notificacion(BorderPane panelPrincipal, String title, String content)
    {
        this.panelPrincipal = panelPrincipal;

        this.title = new Label(title);
        this.title.setWrapText(true);
        this.title.setMaxWidth(Double.MAX_VALUE);
        this.title.setAlignment(Pos.CENTER);

        this.content = new Label(content);
        this.content.setWrapText(true);
        this.close = new Button("X");
        this.close.setStyle("-fx-background-color: white;");
        this.close.setOnMouseEntered(e -> close.setStyle("-fx-background-color: tomato;"));
        this.close.setOnMouseExited(e -> close.setStyle("-fx-background-color: white;"));

        BorderPane panelSuperior = new BorderPane();
        panelSuperior.setCenter(this.title);
        panelSuperior.setRight(this.close);

        setTop(panelSuperior);
        setCenter(this.content);
        setStyle("-fx-background-color: white;-fx-border-color: black;");

        setWidth(RECT_WIDTH);
        setHeight(RECT_HEIGHT);
    }

    public void setCloseAction(EventHandler<MouseEvent> closeAction)
    {
        close.setOnMouseClicked(closeAction);
    }

    public void mostrar()
    {
        Platform.runLater(() ->
        {
            setTranslateX(panelPrincipal.getWidth());
            setTranslateY(panelPrincipal.getHeight() - getHeight() - 10);

            TranslateTransition t = new TranslateTransition(Duration.millis(500), this);
            t.setInterpolator(Interpolator.EASE_BOTH);
            t.setToX(getTranslateX() - getWidth());

            FadeTransition f = new FadeTransition(Duration.millis(1000), this);
            f.setToValue(0.0);

            PauseTransition p = new PauseTransition(Duration.millis(3000));

            SequentialTransition s = new SequentialTransition(t, p, f);
            s.play();

            s.setOnFinished(e -> panelPrincipal.getChildren().remove(this));

            panelPrincipal.widthProperty().addListener(l -> setTranslateX(panelPrincipal.getWidth() - getWidth()));
            panelPrincipal.heightProperty().addListener(l -> setTranslateY(panelPrincipal.getHeight() - getHeight() - 10));

            panelPrincipal.getChildren().add(this);
        });
    }

}
