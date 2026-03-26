public class ValidadorEstado {
    public static boolean esTransicionValida(String origen, String destino) {
        if (origen.equals("ABIERTA") && destino.equals("EN_PROGRESO")) {
            return true;
        }
        if (origen.equals("EN_PROGRESO") && destino.equals("CERRADA")) {
            return true;
        }
        return false;
    }
}