package com.sw.controller;

import java.awt.Window;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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
        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter(title, extensiones);
        chooser.setFileFilter(filter);
        int option = chooser.showOpenDialog(owner);

        if (option == JFileChooser.APPROVE_OPTION)
        {
            ultimoArchivoSeleccionado = chooser.getSelectedFile();
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
