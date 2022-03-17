/**
 * Esta es la clase que evalúa una operación del lenguaje LISP
 * Universidad del Valle de Guatemala
 * @author: Mark Albrand
 * @author: Jimena Hernández
 * @author: Emily Pérez
 * @version: 2-mar-22
 */

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.spi.DirStateFactory.Result;

public class Parser {
    private HashMap<String, String> variables = new HashMap<String, String>();  // Almacenamiento de las variables personales

    /**
     * Método principal para evaluar una línea a código LISP
     * @param linea Línea ingresada por el usuario
     * @return El resultado de la operación en formato String
     */
    public String parse(String linea){
        Pattern pattern;
        Matcher matcher;

        // Operaciones aritméticas simples
        pattern = Pattern.compile("^[(]{1}[+*-/]{1} [0-9.]+ [0-9.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String a = datos[1];
            String b = datos[2];

            if(Objects.equals(datos[0], "+")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);

                    double resultado = Aritmeticos.add(x, y);

                    return Double.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);

                    int resultado = Aritmeticos.add(x, y);

                    return Integer.toString(resultado);
                }
            } else if(Objects.equals(datos[0], "-")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);

                    double resultado = Aritmeticos.sub(x, y);

                    return Double.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);

                    int resultado = Aritmeticos.sub(x, y);

                    return Integer.toString(resultado);
                }
            } else if(Objects.equals(datos[0], "/")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);

                    double resultado = Aritmeticos.div(x, y);

                    return Double.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);

                    int resultado = Aritmeticos.div(x, y);

                    return Integer.toString(resultado);
                }
            } else if(Objects.equals(datos[0], "*")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);

                    double resultado = Aritmeticos.mult(x, y);

                    return Double.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);

                    int resultado = Aritmeticos.mult(x, y);

                    return Integer.toString(resultado);
                }
            }
        }

        // Operaciones aritméticas simples (Una variable)
        pattern = Pattern.compile("^[(]{1}[+\\-]{2} [0-9]+[)]{1}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String a = datos[1];

            if(Objects.equals(datos[0], "++")){
                if(a.contains(".")){
                    double x = Double.parseDouble(a);

                    double resultado = Aritmeticos.incr(x);

                    return Double.toString(resultado);
                }else{
                    int x = Integer.parseInt(a);

                    int resultado = Aritmeticos.incr(x);

                    return Integer.toString(resultado);
                }
            }else if (Objects.equals(datos[0], "--")){
                if(a.contains(".")){
                    double x = Double.parseDouble(a);

                    double resultado = Aritmeticos.decr(x);

                    return Double.toString(resultado);
                }else{
                    int x = Integer.parseInt(a);

                    int resultado = Aritmeticos.decr(x);

                    return Integer.toString(resultado);
                }
            }
        }

        //operaciones dentro de operaciones con dos paréntesis a la derecha
        pattern = Pattern.compile("^[(]{1}[+*\\-/] [0-9]+ [(]{1}.+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex
        matcher = pattern.matcher(linea);
        
        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String a = datos[1];
            String b = datos[3];
            String c = datos[4];

            //evalúa el paréntesis con la operación de suma
            if(Objects.equals(datos[2], "+")){
                if(b.contains(".") || c.contains(".")){
                    //*******FALTA POR CORREGIR*********/
                    // Número con decimales
                    double x = Double.parseDouble(b);
                    double y = Double.parseDouble(c);
                    double resultado = Aritmeticos.add(x, y);
                    
                    //evalúa lo que está fuera del paréntesis dependiendo de la operación
                    if(Objects.equals(datos[0], "+")){
                        if(a.contains(".")){
                            double z = Double.parseDouble(a);
                            double resultadoF = Aritmeticos.add(resultado, z);
                            return Double.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[0], "+")){
                        double z = Double.parseDouble(a);
                        double resultadoF = Aritmeticos.add(resultado, z);
                        return Double.toString(resultadoF);
                    }

                } else{
                    // Número entero
                    int x = Integer.parseInt(b);
                    int y = Integer.parseInt(c);
                    int resultado = Aritmeticos.add(x, y);

                    //evalúa lo que está fuera del paréntesis dependiendo de la operación
                    if(Objects.equals(datos[0], "+")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.add(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                    if(Objects.equals(datos[0], "-")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.sub(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                    if(Objects.equals(datos[0], "*")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.mult(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                    if(Objects.equals(datos[0], "/")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.div(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                }

            //evalúa el paréntesis con la operación de resta
            } else if(Objects.equals(datos[2], "-")){
                if(b.contains(".") || c.contains(".")){
                    //*******FALTA POR CORREGIR*********/
                    // Número con decimales
                    double x = Double.parseDouble(b);
                    double y = Double.parseDouble(c);
                    double resultado = Aritmeticos.sub(x, y);
                    return Double.toString(resultado);

                } else{
                    // Número entero
                    int x = Integer.parseInt(b);
                    int y = Integer.parseInt(c);
                    int resultado = Aritmeticos.sub(x, y);

                    //evalúa lo que está fuera del paréntesis dependiendo de la operación
                    if(Objects.equals(datos[0], "+")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.add(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                    if(Objects.equals(datos[0], "-")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.sub(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                    if(Objects.equals(datos[0], "*")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.mult(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                    if(Objects.equals(datos[0], "/")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.div(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                }

            //evalúa el paréntesis con la operación de división
            } else if(Objects.equals(datos[2], "/")){
                if(b.contains(".") || c.contains(".")){
                    //*******FALTA POR CORREGIR*********/
                    // Número con decimales
                    double x = Double.parseDouble(b);
                    double y = Double.parseDouble(c);
                    double resultado = Aritmeticos.sub(x, y);
                    return Double.toString(resultado);
                    
                } else{
                    // Número entero
                    int x = Integer.parseInt(b);
                    int y = Integer.parseInt(c);
                    int resultado = Aritmeticos.div(x, y);

                    //evalúa lo que está fuera del paréntesis dependiendo de la operación
                    if(Objects.equals(datos[0], "+")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.add(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                    if(Objects.equals(datos[0], "-")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.sub(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                    if(Objects.equals(datos[0], "*")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.mult(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                    if(Objects.equals(datos[0], "/")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.div(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                }

            //evalúa el paréntesis con la operación de multiplicación  
            } else if(Objects.equals(datos[2], "*")){
                if(b.contains(".") || c.contains(".")){
                    //*******FALTA POR CORREGIR*********/
                    // Número con decimales
                    double x = Double.parseDouble(b);
                    double y = Double.parseDouble(c);
                    double resultado = Aritmeticos.sub(x, y);
                    return Double.toString(resultado);

                } else{
                    // Número entero
                    int x = Integer.parseInt(b);
                    int y = Integer.parseInt(c);
                    int resultado = Aritmeticos.mult(x, y);

                    //evalúa lo que está fuera del paréntesis dependiendo de la operación
                    if(Objects.equals(datos[0], "+")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.add(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                    if(Objects.equals(datos[0], "-")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.sub(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                    if(Objects.equals(datos[0], "*")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.mult(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                    if(Objects.equals(datos[0], "/")){
                        Integer z = Integer.parseInt(a);
                        Integer resultadoF = Aritmeticos.div(resultado, z);
                        return Integer.toString(resultadoF);
                    }
                }
            }
        }

        //operaciones dentro de operaciones con dos paréntesis a la izquierda
        pattern = Pattern.compile("^[(]{1}[+*\\-/] [(][+*\\-/] [0-9.]+ [0-9.]+[)]{1} [0-9.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  // Regex
        matcher = pattern.matcher(linea);

        if (matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String a = datos[2];
            String b = datos[3];
            String c = datos[4];

                        //evalúa el paréntesis
            if(Objects.equals(datos[1], "+")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);

                    double r = Aritmeticos.add(x, y);

                    return Double.toString(r);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);

                    int r = Aritmeticos.add(x, y);

                    return Integer.toString(r);
                }
            } else if(Objects.equals(datos[1], "-")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);

                    double r = Aritmeticos.sub(x, y);

                    return Double.toString(r);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);

                    int r = Aritmeticos.sub(x, y);

                    return Integer.toString(r);
                }
            } else if(Objects.equals(datos[1], "/")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);

                    double r = Aritmeticos.div(x, y);

                    return Double.toString(r);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);

                    int r = Aritmeticos.div(x, y);

                    return Integer.toString(r);
                }
            } else if(Objects.equals(datos[1], "*")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);

                    double r = Aritmeticos.mult(x, y);

                    return Double.toString(r);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);

                    int r = Aritmeticos.mult(x, y);

                    return Integer.toString(r);
                }
            }

            String r = datos[5];
            if(Objects.equals(datos[0], "+")){
                if(c.contains(".") || r.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(c);
                    double y = Double.parseDouble(r);

                    double resultado = Aritmeticos.add(x, y);

                    return Double.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(c);
                    int y = Integer.parseInt(r);

                    int resultado = Aritmeticos.add(x, y);

                    return Integer.toString(resultado);
                }
            } else if(Objects.equals(datos[0], "-")){
                if(c.contains(".") || r.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(c);
                    double y = Double.parseDouble(r);

                    double resultado = Aritmeticos.sub(x, y);

                    return Double.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(c);
                    int y = Integer.parseInt(r);

                    int resultado = Aritmeticos.sub(x, y);

                    return Integer.toString(resultado);
                }
            } else if(Objects.equals(datos[0], "/")){
                if(c.contains(".") || r.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(c);
                    double y = Double.parseDouble(r);

                    double resultado = Aritmeticos.div(x, y);

                    return Double.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(c);
                    int y = Integer.parseInt(r);

                    int resultado = Aritmeticos.div(x, y);

                    return Integer.toString(resultado);
                }
            } else if(Objects.equals(datos[0], "*")){
                if(c.contains(".") || r.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(c);
                    double y = Double.parseDouble(r);

                    double resultado = Aritmeticos.mult(x, y);

                    return Double.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(c);
                    int y = Integer.parseInt(r);

                    int resultado = Aritmeticos.mult(x, y);

                    return Integer.toString(resultado);
                }
            }
        }

        //operaciones dentro de operaciones con dos paréntesis
        pattern = Pattern.compile("^[(]{1}[+*\\-/] [(]{1}[+*\\-/] + [0-9.]+ [0-9.][)]{1} [(]{1}[+*\\-/] [0-9.]+ [0-9.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex
        matcher = pattern.matcher(linea);

        if (matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String a = datos[2];
            String b = datos[3];
            String c = datos[5];
            String d = datos[6];
            
        }
        

        return "Expresión inválida. Ingrese '(EXIT)' para salir.";
    }
}
