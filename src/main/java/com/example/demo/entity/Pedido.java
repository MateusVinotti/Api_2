package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "O nome é obrigatorio!")
    private String nome;
    private double preUni;
    private int quant;
    private double preTt;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Endereco> Enderecos;

    public Pedido(String nome, double preUni, int quant, double preTt, List<Endereco> Enderecos) {
        this.nome = nome;
        this.preUni = preUni;
        this.quant = quant;
        this.preTt = preTt;
        this.Enderecos = Enderecos;
    }
    //Bom dia ///

    public Pedido() {
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Endereco> getEnderecos() {
        return Enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        Enderecos = enderecos;
    }

}