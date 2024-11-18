package models;

import java.text.NumberFormat;

import filters.AgeFilter;
import filters.AndFilter;
import filters.DepartamentFilter;
import filters.Filter;
import filters.NameFilter;
import filters.SalaryFilter;
import orders.AgeOrder;
import orders.AndOrder;
import orders.DepartamentOrder;
import orders.NameOrder;
import orders.Order;
import orders.SalaryOrder;

public class Empleado {

    private String nombre;
    private Integer edad;
    private Double salario;
    private String departamento;
    private int id;

    // Filtros -----------------------
    // Limites min y max de edad
    private static Integer minEdad;
    private static Integer maxEdad;
    
    // Limites min y max de salario
    private static Double minSalario;
    private static Double maxSalario;

    private static String filterNameStr;
    private static String filterDpStr;
    // --------------------------------

    // Orden -----------------------
    private static models.OrderType orderNombre;
    private static models.OrderType orderEdad;
    private static models.OrderType orderSalario;
    private static models.OrderType orderDp;

    // --------------------------------

    // Calculamos el espacio de cada columna
    // para ser vistos en la tabla
    public static int longestNameSize = 0;
    public static int longestAgeSize = 0;
    public static int longestSalarySize = 0;
    public static int longestDpSize = 0;

    // contador de instancias
    private static int cont = 0;


