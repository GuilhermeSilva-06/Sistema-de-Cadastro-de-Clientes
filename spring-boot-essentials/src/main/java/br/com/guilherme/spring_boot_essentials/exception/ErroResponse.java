package br.com.guilherme.spring_boot_essentials.exception;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErroResponse {

    private String msg;
    private Integer status;
    private Map<String, String> erros;
}
