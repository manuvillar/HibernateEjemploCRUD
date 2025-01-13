import entidades.Departamento;
import entidades.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> crearEmpleado();
                case 2 -> leerEmpleado();
                case 3 -> leerTodosEmpleados();
                case 4 -> actualizarEmpleado();
                case 5 -> eliminarEmpleado();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Menú CRUD de Empleados ===");
        System.out.println("1. Crear empleado");
        System.out.println("2. Leer empleado por ID");
        System.out.println("3. Leer todos los empleados");
        System.out.println("4. Actualizar empleado");
        System.out.println("5. Eliminar empleado");
        System.out.println("0. Salir");
    }

    private static void crearEmpleado() {
        System.out.println("\n=== Crear Empleado ===");

        Empleado empleado = new Empleado();

        System.out.print("Nombre: ");
        empleado.setNombre(scanner.nextLine());

        System.out.print("Apellido: ");
        empleado.setApellido(scanner.nextLine());

        System.out.print("Oficio: ");
        empleado.setOficio(scanner.nextLine());

        System.out.print("Fecha de alta (YYYY-MM-DD): ");
        empleado.setFechaAlt(LocalDate.parse(scanner.nextLine()));

        System.out.print("Salario: ");
        empleado.setSalario(BigDecimal.valueOf(scanner.nextDouble()));

        System.out.print("Comisión: ");
        empleado.setComision(BigDecimal.valueOf(scanner.nextDouble()));
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Nº Departamento: ");
        empleado.setDeptNo(empleadoDAO.obtenerDepartamento(Short.valueOf(scanner.nextLine())));

        empleadoDAO.create(empleado);
        System.out.println("Empleado creado exitosamente.");
    }

    private static void leerEmpleado() {
        System.out.println("\n=== Leer Empleado por ID ===");

        System.out.print("Ingresa el ID del empleado: ");
        int id = scanner.nextInt();

        Empleado empleado = empleadoDAO.read(id);
        if (empleado != null) {
            System.out.println("ID: " + empleado.getId());
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Apellido: " + empleado.getApellido());
            System.out.println("Oficio: " + empleado.getOficio());
            System.out.println("Fecha de Alta: " + empleado.getFechaAlt());
            System.out.println("Salario: " + empleado.getSalario());
            System.out.println("Comisión: " + empleado.getComision());
        } else {
            System.out.println("No se encontró un empleado con el ID proporcionado.");
        }
    }

    private static void leerTodosEmpleados() {
        System.out.println("\n=== Leer Todos los Empleados ===");

        List<Empleado> empleados = empleadoDAO.readAll();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado empleado : empleados) {
                System.out.println("ID: " + empleado.getId() + ", Nombre: " + empleado.getNombre() + ", Apellido: " + empleado.getApellido());
            }
        }
    }

    private static void actualizarEmpleado() {
        System.out.println("\n=== Actualizar Empleado ===");

        System.out.print("Ingresa el ID del empleado a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Empleado empleado = empleadoDAO.read(id);
        if (empleado == null) {
            System.out.println("No se encontró un empleado con el ID proporcionado.");
            return;
        }

        System.out.print("Nuevo Nombre (" + empleado.getNombre() + "): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            empleado.setNombre(nombre);
        }

        System.out.print("Nuevo Apellido (" + empleado.getApellido() + "): ");
        String apellido = scanner.nextLine();
        if (!apellido.isEmpty()) {
            empleado.setApellido(apellido);
        }

        System.out.print("Nuevo Oficio (" + empleado.getOficio() + "): ");
        String oficio = scanner.nextLine();
        if (!oficio.isEmpty()) {
            empleado.setOficio(oficio);
        }

        System.out.print("Nueva Fecha de Alta (" + empleado.getFechaAlt() + "): ");
        String fechaAlt = scanner.nextLine();
        if (!fechaAlt.isEmpty()) {
            empleado.setFechaAlt(LocalDate.parse(fechaAlt));
        }

        System.out.print("Nuevo Salario (" + empleado.getSalario() + "): ");
        String salario = scanner.nextLine();
        if (!salario.isEmpty()) {
            empleado.setSalario(BigDecimal.valueOf(Double.parseDouble(salario)));
        }

        System.out.print("Nueva Comisión (" + empleado.getComision() + "): ");
        String comision = scanner.nextLine();
        if (!comision.isEmpty()) {
            empleado.setComision(BigDecimal.valueOf(Double.parseDouble(comision)));
        }

        empleadoDAO.update(empleado);
        System.out.println("Empleado actualizado exitosamente.");
    }

    private static void eliminarEmpleado() {
        System.out.println("\n=== Eliminar Empleado ===");

        System.out.print("Ingresa el ID del empleado a eliminar: ");
        int id = scanner.nextInt();

        empleadoDAO.delete(id);
        System.out.println("Empleado eliminado exitosamente (si existía).");
    }
}
