package utils;

import java.util.Scanner;

public final class ConsoleUtility {

    private ConsoleUtility(){
        // Private constructor to prevent instantiation
    }

    public static void cleanScreen(){
        // Limpia la consola (ANSI escape code)
        System.out.print("\033[H\033[2J");   
        System.out.flush();
    }

    public static void waitPressEnterKey(Scanner scanner) {
        //Para pausar la ejecución del programa
        System.out.print("\nPresione ENTER para volver al menú..."); //Mensaje en pantalla
        scanner.nextLine(); //Ahora el programa se detiene hasta que se pulse ENTER
    }
    
    public static void waitPressEnterKey(Scanner scanner, String message) {
        //Para pausar la ejecución del programa
        System.out.print(message); //Mensaje en pantalla
        scanner.nextLine(); //Ahora el programa se detiene hasta que se pulse ENTER
    }

    public static void sleepProccess(long millisec) throws Exception {
        try {
            // Detiene la ejecución por milisegundos
            // Ej: 1000 milisegundos = 1 seg
            Thread.sleep(millisec);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
