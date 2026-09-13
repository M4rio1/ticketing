import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Punto de entrada del sistema de tickets de soporte tecnico corporativo.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 1. Herencia: Tecnico y Solicitante son tipos especializados de Usuario.
        Tecnico tecnicoSoftware = new Tecnico("T01", "Ana Lopez", "ana@empresa.com", "Software", 2);
        Tecnico tecnicoHardware = new Tecnico("T02", "Carlos Mendez", "carlos@empresa.com", "Hardware", 2);
        Tecnico tecnicoGeneral = new Tecnico("T03", "Maria Perez", "maria@empresa.com", "General", 3);

        Solicitante solicitanteContabilidad = new Solicitante("S01", "Luis Ramirez", "luis@empresa.com", "Contabilidad", "1201");
        Solicitante solicitanteVentas = new Solicitante("S02", "Karla Gomez", "karla@empresa.com", "Ventas", "1305");

        // 2. Polimorfismo: una lista de Usuario puede contener tecnicos y solicitantes.
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(tecnicoSoftware);
        usuarios.add(tecnicoHardware);
        usuarios.add(tecnicoGeneral);
        usuarios.add(solicitanteContabilidad);
        usuarios.add(solicitanteVentas);

        // 3. Gestion centralizada: el gestor administra tecnicos, solicitantes y tickets.
        GestorTickets gestor = new GestorTickets();
        gestor.getTecnicos().add(tecnicoSoftware);
        gestor.getTecnicos().add(tecnicoHardware);
        gestor.getTecnicos().add(tecnicoGeneral);
        gestor.getSolicitantes().add(solicitanteContabilidad);
        gestor.getSolicitantes().add(solicitanteVentas);

        // 4. Flujo del ticket: define el ciclo Abierto -> Asignado -> Resuelto -> Cerrado.
        FlujoTicket flujoTicket = new FlujoTicket();

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n========================================================");
            System.out.println(" SISTEMA DE TICKETS DE SOPORTE TECNICO CORPORATIVO");
            System.out.println("========================================================");
            System.out.println(" 1. Ver usuarios del sistema (polimorfismo)");
            System.out.println(" 2. Ver flujo de estados del ticket");
            System.out.println(" 3. Crear ticket y asignar automaticamente");
            System.out.println(" 4. Ver tickets");
            System.out.println(" 5. Registrar error en ticket");
            System.out.println(" 6. Resolver ticket");
            System.out.println(" 7. Cerrar ticket");
            System.out.println(" 8. Generar resumen de control");
            System.out.println(" 9. Salir");
            System.out.print("\n Seleccione una opcion (1-9): ");

            try {
                String opcion = scanner.nextLine().trim();

                switch (opcion) {
                    case "1":
                        mostrarUsuariosPolimorfismo(usuarios);
                        break;

                    case "2":
                        flujoTicket.mostrarFlujo();
                        break;

                    case "3":
                        crearTicket(gestor);
                        break;

                    case "4":
                        gestor.mostrarTickets();
                        break;

                    case "5":
                        registrarError(gestor);
                        break;

                    case "6":
                        resolverTicket(gestor, flujoTicket);
                        break;

                    case "7":
                        cerrarTicket(gestor, flujoTicket);
                        break;

                    case "8":
                        gestor.generarResumenControl();
                        break;

                    case "9":
                        continuar = false;
                        System.out.println("\nGracias por utilizar el sistema de soporte.");
                        break;

                    default:
                        System.out.println("Opcion no valida. Ingrese un numero del 1 al 9.");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Error controlado por try/catch: " + ex.getMessage());
            }
        }
    }

    /**
     * Demuestra polimorfismo: cada objeto ejecuta su propia version de mostrarInformacion().
     */
    private static void mostrarUsuariosPolimorfismo(List<Usuario> usuarios) {
        System.out.println("\n=== USUARIOS DEL SISTEMA ===");

        for (Usuario usuario : usuarios) {
            usuario.mostrarInformacion();
            System.out.println();
        }
    }

    private static void crearTicket(GestorTickets gestor) {
        System.out.println("\nSolicitantes disponibles:");
        for (int i = 0; i < gestor.getSolicitantes().size(); i++) {
            System.out.println(" " + (i + 1) + ". " + gestor.getSolicitantes().get(i).getNombre() + " - " + gestor.getSolicitantes().get(i).getDepartamento());
        }

        System.out.print("Seleccione solicitante: ");
        int indice = Integer.parseInt(scanner.nextLine().trim()) - 1;

        if (indice < 0 || indice >= gestor.getSolicitantes().size()) {
            throw new IndexOutOfBoundsException("Solicitante: seleccion fuera de rango.");
        }

        System.out.print("Titulo del problema: ");
        String titulo = scanner.nextLine();

        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();

        System.out.print("Categoria (Software/Hardware/Red/General): ");
        String categoria = scanner.nextLine();

        System.out.print("Prioridad (Baja/Media/Alta/Critica): ");
        String prioridad = scanner.nextLine();

        Ticket ticket = gestor.crearTicket(titulo, descripcion, categoria, prioridad, gestor.getSolicitantes().get(indice));

        System.out.println("\nTicket creado correctamente.");
        ticket.mostrarResumen();
    }

    private static void registrarError(GestorTickets gestor) {
        Ticket ticket = solicitarTicket(gestor);

        System.out.print("Tipo de error (Software/Hardware/Red/Usuario): ");
        String tipo = scanner.nextLine();

        System.out.print("Descripcion del error: ");
        String descripcion = scanner.nextLine();

        System.out.print("Impacto (Bajo/Medio/Alto/Critico): ");
        String impacto = scanner.nextLine();

        ticket.registrarError(tipo, descripcion, impacto);
        System.out.println("Error registrado correctamente.");
        ticket.mostrarBitacora();
    }

    private static void resolverTicket(GestorTickets gestor, FlujoTicket flujoTicket) {
        Ticket ticket = solicitarTicket(gestor);

        if (!flujoTicket.puedeCambiarEstado(ticket.getEstado(), "Resuelto")) {
            throw new IllegalStateException("El flujo no permite resolver el ticket desde el estado actual.");
        }

        System.out.print("Solucion aplicada: ");
        String solucion = scanner.nextLine();

        ticket.resolver(solucion);
        System.out.println("Ticket resuelto correctamente.");
        ticket.mostrarBitacora();
    }

    private static void cerrarTicket(GestorTickets gestor, FlujoTicket flujoTicket) {
        Ticket ticket = solicitarTicket(gestor);

        if (!flujoTicket.puedeCambiarEstado(ticket.getEstado(), "Cerrado")) {
            throw new IllegalStateException("El flujo no permite cerrar el ticket desde el estado actual.");
        }

        ticket.cerrar();
        System.out.println("Ticket cerrado correctamente.");
        ticket.mostrarBitacora();
    }

    private static Ticket solicitarTicket(GestorTickets gestor) {
        System.out.print("Ingrese numero de ticket: ");
        int numero = Integer.parseInt(scanner.nextLine().trim());
        return gestor.buscarTicket(numero);
    }
}
