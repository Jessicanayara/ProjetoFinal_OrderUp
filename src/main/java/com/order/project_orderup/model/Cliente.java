package com.order.project_orderup.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "cliente")
    private List<Ordem> ordensServico;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(length = 100)
    private String nome ;

    @Column(length = 100)
    private String cpf ;

    @Column(length = 100)
    private String cnpj;

    @Column(length = 100)
    private String  email;

    @Column(length = 100)
    private String endereco;

    @Column(length = 100)
    private String cidade;

    @Column(length = 100)
    private String estado;



}
