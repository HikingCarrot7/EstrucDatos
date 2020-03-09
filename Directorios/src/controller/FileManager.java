package controller;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author nicol
 * @param <T>
 */
public class FileManager<T extends MouseEvent> implements EventHandler<T>
{

    @Override
    public void handle(T e)
    {
        if (e.getClickCount() == 2)
        {
            TablePosition<Directorio, ?> tp = ((TableView) e.getSource()).getFocusModel().getFocusedCell();
            Directorio selected = tp.getTableView().getSelectionModel().getSelectedItems().get(0);

            try
            {
                String url = selected.getRuta();

                ProcessBuilder p = new ProcessBuilder();
                p.command("cmd.exe", "/c", url);
                p.start();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

        }

    }

}
