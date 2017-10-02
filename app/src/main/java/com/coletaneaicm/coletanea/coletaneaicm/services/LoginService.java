package com.coletaneaicm.coletanea.coletaneaicm.services;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Musicas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cesar on 28/09/17.
 */
public interface LoginService {

    @GET("login/{email}/{password}")
    Call<List<Login>> getUser(@Path("email") String email, @Path("password") String password);

}
