package ada_2;

import excepciones.PilaVaciaException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author HikingCarrot7
 */
public class Fichero
{

    public static final String RUTA_FICHERO = "datos\\Data.txt";
    private File file;

    public Fichero() throws FileNotFoundException
    {

        file = new File(RUTA_FICHERO);

        if (!file.exists())
            throw new FileNotFoundException("No se puede encontrar el archivo.");

    }

    public boolean llavesValidas()
    {

        ArrayStack<String> pila = new ArrayStack<>(50);

        try (Scanner in = new Scanner(file))
        {

            while (in.hasNext())
            {

                Object[] caracteres = Arrays.asList(in.nextLine().split(""))
                        .stream()
                        .filter(x -> !x.equals(" "))
                        .collect(Collectors.toList())
                        .toArray();

                for (Object c : caracteres)
                    if (c.equals("{"))
                        pila.push(c.toString());

                    else if (c.equals("}"))
                        pila.pop();

            }

        } catch (FileNotFoundException | PilaVaciaException ex)
        {
            System.out.println(ex.getMessage());
        }

        return pila.isEmpty();

    }

    public void contarPalabras()
    {

        try
        {

            Map<String, Long> palabras = Files.lines(Paths.get(RUTA_FICHERO))
                    .map(line -> line.split("[((.)+)|\\s+]"))
                    .flatMap(Arrays::stream)
                    .filter(x -> !x.equals(" "))
                    .collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()));

            palabras.entrySet()
                    .stream()
                    .collect(Collectors.groupingBy(entry -> entry.getKey().equals("") ? ' ' : entry.getKey().charAt(0), TreeMap::new, Collectors.toList()))
                    .forEach((letter, words) ->
                    {
                        System.out.printf("%n%C%n", letter);
                        words.forEach(palabra -> System.out.printf("%13s: %d%n", palabra.getKey(), palabra.getValue()));
                    });

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}
