package aplicativo.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aplicativo.prueba.dto.LoginDTO;
import aplicativo.prueba.dto.PasswordDTO;
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
	    public PasswordDTO login(@RequestBody LoginDTO login) throws Exception {
		  
		  boolean tieneSesionActiva = sesionService.usuarioTieneSesionActiva(login.getUsernameOrEmail(), login.getPassword());
          if (tieneSesionActiva) {
            
              
              PasswordDTO loginError = new PasswordDTO();
	            loginError.setMessage("a tiene una sesión activa.");
	            return loginError;
          }
		  
		  
		  Usuario usuarioAutenticado =sesionService.login(login.getUsernameOrEmail(), login.getPassword());
	       
		  
		  if (usuarioAutenticado != null) {
			  PasswordDTO login1 = new PasswordDTO();
	            login1.setMail(usuarioAutenticado.getMail());
	            login1.setUserName(usuarioAutenticado.getUserName());
	            login1.setPassword(usuarioAutenticado.getPassword());
	            login1.setMessage("Login exitoso");
	            
	            return login1; 
	        } else {
	         
	        	PasswordDTO loginError = new PasswordDTO();
	            loginError.setMessage("Credenciales incorrectas");
	            return loginError;
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
	  
	  
	  @GetMapping("/password/{usernameOrEmail}")
	    public PasswordDTO logout(@PathVariable String usernameOrEmail) {
	     
	        Usuario usuario = usuarioRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail);
	        
	        if (usuario != null) {
	          
	        	PasswordDTO login = new PasswordDTO();
	            login.setMail(usuario.getMail());
	            login.setUserName(usuario.getUserName());
	            login.setPassword(usuario.getPassword());
	            login.setMessage("Recuperacion exitoso");
	            
	            return login; 
	        } else {
	         
	        	PasswordDTO loginError = new PasswordDTO();
	            loginError.setMessage("Usuario no encontrado");
	            return loginError;
	        }
	        
	       
	    }


}
