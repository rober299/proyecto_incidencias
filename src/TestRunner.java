public class TestRunner {

    // Utilidad para imprimir el resultado del test
    private static void afirmar(boolean condicion, String nombreTest) {
        if (condicion) {
            System.out.println("[OK] PASA: " + nombreTest);
        } else {
            System.out.println("[FALLO] FALLA: " + nombreTest);
        }
    }

    public static void test01_calcularPrioridad_ValoresNormales_DevuelveMultiplicacion() {
        int resultado = LogicaNegocio.calcularPrioridad(2, 3);
        afirmar(resultado == 6, "test01_calcularPrioridad_ValoresNormales_DevuelveMultiplicacion");
    }

    public static void test02_calcularPrioridad_UrgenciaCero_LanzaExcepcion() {
        try {
            LogicaNegocio.calcularPrioridad(0, 3);
            afirmar(false, "test02_calcularPrioridad_UrgenciaCero_LanzaExcepcion (No lanzó excepción)");
        } catch (IllegalArgumentException e) {
            afirmar(true, "test02_calcularPrioridad_UrgenciaCero_LanzaExcepcion");
        }
    }

    public static void test03_cambiarEstado_CerradaAEnProgreso_LanzaExcepcion() {
        try {
            LogicaNegocio.cambiarEstado("CERRADA", "EN_PROGRESO");
            afirmar(false, "test03_cambiarEstado_CerradaAEnProgreso_LanzaExcepcion (Permitió el cambio)");
        } catch (IllegalStateException e) {
            afirmar(true, "test03_cambiarEstado_CerradaAEnProgreso_LanzaExcepcion");
        }
    }

    public static void test04_validarUsuario_NombreVacio_LanzaExcepcion() {
        try {
            LogicaNegocio.validarUsuario("   ");
            afirmar(false, "test04_validarUsuario_NombreVacio_LanzaExcepcion (Tragó nombre vacío)");
        } catch (IllegalArgumentException e) {
            afirmar(true, "test04_validarUsuario_NombreVacio_LanzaExcepcion");
        }
    }

    public static void test05_asignarTecnico_RolCliente_LanzaExcepcionSeguridad() {
        try {
            LogicaNegocio.asignarTecnico("CLIENTE");
            afirmar(false, "test05_asignarTecnico_RolCliente_LanzaExcepcionSeguridad (Asignó a cliente)");
        } catch (SecurityException e) {
            afirmar(true, "test05_asignarTecnico_RolCliente_LanzaExcepcionSeguridad");
        }
    }

    // Ejecución
    public static void main(String[] args) {
        System.out.println("INICIANDO RUNNER DE TESTS UNITARIOS\n");

        test01_calcularPrioridad_ValoresNormales_DevuelveMultiplicacion();
        test02_calcularPrioridad_UrgenciaCero_LanzaExcepcion();
        test03_cambiarEstado_CerradaAEnProgreso_LanzaExcepcion();
        test04_validarUsuario_NombreVacio_LanzaExcepcion();
        test05_asignarTecnico_RolCliente_LanzaExcepcionSeguridad();

        System.out.println("\nEJECUCIÓN FINALIZADA");
    }
}