package br.com.guilherme.spring_boot_essentials.service;

import br.com.guilherme.spring_boot_essentials.dto.ClienteDto;
import br.com.guilherme.spring_boot_essentials.dto.ClienteResponseDto;
import br.com.guilherme.spring_boot_essentials.entity.Cliente;
import br.com.guilherme.spring_boot_essentials.exception.ClienteAlreadyExistsException;
import br.com.guilherme.spring_boot_essentials.exception.ClienteNotFoundException;
import br.com.guilherme.spring_boot_essentials.repository.ClienteRepositoty;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepositoty repository;

    public ClienteService(ClienteRepositoty repository) {
        this.repository = repository;
    }

    public ClienteResponseDto criar(ClienteDto clienteDto) {

        if (repository.findByCpf(clienteDto.getCpf()).isPresent()) {
            throw new ClienteAlreadyExistsException("CPF já cadastrado");
        }

        if (repository.findByEmail(clienteDto.getEmail()).isPresent()) {
            throw new ClienteAlreadyExistsException("E-mail já cadastrado");
        }

        Cliente cliente = new Cliente();

        cliente.setNome(clienteDto.getNome());
        cliente.setCpf(clienteDto.getCpf());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());

        Cliente clienteSalvo = repository.save(cliente);

        return toResponseDto(clienteSalvo);
    }

    public List<ClienteResponseDto> listar() {

        return repository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public ClienteResponseDto buscarPorId(Long id) {

        Cliente cliente = buscarEntidadePorId(id);

        return toResponseDto(cliente);
    }

    public ClienteResponseDto atualizar(ClienteDto clienteDto, Long id) {

        Cliente cliente = buscarEntidadePorId(id);

        repository.findByCpf(clienteDto.getCpf())
                .ifPresent(clienteEncontrado -> {
                    if (!clienteEncontrado.getId().equals(id)) {
                        throw new ClienteAlreadyExistsException("CPF já cadastrado");
                    }
                });

        repository.findByEmail(clienteDto.getEmail())
                .ifPresent(clienteEncontrado -> {
                    if (!clienteEncontrado.getId().equals(id)) {
                        throw new ClienteAlreadyExistsException("E-mail já cadastrado");
                    }
                });

        cliente.setNome(clienteDto.getNome());
        cliente.setCpf(clienteDto.getCpf());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());

        Cliente clienteAtualizado = repository.save(cliente);

        return toResponseDto(clienteAtualizado);
    }

    public void deletar(Long id) {

        Cliente cliente = buscarEntidadePorId(id);

        repository.delete(cliente);
    }

    private Cliente buscarEntidadePorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ClienteNotFoundException("Cliente não encontrado")
                );
    }

    private ClienteResponseDto toResponseDto(Cliente cliente) {

        return ClienteResponseDto.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .build();
    }
}
