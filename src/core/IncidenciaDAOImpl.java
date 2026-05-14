package core;

import java.sql.*;

public class IncidenciaDAOImpl implements IncidenciaDAO {
    private Connection conexion;

    public IncidenciaDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Incidencia insertar(Incidencia incidencia) {
        String sql = "INSERT INTO INCIDENCIAS (titulo, descripcion, prioridad, estado, id_creador, id_categoria) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, incidencia.getTitulo());
            stmt.setString(2, incidencia.getDescripcion());
            stmt.setInt(3, incidencia.getPrioridad());
            stmt.setString(4, incidencia.getEstado());
            stmt.setInt(5, incidencia.getIdCreador());
            stmt.setInt(6, incidencia.getIdCategoria());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    incidencia.setIdIncidencia(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidencia;
    }
}