package com.sw.model.hashtable;

import java.io.Serializable;

/**
 *
 * @author HikingCarrot7
 * @param <K>
 * @param <V>
 */
@SuppressWarnings("unchecked")
public class Hashtable<K, V> implements Serializable
{

    private static final long serialVersionUID = 1L;

    private static int TAMTABLA = 11;
    private int numElementos;
    private double factorCarga;
    private Entry<K, V>[] valores;

    public Hashtable()
    {
        valores = new Entry[TAMTABLA];

        for (int j = 0; j < TAMTABLA; j++)
            valores[j] = null;

        numElementos = 0;
        factorCarga = 0.0;
    }

    private int direccion(K key)
    {
        int i = 0, p;
        long d;

        d = key.hashCode();

        // Aplica aritmética modular para obtener dirección base.
        p = (int) (d % TAMTABLA);
        if (p < 0)
            p = -p;

        // bucle de exploración
        while (valores[p] != null && !valores[p].getKey().equals(key))
        {
            i++;
            p += (i * i) + 1;
            p %= TAMTABLA; // Considera el array como circular.
        }

        return p;
    }

    public void put(K key, V value)
    {
        int posicion;
        posicion = direccion(key);
        valores[posicion] = new Entry(key, value);
        numElementos++;
        factorCarga = (double) (numElementos) / TAMTABLA;

        if (factorCarga > 0.7)
            aumentarTamano();
    }

    public V get(K key)
    {
        int posicion;

        posicion = direccion(key);
        Entry<K, V> entrada = valores[posicion];

        if (entrada == null)
            return null;

        return entrada.getValue();
    }

    public V remove(K key)
    {
        int posicion;
        V value = null;
        posicion = direccion(key);

        if (valores[posicion] != null)
        {
            value = valores[posicion].getValue();
            valores[posicion] = null;
            numElementos--;
        }

        return value;
    }

    public boolean containsKey(K key)
    {
        V element = get(key);
        return element != null;
    }

    public void imprimirTabla()
    {
        for (int i = 0; i < valores.length; i++)
            System.out.println(i + ".- " + valores[i]);
    }

    private void aumentarTamano()
    {
        factorCarga = 0;
        numElementos = 0;
        Entry<K, V> valoresAnteriores[] = valores.clone();
        TAMTABLA *= 2;
        valores = new Entry[TAMTABLA];

        //Se vueven a dispersar los valores
        for (Entry<K, V> entry : valoresAnteriores)
            if (entry != null)
            {
                Entry<K, V> valor = entry;
                put(valor.key, valor.value);
            }

    }

    public int size()
    {
        return numElementos;
    }

    public boolean isEmpty()
    {
        return numElementos == 0;
    }

    private class Entry<K, V> implements Serializable
    {

        private static final long serialVersionUID = 1L;

        private K key;
        private V value;

        public Entry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        public K getKey()
        {
            return key;
        }

        public V getValue()
        {
            return value;
        }

        public void setKey(K key)
        {
            this.key = key;
        }

        public void setValue(V value)
        {
            this.value = value;
        }

        @Override
        public String toString()
        {
            return "Entry{" + "key=" + key + ", value=" + value + '}';
        }

    }

}
