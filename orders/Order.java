package orders;

import models.Empleado;

public interface Order {
    public Empleado[] apply(Empleado[] empleados);
}
