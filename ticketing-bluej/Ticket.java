import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un ticket de soporte tecnico y su ciclo de vida completo.
 */
public class Ticket {
    private final int numero;
    private final String titulo;
    private final String descripcion;
    private final String categoria;
    private final String prioridad;
    private String estado;
    private String solucion;
    private final Solicitante solicitante;
    private Tecnico tecnicoAsignado;
    private final List<RegistroError> bitacora;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;
    private LocalDateTime fechaCierre;

    public Ticket(int numero, String titulo, String descripcion, String categoria, String prioridad, Solicitante solicitante) {
        this.numero = numero;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.solicitante = solicitante;
        this.estado = "Abierto";
        this.solucion = "";
        this.bitacora = new ArrayList<RegistroError>();
        this.fechaCreacion = LocalDateTime.now();
    }

    public int getNumero() {
        return numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public String getSolucion() {
        return solucion;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public Tecnico getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public List<RegistroError> getBitacora() {
        return bitacora;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    /**
     * Asigna un tecnico al ticket, cambia el estado a "Asignado" y sincroniza
     * la carga de trabajo del tecnico agregando este ticket a su lista.
     */
    public void asignarTecnico(Tecnico tecnico) {
        this.tecnicoAsignado = tecnico;
        this.estado = "Asignado";
        tecnico.asignarTicket(this);
    }

    public void registrarError(String tipo, String descripcion, String impacto) {
        bitacora.add(new RegistroError(tipo, descripcion, impacto));
    }

    public void resolver(String solucion) {
        this.solucion = solucion;
        this.estado = "Resuelto";
        this.fechaResolucion = LocalDateTime.now();
    }

    public void cerrar() {
        this.estado = "Cerrado";
        this.fechaCierre = LocalDateTime.now();
    }

    public void mostrarResumen() {
        System.out.println("--- Ticket #" + numero + " ---");
        System.out.println("  Titulo: " + titulo);
        System.out.println("  Categoria: " + categoria);
        System.out.println("  Prioridad: " + prioridad);
        System.out.println("  Estado: " + estado);
        System.out.println("  Solicitante: " + solicitante.getNombre() + " (" + solicitante.getDepartamento() + ")");
        System.out.println("  Tecnico asignado: " + (tecnicoAsignado != null ? tecnicoAsignado.getNombre() : "sin asignar"));
    }

    public void mostrarBitacora() {
        System.out.println("--- Bitacora del ticket #" + numero + " ---");

        if (bitacora.isEmpty()) {
            System.out.println("  No hay errores registrados en este ticket.");
            return;
        }

        for (RegistroError registro : bitacora) {
            System.out.println("  " + registro.toString());
        }
    }
}
