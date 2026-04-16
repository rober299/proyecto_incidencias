public interface UsuarioDAO {
    Usuario insertar(Usuario usuario);

    Usuario obtenerPorId(int id);

    boolean eliminarLogico(int id);
}