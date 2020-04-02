package expresiones;

import util.LinkedStack;

/**
 *
 * @author Nicolás
 */
public class Conversor
{

    private static final String APERTURA_PARENTESIS = "(";
    private static final String CIERRE_PARENTESIS = ")";
    private static final String SUMA = "+";
    private static final String RESTA = "-";
    private static final String MULTIPLICACION = "*";
    private static final String DIVISION = "/";
    private static final String EXPONENTE = "^";

    public static String deInfijoAPrefijo(String expresion)
    {
        StringBuilder resultado = new StringBuilder();
        String[] charsExpresionInvertida = invertirCadena(expresion).split("");
        LinkedStack<String> operadores = new LinkedStack<>();

        for (int i = 0; i < charsExpresionInvertida.length; i++)
        {
            String caracterActual = charsExpresionInvertida[i];

            if (esParentesisIzquierdo(caracterActual))
            {
                while (!esParentesisDerecho(operadores.peek()))
                    resultado.append(operadores.pop());

                operadores.pop();

            } else if (esOperador(caracterActual) || esParentesisDerecho(caracterActual))
                operadores.push(caracterActual);

            else
                resultado.append(caracterActual);
        }

        vaciarPila(resultado, operadores);
        return invertirCadena(resultado.toString());
    }

    private static void vaciarPila(StringBuilder resultado, LinkedStack<String> operadores)
    {
        while (!operadores.isEmpty())
            resultado.append(operadores.pop());
    }

    private static String invertirCadena(String cadena)
    {
        String cadenaInvertida = "";

        for (int i = cadena.length() - 1; i >= 0; i--)
            cadenaInvertida += String.valueOf(cadena.charAt(i));

        return cadenaInvertida;
    }

    private static boolean esParentesisIzquierdo(String caracter)
    {
        return caracter.equals(APERTURA_PARENTESIS);
    }

    private static boolean esParentesisDerecho(String caracter)
    {
        return caracter.equals(CIERRE_PARENTESIS);
    }

    private static boolean esOperador(String caracter)
    {
        switch (caracter)
        {
            case SUMA:
            case RESTA:
            case MULTIPLICACION:
            case DIVISION:
            case EXPONENTE:
                return true;
        }

        return false;
    }

    private static boolean esOperando(String caracter)
    {
        return Character.isDigit(caracter.toCharArray()[0]);
    }

}
