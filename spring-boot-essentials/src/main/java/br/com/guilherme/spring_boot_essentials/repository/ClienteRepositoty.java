package br.com.guilherme.spring_boot_essentials.repository;

import br.com.guilherme.spring_boot_essentials.dto.ClienteResponseDto;
import br.com.guilherme.spring_boot_essentials.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoty extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findByEmail(String email);

    List<Cliente> findByNomeContaining(String nome);
}
