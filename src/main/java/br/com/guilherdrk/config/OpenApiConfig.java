package br.com.guilherdrk.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean // um objeto Instanciado que é montado e gerenciado pelo IOC container, faz parte da injeção de dados do Spring
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("REST API's RESTful from zero with Java, Spring Boot, Kubernetes and Docker")
                        .version("v1")
                        .description("REST API's RESTful from zero with Java, Spring Boot, Kubernetes and Docker")
                        .termsOfService("https://github.com/guilherdrk")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/guilherdrk")
                        )
                );
    }
}
