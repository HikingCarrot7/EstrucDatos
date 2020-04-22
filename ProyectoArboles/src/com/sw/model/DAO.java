package com.sw.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Nicolás
 */
public class DAO implements Loader<Egresado[]>
{

    private final String RUTA_ARCHIVO;

    public DAO(String RUTA_ARCHIVO)
    {
        this.RUTA_ARCHIVO = RUTA_ARCHIVO;
    }

    @Override
    public Egresado[] loadData()
    {
        try
        {
            ArrayList<Egresado> lista = Files.lines(Paths.get(RUTA_ARCHIVO))
                    .map(line -> line.split(","))
                    .map(data -> new Egresado(data[0].trim(), data[1].trim(), Double.parseDouble(data[2].trim())))
                    .collect(Collectors.toCollection(ArrayList::new));

            Egresado[] egresados = new Egresado[lista.size()];

            for (int i = 0; i < lista.size(); i++)
                egresados[i] = lista.get(i);

            return egresados;

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        return null;
    }

}
