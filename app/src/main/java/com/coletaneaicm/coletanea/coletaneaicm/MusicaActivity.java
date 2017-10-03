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
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Musica;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Musicas;
import com.coletaneaicm.coletanea.coletaneaicm.R;
import com.coletaneaicm.coletanea.coletaneaicm.Repositories.Repository;
import com.coletaneaicm.coletanea.coletaneaicm.adapters.CategoriasAdapter;
import com.coletaneaicm.coletanea.coletaneaicm.adapters.MusicasAdapter;
import com.coletaneaicm.coletanea.coletaneaicm.retrofit.RetrofitInicializador;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Categorias categoria = (Categorias) getIntent().getSerializableExtra("categoria");

        final Repository repository = new Repository(this);

        ArrayList<Musicas> musicas = (ArrayList<Musicas>) repository.getMusicas(categoria);
        final ListView listaMusicas = (ListView) findViewById(R.id.lista_musicas);

        if(!musicas.isEmpty()) {

            MusicasAdapter musicasAdapter = new MusicasAdapter(this, musicas);

            listaMusicas.setAdapter(musicasAdapter);

        } else {

            ArrayList<Musicas> musicas1 = (ArrayList<Musicas>) repository.getMusicas(categoria);

            if(musicas1.isEmpty()) {

                Call<List<Musicas>> callMusicas = new RetrofitInicializador().getMusicas().getMusicas(categoria.getId());

                callMusicas.enqueue(new Callback<List<Musicas>>() {
                    @Override
                    public void onResponse(Call<List<Musicas>> call, Response<List<Musicas>> response) {

                        ArrayList<Musicas> musicasList = (ArrayList<Musicas>) response.body();
                        repository.criarMusica(musicasList);

                        Log.i("onResponse", " Sucesso ao Salvar Categorias");

                        MusicasAdapter musicasAdapter = new MusicasAdapter(MusicaActivity.this, musicasList);

                        listaMusicas.setAdapter(musicasAdapter);

                        listaMusicas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent goSingle = new Intent(MusicaActivity.this, SingleMusicaActivity.class);

                                Musica musica = (Musica) parent.getItemAtPosition(position);

                                goSingle.putExtra("musica", musica);

                                startActivity(goSingle);

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<List<Musicas>> call, Throwable t) {
                        Log.e("onFailure", " Erro ao Salvar categorias: " + t.getMessage());
                    }
                });
            }


        }
    }

}