    public Empleado(String nombre, Integer edad, Double salario, String departamento) {
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setSalario(salario);
        this.setDepartamento(departamento);
        cont++;
        this.setId(cont);
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
        if (this.nombre.length() > longestNameSize) {
            longestNameSize = this.nombre.length();
        }
    }
    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
        if (this.edad.toString().length() > longestAgeSize) {
            longestAgeSize = this.edad.toString().length();
        }
    }
    public Double getSalario() {
        return salario;
    }
    
    public String getSalarioWithCurrencyFormat() {
        // Creamos el formato moneda para el salario
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return currencyFormat.format(salario);
    }
    public void setSalario(Double salario) {
        this.salario = salario;
        if (this.salario.toString().length() > longestSalarySize) {
            longestSalarySize = this.salario.toString().length();
        }
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
        if (this.departamento.length() > longestDpSize) {
            longestDpSize = this.departamento.length();
        }
    }


    // Métodos estáticos

    public static void mostrarTodosEmpleados(Empleado[] empleados, String emptyDataMsg) {
        // validamos
        if(Empleado.hasActiveFilters() && Empleado.hasActiveOrders()) { // Filtros y Ordenes activos
            Empleado.printAllEmpleados(Empleado.ordenarEmpleados(Empleado.filtrarEmpleados(empleados)), emptyDataMsg);

        } else if (Empleado.hasActiveFilters() && !Empleado.hasActiveOrders()) { // Solo Filtros activos
            Empleado.printAllEmpleados(Empleado.filtrarEmpleados(empleados), emptyDataMsg);

        } else if (!Empleado.hasActiveFilters() && Empleado.hasActiveOrders()) { // Solo Ordenes activos
            Empleado.printAllEmpleados(Empleado.ordenarEmpleados(empleados), emptyDataMsg);

        } else { // Ningún filtro y orden activos
            Empleado.printAllEmpleados(empleados, emptyDataMsg);
        }
    }

    public static void printAllEmpleados(Empleado[] empleados, String emptyDataMsg) {
        if (empleados != null) {
            // Cabeceras
            // Calculamos los anchos de las columnas
            int idColWide = Integer.toString(cont).length() + 2;
            int nameColWide = (longestNameSize > "nombre".length() ? longestNameSize : "nombre".length()) + 2;
            int edadColWide = (longestAgeSize > "edad".length() ? longestAgeSize : "edad".length());
            // Calculamos el total de puntos de miles que tiene el valor
            int puntos = longestSalarySize % 3 == 0 ? (longestSalarySize/3) - 1 : (int) (longestSalarySize/3);
            int salarioColWide = (longestSalarySize + 5 + puntos) > "salario".length() ? longestSalarySize + 5 + puntos : "salario".length();
            int dpColWide = longestDpSize > "departamento".length() ? longestDpSize : "departamento".length();

            // Creamos el formato de las cabeceras
            String formatHeader = "| %-"+ idColWide +"s "
                            .concat("| %-"+ nameColWide +"s ")
                            .concat("| %-"+ edadColWide +"s ")
                            .concat("| %-"+ salarioColWide +"s ")
                            .concat("| %-"+ dpColWide +"s |%n");

            // Calculamos el total del ancho de la tabla
            int totalLines = 16 + idColWide + nameColWide + edadColWide + salarioColWide + dpColWide;

            // Imprimimos el borde superior de la tabla
            System.out.printf("-".repeat(totalLines) + "%n");
            
            // Imprimimos las cabeceras de las columnas
            System.out.printf(formatHeader, "ID", "NOMBRE", "EDAD", "SALARIO", "DEPARTAMENTO");

            // Imprimimos el borde que divide las cabeceras del contenido
            System.out.printf("-".repeat(totalLines) + "%n");
            

            // Contenido
            // Creamos el formato del contenido
            String formatContent = "| %-"+ idColWide +"s "
                            .concat("| %-"+ nameColWide +"s ")
                            .concat("| %"+ edadColWide +"d ")
                            .concat("| %-"+ salarioColWide +"s ")
                            .concat("| %-"+ dpColWide +"s |%n");
            // Creamos el formato moneda para el salario
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

            // Imprimimos el contenido
            for (Empleado empleado : empleados) {

                System.out.printf(formatContent, empleado.getId(), 
                                          empleado.getNombre(),
                                          empleado.getEdad(),
                                          currencyFormat.format(empleado.getSalario()),
                                          empleado.getDepartamento());
            }
            // Imprimimos el borde inferior para cerrar la tabla
            System.out.printf("-".repeat(totalLines) + "%n");

            // Imprimimos filtros y ordenes si están activos
            printActiveFilters();
            printActiveOrders();

        } else {
            System.out.println(emptyDataMsg);
        }
    }
    
    public static void printEmpleado(Empleado empleado) {
            // Cabeceras
            // Calculamos los anchos de las columnas
            int idColWide = Integer.toString(cont).length() + 2;
            int nameColWide = (longestNameSize > "nombre".length() ? longestNameSize : "nombre".length()) + 2;
            int edadColWide = (longestAgeSize > "edad".length() ? longestAgeSize : "edad".length());
            // Calculamos el total de puntos de miles que tiene el valor
            int puntos = longestSalarySize % 3 == 0 ? (longestSalarySize/3) - 1 : (int) (longestSalarySize/3);
            int salarioColWide = (longestSalarySize + 5 + puntos) > "salario".length() ? longestSalarySize + 5 + puntos : "salario".length();
            int dpColWide = longestDpSize > "departamento".length() ? longestDpSize : "departamento".length();

            // Creamos el formato de las cabeceras
            String formatHeader = "| %-"+ idColWide +"s "
                            .concat("| %-"+ nameColWide +"s ")
                            .concat("| %-"+ edadColWide +"s ")
                            .concat("| %-"+ salarioColWide +"s ")
                            .concat("| %-"+ dpColWide +"s |%n");

            // Calculamos el total del ancho de la tabla
            int totalLines = 16 + idColWide + nameColWide + edadColWide + salarioColWide + dpColWide;

            // Imprimimos el borde superior de la tabla
            System.out.printf("-".repeat(totalLines) + "%n");
            
            // Imprimimos las cabeceras de las columnas
            System.out.printf(formatHeader, "ID", "NOMBRE", "EDAD", "SALARIO", "DEPARTAMENTO");

            // Imprimimos el borde que divide las cabeceras del contenido
            System.out.printf("-".repeat(totalLines) + "%n");
            

            // Contenido
            // Creamos el formato del contenido
            String formatContent = "| %-"+ idColWide +"s "
                            .concat("| %-"+ nameColWide +"s ")
                            .concat("| %"+ edadColWide +"d ")
                            .concat("| %-"+ salarioColWide +"s ")
                            .concat("| %-"+ dpColWide +"s |%n");
            // Creamos el formato moneda para el salario
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

            // Imprimimos el contenido
            System.out.printf(formatContent, empleado.getId(), 
                                        empleado.getNombre(),
                                        empleado.getEdad(),
                                        currencyFormat.format(empleado.getSalario()),
                                        empleado.getDepartamento());
            // Imprimimos el borde inferior para cerrar la tabla
            System.out.printf("-".repeat(totalLines) + "%n");
    }

    public static Empleado buscarPorNombre(Empleado[] empleados, String nombre) {
        if (empleados.length > 0 && empleados[0] != null) {
            for (Empleado empleado : empleados) {
                if (empleado.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                    return empleado;
                }
            }
        }

        return null;
    }

    public static Empleado incremetoSalario(Empleado empleado, double porcentajeIncremento) {
        // Incrementamos el salario con relación al porcentaje de incremento
        double newSalario = empleado.getSalario() + (empleado.getSalario() * porcentajeIncremento / 100);
        // Asignamos el nuevo salario con el aumento
        empleado.setSalario(newSalario);
        return empleado;
    }

    public static Empleado[] filtrarEmpleados(Empleado[] empleados) {
        Filter[] filters = new Filter[4];
        int index = 0;
        if (filterNameStr != null) {
            filters[index] = new NameFilter(filterNameStr);
            if (filters[index].apply(empleados) == null) {
                removeFilterName();   
            }
            index++;
        }
        if (minEdad != null && maxEdad != null) {
            filters[index] = new AgeFilter(minEdad, maxEdad);
            if (filters[index].apply(empleados) == null) {
                removeFilterAge();   
            }
            index++;
        }
        
        if (minSalario != null && maxSalario != null) {
            filters[index] = new SalaryFilter(minSalario, maxSalario);
            if (filters[index].apply(empleados) == null) {
                removeFilterSalary();   
            }
            index++;
        }

        if (filterDpStr != null) {
            filters[index] = new DepartamentFilter(filterDpStr);
            if (filters[index].apply(empleados) == null) {
                removeFilterDp();   
            }
            index++;
        }

        return new AndFilter(filters).apply(empleados);
    }
    
    public static Empleado[] ordenarEmpleados(Empleado[] empleados) {
        Order[] orders = new Order[4];
        int index = 0;
        if (orderNombre != null) {
            orders[index] = new NameOrder(orderNombre);
            index++;
        }
        
        if (orderEdad != null) {
            orders[index] = new AgeOrder(orderEdad);
            index++;
        }
        
        if (orderSalario != null) {
            orders[index] = new SalaryOrder(orderSalario);
            index++;
        }
        
        if (orderDp != null) {
            orders[index] = new DepartamentOrder(orderDp);
            index++;
        }

        return new AndOrder(orders).apply(empleados);
    }

    public static boolean hasActiveOrders() {
        return orderNombre != null || orderEdad != null || orderSalario != null || orderDp != null;
    }

    public static void printActiveFilters() {
        if (hasActiveFilters()) {
            String activeFilters = "";
            
            if (filterNameStr!= null) {
                activeFilters += "Nombre (\"" + filterNameStr + "\"), ";
            }
            
            if (minEdad != null && maxEdad != null) {
                activeFilters += "Edad (min: " + minEdad + ", max: " + maxEdad + "), ";
            }
            
            if (minSalario != null && maxSalario != null) {
                // Creamos el formato moneda para el salario
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                activeFilters += "Salario (min: " + currencyFormat.format(minSalario) + ", max: " + currencyFormat.format(maxSalario) + "), ";
            }
            
            if (filterDpStr != null) {
                activeFilters += "Departamento (\"" + filterDpStr + "\"), ";
            }
            activeFilters = activeFilters.substring(0, activeFilters.length() - 2);
            System.out.print("*Filtro(s): " + activeFilters +"\n");
        }
    }
    
    public static void printActiveOrders() {
        if (hasActiveOrders()) {
            String activeOrders = "";
            
            if (orderNombre != null) {
                activeOrders += "Nombre (" + orderNombre.toString() + "), ";
            }
            
            if (orderEdad != null) {
                activeOrders += "Edad (" + orderEdad.toString() + "), ";
            }
            
            if (orderSalario != null) {
                activeOrders += "Salario (" + orderSalario.toString() + "), ";
            }
            
            if (orderDp != null) {
                activeOrders += "Departamento (" + orderDp.toString() + "), ";
            }
            activeOrders = activeOrders.substring(0, activeOrders.length() - 2);
            System.out.print("*Orden(es): " + activeOrders +"\n");
        }
    }


    public static models.OrderType getOrderNombre() {
        return orderNombre;
    }

    public static void setOrderNombre(models.OrderType orderNombre) {
        Empleado.orderNombre = orderNombre;
    }

    public static models.OrderType getOrderEdad() {
        return orderEdad;
    }

    public static void setOrderEdad(models.OrderType orderEdad) {
        Empleado.orderEdad = orderEdad;
    }

    public static models.OrderType getOrderSalario() {
        return orderSalario;
    }

    public static void setOrderSalario(models.OrderType orderSalario) {
        Empleado.orderSalario = orderSalario;
    }

    public static models.OrderType getOrderDp() {
        return orderDp;
    }

    public static void setOrderDp(models.OrderType orderDp) {
        Empleado.orderDp = orderDp;
    }


    public static boolean hasActiveFilters() {
        return filterNameStr != null || 
               (minEdad != null && maxEdad != null) || 
               (minSalario != null && maxSalario != null) || 
               filterDpStr != null;
    }


    public static void addFilterName(String name) {
        filterNameStr = name;
    }
    
    public static void removeFilterName() {
        filterNameStr = null;
    }
    
    public static void addFilterDp(String departament) {
        filterDpStr = departament;
    }
    
    public static void removeFilterDp() {
        filterDpStr = null;
    }

    public static void addFilterAge(Integer minAge, Integer maxAge) {
        minEdad = minAge;
        maxEdad = maxAge;
    }
    
    public static void removeFilterAge() {
        minEdad = null;
        maxEdad = null;
    }
    
    public static void addFilterSalary(Double minSalary, Double maxSalary) {
        minSalario = minSalary;
        maxSalario = maxSalary;
    }
    
    public static void removeFilterSalary() {
        minSalario = null;
        maxSalario = null;
    }

    public static void clearFilters() {
        removeFilterName();
        removeFilterAge();
        removeFilterSalary();
        removeFilterDp();
    }
    
    public static void clearOrders() {
        setOrderNombre(null);
        setOrderEdad(null);
        setOrderSalario(null);
        setOrderDp(null);
    }

}
