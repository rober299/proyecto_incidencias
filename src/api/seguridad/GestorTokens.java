package api.seguridad;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GestorTokens {
    // Guarda el token y el rol asociado (ej: "admin" o "user")
    private static final Map<String, String> tokensValidos = new HashMap<>();
    private static final Map<String, LocalDateTime> expiracionTokens = new HashMap<>();

    // Genera un token válido por 1 hora
    public static String generarToken(String rol) {
        String token = UUID.randomUUID().toString();
        tokensValidos.put(token, rol);
        expiracionTokens.put(token, LocalDateTime.now().plusHours(1));
        return token;
    }

    public static boolean validarToken(String token) {
        if (!tokensValidos.containsKey(token)) {
            return false;
        }
        if (LocalDateTime.now().isAfter(expiracionTokens.get(token))) {
            tokensValidos.remove(token); // Limpiamos si caducó
            expiracionTokens.remove(token);
            return false;
        }
        return true;
    }

    public static String obtenerRol(String token) {
        return tokensValidos.get(token);
    }
}