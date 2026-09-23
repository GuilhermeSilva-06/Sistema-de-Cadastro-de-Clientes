package br.com.guilherme.spring_boot_essentials.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;


@Entity
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "O nome não pode ser vazio")
    private String nome;

    @Column(nullable = false, unique = true)
    @NotNull(message = "O CPF não pode ser vazio")
    private String cpf;

    @Column(nullable = false, unique = true)
    @NotNull(message = "O email não pode ser vazio")
    private String email;

    @Column(nullable = false)
    private String telefone;
}
