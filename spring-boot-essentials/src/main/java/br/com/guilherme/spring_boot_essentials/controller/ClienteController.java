package br.com.guilherme.spring_boot_essentials.controller;

import br.com.guilherme.spring_boot_essentials.dto.ClienteDto;
import br.com.guilherme.spring_boot_essentials.dto.ClienteResponseDto;
import br.com.guilherme.spring_boot_essentials.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;


@Tag(
        name = "Clientes",
        description = "Operações de cadastro e gerenciamento de clientes"
)
@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(
            summary = "Cadastrar cliente",
            description = "Cadastra um cliente após validar os dados informados."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Cliente cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados informados inválidos"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "CPF ou e-mail já cadastrado"
            )
    })

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto criar(
            @Valid @RequestBody ClienteDto clienteDto) {

        return clienteService.criar(clienteDto);
    }


    @Operation(
            summary = "Listar e filtrar clientes",
            description = "Lista os clientes e permite filtrar por nome, CPF ou e-mail."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta realizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetros inválidos, se aplicável"
            )
    })

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponseDto> listar(
            @RequestParam(required = false) String nome, String cpf, String email) {

        if (nome != null && !nome.isBlank()) {
            return clienteService.buscarPorNome(nome);
        }

        if (cpf != null && !cpf.isBlank()) {
            return clienteService.buscarPorCpf(cpf);
        }

        if (email != null && !email.isBlank()) {
            return clienteService.buscarPorEmail(email);
        }

        return clienteService.listar();
    }

    @Operation(
            summary = "Buscar cliente por ID",
            description = "Retorna os dados de um cliente específico."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado"
            )
    })

    @GetMapping("/{id}")
    public ClienteResponseDto buscarPorId(
            @PathVariable Long id) {

        return clienteService.buscarPorId(id);
    }


    @Operation(
            summary = "Atualizar cliente",
            description = "Atualiza os dados de um cliente existente. O CPF não pode ser alterado."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados informados inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "E-mail já cadastrado ou CPF não pode ser alterado"
            )
    })

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDto atualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDto clienteDto) {

        return clienteService.atualizar(clienteDto, id);
    }


    @Operation(
            summary = "Excluir cliente",
            description = "Exclui um cliente cadastrado pelo seu ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Cliente excluído com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado"
            )
    })

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {

        clienteService.deletar(id);
    }
}
