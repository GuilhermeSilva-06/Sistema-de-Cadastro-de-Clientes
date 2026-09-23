package br.com.guilherme.spring_boot_essentials.service;

import br.com.guilherme.spring_boot_essentials.dto.ClienteDto;
import br.com.guilherme.spring_boot_essentials.entity.Cliente;
import br.com.guilherme.spring_boot_essentials.repository.ClienteRepositoty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepositoty repository;

    public ClienteService(ClienteRepositoty repositoty) {
        this.repository = repositoty;
    }

    public Cliente criar(ClienteDto clienteDto) {

        Cliente cliente = new Cliente();

        cliente.setNome(clienteDto.getNome());
        cliente.setCpf(clienteDto.getCpf());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());

        return repository.save(cliente);
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente atualizar(ClienteDto clienteDto, Long id) {
        Cliente cliente = buscarPorId(id);

        cliente.setNome(clienteDto.getNome());
        cliente.setCpf(clienteDto.getCpf());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());

        return repository.save(cliente);
    }


    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"
                ));
    }

    public void deletar(Long id) {
        Cliente cliente = buscarPorId(id);

        repository.delete(cliente);
    }
}
