package hash;

/**
 *
 * @author Nicolás
 */
public class HashFunctions
{

    public long sumandoDigitos(String palabra)
    {
        char[] letras = palabra.toCharArray();
        long hash = 0;

        for (char letra : letras)
            hash += valorCaracter(letra);

        return hash;
    }

    public long multiplicarPotencia27(String palabra)
    {
        char[] letras = palabra.toCharArray();
        long hash = 0;

        for (int i = letras.length - 1, j = 0; i >= 0; i--, j++)
            hash += valorCaracter(letras[j]) * Math.pow(27, i);

        return hash;
    }

    private int valorCaracter(char caracter)
    {
        if (caracter < 97)
            return caracter + 91;

        return caracter - 96;
    }
}
