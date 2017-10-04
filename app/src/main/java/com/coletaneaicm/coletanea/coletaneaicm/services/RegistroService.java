package com.coletaneaicm.coletanea.coletaneaicm.services;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Registro;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by cesar on 04/10/17.
 */

public interface RegistroService {

    @POST("/register/user/save")
    Call<Registro> saveUser(@Body String nome, @Body String email, @Body String password);

}
