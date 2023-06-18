package com.order.project_orderup.dto;

import com.order.project_orderup.model.Ordem;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
import java.util.UUID;

@Data
public class UsuarioDTO {


    private long id;

    private List<Ordem> ordensServico;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "Email é obrigatório")
    private String email;

    @Size(min = 6, message = "A senha deve conter pelo menos 6 caracteres")
    private String senha;

}
