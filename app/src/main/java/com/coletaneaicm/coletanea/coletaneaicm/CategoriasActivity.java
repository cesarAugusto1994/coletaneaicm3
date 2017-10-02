package com.coletaneaicm.coletanea.coletaneaicm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Categorias;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Colecoes;
import com.coletaneaicm.coletanea.coletaneaicm.Repositories.Repository;
import com.coletaneaicm.coletanea.coletaneaicm.adapters.CategoriasAdapter;
import com.coletaneaicm.coletanea.coletaneaicm.adapters.ColecoesAdapter;
import com.coletaneaicm.coletanea.coletaneaicm.retrofit.RetrofitInicializador;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Colecoes colecao = (Colecoes) getIntent().getSerializableExtra("colecao");

        final Repository repository = new Repository(this);

        ArrayList<Categorias> categorias = (ArrayList<Categorias>) repository.getCategorias(colecao);
        final ListView listaCategorias = (ListView) findViewById(R.id.lista_categorias);

        Log.i("onResponse", " "+ categorias.size());

        if(!categorias.isEmpty()) {

            CategoriasAdapter categoriasAdapter = new CategoriasAdapter(this, categorias);

            listaCategorias.setAdapter(categoriasAdapter);

            listaCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent goCategorias = new Intent(CategoriasActivity.this, CategoriasActivity.class);
                    Categorias categoria = (Categorias) adapterView.getItemAtPosition(i);
                    goCategorias.putExtra("categoria", categoria);

                    startActivity(goCategorias);
                }
            });
        } else {

            ArrayList<Categorias> categorias1 = (ArrayList<Categorias>) repository.getCategorias(colecao);

            if(categorias1.isEmpty()) {

                Call<List<Categorias>> callCatetegorias = new RetrofitInicializador().getCategorias().getCategorias(colecao.getId());

                callCatetegorias.enqueue(new Callback<List<Categorias>>() {
                    @Override
                    public void onResponse(Call<List<Categorias>> call, Response<List<Categorias>> response) {

                        ArrayList<Categorias> categorias = (ArrayList<Categorias>) response.body();
                        repository.criarCategoria(categorias);

                        Log.i("onResponse", " Sucesso ao Salvar Categorias");

                        CategoriasAdapter categoriasAdapter = new CategoriasAdapter(CategoriasActivity.this, categorias);

                        listaCategorias.setAdapter(categoriasAdapter);

                        listaCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent goCategorias = new Intent(CategoriasActivity.this, CategoriasActivity.class);
                                Categorias categoria = (Categorias) adapterView.getItemAtPosition(i);
                                goCategorias.putExtra("categoria", categoria);

                                //startActivity(goCategorias);
                            }
                        });
/*
                            for (int i = 0; i < categorias.size(); i++) {

                                ArrayList<Musicas> musicas = (ArrayList<Musicas>) repository.getMusicas(categorias.get(i));

                                if (musicas.isEmpty()) {

                                    Call<List<Musicas>> callMusicas = new RetrofitInicializador().getMusicas().getMusicas(categorias.get(i).getId());

                                    callMusicas.enqueue(new Callback<List<Musicas>>() {
                                        @Override
                                        public void onResponse(Call<List<Musicas>> call, Response<List<Musicas>> response) {

                                            ArrayList<Musicas> musicas = (ArrayList<Musicas>) response.body();
                                            repository.criarMusica(musicas);

                                            Log.i("onResponse", " Sucesso ao Salvar Musicas");
                                        }

                                        @Override
                                        public void onFailure(Call<List<Musicas>> call, Throwable t) {
                                            Log.e("onFailure", " Erro ao Salvar Musicas: " + t.getMessage());
                                        }
                                    });

                                }

                            }
                            */

                    }

                    @Override
                    public void onFailure(Call<List<Categorias>> call, Throwable t) {
                        Log.e("onFailure", " Erro ao Salvar categorias: " + t.getMessage());
                    }
                });
            }


        }

    }

}
