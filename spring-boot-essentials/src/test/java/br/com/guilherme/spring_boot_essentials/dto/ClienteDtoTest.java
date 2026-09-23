package br.com.guilherme.spring_boot_essentials.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDtoTest {

    private static Validator validator;

    @BeforeAll
    static void configurarValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveRetornarErroQuandoNomeEstiverVazio() {

        ClienteDto cliente = new ClienteDto(
                "",
                "123.456.789-09",
                "email@email.com",
                "(11) 99999-9999"
        );

        Set violations = validator.validate(cliente);

        assertFalse(violations.isEmpty());
    }

    @Test
    void deveAceitarNomeValido(){

        ClienteDto cliente = new ClienteDto(
                "Guilherme",
                "123.456.789-09",
                "email@email.com",
                "(11) 99999-9999"
        );

        Set violations = validator.validate(cliente);

        assertTrue(violations.isEmpty());
    }


    @Test
    void deveRetornarErroQuandoEmailEstiverVazio() {

        ClienteDto cliente = new ClienteDto(
                "Guilherme",
                "123.456.789-09",
                "",
                "(11) 99999-9999"
        );

        Set violations = validator.validate(cliente);

        assertFalse(violations.isEmpty());
    }

    @Test
    void deveRetornarErroQuandoEmailForInvalido() {

        ClienteDto cliente = new ClienteDto(
                "Guilherme",
                "123.456.789-09",
                "email-invalido",
                "(11) 99999-9999"
        );

        Set violations = validator.validate(cliente);

        assertFalse(violations.isEmpty());
    }

    @Test
    void deveAceitarEmailValido() {

        ClienteDto cliente = new ClienteDto(
                "Guilherme",
                "123.456.789-09",
                "guilherme@email.com",
                "(11) 99999-9999"
        );

        Set violations = validator.validate(cliente);

        assertTrue(violations.isEmpty());
    }



    @Test
    void deveRetornarErroQuandoCpfEstiverVazio() {

        ClienteDto cliente = new ClienteDto(
                "Guilherme",
                "",
                "guilherme@email.com",
                "(11) 99999-9999"
        );

        Set violations = validator.validate(cliente);

        assertFalse(violations.isEmpty());
    }

    @Test
    void deveRetornarErroQuandoCpfEstiverEmFormatoInvalido() {

        ClienteDto cliente = new ClienteDto(
                "Guilherme",
                "12345678909",
                "guilherme@email.com",
                "(11) 99999-9999"
        );

        Set violations = validator.validate(cliente);

        assertFalse(violations.isEmpty());
    }

    @Test
    void deveAceitarCpfNoFormatoCorreto() {

        ClienteDto cliente = new ClienteDto(
                "Guilherme",
                "123.456.789-09",
                "guilherme@email.com",
                "(11) 99999-9999"
        );

        Set violations = validator.validate(cliente);

        assertTrue(violations.isEmpty());
    }



    @Test
    void deveRetornarErroQuandoTelefoneEstiverVazio() {

        ClienteDto cliente = new ClienteDto(
                "Guilherme",
                "123.456.789-09",
                "guilherme@email.com",
                ""
        );

        Set violations = validator.validate(cliente);

        assertFalse(violations.isEmpty());
    }

    @Test
    void deveRetornarErroQuandoTelefoneForInvalido() {

        ClienteDto cliente = new ClienteDto(
                "Guilherme",
                "123.456.789-09",
                "guilherme@email.com",
                "telefone-invalido"
        );

        Set violations = validator.validate(cliente);

        assertFalse(violations.isEmpty());
    }

    @Test
    void deveAceitarTelefoneValido() {

        ClienteDto cliente = new ClienteDto(
                "Guilherme",
                "123.456.789-09",
                "guilherme@email.com",
                "(11) 99999-9999"
        );

        Set violations = validator.validate(cliente);

        assertTrue(violations.isEmpty());
    }

}