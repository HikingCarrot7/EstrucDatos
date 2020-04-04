package expresiones;

import static expresiones.Operador.DIVISION;
import static expresiones.Operador.ESPACIO;
import static expresiones.Operador.MULTIPLICACION;
import static expresiones.Operador.RESTA;
import static expresiones.Operador.SUMA;
import static expresiones.Operador.esEspacio;
import static expresiones.Operador.esOperador;
import static expresiones.Operador.esParentesis;
import static expresiones.Operador.esParentesisDerecho;
import static expresiones.Operador.esParentesisIzquierdo;
import util.LinkedStack;
import util.Stack;

/**
 *
 * @author Nicolás
 */
public class Conversor
{

    public static String deInfijoAPrefijo(String expresion)
    {
        StringBuilder resultado = new StringBuilder();
        String[] tokens = invertirCadena(expresion).split("");
        Stack<String> operadores = new LinkedStack<>();

        for (String token : tokens)
            if (esParentesisIzquierdo(token))
            {
                while (!esParentesisDerecho(operadores.peek()))
                    resultado.append(operadores.pop());

                operadores.pop();

            } else if (esOperador(token) || esParentesisDerecho(token))
                operadores.push(token);

            else
                resultado.append(token);

        vaciarPila(resultado, operadores);
        return invertirCadena(resultado.toString());
    }

    public static String deInfijoAPostfijo(String expresion)
    {
        StringBuilder resultado = new StringBuilder();
        String[] tokens = expresion.split("");
        Stack<String> pila = new LinkedStack<>();

        for (int i = 0; i < tokens.length; i++)
        {
            if (esEspacio(tokens[i]))
                continue;

            if (esParentesisIzquierdo(tokens[i]))
            {
                pila.push(tokens[i]);
                continue;
            }

            if (esParentesisDerecho(tokens[i]))
            {
                while (!esParentesisIzquierdo(pila.peek()))
                    resultado.append(pila.pop()).append(ESPACIO);

                pila.pop();
                continue;
            }

            if (esOperador(tokens[i]))
            {
                if (!pila.isEmpty() && procedencia(pila.peek(), tokens[i]))
                    resultado.append(pila.pop()).append(ESPACIO);

                pila.push(tokens[i]);

            } else
            {
                while (i < tokens.length && !(esOperador(tokens[i]) || esParentesis(tokens[i]) || esEspacio(tokens[i])))
                    resultado.append(tokens[i++]);

                resultado.append(ESPACIO);
                i--;
            }

        }

        vaciarPila(resultado, pila);
        return resultado.substring(0, resultado.length() - 1);
    }

    private static void vaciarPila(StringBuilder resultado, Stack<String> operadores)
    {
        while (!operadores.isEmpty())
            resultado.append(operadores.pop()).append(ESPACIO);
    }

    private static boolean procedencia(String caracter1, String caracter2)
    {
        if ((caracter1.equals(MULTIPLICACION) || caracter1.equals(DIVISION))
                && (caracter2.equals(SUMA) || caracter2.equals(RESTA)))
            return true;

        else if ((caracter1.equals(SUMA) || caracter1.equals(RESTA))
                && (caracter2.equals(SUMA) || caracter2.equals(RESTA)))
            return true;

        else if ((caracter1.equals(MULTIPLICACION) || caracter1.equals(DIVISION))
                && (caracter2.equals(MULTIPLICACION) || caracter2.equals(DIVISION)))
            return true;

        return false;
    }

    private static String invertirCadena(String cadena)
    {
        String cadenaInvertida = "";

        for (int i = cadena.length() - 1; i >= 0; i--)
            cadenaInvertida += String.valueOf(cadena.charAt(i));

        return cadenaInvertida;
    }

}
