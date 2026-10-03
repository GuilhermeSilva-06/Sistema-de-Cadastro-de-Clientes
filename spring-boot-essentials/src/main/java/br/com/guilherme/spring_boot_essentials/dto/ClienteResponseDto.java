package br.com.guilherme.spring_boot_essentials.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponseDto {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
