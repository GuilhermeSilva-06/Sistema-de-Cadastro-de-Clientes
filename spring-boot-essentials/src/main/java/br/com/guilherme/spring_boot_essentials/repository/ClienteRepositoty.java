package br.com.guilherme.spring_boot_essentials.repository;

import br.com.guilherme.spring_boot_essentials.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositoty extends JpaRepository<Cliente, Long> {

}
