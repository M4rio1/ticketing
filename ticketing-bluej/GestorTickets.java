import java.util.ArrayList;
import java.util.List;

/**
 * Administra de forma centralizada tecnicos, solicitantes y tickets.
 */
public class GestorTickets {
    private final List<Tecnico> tecnicos;
    private final List<Solicitante> solicitantes;
    private final List<Ticket> tickets;

    private int contadorTickets;

    public GestorTickets() {
        this.tecnicos = new ArrayList<Tecnico>();
        this.solicitantes = new ArrayList<Solicitante>();
        this.tickets = new ArrayList<Ticket>();
        this.contadorTickets = 1;
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public List<Solicitante> getSolicitantes() {
        return solicitantes;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Crea un ticket y trata de asignarlo automaticamente al tecnico con
     * especialidad coincidente (o "General" como comodin) que tenga menor carga.
     * Si no hay tecnico disponible, el ticket queda "Abierto" sin asignar.
     */
    public Ticket crearTicket(String titulo, String descripcion, String categoria, String prioridad, Solicitante solicitante) {
        Ticket ticket = new Ticket(contadorTickets, titulo, descripcion, categoria, prioridad, solicitante);
        contadorTickets++;
        tickets.add(ticket);

        Tecnico tecnicoElegido = buscarTecnicoConMenorCarga(categoria);
        if (tecnicoElegido != null) {
            ticket.asignarTecnico(tecnicoElegido);
        }

        return ticket;
    }

    /**
     * Balanceo de carga: primero busca tecnicos cuya especialidad coincida
     * exactamente con la categoria (sin distinguir mayusculas/minusculas); si no
     * hay ninguno, usa los tecnicos de especialidad "General" como comodin.
     * Entre los candidatos, elige el de menor obtenerCargaActual().
     */
    private Tecnico buscarTecnicoConMenorCarga(String categoria) {
        List<Tecnico> candidatos = new ArrayList<Tecnico>();

        for (Tecnico tecnico : tecnicos) {
            if (tecnico.getEspecialidad().equalsIgnoreCase(categoria)) {
                candidatos.add(tecnico);
            }
        }

        if (candidatos.isEmpty()) {
            for (Tecnico tecnico : tecnicos) {
                if (tecnico.getEspecialidad().equalsIgnoreCase("General")) {
                    candidatos.add(tecnico);
                }
            }
        }

        if (candidatos.isEmpty()) {
            return null;
        }

        Tecnico menorCarga = candidatos.get(0);
        for (Tecnico tecnico : candidatos) {
            if (tecnico.obtenerCargaActual() < menorCarga.obtenerCargaActual()) {
                menorCarga = tecnico;
            }
        }

        return menorCarga;
    }

    public Ticket buscarTicket(int numero) {
        for (Ticket ticket : tickets) {
            if (ticket.getNumero() == numero) {
                return ticket;
            }
        }

        throw new IllegalArgumentException("No existe un ticket con el numero " + numero + ".");
    }

    public void mostrarTickets() {
        System.out.println("\n=== TICKETS REGISTRADOS ===");

        if (tickets.isEmpty()) {
            System.out.println("No hay tickets registrados.");
            return;
        }

        for (Ticket ticket : tickets) {
            ticket.mostrarResumen();
            System.out.println();
        }
    }

    public void generarResumenControl() {
        int abiertos = 0;
        int asignados = 0;
        int resueltos = 0;
        int cerrados = 0;

        for (Ticket ticket : tickets) {
            switch (ticket.getEstado()) {
                case "Abierto":
                    abiertos++;
                    break;
                case "Asignado":
                    asignados++;
                    break;
                case "Resuelto":
                    resueltos++;
                    break;
                case "Cerrado":
                    cerrados++;
                    break;
                default:
                    break;
            }
        }

        System.out.println("\n=== RESUMEN DE CONTROL ===");
        System.out.println("  Abiertos:  " + abiertos);
        System.out.println("  Asignados: " + asignados);
        System.out.println("  Resueltos: " + resueltos);
        System.out.println("  Cerrados:  " + cerrados);
        System.out.println("  Total de tickets: " + tickets.size());
    }
}
