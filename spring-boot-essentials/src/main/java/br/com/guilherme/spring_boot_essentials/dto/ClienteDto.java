package br.com.guilherme.spring_boot_essentials.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteDto {

    @NotNull
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull
    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(
            regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
            message = "O CPF deve estar no formato 000.000.000-00"
    )
    private String cpf;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Digite um Email válido")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(
            regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
            message = "Digite um telefone válido (ex: (11) 99999-9999)"
    )
    private String telefone;

}
