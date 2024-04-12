package aplicativo.prueba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import aplicativo.prueba.model.Usuario;


public interface UsuarioService {
	
	public List<Usuario> findAll();
	public Usuario findById(Integer id) throws Exception;
	public Usuario  save(Usuario usuario) throws Exception;
	public void deleteById(Integer id);
	public Usuario asignarRolUsuario(Integer usuarioId, Integer rolId) ;
	

	 
	 public boolean validarContraseña(String contraseña);
	   public boolean validarNombreUsuario(String nombreUsuario) ;

}
