package br.com.guilherme.spring_boot_essentials.dto;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteDto {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

}
