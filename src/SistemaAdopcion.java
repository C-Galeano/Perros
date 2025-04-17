import java.util.*;

public class SistemaAdopcion {

    private static List<Perro> perrosDisponibles = new ArrayList<>();
    private static List<Persona> personasRegistradas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion = 0;

        do {
            mostrarMenu();
            try {
                System.out.print("Ingrese su opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        registrarPersona();
                        break;
                    case 2:
                        registrarPerro();
                        break;
                    case 3:
                        verPersonasRegistradas();
                        break;
                    case 4:
                        verPerrosDisponibles();
                        break;
                    case 5:
                        adoptarPerro();
                        break;
                    case 6:
                        consultarPerroMasViejoPorPersona();
                        break;
                    case 7:
                        System.out.println("\nSaliendo del sistema de adopción. ¡Gracias!");
                        break;
                    default:
                        System.out.println("\nOpción no válida. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError: Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine();
                opcion = 0;
            }
            if (opcion != 7) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcion != 7);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Sistema de Adopción de Perros ---");
        System.out.println("1. Registrar Persona");
        System.out.println("2. Registrar Perro (para adopción)");
        System.out.println("3. Ver Personas Registradas");
        System.out.println("4. Ver Perros Disponibles para Adopción");
        System.out.println("5. Adoptar un Perro");
        System.out.println("6. Consultar Perro más Viejo Adoptado por Persona");
        System.out.println("7. Salir");
        System.out.println("-------------------------------------");
    }

    private static void registrarPersona() {
        System.out.println("\n--- Registrar Nueva Persona ---");
        System.out.print("Ingrese el documento de la persona: ");
        String documento = scanner.nextLine();


        if (buscarPersonaPorDocumento(documento) != null) {
            System.out.println("Error: Ya existe una persona registrada con ese documento.");
            return;
        }

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        int edad = -1;
        while (edad < 0) {
            try {
                System.out.print("Ingrese la edad: ");
                edad = scanner.nextInt();
                if (edad < 0) {
                    System.out.println("Error: La edad no puede ser negativa.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido para la edad.");
                scanner.next();
            } finally {
                scanner.nextLine();
            }
        }


        Persona nuevaPersona = new Persona(nombre, apellido, edad, documento);
        personasRegistradas.add(nuevaPersona);
        System.out.println("¡Persona registrada exitosamente!");
    }

    private static void registrarPerro() {
        System.out.println("\n--- Registrar Nuevo Perro para Adopción ---");
        System.out.print("Ingrese la placa del perro: ");
        String placa = scanner.nextLine();


        if (buscarPerroDisponiblePorPlaca(placa) != null) {
            System.out.println("Error: Ya existe un perro disponible con esa placa.");
            return;
        }
        if (buscarPerroAdoptadoPorPlacaGeneral(placa) != null) {
            System.out.println("Error: Un perro con esa placa ya ha sido adoptado previamente.");
            return;
        }


        System.out.print("Ingrese el nombre del perro: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la raza: ");
        String raza = scanner.nextLine();
        int edad = -1;
        while (edad < 0) {
            try {
                System.out.print("Ingrese la edad del perro: ");
                edad = scanner.nextInt();
                if (edad < 0) {
                    System.out.println("Error: La edad no puede ser negativa.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido para la edad.");
                scanner.next();
            } finally {
                scanner.nextLine();
            }
        }
        System.out.print("Ingrese el tamaño (Pequeño, Mediano, Grande): ");
        String tamaño = scanner.nextLine();

        Perro nuevoPerro = new Perro(placa, nombre, raza, edad, tamaño);
        perrosDisponibles.add(nuevoPerro);
        System.out.println("¡Perro registrado exitosamente y disponible para adopción!");
    }

    private static void verPersonasRegistradas() {
        System.out.println("\n--- Personas Registradas ---");
        if (personasRegistradas.isEmpty()) {
            System.out.println("No hay personas registradas en el sistema.");
        } else {
            for (Persona persona : personasRegistradas) {
                System.out.println(persona);
                System.out.println("---");
            }
        }
    }

    private static void verPerrosDisponibles() {
        System.out.println("\n--- Perros Disponibles para Adopción ---");
        if (perrosDisponibles.isEmpty()) {
            System.out.println("No hay perros disponibles para adoptar en este momento.");
        } else {
            for (Perro perro : perrosDisponibles) {
                System.out.println(perro);
            }
        }
    }

    private static void adoptarPerro() {
        System.out.println("\n--- Adoptar un Perro ---");

        if (personasRegistradas.isEmpty()) {
            System.out.println("No hay personas registradas para poder adoptar.");
            return;
        }
        if (perrosDisponibles.isEmpty()) {
            System.out.println("No hay perros disponibles para adoptar en este momento.");
            return;
        }

        System.out.print("Ingrese el documento de la persona que adoptará: ");
        String docPersona = scanner.nextLine();
        Persona personaAdoptante = buscarPersonaPorDocumento(docPersona);

        if (personaAdoptante == null) {
            System.out.println("Error: No se encontró una persona con ese documento.");
            return;
        }


        if (personaAdoptante.getPerrosAdoptados().size() >= Persona.MAX_PERROS_ADOPTADOS) {
            System.out.println("Error: La persona " + personaAdoptante.getNombre() + " ya ha alcanzado el límite de adopciones (" + Persona.MAX_PERROS_ADOPTADOS + ").");
            return;
        }

        System.out.println("\nPerros disponibles:");
        verPerrosDisponibles();
        System.out.print("Ingrese la placa del perro a adoptar: ");
        String placaPerro = scanner.nextLine();
        Perro perroAAdoptar = buscarPerroDisponiblePorPlaca(placaPerro);

        if (perroAAdoptar == null) {
            System.out.println("Error: No se encontró un perro disponible con esa placa.");
            return;
        }

        // Intentar la adopción (el método de Persona ya valida el límite)
        if (personaAdoptante.adoptarPerro(perroAAdoptar)) {
            perrosDisponibles.remove(perroAAdoptar);
            System.out.println("¡Adopción exitosa! " + personaAdoptante.getNombre() + " ha adoptado a " + perroAAdoptar.getNombre() + ".");
        }

    }

    private static void consultarPerroMasViejoPorPersona() {
        System.out.println("\n--- Consultar Perro Más Viejo Adoptado ---");

        if (personasRegistradas.isEmpty()) {
            System.out.println("No hay personas registradas en el sistema.");
            return;
        }

        System.out.print("Ingrese el documento de la persona a consultar: ");
        String docPersona = scanner.nextLine();
        Persona persona = buscarPersonaPorDocumento(docPersona);

        if (persona == null) {
            System.out.println("Error: No se encontró una persona con ese documento.");
            return;
        }

        Perro perroMasViejo = persona.perroMasGrande();

        if (perroMasViejo == null) {
            System.out.println(persona.getNombre() + " " + persona.getApellido() + " no ha adoptado ningún perro todavía.");
        } else {
            System.out.println("El perro adoptado más viejo por " + persona.getNombre() + " " + persona.getApellido() + " es:");
            System.out.println(perroMasViejo);
        }
    }


    private static Persona buscarPersonaPorDocumento(String documento) {
        for (Persona persona : personasRegistradas) {
            if (persona.getDocumento().equalsIgnoreCase(documento)) {
                return persona;
            }
        }
        return null;
    }

    private static Perro buscarPerroDisponiblePorPlaca(String placa) {
        for (Perro perro : perrosDisponibles) {
            if (perro.getPlaca().equalsIgnoreCase(placa)) {
                return perro;
            }
        }
        return null;
    }


    private static Perro buscarPerroAdoptadoPorPlacaGeneral(String placa) {
        for (Persona persona : personasRegistradas) {
            for (Perro perroAdoptado : persona.getPerrosAdoptados()) {
                if (perroAdoptado.getPlaca().equalsIgnoreCase(placa)) {
                    return perroAdoptado;
                }
            }
        }
        return null;
    }
}