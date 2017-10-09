package com.coletaneaicm.coletanea.coletaneaicm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Musica;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Musicas;
import com.coletaneaicm.coletanea.coletaneaicm.retrofit.RetrofitInicializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleMusicaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_musica);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Musicas musica = (Musicas) getIntent().getSerializableExtra("musica");


        TextView nome = (TextView) findViewById(R.id.musica_single_nome);
        nome.setText(musica.getNome());

        Call<Musicas> call = new RetrofitInicializador().getMusica().getMusica(musica.getId());

        call.enqueue(new Callback<Musicas>() {
            @Override
            public void onResponse(Call<Musicas> call, Response<Musicas> response) {

                Log.i("onResponse", " Sucesso ao encontrar musica " + call.request().url().toString());

                Musicas song = response.body();

                TextView letra = (TextView) findViewById(R.id.musica_single_letra);
                letra.setText(song.getLetra());

                Log.i("onResponse", " Sucesso ao encontrar musica " + song.getLetra());

            }

            @Override
            public void onFailure(Call<Musicas> call, Throwable t) {
                Log.e("onFailure", " Erro ao encontrar musica " + t.getMessage());
            }
        });




    }

}
