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
    public synchronized void handle(T e)
    {
        System.out.println(e.getClickCount());

        if (e.getClickCount() == 2)
        {
            e.consume();
            System.out.println("Hola Hola");
            TablePosition<Directorio, ?> tp = ((TableView) e.getSource()).getFocusModel().getFocusedCell();
            Directorio selected = tp.getTableView().getSelectionModel().getSelectedItems().get(0);

            System.out.println(selected.getNombre());
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

/**
 * Encuentra el error xD
 *
 * @author nicol
 * @param <T>
 */
/*class Outer<T>
{

    public static void main(String[] args)
    {
        new Outer().foo();
        Outer<String>.Inner i = new Outer<>().new Inner();
    }

    public void foo()
    {
        Outer<String>.Inner i = new Outer<String>.Inner();
        Outer<String>.Inner[] o = i.getInners();
        System.out.println(Arrays.toString(o));
    }

    final class Inner
    {

        Outer<T>.Inner[] inner = new Outer.Inner[3];

        public Inner[] getInners()
        {
            return inner;
        }

    }
}*/
