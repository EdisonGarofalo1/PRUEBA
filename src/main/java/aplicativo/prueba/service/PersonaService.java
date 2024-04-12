package aplicativo.prueba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;


import aplicativo.prueba.model.Persona;



public interface PersonaService {
	
	public List<Persona> findAll();
	public Persona findById(Integer id) throws Exception;
	public Persona save(Persona persona) throws Exception;
	public void deleteById(Integer id);
	
	
	public boolean validarIdentificacion(String identificacion);
	public String generarCorreoElectronico(Persona persona);

}
	
