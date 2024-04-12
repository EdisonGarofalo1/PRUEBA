package aplicativo.prueba.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import aplicativo.prueba.model.Rol;
import aplicativo.prueba.model.Sesion;
import aplicativo.prueba.model.Usuario;
import aplicativo.prueba.repository.RolRepository;
import aplicativo.prueba.repository.SesionRepository;
import aplicativo.prueba.repository.UsuarioRepository;

@Service
public class UsuarioServiceImp implements UsuarioService{

	
	@Autowired
	   private UsuarioRepository   usuarioRepository;
	

	@Autowired
	   private RolRepository rolRepository;
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Usuario findById(Integer id) throws Exception {
		try {
			return usuarioRepository.findById(id).orElse(null);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public  Usuario  save(Usuario usuario) throws Exception {
		try {

			
			
			Usuario Guardada =   usuarioRepository.save(usuario);

			return Guardada;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
		
	}
	
	
	
	
	
	
	public Usuario asignarRolUsuario(Integer usuarioId, Integer rolId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Rol rol = rolRepository.findById(rolId).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        
        usuario.getRoles().add(rol);
        return usuarioRepository.save(usuario);
    }

	
	 
	
	
	
	
	
	
	
	
	
	   public boolean validarNombreUsuario(String nombreUsuario) {
			  
	        if (!nombreUsuario.matches("^[a-zA-Z0-9]+$")) {
	            return false;
	        }

	      
	        if (!nombreUsuario.matches(".*\\d.*") || !nombreUsuario.matches(".*[A-Z].*")) {
	            return false;
	        }

	      
	        return nombreUsuario.length() >= 8 && nombreUsuario.length() <= 20;
	    }

	 
	  public boolean validarContraseña(String contraseña) {
	  
	        if (contraseña.length() < 8) {
	            return false;
	        }

	      
	        if (!contraseña.matches(".*[A-Z].*")) {
	            return false;
	        }

	        if (contraseña.contains(" ")) {
	            return false;
	        }

	
	        if (!contraseña.matches(".*[^a-zA-Z0-9].*")) {
	            return false;
	        }

	        return true;
	    }
	 


}
