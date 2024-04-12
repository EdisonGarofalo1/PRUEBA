package aplicativo.prueba.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name = "rol")
public class Rol implements Serializable {



	private static final long serialVersionUID = 6318088158676989100L;

@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @Column(name = "RolName", length = 50)
    private String rolName;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;
    
    
    @ManyToMany
    @JoinTable(
        name = "rol_rolOpciones",
        joinColumns = @JoinColumn(name = "Rol_idRol"),
        inverseJoinColumns = @JoinColumn(name = "rolOpciones_idOpcion")
    )
    private List<RolOpcion> opciones;


	public Rol() {
		super();
	}


	public Integer getIdRol() {
		return idRol;
	}


	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}


	public String getRolName() {
		return rolName;
	}


	public void setRolName(String rolName) {
		this.rolName = rolName;
	}


	public List<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public List<RolOpcion> getOpciones() {
		return opciones;
	}


	public void setOpciones(List<RolOpcion> opciones) {
		this.opciones = opciones;
	}
    
    
    

}
