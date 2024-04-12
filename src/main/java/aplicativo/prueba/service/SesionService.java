package aplicativo.prueba.service;



import aplicativo.prueba.model.Sesion;
import aplicativo.prueba.model.Usuario;




public interface SesionService {

	 public Usuario login(String usernameOrEmail, String password) throws Exception;
	 public boolean usuarioTieneSesionActiva(String usernameOrEmail, String password) ;
	 public void registrarCierreSesion(Integer idusuario) ;
	 
}
