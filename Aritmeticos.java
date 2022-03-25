/**
 * Desarrolla las operaciones aritmeticas, con o sin decimales
 * @author Jimena Hernandez/21199
 * @author Mark Albrand/21004
 * @author Emily Perez/21385
 * @version 24/03/2022
 * Universidad del Valle de Guatemala
 * Algoritmos y estructuras de Datos
 * Proyecto 01 - interprete LISP
 *
 * Aritmeticos.java
 */

public class Aritmeticos{
    
    //PROPIEDADES
    double resultado = 1;

    /**
     * Metodo que realiza la suma con doubles
     * @param x Primer valor con decimal
     * @param y Segundo valor con decimal
     * @return Resultado de la suma
     */
    public static double add (double x, double y){
        return x + y;
    }

    /**
     * Metodo que realiza la suma con un numeros enteros
     * @param x Primer valor entero
     * @param y Segundo valor entero
     * @return Resultado de la suma
     */
    public static int add (int x, int y){
        return x + y;
    }

    /**
     * Metodo que realiza la resta con un numeros doubles
     * @param x Primer valor con decimal
     * @param y Segundo valor con decimal
     * @return Resultado de la resta
     */
    public static double sub (double x, double y){
        return x - y;
    }

    /**
     * Metodo que realiza la resta con un numeros enteros
     * @param x Primer valor entero
     * @param y Segundo valor entero
     * @return Resultado de la resta
     */
    public static int sub (int x, int y){
        return x - y;
    }

    /**
     * Metodo que realiza la multiplicacion con un numeros doubles
     * @param x Primer valor con decimal
     * @param y Segundo valor con decimal
     * @return Resultado de la multiplicación
     */
    public static double mult (double x, double y){
        return x * y;
    }

    /**
     * Metodo que realiza la multiplicacion con un numeros enteros
     * @param x Primer valor entero
     * @param y Segundo valor entero
     * @return Resultado de la multiplicación
     */
    public static int mult (int x, int y){
        return x * y;
    }

    /**
     * Metodo que realiza la division con un numeros doubles
     * @param x Primer valor con decimal
     * @param y Segundo valor con decimal
     * @return Resultado de la división
     */
    public static double div (double x, double y){
        return x / y;
    }

    /**
     * Metodo que realiza la division con un numeros enteros
     * @param x Primer valor entero
     * @param y Segundo valor entero
     * @return Resultado de la división
     */
    public static int div (int x, int y){
        return x / y;
    }

    /**
     * Metodo que realiza el incremento con un numero double
     * @param x Primer valor con decimal
     * @return Resultado de la incrementación
     */
    public static double incr (double x){
        return ++x;
    }

    /**
     * Metodo que realiza el incremento con un numero entero
     * @param x Primer valor entero
     * @return Resultado de la incrementación
     */
    public static int incr (int x){
        return ++x;
    }

    /**
     * Metodo que realiza la disminucion con un numero double
     * @param x Primer valor decimal
     * @return Resultado de la decrementación
     */
    public static double decr (double x){
        return --x;
    }

    /**
     * Metodo que realiza la disminucion con un numero entero
     * @param x Primer valor entero
     * @return Resultado de la decrementación
     */
    public static int decr (int x){
        return --x;
    }

    /**
     * Metodo que realiza el valor abosluto con un numero double
     * @param x Primer valor con decimal
     * @return Resultado del valor absoluto
     */
    public static double abs (double x){
        return Math.abs(x);
    }

    /**
     * Metodo que realiza el valor absoluto con un numero entero
     * @param x Primer valor entero
     * @return Resultado del valor absoluto
     */
    public static int abs (int x){
        return Math.abs(x);
    }
}