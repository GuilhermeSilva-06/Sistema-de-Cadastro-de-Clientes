package br.com.guilherme.spring_boot_essentials.exception;

public class ClienteAlreadyExistsException extends RuntimeException {

    public ClienteAlreadyExistsException(String msg) {
        super(msg);
    }
}
