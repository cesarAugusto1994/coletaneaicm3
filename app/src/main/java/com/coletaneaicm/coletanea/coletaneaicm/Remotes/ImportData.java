package com.coletaneaicm.coletanea.coletaneaicm.Remotes;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Categorias;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Colecoes;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Musicas;
import com.coletaneaicm.coletanea.coletaneaicm.Repositories.Repository;
import com.coletaneaicm.coletanea.coletaneaicm.retrofit.RetrofitInicializador;
import com.google.android.gms.appdatasearch.GetRecentContextCall;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImportData {
    
    private Call<List<Colecoes>> callColecoes;
    private Call<List<Categorias>> callCategorias;
    private Call<List<Musicas>> callMusicas;
    private ArrayList<Colecoes> colecoes;
    final private Context contextClass;

    final private Repository repository;
    
    public ImportData(Context context) {
        contextClass = context;
        repository = new Repository(context);
        callColecoes = new RetrofitInicializador().getColecoes().getColecoes();
        callCategorias = new RetrofitInicializador().getCategorias().getAll();
        callMusicas = new RetrofitInicializador().getMusicas().getAll();
    }

    public ArrayList<Colecoes> getData() {
        return this.colecoes;
    }

    private ProgressDialog load() {

        final ProgressDialog progressDialog = new ProgressDialog(contextClass);
        progressDialog.setMessage("Baixando do servidor...");
        return progressDialog;

    }

    public void run() {

        final ProgressDialog progressDialog = new ProgressDialog(contextClass);
        progressDialog.setMessage("Baixando do servidor...");
        progressDialog.show();


        callColecoes.enqueue(new Callback<List<Colecoes>>() {

            @Override
            public void onResponse(Call<List<Colecoes>> call, Response<List<Colecoes>> response) {

                ArrayList<Colecoes> colecoes = (ArrayList<Colecoes>) response.body();
                repository.criarColecao(colecoes);

                progressDialog.dismiss();

                Log.i("onResponse", " Sucesso ao Salvar Colecoes");
            }

            @Override
            public void onFailure(Call<List<Colecoes>> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("onFailure", " Erro ao Salvar Colecoes: " + t.getMessage());
            }

        });
/*
        load().show();

        callCategorias.enqueue(new Callback<List<Categorias>>() {
            
            @Override
            public void onResponse(Call<List<Categorias>> call, Response<List<Categorias>> response) {

                ArrayList<Categorias> categorias = (ArrayList<Categorias>) response.body();
                repository.criarCategoria(categorias);

                load().dismiss();

                Log.i("onResponse", " Sucesso ao Salvar Categorias");
            }

            @Override
            public void onFailure(Call<List<Categorias>> call, Throwable t) {
                load().dismiss();
                Log.e("onFailure", " Erro ao Salvar Categorias: " + t.getMessage());
            }

        });

        load().show();

        callMusicas.enqueue(new Callback<List<Musicas>>() {
            
            @Override
            public void onResponse(Call<List<Musicas>> call, Response<List<Musicas>> response) {

                ArrayList<Musicas> musicas = (ArrayList<Musicas>) response.body();
                repository.criarMusica(musicas);

                load().dismiss();

                Log.i("onResponse", " Sucesso ao Salvar Musicas");
            }

            @Override
            public void onFailure(Call<List<Musicas>> call, Throwable t) {
                load().dismiss();
                Log.e("onFailure", " Erro ao Salvar Musicas: " + t.getMessage());
            }

        });
*/
    }
}