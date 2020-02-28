package test;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public void getDirectorios(File file)
    {
        for (String direc : file.list())
        {
            File f = new File(file.getAbsolutePath(), direc);

            if (f.isDirectory())
                getDirectorios(f);

            else
                System.out.println(f.getAbsolutePath());

        }

    }

}
