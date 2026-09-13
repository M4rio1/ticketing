/**
 * Clase base abstracta para todo usuario del sistema de tickets.
 * Tecnico y Solicitante heredan de aqui (herencia).
 */
public abstract class Usuario {
    private final String id;
    private final String nombre;
    private final String email;

    public Usuario(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Cada tipo de usuario muestra su informacion de forma distinta (polimorfismo).
     */
    public abstract void mostrarInformacion();
}
