package com.coletaneaicm.coletanea.coletaneaicm;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Colecoes;
import com.coletaneaicm.coletanea.coletaneaicm.Repositories.Repository;
import com.coletaneaicm.coletanea.coletaneaicm.adapters.ColecoesAdapter;
import com.coletaneaicm.coletanea.coletaneaicm.retrofit.RetrofitInicializador;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ColecoesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });



        this.getColecoes();
    }

    /**
     * Fetches mail messages by making HTTP request
     * url: https://api.androidhive.info/json/inbox.json
     */
    private void getColecoes() {


        Repository repository = new Repository(this);

        List<Colecoes> colecoes = repository.getColecoes();

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager );
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new ColecoesAdapter(ColecoesActivity.this, colecoes));


        /*
        Call<List<Colecoes>> call = new RetrofitInicializador().getColecoes().getColecoes();

        call.enqueue(new Callback<List<Colecoes>>() {
            @Override
            public void onResponse(Call<List<Colecoes>> call, Response<List<Colecoes>> response) {

                List<Colecoes> colecoes = response.body();

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);

                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ColecoesActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager );

                //recyclerView.setAdapter(new ColecoesAdapter(ColecoesActivity.this, colecoes));

            }

            @Override
            public void onFailure(Call<List<Colecoes>> call, Throwable t) {

            }
        });*/
    }
}