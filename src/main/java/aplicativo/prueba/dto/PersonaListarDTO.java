package aplicativo.prueba.dto;

import java.sql.Date;



public class PersonaListarDTO {
	 private Integer idPersona;

	  
	    private String nombres;

	 
	    private String apellidos;


	    private String identificacion;

	    private Date fechaNacimiento;

		public Integer getIdPersona() {
			return idPersona;
		}

		public void setIdPersona(Integer idPersona) {
			this.idPersona = idPersona;
		}

		public String getNombres() {
			return nombres;
		}

		public void setNombres(String nombres) {
			this.nombres = nombres;
		}

		public String getApellidos() {
			return apellidos;
		}

		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}

		public String getIdentificacion() {
			return identificacion;
		}

		public void setIdentificacion(String identificacion) {
			this.identificacion = identificacion;
		}

		public Date getFechaNacimiento() {
			return fechaNacimiento;
		}

		public void setFechaNacimiento(Date fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}
	    
	    
}
