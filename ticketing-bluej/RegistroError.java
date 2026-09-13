import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa un evento de error registrado en la bitacora de un ticket.
 */
public class RegistroError {
    private final String tipo;
    private final String descripcion;
    private final String impacto;
    private final LocalDateTime fecha;

    public RegistroError(String tipo, String descripcion, String impacto) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.impacto = impacto;
        this.fecha = LocalDateTime.now();
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImpacto() {
        return impacto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "[" + fecha.format(formato) + "] Tipo: " + tipo + " | Impacto: " + impacto + " | " + descripcion;
    }
}
