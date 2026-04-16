import java.sql.*;

public class UsuarioDAOImpl implements UsuarioDAO {
    private Connection conexion;

    public UsuarioDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Usuario insertar(Usuario usuario) {
        String sql = "INSERT INTO USUARIOS (email, password, activo, id_rol) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getPassword());
            stmt.setBoolean(3, usuario.isActivo());
            stmt.setInt(4, usuario.getIdRol());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setIdUsuario(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public Usuario obtenerPorId(int id) {
        String sql = "SELECT id_usuario, email, password, activo, id_rol FROM USUARIOS WHERE id_usuario = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario(rs.getString("email"), rs.getString("password"), rs.getBoolean("activo"),
                            rs.getInt("id_rol"));
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean eliminarLogico(int id) {
        String sql = "UPDATE USUARIOS SET activo = FALSE WHERE id_usuario = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}