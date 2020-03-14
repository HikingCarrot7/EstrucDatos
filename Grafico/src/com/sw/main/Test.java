package com.sw.main;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author HikingCarrot7
 */
public class Test extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Test.class.getResource("/com/sw/view/VistaGrafo.fxml"));
        Pane pane = (Pane) loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setTitle("Grafos");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}
