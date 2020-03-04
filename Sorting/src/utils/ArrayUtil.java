package utils;

/**
 *
 * @author nicol
 */
public class ArrayUtil
{

    public static int[] generarNumerosAleatorios(int nNumeros)
    {
        int[] numeros = new int[nNumeros];

        for (int i = 0; i < numeros.length; i++)
            numeros[i] = (int) (Math.random() * 100000);

        return numeros;
    }

}
