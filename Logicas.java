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

    /**
     * Metodo que evalua si los numeros son iguales
     * @param x
     * @param y
     * @return
     */
    public static boolean equal (int x, int y){
        return x == y;
    } 

    /**
     * Metodo que evalua si los numeros son desiguales
     * @param x
     * @param y
     * @return
     */
    public static boolean nequal (int x, int y){
        return x != y;
    } 

    /**
     * Metodo que evalua si x es mayor que y
     * @param x
     * @param y
     * @return
     */
    public static boolean greater (int x, int y){
        return x > y;
    } 

    /**
     * Metodo que evalua si x es mayor o igual que y
     * @param x
     * @param y
     * @return
     */
    public static boolean greaterEq (int x, int y){
        return x >= y;
    } 

    /**
     * Metodo que evalua si x es menor que y
     * @param x
     * @param y
     * @return
     */
    public static boolean less (int x, int y){
        return x < y;
    } 

    /**
     * Metodo que evalua si x es menos o igual que y
     * @param x
     * @param y
     * @return
     */
    public static boolean lessEq (int x, int y){
        return x <= y;
    } 

    
    /**
     * Metodo que evalua si los numeros son iguales
     * @param x
     * @param y
     * @return
     */
    public static boolean equal (Double x, Double y){
        return x == y;
    } 

    /**
     * Metodo que evalua si los numeros son desiguales
     * @param x
     * @param y
     * @return
     */
    public static boolean nequal (Double x, Double y){
        return x != y;
    } 

    /**
     * Metodo que evalua si x es mayor que y
     * @param x
     * @param y
     * @return
     */
    public static boolean greater (Double x, Double y){
        return x > y;
    } 

    /**
     * Metodo que evalua si x es mayor o igual que y
     * @param x
     * @param y
     * @return
     */
    public static boolean greaterEq (Double x, Double y){
        return x >= y;
    } 

    /**
     * Metodo que evalua si x es menor que y
     * @param x
     * @param y
     * @return
     */
    public static boolean less (Double x, Double y){
        return x < y;
    } 

    /**
     * Metodo que evalua si x es menos o igual que y
     * @param x
     * @param y
     * @return
     */
    public static boolean lessEq (Double x, Double y){
        return x <= y;
    } 
}