package orders;

import java.util.Arrays;

import models.Empleado;
import models.OrderType;

public class AgeOrder implements Order {
    private OrderType orderType;

    public AgeOrder(OrderType orderType) {
        this.orderType = orderType;
    }

    public Empleado[] apply(Empleado[] empleados) {
        if (empleados != null) {
            int n = empleados.length;
            Empleado[] orderedEmpleados = Arrays.copyOf(empleados, n);
            Empleado temp;
            // Utilizamos bubblesort para ordenar
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (this.orderType == OrderType.ASC) {
                        if (orderedEmpleados[j].getEdad() > orderedEmpleados[j + 1].getEdad()) {
                            temp = orderedEmpleados[j];
                            orderedEmpleados[j] = orderedEmpleados[j + 1];
                            orderedEmpleados[j + 1] = temp;
                        }
                    } else if (this.orderType == OrderType.DESC) {
                        if (orderedEmpleados[j].getEdad() < orderedEmpleados[j + 1].getEdad()) {
                            temp = orderedEmpleados[j];
                            orderedEmpleados[j] = orderedEmpleados[j + 1];
                            orderedEmpleados[j + 1] = temp;
                        }
                    }
                }
            }

            return orderedEmpleados;
        }

        return null;
    }

}
