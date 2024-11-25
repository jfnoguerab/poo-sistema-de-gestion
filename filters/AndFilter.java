package filters;

import models.Empleado;

public class AndFilter implements Filter {

    private Filter[] filters;

    public AndFilter(Filter[] filters) {
        this.filters = filters;
    }

    @Override
    public Empleado[] apply(Empleado[] empleados) {
        
        if (empleados != null && this.filters != null) {
            Empleado[] filteredEmpleados = empleados;
            for (Filter filter : filters) {
                if (filter != null) {
                    filteredEmpleados = filter.apply(filteredEmpleados);
                }
            }
            return filteredEmpleados;
        }

        return null;
    }
}
