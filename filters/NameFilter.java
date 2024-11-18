package filters;

import java.util.Arrays;

import models.Empleado;

public class NameFilter implements Filter {

    private String filterNameStr;


    public NameFilter(String filterNameStr) {
        this.filterNameStr = filterNameStr;
    }

    public Empleado[] apply(Empleado[] empleados) {
        // Si existe algún filtro recorremos el arreglo y lo filtramos
        Empleado[] filteredEmpleados = new Empleado[1];
        if (empleados != null) {
            for (Empleado empleado : empleados) {
                // Revisamos si tiene filtro de nombre
                if (this.filterNameStr != null) {
                    if (empleado.getNombre().toLowerCase().contains(this.filterNameStr.toLowerCase())) {
                        // Si ya hay registrado algún empleado debemos aumentar en 1 el tamaño del arreglo
                        if (filteredEmpleados[filteredEmpleados.length - 1] != null && filteredEmpleados[filteredEmpleados.length - 1] != empleado) {
                            filteredEmpleados = Arrays.copyOf(filteredEmpleados, filteredEmpleados.length + 1);
                        }

                        if (filteredEmpleados[filteredEmpleados.length - 1] != empleado) {
                            filteredEmpleados[filteredEmpleados.length - 1] = empleado;
                        }
                    }
                }
            }
        }

        return filteredEmpleados[filteredEmpleados.length - 1] != null ? filteredEmpleados : null;
    }
}
