package com.sw.main;

import com.sw.controller.Controller;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Nicolás
 */
public class Main extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/com/sw/view/Vista.fxml"));
        Pane ventana = (Pane) loader.load();
        Scene scene = new Scene(ventana);
        scene.getStylesheets().add("/com/sw/styles/Stylesheet.css");
        ((Controller) loader.getController()).initStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

}
