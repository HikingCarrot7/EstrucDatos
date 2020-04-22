package arbolb;

/**
 *
 * @author Nicolás
 */
public class Test {

    public static void main(String[] args) {
        ArbolB<Integer> arbol = new ArbolB<>(15);
        arbol.insertar(23);
        arbol.insertar(65);
        arbol.insertar(98);
        arbol.insertar(2);
        arbol.insertar(78);
        arbol.insertar(19);
        arbol.preorder();

        Integer aEliminar = 65;
        System.out.println("\n\nEliminando el entero: " + aEliminar);
        arbol.eliminar(aEliminar);
        arbol.preorder();

        Integer aAnadir = -56;
        System.out.println("\n\nAñadiendo a: " + aAnadir);
        arbol.insertar(aAnadir);
        arbol.preorder();
    }
}
