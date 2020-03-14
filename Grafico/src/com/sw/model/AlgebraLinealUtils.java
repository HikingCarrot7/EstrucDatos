package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
public class AlgebraLinealUtils
{

    public static double getDeterminante2x2(double v00, double v01, double v10, double v11)
    {
        return getDeterminante(0, new double[][]
        {
            {
                v00, v01
            },
            {
                v10, v11
            }
        });
    }

    public static double getDeterminante(int i, double[][] matriz)
    {
        if (matriz.length == 2)
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];

        else
        {
            double deter = 0;

            for (int j = 0; j < matriz.length; j++)
            {
                double[][] subMatriz = getSubMatriz(i, j, matriz);
                deter += Math.pow(-1, i + j) * matriz[i][j] * getDeterminante(i, subMatriz);
            }

            return deter;

        }

    }

    public static double[][] getSubMatriz(int fila, int columna, double[][] matriz)
    {
        double[][] subMatriz = new double[matriz.length - 1][matriz.length - 1];

        for (int i = 0, k = 0; i < matriz.length; i++)
            for (int j = 0, l = 0; i != fila && j < matriz.length; j++)
                if (j != columna)
                    subMatriz[l == subMatriz.length - 1 ? k++ : k][l++] = matriz[i][j];

        return subMatriz;
    }

    public static double[][] getAdjunta(double[][] matriz)
    {
        double[][] matrizCofactores = new double[matriz.length][matriz.length];

        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz.length; j++)
                matrizCofactores[i][j] = Math.pow(-1, i + j) * getDeterminante(0, getSubMatriz(i, j, matriz));

        return getTranspuesta(matrizCofactores);

    }

    public static double[][] getTranspuesta(double[][] matriz)
    {
        double[][] matrizTranspuesta = new double[matriz.length][matriz.length];

        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz.length; j++)
                matrizTranspuesta[i][j] = matriz[j][i];

        return matrizTranspuesta;

    }

    public static double[][] multiplicarPorEscalar(double escalar, double[][] matriz)
    {
        for (double[] fila : matriz)
            for (int j = 0; j < matriz.length; j++)
                fila[j] *= escalar;

        return matriz;

    }
}
