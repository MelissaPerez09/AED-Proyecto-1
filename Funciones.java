import java.util.ArrayList;

public class Funciones {
    private String param;
    private String nombre;
    private ArrayList<String> procesos;

    // (DEFUN FUN (X))
    public Funciones(ArrayList<String> lineas){
        String definicion = lineas.get(0);
        definicion = definicion.replace("(", "");
        definicion = definicion.replace(")", "");

        String[] datos = definicion.split(" ");
        this.nombre = datos[1];
        this.param = datos[2];

        for (int i = 1; i<lineas.size(); i++){
            this.procesos.add(lineas.get(i));
        }
    }

}
