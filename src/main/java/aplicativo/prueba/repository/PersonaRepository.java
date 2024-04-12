package aplicativo.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicativo.prueba.model.Persona;



public interface PersonaRepository extends  JpaRepository< Persona, Integer> {

}
