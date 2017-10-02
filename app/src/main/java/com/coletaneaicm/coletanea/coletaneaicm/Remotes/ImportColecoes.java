package com.coletaneaicm.coletanea.coletaneaicm.Remotes;

import android.content.Context;
import android.util.Log;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Colecoes;
import com.coletaneaicm.coletanea.coletaneaicm.Repositories.Repository;
import com.coletaneaicm.coletanea.coletaneaicm.retrofit.RetrofitInicializador;
import com.google.android.gms.appdatasearch.GetRecentContextCall;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImportColecoes {
    
    private Call<List<Colecoes>> call;
    private ArrayList<Colecoes> colecoes;

    private Repository repository;
    
    public ImportColecoes(Context context) {
        this.repository = new Repository(context);
        this.call = new RetrofitInicializador().getColecoes().getColecoes();
    }

    public ArrayList<Colecoes> getData() {
        return this.colecoes;
    }

    public void run() {
         
        this.call.enqueue(new Callback<List<Colecoes>>() {

            @Override
            public void onResponse(Call<List<Colecoes>> call, Response<List<Colecoes>> response) {

                ArrayList<Colecoes> colecoes = (ArrayList<Colecoes>) response.body();
                repository.criarColecao(colecoes);

                colecoes = colecoes;

                Log.i("onResponse", " Sucesso ao Salvar Colecoes");
            }

            @Override
            public void onFailure(Call<List<Colecoes>> call, Throwable t) {
                Log.e("onFailure", " Erro ao Salvar Colecoes: " + t.getMessage());
            }

        });

    }
}