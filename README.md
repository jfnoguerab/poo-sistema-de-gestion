## Intro a POO: Actividad integradora

### Estableciendo las funcionalidades:

**Tu misión será desarrollar un programa de administración de empleados** que incluya una interfaz de menú para el usuario. Inicialmente, los usuarios tendrán la capacidad de añadir nuevos empleados al sistema, ingresando datos como el nombre, la edad, el salario y el departamento en el que trabajan. Posteriormente, podrán optar por visualizar un resumen en forma de tabla que muestra información sobre todos los empleados, asignando a cada uno un número de fila.

Adicionalmente, el sistema brindará la opción de filtrar y ordenar la lista de empleados. Los usuarios podrán establecer criterios de búsqueda basados en atributos como el nombre, la edad, el salario y el departamento. Además, tendrán la flexibilidad de definir límites mínimos y máximos para la edad y el salario. La funcionalidad para ordenar la lista de empleados según diferentes parámetros como el nombre, la edad o el salario también estará disponible.

---

#### Actividad: Maquetando el sistema de gestión:

1. Crear un proyecto JAVA llamado `sistema-de-gestion` con su clase  `Application` y su carpeta `modelos`.

2. Dentro de la carpeta `modelos` crea la clase `Empleado` con los atributos `nombre`, `edad`, `salario` y `departamento`. Todos sus atributos deben ser declarados como **privados** y deben contar con sus métodos getter y setter.

---

#### Actividad: Diseñando los métodos:
Para la realización de esta actividad, consideraremos los siguientes métodos y sus respectivas funcionalidades:

* **Mostrar todos los empleados:** Este método se encarga de recibir un arreglo de objetos empleados e imprimir en consola la lista de empleados creados. La información se presentará en formato de tabla, donde la primera columna mostrará el número de fila y la primera fila contendrá los encabezados de cada columna.

* **Filtrar empleados:** Al recibir un arreglo de empleados, este método devolverá un nuevo arreglo filtrado según criterios específicos. Los filtros pueden aplicarse por nombre, edad, salario o departamento. El método recibirá la información del atributo por el cual se desea filtrar, y en caso de nombre y departamento, se proporcionará un String para el filtrado, mientras que para salario y edad se especificarán valores numéricos máximo y mínimo.

* **Ordenar empleados:** Este método recibirá el arreglo de empleados y el atributo por el cual se desea ordenar. Utilizará el algoritmo de ordenamiento de burbuja para realizar el ordenamiento y devolverá el arreglo de empleados ordenado según el criterio especificado.

* **Buscar por nombre:** Recibirá un arreglo de empleados y un nombre, devolviendo el primer objeto empleado que coincida con dicho nombre.

* **Incrementar salario:** Este método recibirá un objeto empleado y un porcentaje de aumento salarial. Devolverá el mismo objeto con su salario incrementado según el porcentaje proporcionado.

---

### Diseñando el menú interactivo

#### Actividad: Diseñando los métodos:

En esta actividad deberás crear un menú para que el usuario pueda utilizar el sistema de gestión. Este cuenta con el siguiente menú de opciones:

1. **Mostrar todos los empleados**
2. **Crear empleado:** esta opción debe validar que no exista otro empleado con el mismo nombre. En caso de que el usuario ingrese un empleado existente debe aparecer un mensaje de error y volver al menú principal.
3. **Filtrar empleados:** el usuario puede seleccionar el atributo a filtrar y el valor del filtro.
4. **Ordenar empleados**: el usuario puede seleccionar el atributo por el cual ordenar.
5. **Incrementar salario:** el usuario debe ingresar el nombre y el porcentaje de aumento.
6. **Limpiar filtros:** esto devuelve el arreglo de empleados que se muestra a su versión original.
7. **Salir del programa**

---
### ¿Cómo correr el programa?

Desde la consola de comandos, debemos ubicarnos al nivel del archivo `Application.java` y lanzar el siguiente comando:

~~~
java Application.java
~~~

_* Cabe aclarar que ya se debe tener instalado Java en el sistema para usar el comando._