/**
 * Permite ingresar y mostrar datos.
 * @author Jimena Hernandez/21199
 * @author Mark Albrand/21004
 * @author Emily Perez/21385
 * @version 24/03/2022
 * Universidad del Valle de Guatemala
 * Algoritmos y estructuras de Datos
 * Proyecto 01 - interprete LISP
 *
 * Logicas.java
 */

//LIBRERIAS
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Vista {
    
    //PROPIEDADES
    private static Scanner scan = new Scanner(System.in);

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
        System.out.println("\n Interprete Lisp v1.0.0 (Grupo 5) AED \n Ingrese '(HELP)' para ayuda. ");
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
        mensaje("\nPara definir una operacion debe ingresarlas en formato lisp. Ejemplo: (+ 2 3)\n");
        mensaje("Para definir una variable, se usa el comando 'let'. Ejemplo '(let var 1)\n");
        mensaje("Para definir una función, se usa el comando 'DEFUN'. Se necesita un parámetro para cada función. Ejemplo '(DEFUN myFUnc (x))'\n");
        mensaje("\tSe abrirá un editor, donde puede ingresar todas las líneas de las funciones. Para finalizar, utilice '(END)'\n");
        mensaje("\tPara más información: https://markalbrand56.github.io/AED-Proyecto-1/Funciones.html\n");
    }

    /**
    * Despliega el menú de opciones para el usuario. Luego, recibe la opción elegida por el usuario y la devuelve.
    * @return La opción elegida por el usuario
    */
    public static ArrayList<String> getLinea(){
        ArrayList<String> funcion = new ArrayList<String>();
		String linea;
        System.out.print("\n" +"··· ");
                
        linea = scan.nextLine();
        while(!linea.equals(("(END)"))){
            funcion.add(linea);
            System.out.print("\n" +"··· ");
            linea = scan.nextLine();
        } 
		return funcion;
    }

}