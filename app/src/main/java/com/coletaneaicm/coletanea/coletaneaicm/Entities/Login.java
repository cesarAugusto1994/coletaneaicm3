package com.coletaneaicm.coletanea.coletaneaicm.Entities;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by cesar on 02/10/17.
 */
public class Login implements Serializable {

    public static final String LOGIN_SUCESSO = "Sucesso";

    private String classe;

    private String mensagem;

    private User user;

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }
}
