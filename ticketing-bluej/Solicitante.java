/**
 * Usuario que solicita soporte tecnico y aparece como duenio de los tickets.
 */
public class Solicitante extends Usuario {
    private final String departamento;
    private final String extension;

    public Solicitante(String id, String nombre, String email, String departamento, String extension) {
        super(id, nombre, email);
        this.departamento = departamento;
        this.extension = extension;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("[SOLICITANTE] " + getNombre() + " (ID: " + getId() + ")");
        System.out.println("  Email: " + getEmail());
        System.out.println("  Departamento: " + departamento);
        System.out.println("  Extension: " + extension);
    }
}
