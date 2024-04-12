package aplicativo.prueba.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;


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

import aplicativo.prueba.dto.LoginDTO;
import aplicativo.prueba.dto.UsuarioListarDTO;
import aplicativo.prueba.dto.UsuariomeEditarAgregarDTO;
import aplicativo.prueba.model.Persona;
import aplicativo.prueba.model.Usuario;
import aplicativo.prueba.repository.UsuarioRepository;
import aplicativo.prueba.service.PersonaService;
import aplicativo.prueba.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;



@RestController
@RequestMapping("/api/usuario")

@Api(tags = "Gestión de Usuario")
public class UsuarioController {
	
	@Autowired
	private  UsuarioService    usuarioService;
	@Autowired
	private  PersonaService    personaService;
	
	@Autowired
	   private UsuarioRepository   usuarioRepository;
	
	 @Autowired
	    private ModelMapper modelMapper;
	
	 

	  
	 
	@GetMapping("/listar")
	@ApiOperation(value = "Listar Usuario", notes = "Lista todas las Usuario existente.")
	public List<UsuarioListarDTO> listar() {
		
		
		   List<Usuario> usuarios = usuarioService.findAll();

	       
	        return usuarios.stream()
	                .map(usuario -> modelMapper.map(usuario, UsuarioListarDTO.class))
	                .collect(Collectors.toList());
	    
		
	}
	
	

	@GetMapping("/ver/{id}")
	@ApiOperation(value = "ver Usuario por id", notes = " ver Usuario mediante el id  ")
	public UsuarioListarDTO detalle(@PathVariable Integer id) throws Exception {
		   Usuario usuario = usuarioService.findById(id);
		   if (usuario == null) {
	            throw new Exception("Usuario no encontrado con ID: " + id);
	        }

	      
	        return modelMapper.map(usuario, UsuarioListarDTO.class);
	}
	
	@PostMapping("/crear")
	 @ApiOperation(value = "Crear un nueva Usuario", notes = "Crea un nueva Usuario en el sistema.")
	@ResponseStatus(HttpStatus.CREATED)
	public  ResponseEntity<String> crear(@RequestBody UsuariomeEditarAgregarDTO usuarioDTO) throws Exception {
		 
	
		  
		   
		if (!usuarioService.validarNombreUsuario(usuarioDTO.getUserName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de usuario no cumple con los requisitos.");
        }


		
		 if (!usuarioService.validarContraseña(usuarioDTO.getPassword())) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no cumple con los requisitos.");
         }
        
		
		 Usuario usuarioExistente = usuarioRepository.findByUsernameOrEmail(usuarioDTO.getUserName(), usuarioDTO.getUserName());
	        if (usuarioExistente != null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de usuario ya está en uso.");
	        }

		 
		 
			Persona persona =personaService.findById(usuarioDTO.getIdPersona());
			String correo = personaService.generarCorreoElectronico(persona);
			
			
			
			Usuario usuario = new Usuario();
			usuario.setUserName(usuarioDTO.getUserName());
			//usuario.setMail(correo.toString());
			usuario.setPassword(usuarioDTO.getPassword());
			usuario.setStatus(usuarioDTO.getStatus());
			usuario.setPersona(persona);
			
				
				Usuario guardar= usuarioService.save(usuario);
				    if (guardar != null) {
			            return ResponseEntity.ok("Guardar exitoso"); 
			        } else {
			            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("guardar incorrectas");
			        }
	
		
	}
	@PutMapping("/editar/{id}")
	 @ApiOperation(value = "Actualiza la Usuario", notes = "Actualiza Usuario por el id en el sistema.")
	@ResponseStatus(HttpStatus.CREATED)
	
	public ResponseEntity<String> editar(@RequestBody UsuariomeEditarAgregarDTO usuarioDTO, @PathVariable Integer id) throws Exception {

		
			
			if (!usuarioService.validarNombreUsuario(usuarioDTO.getUserName())) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de usuario no cumple con los requisitos.");
	        }


			
			 if (!usuarioService.validarContraseña(usuarioDTO.getPassword())) {
	             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no cumple con los requisitos.");
	         }
			Usuario usuario = usuarioService.findById(id);
	
				
				
				Persona persona =personaService.findById(usuarioDTO.getIdPersona());
				  
				usuario.setUserName(usuarioDTO.getUserName());
				//usuario.setMail(usuarioDTO.getMail());
				usuario.setPassword(usuarioDTO.getPassword());
				usuario.setStatus(usuarioDTO.getStatus());
				usuario.setPersona(persona);
				
				
				Usuario guardar= usuarioService.save(usuario);
				
			
			    if (guardar != null) {
		            return ResponseEntity.ok("Guardar exitoso"); 
		        } else {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("guardar incorrectas");
		        }

	
	}
	
	

	   @DeleteMapping("/{id}")
		@ApiOperation(value = "Eliminar", notes = "Eliminar un Usuario por id.")
	   public ResponseEntity<?> delete (@PathVariable(value ="id") Integer id) throws Exception{
		   
		 
		   Usuario usuario = usuarioService.findById(id);
		   
		   if(usuario == null) {
			   
			   return ResponseEntity.notFound().build();
			   
		   }
		   
		   usuarioService.deleteById(id);
		   
		   return  ResponseEntity.ok().build();
	   }
	   
	   @PostMapping("/{usuarioId}/roles/{rolId}")
	    public ResponseEntity<String> asignarRol(@PathVariable Integer usuarioId, @PathVariable Integer rolId) {
	        Usuario usuario = usuarioService.asignarRolUsuario(usuarioId, rolId);
	        return ResponseEntity.ok("Rol asignado correctamente al usuario");
	    }
	   
	 
	   
}
