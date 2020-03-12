package controller;

import javafx.application.Platform;
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
        table.setItems(FXCollections.observableArrayList(directorios));
        table.refresh();
    }

    public void rellenarTabla(E item, TableView<E> table)
    {
        Platform.runLater(() ->
        {
            table.getItems().clear();
            table.getItems().add(item);
            table.refresh();
        });
    }

    public void limpiarTabla(TableView<E> table)
    {
        Platform.runLater(() ->
        {
            table.getItems().clear();
            table.refresh();
        });
    }

}
