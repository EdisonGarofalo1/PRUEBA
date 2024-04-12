package aplicativo.prueba.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import aplicativo.prueba.model.Sesion;
import aplicativo.prueba.model.Usuario;
import aplicativo.prueba.repository.SesionRepository;
import aplicativo.prueba.repository.UsuarioRepository;
@Service
public class SesionServiceImpl  implements SesionService{
	@Autowired
	   private SesionRepository sesionRepository;
	@Autowired
	   private UsuarioRepository   usuarioRepository;
	@Override
	public Usuario login(String usernameOrEmail, String password) throws Exception {
		
		try {
			 Usuario usuario = usuarioRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
			
			 if (usuario != null && usuario.getPassword().equals(password)) {
		        	
	        	
	        	 Sesion sesion = new Sesion();
	        	
	             sesion.setUsuario(usuario);
	           
	             sesion.setFechaIngreso(new Date(System.currentTimeMillis()));
	             
	             sesionRepository.save(sesion);
	            
	             return usuario; 
	        	 
	        }
			  
			   return null; 
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public boolean usuarioTieneSesionActiva(String usernameOrEmail, String password) {
		
		 Usuario usuario = usuarioRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
			

			 
			 Optional<Sesion> sesionActiva = sesionRepository.findByUsuarioIdAndActivaTrue(usuario.getIdUsuario());
		        
		 return sesionActiva.isPresent();
    }
	
}
