/**
 * Esta es la clase que permite hacer las operaciones.
 * Universidad del Valle de Guatemala
 * @author: Mark Albrand
 * @author: Jimena Hernández
 * @author: Emily Pérez
 * @version: 2-mar-22
 */
public class Controlador {

    /**
    * Main del programa.
    **/ 
    private static Vista vista = new Vista();
    public static void main(String[] args){
        Parser parser= new Parser();

        vista.inicio();
        vista.mensaje(parser.parse(vista.getOperacion()));
        
    }

}
