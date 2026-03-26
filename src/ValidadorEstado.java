public class ValidadorEstado {
    public static boolean esTransicionValida(String origen, String destino) {

        boolean deAbiertaAProgreso = origen.equals("ABIERTA") && destino.equals("EN_PROGRESO");
        boolean deProgresoACerrada = origen.equals("EN_PROGRESO") && destino.equals("CERRADA");

        return deAbiertaAProgreso || deProgresoACerrada;
    }
}