import java.util.Arrays;
import java.util.Scanner;

import models.Empleado;
import models.OrderType;
import utils.ConsoleUtility;
import utils.MenuUtility;

public class Application {
    private static Scanner scanner = new Scanner(System.in);
    private static Empleado[] empleadosArr = new Empleado[6];
    public static void main(String[] args) {

        try {

            // Creamos varios empleados por defecto
            Empleado empl1 = new Empleado("John Smith", 45, Double.valueOf(5000), "CTO");
            Empleado empl2 = new Empleado("Pepe Smith", 30, Double.valueOf(1500), "QA");
            Empleado empl3 = new Empleado("Ana Perez", 25, Double.valueOf(2500), "UX");
            Empleado empl4 = new Empleado("Peter Johnson", 23, Double.valueOf(2500), "Development");
            Empleado empl5 = new Empleado("Marimar Cortes", 23, Double.valueOf(2500), "UX");
            Empleado empl6 = new Empleado("Juan Perez", 28, Double.valueOf(1000), "DevOps");

            empleadosArr[0] = empl1;
            empleadosArr[1] = empl2;
            empleadosArr[2] = empl3;
            empleadosArr[3] = empl4;
            empleadosArr[4] = empl5;
            empleadosArr[5] = empl6;

            // Carga el menú principal
            mainMenu();

        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        
    }

    private static void mainMenu() throws Exception {
        boolean showMenu = true;
        int opMenuUsu = 0;

        do {
            // Limpia la consola
            ConsoleUtility.cleanScreen();

            MenuUtility.header("Administración de empleados");

            // Menu
            String[] menu = {
                "Mostrar todos los empleados",
                "Agregar un empleado",
                "Filtrar empleados",
                "Ordenar empleados",
                "Incrementar salario",
                "Limpiar filtros",
                "Limpiar ordenes",
                "Salir"
            };

            // Muestra el menú y devuelve la opción válida ingresada por el usuario que este dentro del rango del menú
            opMenuUsu = MenuUtility.createMenuAndGetOption(scanner, menu, "\nIngrese el número de la opción correspondiente: ");

            // Limpia la consola
            ConsoleUtility.cleanScreen();

            // Opciones del menú
            switch (opMenuUsu) {
                case 1:
                    mostrarTodosEmpleados();
                    break;
                case 2:
                    agregarEmpleado();
                    break;
                case 3:
                    filtrarEmpleados();
                    break;
                case 4:
                    ordenarEmpleados();
                    break;
                case 5:
                    incrementarSalario();
                    break;
                case 6:
                    limpiarFiltros();
                    break;
                case 7:
                    limpiarOrdenes();
                    break;
                case 8:
                    showMenu = MenuUtility.exit();
                    break;
            }

            // Limpia la consola
            ConsoleUtility.cleanScreen();
                
        } while (showMenu);

    }

    private static void mostrarTodosEmpleados() {
        MenuUtility.header("Mostrar todos los empleados");
        // Imprimimos todos los empleados registrados

        Empleado.mostrarTodosEmpleados(empleadosArr, "No hay empleados registrados.");

        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }

    private static void agregarEmpleado() {
        MenuUtility.header("Agregar un empleado");
        // Solicitamos los datos
        String nombreEmpleado = MenuUtility.solicitarCadena(scanner, "Ingrese el nombre del empleado: ");

        // Validamos que el nombre ingresado no haya sido registrado anteriormente
        Empleado valEmpleado = Empleado.buscarPorNombre(empleadosArr, nombreEmpleado);

        if (valEmpleado == null) {

            Integer edadEmpleado = MenuUtility.solicitaNumeroInteger(scanner, "Ingrese la edad del empleado: ");
            Double salarioEmpleado = MenuUtility.solicitaNumeroDouble(scanner, "Ingrese el salario del empleado: ");
            String departamentoEmpleado = MenuUtility.solicitarCadena(scanner, "Ingrese el departamento del empleado: ");
    
    
            // Si ya hay registrado algún empleado debemos aumentar en 1 el tamaño del arreglo
            if (empleadosArr[empleadosArr.length - 1] != null) {
                empleadosArr = Arrays.copyOf(empleadosArr, empleadosArr.length + 1);
            }
    
            // Asignamos los valores
            Empleado nuevoEmpleado = new Empleado(nombreEmpleado, edadEmpleado, salarioEmpleado, departamentoEmpleado);
            empleadosArr[empleadosArr.length - 1] = nuevoEmpleado;
        } else {
            // El empleado ya existe
            System.out.println("Lo sentimos, el empleado ya fue registrado anteriormente.");
        }

        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }

    private static void filtrarEmpleados() throws Exception {
        boolean showMenu = true;
        int opMenuUsu = 0;
        // Menú
        String[] menu = {
            "Nombre",
            "Edad",
            "Salario",
            "Departamento",
            "Volver al menú"
        };

        do {            
            // Limpia la consola
            ConsoleUtility.cleanScreen();
    
            MenuUtility.header("Filtrar empleados");
            
            System.out.println("Filtrar por: ");
    
            // Muestra el menú y devuelve la opción válida ingresada por el usuario que este dentro del rango del menú
            opMenuUsu = MenuUtility.createMenuAndGetOption(scanner, menu, "\nIngrese el número de la opción correspondiente: ");
    
            // Limpia la consola
            ConsoleUtility.cleanScreen();
    
            switch (opMenuUsu) {
                case 1:
                    filterByName();
                    break;
                case 2:
                    filterByAge();
                    break;
                case 3:
                    filterBySalary();
                    break;
                case 4:
                    filterByDepartament();
                    break;
                case 5:
                    showMenu = MenuUtility.exit("\nVolviendo al menú principal...\n", 1500);
                    break;
            }
        } while (showMenu);


    }

    private static void filterByName() {
        MenuUtility.header("Filtrar por nombre");

        String nombreEmpleado = MenuUtility.solicitarCadena(scanner, "Ingrese el nombre del empleado: ");

        // Agregamos el filtro de nombre
        Empleado.addFilterName(nombreEmpleado);


        // Mostramos los empleados filtrados por nombre
        Empleado.mostrarTodosEmpleados(empleadosArr, "\nNo hay empleados que cumplan con el filtro ingresado.");


        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }
    
    private static void filterByAge() {
        MenuUtility.header("Filtrar por edad");

        Integer minEdad = MenuUtility.solicitaNumeroInteger(scanner, "Ingrese la edad mínima: ");
        Integer maxEdad = MenuUtility.solicitaNumeroInteger(scanner, "Ingrese la edad máxima: ");

        // Agregamos el filtro de edad
        Empleado.addFilterAge(minEdad, maxEdad);

        // Mostramos los empleados filtrados por edad
        Empleado.mostrarTodosEmpleados(empleadosArr, "\nNo hay empleados que cumplan con el filtro ingresado.");

        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }
    
    private static void filterBySalary() {
        MenuUtility.header("Filtrar por salario");

        Double minSalario = MenuUtility.solicitaNumeroDouble(scanner, "Ingrese el valor de salario mínimo: ");
        Double maxSalario = MenuUtility.solicitaNumeroDouble(scanner, "Ingrese el valor de salario máximo: ");

        // Agregamos el filtro de edad
        Empleado.addFilterSalary(minSalario, maxSalario);

        // Mostramos los empleados filtrados por salario
        Empleado.mostrarTodosEmpleados(empleadosArr, "\nNo hay empleados que cumplan con el filtro ingresado.");

        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }

    private static void filterByDepartament() {
        MenuUtility.header("Filtrar por departamento");

        String departamento = MenuUtility.solicitarCadena(scanner, "Ingrese el nombre del departamento: ");

        // Agregamos el filtro de nombre
        Empleado.addFilterDp(departamento);

        // Mostramos los empleados filtrados por salario
        Empleado.mostrarTodosEmpleados(empleadosArr, "\nNo hay empleados que cumplan con el filtro ingresado.");

        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }
    
    private static void ordenarEmpleados() throws Exception {
        boolean showMenu = true;
        int opMenuUsu = 0;
        // Menú
        String[] menu = {
            "Nombre",
            "Edad",
            "Salario",
            "Departamento",
            "Volver al menú"
        };

        do {            
            // Limpia la consola
            ConsoleUtility.cleanScreen();
    
            MenuUtility.header("Ordenar empleados");
            
            System.out.println("Ordenar por: ");
    
            // Muestra el menú y devuelve la opción válida ingresada por el usuario que este dentro del rango del menú
            opMenuUsu = MenuUtility.createMenuAndGetOption(scanner, menu, "\nIngrese el número de la opción correspondiente: ");
    
            // Limpia la consola
            ConsoleUtility.cleanScreen();
    
            switch (opMenuUsu) {
                case 1:
                    orderByName();
                    break;
                case 2:
                    orderByAge();
                    break;
                case 3:
                    orderBySalary();
                    break;
                case 4:
                    orderByDepartament();
                    break;
                case 5:
                    showMenu = MenuUtility.exit("\nVolviendo al menú principal...\n", 1500);
                    break;
            }
        } while (showMenu);

    }

    private static void orderByName() {
        MenuUtility.header("Filtrar por nombre");

        // Muestra el menú y devuelve la opción válida ingresada por el usuario que este dentro del rango del menú
        int opMenuUsu = solicitaOrderMenu();

        if (opMenuUsu == 1) { // ASC
            // Agregamos el orden de nombre
            Empleado.setOrderNombre(OrderType.ASC);
        } else { // DESC
            Empleado.setOrderNombre(OrderType.DESC);
        }

        // Mostramos los empleados ordenados por nombre
        Empleado.mostrarTodosEmpleados(empleadosArr, "\nNo existen empleados para el orden utilizado.");

        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }
    
    private static void orderByAge() {
        MenuUtility.header("Filtrar por edad");

        // Muestra el menú y devuelve la opción válida ingresada por el usuario que este dentro del rango del menú
        int opMenuUsu = solicitaOrderMenu();

        if (opMenuUsu == 1) { // ASC
            // Agregamos el orden de edad
            Empleado.setOrderEdad(OrderType.ASC);
        } else { // DESC
            Empleado.setOrderEdad(OrderType.DESC);
        }

        // Mostramos los empleados ordenados por nombre
        Empleado.mostrarTodosEmpleados(empleadosArr, "\nNo existen empleados para el orden utilizado.");

        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }
    
    private static void orderBySalary() {
        MenuUtility.header("Filtrar por salario");

        // Muestra el menú y devuelve la opción válida ingresada por el usuario que este dentro del rango del menú
        int opMenuUsu = solicitaOrderMenu();

        if (opMenuUsu == 1) { // ASC
            // Agregamos el orden de salario
            Empleado.setOrderSalario(OrderType.ASC);
        } else { // DESC
            Empleado.setOrderSalario(OrderType.DESC);
        }

        // Mostramos los empleados ordenados por nombre
        Empleado.mostrarTodosEmpleados(empleadosArr, "\nNo existen empleados para el orden utilizado.");

        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }
    
    private static void orderByDepartament() {
        MenuUtility.header("Filtrar por departamento");

        // Muestra el menú y devuelve la opción válida ingresada por el usuario que este dentro del rango del menú
        int opMenuUsu = solicitaOrderMenu();

        if (opMenuUsu == 1) { // ASC
            // Agregamos el orden de departamento
            Empleado.setOrderDp(OrderType.ASC);
        } else { // DESC
            Empleado.setOrderDp(OrderType.DESC);
        }

        // Mostramos los empleados ordenados por nombre
        Empleado.mostrarTodosEmpleados(empleadosArr, "\nNo existen empleados para el orden utilizado.");

        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }

    private static int solicitaOrderMenu() {
        System.out.println("Seleccione el orden deseado: ");
        // Menú
        String[] menu = {
            "Ascendente",
            "Descendente"
        };

        // Muestra el menú y devuelve la opción válida ingresada por el usuario que este dentro del rango del menú
        return MenuUtility.createMenuAndGetOption(scanner, menu, "\nIngrese el número de la opción correspondiente: ");
    }
    
    private static void incrementarSalario() {
        MenuUtility.header("Incrementar salario");
        // Solicitamos los datos
        String nombreEmpleado = MenuUtility.solicitarCadena(scanner, "Ingrese el nombre del empleado: ");

        // Validamos que el nombre ingresado no haya sido registrado anteriormente
        Empleado searchedEmpleado = Empleado.buscarPorNombre(empleadosArr, nombreEmpleado);

        if (searchedEmpleado != null) {
            System.out.println("La primera coincidencia con el nombre ingresado es:");
            Empleado.printEmpleado(searchedEmpleado);
            // Mostramos un mensaje para validar si desea continuar con el empleado encontrado
            Character[] opClearFilters = {'S', 'N'};
            Character clearFilters = MenuUtility.solicitaCharacter(scanner, "Desea continuar [S/N]?",opClearFilters);
     
            if (clearFilters == 'S') {

               Double porcentajeAumento = MenuUtility.solicitaNumeroDouble(scanner, "Ingrese el porcentaje de aumento para el empleado: ");

               empleadosArr[searchedEmpleado.getId() - 1] = Empleado.incremetoSalario(searchedEmpleado, porcentajeAumento);

               System.out.println("\nEl salario del empleado \""+searchedEmpleado.getNombre()+"\" se incrementó en un "+porcentajeAumento+"% quedando por un total de "+searchedEmpleado.getSalarioWithCurrencyFormat()+".\n");
            }
    
        } else {
            // El empleado ya existe
            System.out.println("Lo sentimos, el empleado ingresado no está registrado.");
        }

        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }
    
    private static void limpiarFiltros() {
        MenuUtility.header("Limpiar filtros");

        if (Empleado.hasActiveFilters()){
            
            // Mostramos un mensaje para validar si desea limpiar los filtros
            Character[] opClearFilters = {'S', 'N'};
            Character clearFilters = MenuUtility.solicitaCharacter(scanner, "Desea limpiar los filtros [S/N]?", opClearFilters);
    
            if (clearFilters == 'S') {
    
                Empleado.clearFilters();
    
                System.out.println("\nLos filtros se limpiaron satisfactoriamente.\n");
    
                // Mostramos el listado completo de empleados
                Empleado.mostrarTodosEmpleados(empleadosArr, "No hay empleados registrados.");
                
            }
        } else {
            System.out.println("\nNo hay filtros activos para limpiar.\n");
        }

        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }
    
    private static void limpiarOrdenes() {
        MenuUtility.header("Limpiar ordenes");

        if (Empleado.hasActiveOrders()){
            
            // Mostramos un mensaje para validar si desea limpiar los ordenes
            Character[] opClearOrders = {'S', 'N'};
            Character clearOrders = MenuUtility.solicitaCharacter(scanner, "Desea limpiar los ordenes [S/N]?", opClearOrders);
    
            if (clearOrders == 'S') {
    
                Empleado.clearOrders();
    
                System.out.println("\nLos ordenes se limpiaron satisfactoriamente.\n");
    
                // Mostramos el listado completo de empleados
                Empleado.mostrarTodosEmpleados(empleadosArr, "No hay empleados registrados.");
                
            }
        } else {
            System.out.println("\nNo hay ordenes activos para limpiar.\n");
        }

        // Pausar la ejecución del programa hasta que presione ENTER
        ConsoleUtility.waitPressEnterKey(scanner);
    }
}
