package com.coletaneaicm.coletanea.coletaneaicm.Entities;

import java.io.Serializable;

/**
 * Created by cesar on 28/09/17.
 */


public class Musicas implements Serializable {

    private Integer id;

    private String nome;

    private String letra;

    public String numero;

    public String tom;

    public Integer Categoria;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String toString() {
        return this.getNome();
    }

    public void setTom(String tom) {
        this.tom = tom;
    }

    public String getTom() {
        return tom;
    }
}
