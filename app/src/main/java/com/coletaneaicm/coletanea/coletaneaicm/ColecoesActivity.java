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

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Colecoes;
import com.coletaneaicm.coletanea.coletaneaicm.Repositories.Repository;
import com.coletaneaicm.coletanea.coletaneaicm.adapters.ColecoesAdapter;

import java.util.ArrayList;

public class ColecoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colecoes);

        Repository repository = new Repository(this);
        ArrayList<Colecoes> colecoes = (ArrayList<Colecoes>) repository.getColecoes();

        ListView listaColecoes = (ListView) findViewById(R.id.lista_colecoes);

        ColecoesAdapter colecoesAdapter = new ColecoesAdapter(this, colecoes);

        listaColecoes.setAdapter(colecoesAdapter);

        listaColecoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent goCategorias = new Intent(ColecoesActivity.this, CategoriasActivity.class);
                Colecoes colecao = (Colecoes) adapterView.getItemAtPosition(i);
                goCategorias.putExtra("colecao", colecao);

                Log.i("onResponse", " "+ colecao.getNome());
                startActivity(goCategorias);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
