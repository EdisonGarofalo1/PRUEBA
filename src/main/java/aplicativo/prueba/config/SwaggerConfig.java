package aplicativo.prueba.config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfig {
	  @Bean   
	    public Docket   api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	        	//	.ignoredParameterTypes(UserPrincipal.class) // Ignorar el tipo UserPrincipal en los parámetros de los controladores
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("aplicativo.prueba.controllers"))
	                .paths(PathSelectors.any()) // Puedes usar PathSelectors para especificar rutas específicas si es necesario
	               // .contact(new Contact("Nombre del Contacto", "URL del Contacto", "correo@ejemplo.com"))
	                .build()
	                .apiInfo(apiInfo());
	        
	        //.useDefaultResponseMessages(false) // Deshabilitar mensajes de respuesta predeterminados
	       // .enableUrlTemplating(true); // Habilitar la plantilla de URL
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("API del Carrito de Compra")
	                .description("Documentación de la API del Carrito de Compra")
	                .version("1.0")
	                .build();
	    }
}
