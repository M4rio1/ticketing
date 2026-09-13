import java.util.ArrayList;
import java.util.List;

/**
 * Usuario tecnico que puede recibir tickets asignados segun su especialidad.
 */
public class Tecnico extends Usuario {
    private final String especialidad;
    private final int nivelExperiencia;
    private final List<Ticket> ticketsAsignados;

    public Tecnico(String id, String nombre, String email, String especialidad, int nivelExperiencia) {
        super(id, nombre, email);
        this.especialidad = especialidad;
        this.nivelExperiencia = nivelExperiencia;
        this.ticketsAsignados = new ArrayList<Ticket>();
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getNivelExperiencia() {
        return nivelExperiencia;
    }

    public List<Ticket> getTicketsAsignados() {
        return ticketsAsignados;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("[TECNICO] " + getNombre() + " (ID: " + getId() + ")");
        System.out.println("  Email: " + getEmail());
        System.out.println("  Especialidad: " + especialidad);
        System.out.println("  Nivel de experiencia: " + nivelExperiencia);
        System.out.println("  Carga actual: " + obtenerCargaActual() + " ticket(s) activos");
    }

    /**
     * Agrega un ticket a la lista de tickets asignados a este tecnico.
     */
    public void asignarTicket(Ticket ticket) {
        ticketsAsignados.add(ticket);
    }

    /**
     * Cuenta los tickets asignados que aun no estan cerrados (carga de trabajo activa).
     */
    public int obtenerCargaActual() {
        int carga = 0;

        for (Ticket ticket : ticketsAsignados) {
            if (!ticket.getEstado().equals("Cerrado")) {
                carga++;
            }
        }

        return carga;
    }
}
