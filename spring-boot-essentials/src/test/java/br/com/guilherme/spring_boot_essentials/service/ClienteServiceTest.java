package br.com.guilherme.spring_boot_essentials.service;

import br.com.guilherme.spring_boot_essentials.dto.ClienteDto;
import br.com.guilherme.spring_boot_essentials.dto.ClienteResponseDto;
import br.com.guilherme.spring_boot_essentials.entity.Cliente;
import br.com.guilherme.spring_boot_essentials.exception.ClienteAlreadyExistsException;
import br.com.guilherme.spring_boot_essentials.exception.ClienteNotFoundException;
import br.com.guilherme.spring_boot_essentials.repository.ClienteRepositoty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepositoty repository;

    @InjectMocks
    private ClienteService service;

    private ClienteDto clienteDto;
    private Cliente cliente;

    @BeforeEach
    void configurar() {
        clienteDto = new ClienteDto();
        clienteDto.setNome("João Silva");
        clienteDto.setCpf("123.456.789-00");
        clienteDto.setEmail("joao@example.com");
        clienteDto.setTelefone("(11) 98765-4321");

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João Silva");
        cliente.setCpf("123.456.789-00");
        cliente.setEmail("joao@example.com");
        cliente.setTelefone("(11) 98765-4321");
    }

    @Test
    void deveCriarClienteQuandoDadosForemValidos() {
        when(repository.findByCpf(clienteDto.getCpf()))
                .thenReturn(Optional.empty());
        when(repository.findByEmail(clienteDto.getEmail()))
                .thenReturn(Optional.empty());
        when(repository.save(any(Cliente.class)))
                .thenReturn(cliente);

        ClienteResponseDto resposta = service.criar(clienteDto);

        assertNotNull(resposta);
        assertEquals(1L, resposta.getId());
        assertEquals("João Silva", resposta.getNome());
        assertEquals("joao@example.com", resposta.getEmail());

        verify(repository).save(any(Cliente.class));
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaEstiverCadastrado() {
        when(repository.findByCpf(clienteDto.getCpf()))
                .thenReturn(Optional.of(cliente));

        assertThrows(
                ClienteAlreadyExistsException.class,
                () -> service.criar(clienteDto)
        );

        verify(repository, never()).save(any(Cliente.class));
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaEstiverCadastrado() {
        when(repository.findByCpf(clienteDto.getCpf()))
                .thenReturn(Optional.empty());
        when(repository.findByEmail(clienteDto.getEmail()))
                .thenReturn(Optional.of(cliente));

        assertThrows(
                ClienteAlreadyExistsException.class,
                () -> service.criar(clienteDto)
        );

        verify(repository, never()).save(any(Cliente.class));
    }

    @Test
    void deveListarClientes() {
        when(repository.findAll()).thenReturn(List.of(cliente));

        List<ClienteResponseDto> resposta = service.listar();

        assertEquals(1, resposta.size());
        assertEquals("João Silva", resposta.get(0).getNome());
    }

    @Test
    void deveBuscarClientesPorNome() {
        when(repository.findByNomeContaining("João"))
                .thenReturn(List.of(cliente));

        List<ClienteResponseDto> resposta =
                service.buscarPorNome("João");

        assertEquals(1, resposta.size());
        assertEquals("João Silva", resposta.get(0).getNome());
    }

    @Test
    void deveBuscarClientesPorCpf() {
        when(repository.findByCpfContaining("123"))
                .thenReturn(List.of(cliente));

        List<ClienteResponseDto> resposta =
                service.buscarPorCpf("123");

        assertEquals(1, resposta.size());
        assertEquals("123.456.789-00", resposta.get(0).getCpf());
    }

    @Test
    void deveBuscarClientesPorEmail() {
        when(repository.findByEmailContaining("joao"))
                .thenReturn(List.of(cliente));

        List<ClienteResponseDto> resposta =
                service.buscarPorEmail("joao");

        assertEquals(1, resposta.size());
        assertEquals("joao@example.com", resposta.get(0).getEmail());
    }

    @Test
    void deveBuscarClientePorId() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(cliente));

        ClienteResponseDto resposta = service.buscarPorId(1L);

        assertEquals(1L, resposta.getId());
        assertEquals("João Silva", resposta.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExistirNaBuscaPorId() {
        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ClienteNotFoundException.class,
                () -> service.buscarPorId(99L)
        );
    }

    @Test
    void deveAtualizarClienteSemAlterarCpf() {
        clienteDto.setNome("João Silva Atualizado");

        when(repository.findById(1L))
                .thenReturn(Optional.of(cliente));
        when(repository.findByCpf(clienteDto.getCpf()))
                .thenReturn(Optional.of(cliente));
        when(repository.findByEmail(clienteDto.getEmail()))
                .thenReturn(Optional.of(cliente));
        when(repository.save(any(Cliente.class)))
                .thenAnswer(invocacao -> invocacao.getArgument(0));

        ClienteResponseDto resposta =
                service.atualizar(clienteDto, 1L);

        assertEquals("João Silva Atualizado", resposta.getNome());
        assertEquals("123.456.789-00", resposta.getCpf());

        verify(repository).save(cliente);
    }

    @Test
    void deveImpedirAlteracaoDoCpf() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(cliente));

        clienteDto.setCpf("987.654.321-00");

        when(repository.findByCpf(clienteDto.getCpf()))
                .thenReturn(Optional.empty());
        when(repository.findByEmail(clienteDto.getEmail()))
                .thenReturn(Optional.of(cliente));

        assertThrows(
                ClienteAlreadyExistsException.class,
                () -> service.atualizar(clienteDto, 1L)
        );

        verify(repository, never()).save(any(Cliente.class));
    }

    @Test
    void deveImpedirAtualizacaoComCpfDeOutroCliente() {
        Cliente outroCliente = new Cliente();
        outroCliente.setId(2L);
        outroCliente.setCpf(clienteDto.getCpf());

        when(repository.findById(1L))
                .thenReturn(Optional.of(cliente));
        when(repository.findByCpf(clienteDto.getCpf()))
                .thenReturn(Optional.of(outroCliente));

        assertThrows(
                ClienteAlreadyExistsException.class,
                () -> service.atualizar(clienteDto, 1L)
        );

        verify(repository, never()).save(any(Cliente.class));
    }

    @Test
    void deveDeletarClienteExistente() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(cliente));

        service.deletar(1L);

        verify(repository).delete(cliente);
    }

    @Test
    void deveLancarExcecaoAoDeletarClienteInexistente() {
        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ClienteNotFoundException.class,
                () -> service.deletar(99L)
        );

        verify(repository, never()).delete(any(Cliente.class));
    }
}