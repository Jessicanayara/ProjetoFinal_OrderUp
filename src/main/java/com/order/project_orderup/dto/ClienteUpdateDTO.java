package com.order.project_orderup.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
@Data
public class ClienteUpdateDTO {
    private long id;

    private UsuarioDTO usuario;

    private Integer numeroCliente;

    @NotBlank(message = "Nome é obrigatorio")
    private String nome;
    @CPF(message = "CPF invalido")
    private String cpf;
    @CNPJ(message = "CNPJ invalido")
    private String cnpj;
    @NotBlank(message = "Email é obrigatorio")
    private String email;

    private String endereco;
    private String cidade;
    private String estado;
}
