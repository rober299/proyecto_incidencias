package api;

import api.config.ServidorAPI;

public class MainAPI {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   ARRANQUE DEL BACKEND - GESTIÓN IT      ");
        System.out.println("==========================================");

        ServidorAPI servidor = new ServidorAPI();
        servidor.iniciar();
    }
}