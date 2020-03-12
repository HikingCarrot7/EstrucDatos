package model;

import controller.Directorio;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;

/**
 *
 * @author HikingCarrot7
 */
public class DAODirectorios
{

    private final String RUTA_LISTA_ARCHIVOS = "data.dat";
    private final String SALTO_LINEA = "\r\n";
    private final String SEPARADOR = "<";

    private File file;

    public DAODirectorios()
    {
        file = new File(RUTA_LISTA_ARCHIVOS);

        if (!file.exists())
            try
            {
                file.createNewFile();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    public void guardarDirectorios(List<Directorio> directorios)
    {
        String result = "";
        result = directorios.stream()
                .map(d -> d.getNombre() + SEPARADOR + d.getRuta() + SEPARADOR + d.getFecha() + SALTO_LINEA)
                .reduce(result, String::concat);

        try (Formatter out = new Formatter(new FileWriter(file, false)))
        {
            out.format("%s", result);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public List<Directorio> getDirectorios()
    {
        List<Directorio> directorios = FXCollections.observableArrayList();

        try (Scanner in = new Scanner(file))
        {
            while (in.hasNext())
            {
                String[] directorio = in.nextLine().split(SEPARADOR);
                directorios.add(new Directorio(directorio[0], directorio[1], directorio[2]));
            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return directorios;
    }

    public File getFile()
    {
        return file;
    }

}
