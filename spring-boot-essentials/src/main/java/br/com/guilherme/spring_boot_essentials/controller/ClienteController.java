package br.com.guilherme.spring_boot_essentials.controller;

import br.com.guilherme.spring_boot_essentials.dto.ClienteDto;
import br.com.guilherme.spring_boot_essentials.entity.Cliente;
import br.com.guilherme.spring_boot_essentials.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criar(@Valid @RequestBody ClienteDto clienteDto) {
        return clienteService.criar(clienteDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDto clienteDto) {

        return clienteService.atualizar(clienteDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }

}
