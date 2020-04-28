package com.sw.model.persistence;

import com.sw.model.Egresado;
import com.sw.model.exceptions.RutaInvalidaException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Nicolás
 */
public class DAO implements Loader<ArrayList<Egresado>>
{

    private final String RUTA_ARCHIVO;

    public DAO(String RUTA_ARCHIVO)
    {
        this.RUTA_ARCHIVO = RUTA_ARCHIVO;
    }

    @Override
    public ArrayList<Egresado> load()
    {
        try
        {
            return Files.lines(Paths.get(RUTA_ARCHIVO))
                    .map(line -> line.split(","))
                    .map(data -> new Egresado(data[0].trim(), data[1].trim(), Double.parseDouble(data[2].trim())))
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException ex)
        {
            throw new RutaInvalidaException();
        }
    }

}
