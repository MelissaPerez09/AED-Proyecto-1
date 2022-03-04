import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public String parse(String linea){
        Pattern pattern;
        Matcher matcher;

        // Operaciones aritméticas simples
        pattern = Pattern.compile("^[(]{1}[+*-/]+ [0-9]+ [0-9]+[)]{1}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            String[] datos = linea.split(" ");
            String a = datos[1];
            String b = datos[2];

            if(a.contains(".") || b.contains(".")){
                // Número con decimales
                double x = Double.parseDouble(a);
                double y = Double.parseDouble(b);

                return "";
            } else{
                // Número entero
                int x = Integer.parseInt(a);
                int y = Integer.parseInt(b);

                return "";
            }
        }
        return "Expresión inválida. Ingrese (EXIT) para salir.";
    }
}
