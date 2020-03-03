package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author HikingCarrot7
 */
public class MezclaDirecta
{

    static void mezclaDirecta(File f) throws IOException
    {
        int longSec;
        int numReg;
        File f1 = new File("ArchivoAux1.dat");
        File f2 = new File("ArchivoAux2.dat");
        /* número de registros se obtiene dividiendo el tamaño
del archivo por el tamaño del registro: 4.
         */
        numReg = (int) f.length() / 4;
        longSec = 1;

        while (longSec < numReg)
        {
            distribuir(f, f1, f2, longSec, numReg);
            mezclar(f1, f2, f, longSec, numReg);
            longSec *= 2;
        }

    }

    static void distribuir(File f, File f1, File f2, int longSec, int numReg) throws IOException
    {
        int numSec, resto, i;

        DataInputStream flujo = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
        DataOutputStream flujo1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f1)));
        DataOutputStream flujo2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f2)));
        numSec = numReg / (2 * longSec);
        resto = numReg % (2 * longSec);

//distribuye secuencias de longitud longSec
        for (i = 1; i <= numSec; i++)
        {
            subSecuencia(flujo, flujo1, longSec);
            subSecuencia(flujo, flujo2, longSec);
        }
        /*
Se procesa el resto de registros del archivo
         */
        if (resto > longSec)
            resto -= longSec;
        else
        {
            longSec = resto;
            resto = 0;
        }

        subSecuencia(flujo, flujo1, longSec);
        subSecuencia(flujo, flujo2, resto);

        flujo.close();
        flujo1.close();
        flujo2.close();
    }

    private static void subSecuencia(DataInput f, DataOutput t, int longSec) throws IOException
    {
        int clave;
//escribe en el flujo t el dato entero leído de f
        for (int j = 1; j <= longSec; j++)
        {
            clave = f.readInt();
            t.writeInt(clave);
        }
    }

    static void mezclar(File f1, File f2, File f, int lonSec, int numReg) throws IOException
    {
        int numSec, resto, i, j, k;
        int clave1 = 0, clave2 = 0;
        numSec = numReg / (2 * lonSec); // número de subsecuencias
        resto = numReg % (2 * lonSec);

        DataOutputStream flujo = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(f)));
        DataInputStream flujo1 = new DataInputStream(
                new BufferedInputStream(new FileInputStream(f1)));
        DataInputStream flujo2 = new DataInputStream(
                new BufferedInputStream(new FileInputStream(f2)));
//claves iniciales
        clave1 = flujo1.readInt();
        clave2 = flujo2.readInt();
//bucle para controlar todo el proceso de mezcla
        for (int s = 1; s <= numSec + 1; s++)
        {
            int n1, n2;
            n1 = n2 = lonSec;
            if (s == numSec + 1) // proceso de subsecuencia incompleta
                if (resto > lonSec)
                    n2 = resto - lonSec;
                else
                {
                    n1 = resto;
                    n2 = 0;
                }

            i = j = 1;
            while (i <= n1 && j <= n2)
            {
                int clave;
                if (clave1 < clave2)
                {
                    clave = clave1;

                    try
                    {
                        clave1 = flujo1.readInt();

                    } catch (EOFException e)
                    {
                        System.out.println(e.getMessage());
                    }

                    i++;

                } else
                {
                    clave = clave2;

                    try
                    {
                        clave2 = flujo2.readInt();

                    } catch (EOFException e)
                    {
                        System.out.println(e.getMessage());
                    }

                    j++;
                }

                flujo.writeInt(clave);
            }
            /*
Los registros no procesados se escriben directamente
             */
            for (k = i; k <= n1; k++)
            {
                flujo.writeInt(clave1);

                try
                {
                    clave1 = flujo1.readInt();

                } catch (EOFException e)
                {
                    System.out.println(e.getMessage());
                }
            }

            for (k = j; k <= n2; k++)
            {
                flujo.writeInt(clave2);
                try
                {
                    clave2 = flujo2.readInt();

                } catch (EOFException e)
                {
                    System.out.println(e.getMessage());
                }

            }

        }

        flujo.close();
        flujo1.close();
        flujo2.close();
    }

    static void leer(File f) throws FileNotFoundException, IOException
    {
        int clave, k;
        boolean mas = true;
        DataInputStream flujo = null;

        try
        {
            flujo = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(f)));
            k = 0;
            System.out.println("ARCHIVO DE CLAVES TIPO INT");
            while (mas)
            {
                k++;
                System.out.print(flujo.readInt() + " ");
                if (k % 11 == 0)
                    System.out.println();
            }

        } catch (EOFException e)
        {
            System.out.println("n *** Fin del archivo ***n");

            try
            {
                flujo.close();
            } catch (IOException er)
            {
                er.printStackTrace();
            }
        }
    }

    public static void crearArchivo(File Ffichero)
    {

// crear un objeto de tipo archivo
        DataOutputStream archivo = null;
// creando e inicializando los campos del registro
// observar que se debe usar clases numericas apropiadas
        int clave = 0;
        String nombre = new String("");
        int edad = 0;
// creando objeto teclado
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
// abriendo archivo, capturando y grabando datos
        try
        {
//* Creando y grabando a un archivo, esta larga la instrucción*/
            archivo = new DataOutputStream(new FileOutputStream(Ffichero, true));
            System.out.println("dame clave: ");
            clave = Integer.parseInt(teclado.readLine());

//grabando al archivo
            archivo.writeInt(clave);

            archivo.close();
        } catch (FileNotFoundException e)
        {
            /* Archivo no encontrado */

        } catch (IOException e)
        {
            /* Error al escribir */

        }
    }

    public static void main(String[] a) throws FileNotFoundException, IOException
    {
        Scanner s = new Scanner(System.in);
        File f = new File("ArchivoOrigen.dat");

        int opcion = 0;
        while (opcion != 4)
        {
            System.out.println("menun"
                    + "1.-agregarn"
                    + "2.-leern"
                    + "3.-ordenarn"
                    + "4.-salir");
            System.out.println("que opcion desea realizar");
            opcion = s.nextInt();
            switch (opcion)
            {
                case 1:
                    crearArchivo(f);
                    System.out.println("dato agregado");
                    break;
                case 2:
                    leer(f);
                    System.out.println("datos mostrados con exito");
                    break;
                case 3:
                    mezclaDirecta(f);
                    System.out.println("Datos ordenados exitosamente");
                case 4:
                    System.out.println("programa finalizado");
                    break;
                default:
                    System.out.println("opcion invalida");
                    break;
            }

        }
    }

}
