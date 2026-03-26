public class TestTDD {
    private static int fallos = 0;

    private static void afirmar(boolean condicion, String nombreTest) {
        if (condicion) {
            System.out.println("[OK] PASA: " + nombreTest);
        } else {
            System.out.println("[FALLO] FALLA: " + nombreTest);
            fallos++;
        }
    }

    public static void main(String[] args) {
        System.out.println("CICLO TDD: FASE DE PRUEBAS\n");

        // 1. ABIERTA -> EN_PROGRESO (Debe ser VÁLIDA)
        afirmar(ValidadorEstado.esTransicionValida("ABIERTA", "EN_PROGRESO"), "Transicion ABIERTA -> EN_PROGRESO");

        // 2. EN_PROGRESO -> CERRADA (Debe ser VÁLIDA)
        afirmar(ValidadorEstado.esTransicionValida("EN_PROGRESO", "CERRADA"), "Transicion EN_PROGRESO -> CERRADA");

        // 3. ABIERTA -> CERRADA (Debe ser INVÁLIDA)
        afirmar(!ValidadorEstado.esTransicionValida("ABIERTA", "CERRADA"),
                "Transicion invalida ABIERTA -> CERRADA es rechazada");

        if (fallos > 0) {
            System.out
                    .println("\n[NO ÉXITO] ATENCIÓN: Hay " + fallos + " test(s) fallando. Toca implementar el código");
        } else {
            System.out.println("\n[ÉXITO] TODO CORRECTO: Todos los tests pasan perfectamente.");
        }
    }
}