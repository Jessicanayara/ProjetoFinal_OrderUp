package com.order.project_orderup.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class OrdemDTO {


    private UsuarioDTO usuario;


    @NotBlank(message = "campo obrigatorio")
    private String responsavel;

    @NotBlank(message = "campo obrigatorio")
    private String data;


    private ClienteUpdateDTO cliente;

    private String equipamento;

    private String marca;

    private String numserie;

    private String diagnostico;

    private String pecas;

    private String laudo;

    @NotBlank(message = "campo obrigatorio")
    @CPF(message = "CPF invalido")
    private String documento;

    @NotBlank(message = "campo obrigatorio")
    private String assinatura;


}
