package orm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class UsuarioORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean activo;

    @Column(name = "id_rol")
    private int idRol;

    @OneToMany(mappedBy = "creador", fetch = FetchType.LAZY)
    private List<IncidenciaORM> incidencias;

    // Constructor vacío obligatorio para ORM
    public UsuarioORM() {
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}