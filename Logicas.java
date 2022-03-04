/**
 * Universidad del Valle de Guatemala
 * @author Jimena Hernandez/21199
 * @author Mark Albrand/21004
 * @author Emily Perez/21385
 * @version 03/03/2022
 * Algoritmos y estructuras de Datos 
 * Proyecto 01 - interprete LISP
 * 
 * Logicas.java 
 * Desarrolla las operaciones logicas
 */

public class Logicas{
    boolean resultado = true;

    public static boolean equal (int x, int y){
        return x == y;
    } 

    public static boolean nequal (int x, int y){
        return x != y;
    } 

    public static boolean greater (int x, int y){
        return x > y;
    } 

    public static boolean greaterEq (int x, int y){
        return x >= y;
    } 

    public static boolean less (int x, int y){
        return x < y;
    } 

    public static boolean lessEq (int x, int y){
        return x <= y;
    } 
}