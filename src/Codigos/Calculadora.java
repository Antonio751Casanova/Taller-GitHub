package Codigos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        // Uso del operador diamante <> (mejor práctica en Java)
        List<String> historial = new ArrayList<>(); 
        String titulo = "=== Calculadora de consola ===";

        while (true) {
            System.out.println("\n" + titulo);
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Módulo (Residuo)");
            System.out.println("6. Ver historial");
            System.out.println("7. Limpiar historial");
            System.out.println("0. Salir");

            int opcion = leerOpcion(entrada);
            if (opcion == 0) {
                System.out.println("¡Hasta luego!");
                break;
            }
            if (opcion == 6) {
                if (historial.isEmpty()) {
                    System.out.println("Todavía no hay operaciones.");
                } else {
                    for (String operacion : historial) {
                        System.out.println(operacion);
                    }
                }
                continue;
            }
            if (opcion == 7) {
                historial.clear();
                System.out.println("El historial ha sido borrado.");
                continue;
            }
            if (opcion < 1 || opcion > 5) {
                System.out.println("Opción no válida.");
                continue;
            }

            double a = leerNumero(entrada, "Primer número: ");
            double b = leerNumero(entrada, "Segundo número: ");
            double resultado;
            String simbolo;

            switch (opcion) {
                case 1:
                    resultado = a + b;
                    simbolo = "+";
                    break;
                case 2:
                    resultado = a - b;
                    simbolo = "-";
                    break;
                case 3:
                    resultado = a * b;
                    simbolo = "×";
                    break;
                case 4:
                    if (b == 0) {
                        System.out.println("No se puede dividir entre cero.");
                        continue;
                    }
                    resultado = a / b;
                    simbolo = "÷";
                    break;
                default: // Corresponde al caso 5 (Módulo)
                    if (b == 0) {
                        System.out.println("No se puede calcular el módulo con cero.");
                        continue;
                    }
                    resultado = a % b;
                    simbolo = "%";
                    break;
            }

            String detalle = a + " " + simbolo + " " + b + " = " + resultado;
            System.out.println("Resultado: " + detalle);
            historial.add(detalle);
        }
        entrada.close();
    }

    private static int leerOpcion(Scanner entrada) {
        System.out.print("Elige una opción: ");
        try {
            return Integer.parseInt(entrada.nextLine().trim());
        } catch (NumberFormatException error) {
            return -1;
        }
    }

    private static double leerNumero(Scanner entrada, String pregunta) {
        while (true) {
            System.out.print(pregunta);
            try {
                double numero = Double.parseDouble(entrada.nextLine().trim());
                if (Double.isFinite(numero)) {
                    return numero;
                }
            } catch (NumberFormatException error) {
                // Pedimos el número de nuevo debajo.
            }
            System.out.println("Escribe un número válido (usa punto para decimales).");
        }
    }
}