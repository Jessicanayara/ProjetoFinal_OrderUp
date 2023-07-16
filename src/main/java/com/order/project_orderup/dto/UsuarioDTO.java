package com.order.project_orderup.dto;

import com.order.project_orderup.model.Ordem;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
public class UsuarioDTO {

    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;


    private List<Ordem> ordensServico;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;



    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatório")
    @Size(min = 6, message = "A senha deve conter pelo menos 6 caracteres")
    private String senha;


}
