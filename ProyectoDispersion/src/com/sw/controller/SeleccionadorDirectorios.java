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
public class SeleccionadorDirectorios
{

    private static SeleccionadorDirectorios instance;

    public synchronized static SeleccionadorDirectorios getInstance()
    {
        if (instance == null)
            instance = new SeleccionadorDirectorios();

        return instance;
    }

    private File ultimoArchivoSeleccionado;

    private SeleccionadorDirectorios()
    {

    }

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

    public File seleccionarDirectorio(Window owner, String title)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = chooser.showOpenDialog(owner);

        if (option == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile();

        return null;
    }

    public File getUltimoArchivoSeleccionado()
    {
        return ultimoArchivoSeleccionado;
    }

}
