package aplicativo.prueba.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "RolOpciones")
public class RolOpcion implements Serializable {
	
	

	private static final long serialVersionUID = 6214472828178594253L;

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer idOpcion;

	    @Column(name = "NombreOpcion", length = 50)
	    private String nombreOpcion;

	    
	    @ManyToMany(mappedBy = "opciones")
	    private List<Rol> roles;


		public RolOpcion() {
			super();
		}


		public Integer getIdOpcion() {
			return idOpcion;
		}


		public void setIdOpcion(Integer idOpcion) {
			this.idOpcion = idOpcion;
		}


		public String getNombreOpcion() {
			return nombreOpcion;
		}


		public void setNombreOpcion(String nombreOpcion) {
			this.nombreOpcion = nombreOpcion;
		}


		public List<Rol> getRoles() {
			return roles;
		}


		public void setRoles(List<Rol> roles) {
			this.roles = roles;
		}


}
