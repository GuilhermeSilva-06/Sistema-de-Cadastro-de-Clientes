package br.com.guilherme.spring_boot_essentials.service;

import br.com.guilherme.spring_boot_essentials.entity.Cliente;
import br.com.guilherme.spring_boot_essentials.repository.ClienteRepositoty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepositoty repository;

    public ClienteService(ClienteRepositoty repositoty){
        this.repository = repositoty;
    }

    public Cliente criar(Cliente cliente){
        return repository.save(cliente);
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }
}
