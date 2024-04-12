package aplicativo.prueba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aplicativo.prueba.model.Sesion;




public interface SesionRepository extends  JpaRepository< Sesion, Integer>{
	
	
	
	
	@Query("from Sesion  s  where s.idSesion=?1 ")
	
	
	Optional<Sesion>  findByUsuarioIdAndActivaTrue(Integer idSesion);

}
