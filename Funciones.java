import java.util.ArrayList;

public class Funciones {
    private String param;
    private String nombre;
    private ArrayList<String> procesos;

    // (DEFUN FUN (X))
    public Funciones(String definicion, ArrayList<String> lineas){
        definicion = definicion.replace("(", "");
        definicion = definicion.replace(")", "");

        String[] datos = definicion.split(" ");
        this.nombre = datos[1];
        this.param = datos[2];

        for (int i = 0; i<lineas.size(); i++){
            this.procesos.add(lineas.get(i));
        }
    }

}
