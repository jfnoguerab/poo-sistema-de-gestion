package orders;

import models.Empleado;

public class AndOrder implements Order {

    private Order[] orders;

    public AndOrder(Order[] orders) {
        this.orders = orders;
    }

    public Empleado[] apply(Empleado[] empleados) {
        
        if (empleados != null && this.orders != null) {
            Empleado[] orderedEmpleados = empleados;
            for (Order order : orders) {
                if (order != null) {
                    orderedEmpleados = order.apply(orderedEmpleados);
                }
            }
            return orderedEmpleados;
        }

        return null;
    }
}
