package aplicativo.prueba.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aplicativo.prueba.model.Usuario;



public interface UsuarioRepository  extends  JpaRepository< Usuario, Integer>{
	
	@Query("from Usuario  u  where u.userName=?1 or u.mail=?2")
	
	
	 Usuario findByUsernameOrEmail(String userName, String mail);

}
