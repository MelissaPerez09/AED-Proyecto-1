import java.util.ArrayList;

public class Funciones {
    private String param;
    private String nombre;
    private ArrayList<String> procesos = new ArrayList<>();

    // (DEFUN FUN (X))
    public Funciones(String definicion, ArrayList<String> lineas){
        definicion = definicion.replace("(", "");
        definicion = definicion.replace(")", "");

        String[] datos = definicion.split(" ");
        this.nombre = datos[1];
        this.param = datos[2];

        for (int i = 0; i<lineas.size(); i++){
            if(lineas.get(i) != null){
                this.procesos.add(lineas.get(i));
            }

        }
    }

    public String getNombre() {
        return nombre;
    }

    public String eval(String var){
        Parser localParser = new Parser();
        if(var.contains(".")){
            double resultado = 0;
            for (int i = 0; i < procesos.size(); i++) {
                String linea = procesos.get(i).replace(param, var);
                resultado += Double.parseDouble(localParser.parse(linea));
            }
            return Double.toString(resultado);
        }
         return "ERROR";

    }

}
