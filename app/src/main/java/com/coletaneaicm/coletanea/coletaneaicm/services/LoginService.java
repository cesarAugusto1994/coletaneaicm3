package com.coletaneaicm.coletanea.coletaneaicm.services;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Login;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Musicas;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cesar on 28/09/17.
 */
public interface LoginService {

    @GET("login/{email}/{password}")
    Call<Login> getUser(@Path("email") String email, @Path("password") String password);

    @GET("user/{email}/data")
    Call<Boolean> isUserSigned(@Path("email") String email);

    @GET("user/{email}/data")
    Call<User> getUserProfile(@Path("email") String email);

}
