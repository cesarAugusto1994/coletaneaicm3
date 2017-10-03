package com.coletaneaicm.coletanea.coletaneaicm.Entities;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by cesar on 02/10/17.
 */
public class Login implements Serializable {

    public static final String LOGIN_SUCESSO = "Sucesso";

    private String classe;

    private String msg;

    private Boolean acerto;

    public String getClasse() {
        return classe;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getAcerto() {
        return acerto;
    }
}
