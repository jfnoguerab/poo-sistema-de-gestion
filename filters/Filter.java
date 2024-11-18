package filters;

import models.Empleado;

public interface Filter {
    public Empleado[] apply(Empleado[] empleados);
}
