package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author HikingCarrot7
 */
public class TableManager
{

    public void rellenarTabla(ObservableList<Directorio> directorios, TableView<Directorio> table)
    {
        table.setItems(directorios);
        table.refresh();
    }

    public void limpiarTabla(TableView<Directorio> table)
    {
        table.getItems().clear();
        table.setItems(FXCollections.observableArrayList());
        table.refresh();
    }
}
