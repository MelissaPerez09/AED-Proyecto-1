
/**
 * Universidad del Valle de Guatemala
 * @author Jimena Hernandez/21199
 * @author Mark Albrand/21004
 * @author Emily Perez/21385
 * @version 25/02/2022
 * Algoritmos y estructuras de Datos 
 * Proyecto 01 - interprete LISP
 * 
 * Aritmeticos.java 
 * Desarrolla las operaciones aritmeticas
 */

public class Aritmeticos{
    double resultado = 1;

    public static double add (double x, double y){
        return x + y;
    }

    public static int add (int x, int y){
        return x + y;
    }

    public static double sub (double x, double y){
        return x - y;
    }

    public static int sub (int x, int y){
        return x - y;
    }

    public static double mult (double x, double y){
        return x * y;
    }

    public static int mult (int x, int y){
        return x * y;
    }

    public static double div (double x, double y){
        return x / y;
    }

    public static int div (int x, int y){
        return x / y;
    }

    public static double incr (double x){
        return x ++;
    }

    public static int incr (int x){
        return x ++;
    }

    public static double decr (double x){
        return x--;
    }

    public static int decr (int x){
        return x--;
    }

    public static double abs (double x){
        return Math.abs(x);
    }

    public static int abs (int x){
        return Math.abs(x);
    }
}