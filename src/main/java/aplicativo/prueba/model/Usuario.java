package aplicativo.prueba.model;
import java.io.Serializable;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
	
	 
	private static final long serialVersionUID = 1245602698118385910L;

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer idUsuario;

	    @Column(name = "UserName", length = 50)
	    private String userName;

	    @Column(name = "Password", length = 500)
	    private String password;

	    @Column(name = "Mail", length = 120)
	    private String mail;

	    @Column(name = "SessionActive", length = 1)
	    private String sessionActive;

	 
	    @ManyToOne
	    @JoinColumn(name = "Persona_idPersona2")
	    private Persona persona;

	    @Column(name = "Status", length = 20)
	    private String status;
	    
	    @ManyToMany
	    @JoinTable(
	        name = "rol_usuarios",
	        joinColumns = @JoinColumn(name = "ROL_idRol"),
	        inverseJoinColumns = @JoinColumn(name = "usuario_idUsuario")
	    )
	    private List<Rol> roles;
	    
	    
	    @OneToMany(mappedBy = "usuario")
	    private List<Sesion> sesiones;

		public Usuario() {
			super();
		}

		public Integer getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(Integer idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		public String getSessionActive() {
			return sessionActive;
		}

		public void setSessionActive(String sessionActive) {
			this.sessionActive = sessionActive;
		}

		public Persona getPersona() {
			return persona;
		}

		public void setPersona(Persona persona) {
			this.persona = persona;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public List<Rol> getRoles() {
			return roles;
		}

		public void setRoles(List<Rol> roles) {
			this.roles = roles;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	    

	    
	    
	    
}
