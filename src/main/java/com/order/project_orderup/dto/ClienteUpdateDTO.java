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


    private String nome;

    private String cpf;

    private String cnpj;

    private String email;

    private String endereco;
    private String cidade;
    private String estado;
}
