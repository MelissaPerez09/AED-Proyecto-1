
/**
 * Universidad del Valle de Guatemala
 * @author Jimena Hernandez/21199
 * @author Mark Albrand/21004
 * @author Emily Perez/21385
 * @version 03/03/2022
 * Algoritmos y estructuras de Datos 
 * Proyecto 01 - interprete LISP
 * 
 * Aritmeticos.java 
 * Desarrolla las operaciones aritmeticas
 */

public class Aritmeticos{
    double resultado = 1;

    /**
     * Metodo que realiza la suma con doubles
     * @param x
     * @param y
     * @return
     */
    public static double add (double x, double y){
        return x + y;
    }

    /**
     * Metodo que realiza la suma con un numeros enteros
     * @param x
     * @param y
     * @return
     */
    public static int add (int x, int y){
        return x + y;
    }

    /**
     * Metodo que realiza la resta con un numeros doubles
     * @param x
     * @param y
     * @return
     */
    public static double sub (double x, double y){
        return x - y;
    }

    /**
     * Metodo que realiza la resta con un numeros enteros
     * @param x
     * @param y
     * @return
     */
    public static int sub (int x, int y){
        return x - y;
    }

    /**
     * Metodo que realiza la multiplicacion con un numeros doubles
     * @param x
     * @param y
     * @return
     */
    public static double mult (double x, double y){
        return x * y;
    }

    /**
     * Metodo que realiza la multiplicacion con un numeros enteros
     * @param x
     * @param y
     * @return
     */
    public static int mult (int x, int y){
        return x * y;
    }

    /**
     * Metodo que realiza la division con un numeros doubles
     * @param x
     * @param y
     * @return
     */
    public static double div (double x, double y){
        return x / y;
    }

    /**
     * Metodo que realiza la division con un numeros enteros
     * @param x
     * @param y
     * @return
     */
    public static int div (int x, int y){
        return x / y;
    }

    /**
     * Metodo que realiza el incremento con un numero double
     * @param x
     * @return
     */
    public static double incr (double x){
        return ++x;
    }

    /**
     * Metodo que realiza el incremento con un numero entero
     * @param x
     * @return
     */
    public static int incr (int x){
        return ++x;
    }

    /**
     * Metodo que realiza la disminucion con un numero double
     * @param x
     * @return
     */
    public static double decr (double x){
        return --x;
    }

    /**
     * Metodo que realiza la disminucion con un numero entero
     * @param x
     * @return
     */
    public static int decr (int x){
        return --x;
    }

    /**
     * Metodo que realiza el valor abosluto con un numero double
     * @param x
     * @return
     */
    public static double abs (double x){
        return Math.abs(x);
    }

    /**
     * Metodo que realiza el valor absoluto con un numero entero
     * @param x
     * @return
     */
    public static int abs (int x){
        return Math.abs(x);
    }
}