/**
 * Esta es la clase que permite ingresar y mostrar datos.
 * Universidad del Valle de Guatemala
 * @author: Mark Albrand
 * @author: Jimena Hernández
 * @author: Emily Pérez
 * @version: 2-mar-22
 */

import java.util.Scanner;

public class Vista {
    private Scanner scan = new Scanner(System.in);

    /**
     * Método para imprimir un mensaje
     * @param mensaje Mensaje a imprimir
     */
    public void mensaje(String mensaje){
        System.out.print(mensaje);
    }

    /**
     * Bienvenida del interprete
     */
    public void inicio(){
        System.out.println("\n Interprete Lisp v0.1.0 (Grupo 5) AED ");
    }

    /**
     * 
     * @return operacion
     */
    public String getOperacion(){

        mensaje("\n >>> ");
        return scan.nextLine();
    }

    /**
    * Método para salir del programa
    * 
    */
    public void salida(){

        mensaje("\n >>> ");
        System.exit(0);

    }  
    



}