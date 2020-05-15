package com.sw.controller;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 *
 * @author Nicolás
 */
public class SeleccionadorArchivos
{

    private static SeleccionadorArchivos instance;

    private SeleccionadorArchivos()
    {

    }

    private File ultimoArchivoSeleccionado;

    public File seleccionarArchivo(Window owner, String title, String... extensiones)
    {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(title, extensiones));
        File selectedFile = chooser.showOpenDialog(owner);

        if (selectedFile != null)
        {
            ultimoArchivoSeleccionado = selectedFile;
            return ultimoArchivoSeleccionado;
        }

        return null;
    }

    public File getUltimoArchivoSeleccionado()
    {
        return ultimoArchivoSeleccionado;
    }

    public synchronized static SeleccionadorArchivos getInstance()
    {
        if (instance == null)
            instance = new SeleccionadorArchivos();

        return instance;
    }

}
