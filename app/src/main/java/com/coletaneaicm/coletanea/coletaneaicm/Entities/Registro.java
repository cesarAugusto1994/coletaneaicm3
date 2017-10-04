package com.coletaneaicm.coletanea.coletaneaicm.Entities;

import java.io.Serializable;

/**
 * Created by cesar on 04/10/17.
 */

public class Registro implements Serializable{

    private String classe;

    private String mensagem;

    private Boolean bln;

    public String getClasse() {
        return classe;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Boolean getBln() {
        return bln;
    }
}
