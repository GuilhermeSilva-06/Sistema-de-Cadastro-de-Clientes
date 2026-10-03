package br.com.guilherme.spring_boot_essentials.exception;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(String msg){
        super(msg);
    }
}
