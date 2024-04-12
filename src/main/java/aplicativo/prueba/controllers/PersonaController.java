package aplicativo.prueba.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import aplicativo.prueba.dto.PersonaDTO;
import aplicativo.prueba.dto.PersonaListarDTO;
import aplicativo.prueba.dto.UsuarioListarDTO;
import aplicativo.prueba.model.Persona;
import aplicativo.prueba.model.Usuario;
import aplicativo.prueba.service.PersonaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/persona")

@Api(tags = "Gestión de Personas")
public class PersonaController {
	
	@Autowired
	private  PersonaService    personaService;
	
	 @Autowired
	    private ModelMapper modelMapper;
	
	@GetMapping("/listar")
	@ApiOperation(value = "Listar Persona", notes = "Lista todas las Persona existente.")
	public List<PersonaListarDTO> listar() {
	
		

		   List<Persona> persona = personaService.findAll();
	       
	        return persona.stream()
	                .map(usuario -> modelMapper.map(usuario, PersonaListarDTO.class))
	                .collect(Collectors.toList());
	}
	
	

	@GetMapping("/ver/{id}")
	@ApiOperation(value = "ver Persona por id", notes = " ver Persona mediante el id  ")
	public PersonaListarDTO detalle(@PathVariable Integer id) throws Exception {
		
		
		Persona persona = personaService.findById(id);
		   if (persona == null) {
	            throw new Exception("persona no encontrado con ID: " + id);
	        }

	      
	        return modelMapper.map(persona, PersonaListarDTO.class);
	}
	
	@PostMapping("/crear")
	 @ApiOperation(value = "Crear un nueva Persona", notes = "Crea un nueva Persona en el sistema.")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> crear(@RequestBody PersonaDTO personaDTO) throws Exception {
		 if (!personaService.validarIdentificacion(personaDTO.getIdentificacion())) {
             return ResponseEntity.badRequest().body("La identificación no cumple con los requisitos.");
         }
		 Persona persona = new Persona();
		 
		  persona.setNombres(personaDTO.getNombres());
		    persona.setApellidos(personaDTO.getApellidos());
		    persona.setIdentificacion(personaDTO.getIdentificacion());
		    persona.setFechaNacimiento(personaDTO.getFechaNacimiento());
		    
		    
		    Persona guardar= personaService.save(persona);
		    if (guardar != null) {
	            return ResponseEntity.ok("Guardar exitoso"); 
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("guardar incorrectas");
	        }
		
		
	}
	@PutMapping("/editar/{id}")
	 @ApiOperation(value = "Actualiza la Persona", notes = "Actualiza Persona por el id en el sistema.")
	@ResponseStatus(HttpStatus.CREATED)
	
	public ResponseEntity<String> editar(@RequestBody PersonaDTO personaDTO, @PathVariable Integer id) throws Exception {

		try {
			if (!personaService.validarIdentificacion(personaDTO.getIdentificacion())) {
	             return ResponseEntity.badRequest().body("La identificación no cumple con los requisitos.");
	         }
			Persona persona = personaService.findById(id);
			  persona.setNombres(personaDTO.getNombres());
			    persona.setApellidos(personaDTO.getApellidos());
			    persona.setIdentificacion(personaDTO.getIdentificacion());
			    persona.setFechaNacimiento(personaDTO.getFechaNacimiento());
			    Persona guardar= personaService.save(persona);
			    
			    if (guardar != null) {
		            return ResponseEntity.ok("Guardar exitoso"); 
		        } else {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("guardar incorrectas");
		        }
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	

	   @DeleteMapping("/{id}")
		@ApiOperation(value = "Eliminar", notes = "Eliminar un Persona por id.")
	   public ResponseEntity<?> delete (@PathVariable(value ="id") Integer id) throws Exception{
		   
		 
		   Persona DB = personaService.findById(id);
		   
		   if(DB == null) {
			   
			   return ResponseEntity.notFound().build();
			   
		   }
		   
		   personaService.deleteById(id);
		   
		   return  ResponseEntity.ok().build();
	   }
	   
	

}
