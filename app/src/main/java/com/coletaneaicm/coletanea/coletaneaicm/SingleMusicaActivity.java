package com.coletaneaicm.coletanea.coletaneaicm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Musica;

public class SingleMusicaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_musica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Musica musica = (Musica) getIntent().getSerializableExtra("musica");

        TextView nome = (TextView) findViewById(R.id.musica_single_nome);
        nome.setText(musica.getNome());

        TextView letra = (TextView) findViewById(R.id.musica_single_letra);
        letra.setText(musica.getLetra());
    }

}
