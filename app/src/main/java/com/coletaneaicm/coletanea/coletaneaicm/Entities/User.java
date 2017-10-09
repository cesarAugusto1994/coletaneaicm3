package com.coletaneaicm.coletanea.coletaneaicm.Entities;

import java.io.Serializable;

/**
 * Created by cesar on 03/10/17.
 */

public class User implements Serializable {

    private Integer id;
    private String nome;
    private String avatar;
    private String email;
    private String roles;
    private String cidade;
    private String uf;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAvatar() {

        if (null != avatar) {
            return "https://coletaneaicm.com/web/assets/blog/img/avatar/" + avatar;
        }

        return "https://coletaneaicm.com/web/assets/blog/img/defaults/avatar.png";
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getRoles() {
        return roles;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
