package com.order.project_orderup.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public
class Ordem{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(length = 100)
    private String responsavel;

    @Column(length = 100)
    private String data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(length = 100)
    private String equipamento;

    @Column(length = 100)
    private String marca;

    @Column(length = 100)
    private String numserie;

    @Column(length = 100)
    private String diagnostico;

    @Column(length = 100)
    private String pecas;

    @Column(length = 100)
    private String laudo;

    @Column(length = 100)
    private String documento;

    @Column(length = 100)
    private String assinatura;




}