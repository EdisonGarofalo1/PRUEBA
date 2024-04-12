package aplicativo.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aplicativo.prueba.dto.LoginDTO;
import aplicativo.prueba.model.Usuario;
import aplicativo.prueba.repository.UsuarioRepository;
import aplicativo.prueba.service.SesionService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/sesion")

@Api(tags = "Gestión de Sesion")
public class SesionController {
	
	
	@Autowired
	private  SesionService    sesionService;
	@Autowired
	private UsuarioRepository usuarioRepository;
	  @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody LoginDTO login) throws Exception {
		   
		  boolean tieneSesionActiva = sesionService.usuarioTieneSesionActiva(login.getUsernameOrEmail(), login.getPassword());
          if (tieneSesionActiva) {
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya tiene una sesión activa.");
          }
		  
		  Usuario usuarioAutenticado =sesionService.login(login.getUsernameOrEmail(), login.getPassword());
	       
		  
		  if (usuarioAutenticado != null) {
	            return ResponseEntity.ok("Login exitoso"); 
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
	        }
	       
	   }
	
	  
	  @PostMapping("/logout")
	    public ResponseEntity<Object> logout(@RequestParam Integer usuarioId) {
	     
	        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
	        if (usuario != null) {
	            sesionService.registrarCierreSesion(usuarioId);
	            return ResponseEntity.ok("Sesión cerrada correctamente");
	        } else {
	            return ResponseEntity.badRequest().body("Usuario no encontrado");
	        }
	    }

}
