package api.dtos;

import core.ValidacionDatosException;

public class ValidadorDTO {

    public static void validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidacionDatosException("El email es obligatorio y no puede estar vacío.");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new ValidacionDatosException("El formato del email es inválido. Debe contener '@' y un dominio.");
        }
    }

    public static void validarPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new ValidacionDatosException("La contraseña es obligatoria.");
        }
        if (password.length() < 6) {
            throw new ValidacionDatosException("La contraseña debe tener un mínimo de 6 caracteres.");
        }
    }
}