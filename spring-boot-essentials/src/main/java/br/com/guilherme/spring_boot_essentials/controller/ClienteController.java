package br.com.guilherme.spring_boot_essentials.controller;

import br.com.guilherme.spring_boot_essentials.entity.Cliente;
import br.com.guilherme.spring_boot_essentials.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service){
        this.service = service;
    }

    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente){
        return service.criar(cliente);
    }

    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }
}
