package com.coletaneaicm.coletanea.coletaneaicm.Entities;

import java.io.Serializable;

/**
 * Created by cesar on 28/09/17.
 */
public class Categorias implements Serializable {

    private Integer id;

    private String nome;

    private Integer colecao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String toString() {
        return this.getNome();
    }

    public void setColecao(Integer colecao) {
        this.colecao = colecao;
    }

    public Integer getColecao() {
        return colecao;
    }
}
