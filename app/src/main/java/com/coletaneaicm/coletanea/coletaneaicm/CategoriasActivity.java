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

import java.util.ArrayList;

public class CategoriasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Colecoes colecao = (Colecoes) getIntent().getSerializableExtra("colecao");

        Repository repository = new Repository(this);

        ArrayList<Categorias> categorias = (ArrayList<Categorias>) repository.getCategorias(colecao);

        Log.i("onResponse", " "+ categorias.size());

        if(!categorias.isEmpty()) {

            ListView listaCategorias = (ListView) findViewById(R.id.lista_categorias);

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
        }

    }

}
