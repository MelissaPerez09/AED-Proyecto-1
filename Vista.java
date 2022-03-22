/**
 * Esta es la clase que permite ingresar y mostrar datos.
 * Universidad del Valle de Guatemala
 * @author Mark Albrand
 * @author Jimena Hernández
 * @author Emily Pérez
 * @version 2-mar-22
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
        System.out.println("\n Interprete Lisp v0.2.0 (Grupo 5) AED \n Ingrese '(HELP)' para ayuda. ");
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

    /**
    * Método para pedir ayuda
    * 
    */
    public void ayuda(){
        mensaje("\n >>>Help: ");
        mensaje("\n Para definir una operacion debe ingresarlas en formato lisp. Ejemplo: (+ 2 3)\n Para definir una variable debe ingresarla de la siguiente forma: (let nombredelavaribale valordelavariable)");
    }

}