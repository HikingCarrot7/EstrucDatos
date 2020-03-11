package test;

import controller.VistaController;
import java.io.IOException;
import java.util.Arrays;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author HikingCarrot7
 */
public class Main extends Application
{

    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(args));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/Vista.fxml"));
            Pane ventana = (Pane) loader.load();
            ((VistaController) loader.getController()).initStage(primaryStage);
            Scene scene = new Scene(ventana);
            scene.getStylesheets().add("styles/Estilos.css");
            primaryStage.setScene(scene);
            primaryStage.setTitle("Algoritmos de ordenamiento");
            primaryStage.centerOnScreen();
            primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/icons/Java.png")));
            primaryStage.show();

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}
