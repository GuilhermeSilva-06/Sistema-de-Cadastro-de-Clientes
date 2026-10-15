package br.com.guilherme.spring_boot_essentials.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Sistema de Cadastro de Clientes API",
                version = "1.0.0",
                description = """
                        API REST para gerenciamento de clientes.
                        
                        Funcionalidades:
                        - Cadastro de clientes
                        - Consulta de clientes
                        - Busca por nome, CPF e e-mail
                        - Atualização de clientes
                        - Exclusão de clientes
                        
                        Desenvolvida com Java, Spring Boot,
                        Spring Data JPA e PostgreSQL.
                        """,
                contact = @Contact(
                        name = "Equipe de desenvolvimento"
                )
        )
)
public class OpenApiConfig {
}

