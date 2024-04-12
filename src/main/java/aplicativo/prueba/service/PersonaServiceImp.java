package aplicativo.prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import aplicativo.prueba.dto.PersonaDTO;
import aplicativo.prueba.model.Persona;
import aplicativo.prueba.model.Usuario;
import aplicativo.prueba.repository.PersonaRepository;
@Service
public class PersonaServiceImp implements PersonaService{
	
	@Autowired
	   private 	PersonaRepository personaRepository;
	@Override
	public List<Persona> findAll() {
		return (List<Persona>) personaRepository.findAll();
	}

	@Override
	public Persona findById(Integer id) throws Exception {
		try {
			return personaRepository.findById(id).orElse(null);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	
	}

	@Override
	public Persona save(Persona persona) throws Exception {
		try {

			
			
			 Persona personaGuardada =   personaRepository.save(persona);
 
			 return personaGuardada; 
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteById(Integer id) {
		personaRepository.deleteById(id);
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public String generarCorreoElectronico(Persona usuario) {
	       
	        String[] nombres = usuario.getNombres().toLowerCase().split(" ");
	        String[] apellidos = usuario.getApellidos().toLowerCase().split(" ");

	        
	        String correo = nombres[0].charAt(0) + apellidos[0] + "@" + "mail.com";
	     
	        return correo;
	    }
	 
	 
	 
	 public boolean validarIdentificacion(String identificacion) {
	     
	        if (identificacion.length() != 10 || !identificacion.matches("\\d{10}")) {
	            return false;
	        }

	        for (int i = 0; i <= 6; i++) {
	            if (identificacion.charAt(i) == identificacion.charAt(i + 1) &&
	                identificacion.charAt(i) == identificacion.charAt(i + 2) &&
	                identificacion.charAt(i) == identificacion.charAt(i + 3)) {
	                return false; 
	            }
	        }

	        return true; 
	    }
	

}
