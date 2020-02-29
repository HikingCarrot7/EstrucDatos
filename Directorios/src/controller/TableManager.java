package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author HikingCarrot7
 */
public class TableManager<E>
{

    public void rellenarTabla(ObservableList<E> directorios, TableView<E> table)
    {
        table.setItems(directorios);
        table.refresh();
    }

    public void rellenarTabla(E item, TableView<E> table)
    {
        table.getItems().clear();
        table.getItems().add(item);
        table.refresh();
    }

    public void limpiarTabla(TableView<E> table)
    {
        table.getItems().clear();
        table.setItems(FXCollections.observableArrayList());
        table.refresh();
    }

}
