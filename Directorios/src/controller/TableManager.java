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
        Platform.runLater(() ->
        {
            table.setItems(FXCollections.observableArrayList(directorios));
            refreshTable(table);
        });
    }

    public void rellenarTabla(E item, TableView<E> table)
    {
        table.getItems().clear();
        table.getItems().add(item);
        refreshTable(table);
    }

    public void rellenarTablaConNuevaLista(ObservableList<E> directorios, TableView<E> table)
    {
        table.setItems(FXCollections.observableArrayList(directorios));
        refreshTable(table);
    }

    public void limpiarTabla(TableView<E> table)
    {
        table.getItems().clear();
        refreshTable(table);
    }

    public void refreshTable(TableView<E> table)
    {
        table.refresh();
    }

}
