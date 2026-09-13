import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Define y valida el ciclo de vida lineal de un ticket:
 * Abierto -> Asignado -> Resuelto -> Cerrado.
 */
public class FlujoTicket {
    private final Map<String, List<String>> transiciones;

    public FlujoTicket() {
        transiciones = new HashMap<String, List<String>>();

        List<String> desdeAbierto = new ArrayList<String>();
        desdeAbierto.add("Asignado");
        transiciones.put("Abierto", desdeAbierto);

        List<String> desdeAsignado = new ArrayList<String>();
        desdeAsignado.add("Resuelto");
        transiciones.put("Asignado", desdeAsignado);

        List<String> desdeResuelto = new ArrayList<String>();
        desdeResuelto.add("Cerrado");
        transiciones.put("Resuelto", desdeResuelto);
    }

    /**
     * Indica si es valido pasar del estado actual al estado destino segun el flujo definido.
     */
    public boolean puedeCambiarEstado(String actual, String destino) {
        if (!transiciones.containsKey(actual)) {
            return false;
        }

        return transiciones.get(actual).contains(destino);
    }

    public void mostrarFlujo() {
        System.out.println("\n=== FLUJO DE ESTADOS DEL TICKET ===");
        System.out.println("  Abierto -> Asignado -> Resuelto -> Cerrado");
    }
}
