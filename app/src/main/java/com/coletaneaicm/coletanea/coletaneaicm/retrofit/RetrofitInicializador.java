package com.coletaneaicm.coletanea.coletaneaicm.retrofit;

import com.coletaneaicm.coletanea.coletaneaicm.services.CategoriasService;
import com.coletaneaicm.coletanea.coletaneaicm.services.ColecoesService;
import com.coletaneaicm.coletanea.coletaneaicm.services.FavoritosService;
import com.coletaneaicm.coletanea.coletaneaicm.services.LoginService;
import com.coletaneaicm.coletanea.coletaneaicm.services.MusicaService;
import com.coletaneaicm.coletanea.coletaneaicm.services.MusicasService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cesar on 26/09/17.
 */
public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador() {
        retrofit = new Retrofit.Builder().baseUrl("https://coletaneaicm.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ColecoesService getColecoes() {
        return retrofit.create(ColecoesService.class);
    }

    public CategoriasService getCategorias() {
        return retrofit.create(CategoriasService.class);
    }

    public MusicasService getMusicas() {
        return retrofit.create(MusicasService.class);
    }

    public MusicaService getMusica() {
        return retrofit.create(MusicaService.class);
    }

    public LoginService LoginService() {
        return retrofit.create(LoginService.class);
    }

    public FavoritosService FavoritosService() {
        return retrofit.create(FavoritosService.class);
    }

}
