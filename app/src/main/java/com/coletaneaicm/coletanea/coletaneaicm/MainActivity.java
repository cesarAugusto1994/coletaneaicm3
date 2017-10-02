package com.coletaneaicm.coletanea.coletaneaicm;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Categorias;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Colecoes;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Musica;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Musicas;
import com.coletaneaicm.coletanea.coletaneaicm.Repositories.Repository;
import com.coletaneaicm.coletanea.coletaneaicm.retrofit.RetrofitInicializador;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_colecoes) {

            Intent goColecoes = new Intent(this, ColecoesActivity.class);
            startActivity(goColecoes);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

            final Repository repository = new Repository(this);

            ArrayList<Colecoes> colecoes = (ArrayList<Colecoes>) repository.getColecoes();

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Baixando do servidor...");
            ///progressDialog.setIndeterminate(false);
            progressDialog.show();


            if (colecoes.isEmpty()) {


                Call<List<Colecoes>> call = new RetrofitInicializador().getColecoes().getColecoes();

                call.enqueue(new Callback<List<Colecoes>>() {
                    @Override
                    public void onResponse(Call<List<Colecoes>> call, Response<List<Colecoes>> response) {

                        ArrayList<Colecoes> colecoes = (ArrayList<Colecoes>) response.body();
                        repository.criarColecao(colecoes);

                        Log.i("onResponse", " Sucesso ao Salvar Colecoes");
                    }

                    @Override
                    public void onFailure(Call<List<Colecoes>> call, Throwable t) {

                        Log.e("onFailure", " Erro ao Salvar Colecoes: " + t.getMessage());

                    }
                });

            }

            for (int i = 0; i < colecoes.size(); i++) {

                ArrayList<Categorias> categorias = (ArrayList<Categorias>) repository.getCategorias(colecoes.get(i));

                if(categorias.isEmpty()) {

                    Call<List<Categorias>> callCatetegorias = new RetrofitInicializador().getCategorias().getCategorias(colecoes.get(i).getId());

                    callCatetegorias.enqueue(new Callback<List<Categorias>>() {
                        @Override
                        public void onResponse(Call<List<Categorias>> call, Response<List<Categorias>> response) {

                            ArrayList<Categorias> categorias = (ArrayList<Categorias>) response.body();
                            repository.criarCategoria(categorias);

                            Log.i("onResponse", " Sucesso ao Salvar Categorias");

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

                        }

                        @Override
                        public void onFailure(Call<List<Categorias>> call, Throwable t) {
                            Log.e("onFailure", " Erro ao Salvar categorias: " + t.getMessage());
                        }
                    });
                }

                ArrayList<Categorias> categoriasList = (ArrayList<Categorias>) repository.getCategorias(colecoes.get(i));

                Log.i("onResponse", " " + categoriasList.size());

                for (int i2 = 0; i2 < categoriasList.size(); i2++) {

                    ArrayList<Musicas> musicas = (ArrayList<Musicas>) repository.getMusicas(categoriasList.get(i2));

                    if (musicas.isEmpty()) {

                        Call<List<Musicas>> callMusicas = new RetrofitInicializador().getMusicas().getMusicas(categoriasList.get(i2).getId());

                        callMusicas.enqueue(new Callback<List<Musicas>>() {
                            @Override
                            public void onResponse(Call<List<Musicas>> call, Response<List<Musicas>> response) {

                                ArrayList<Musicas> musicas = (ArrayList<Musicas>) response.body();
                                repository.criarMusica(musicas);

                                Log.i("onResponse", " Sucesso ao Salvar Musicas");
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<List<Musicas>> call, Throwable t) {
                                Log.e("onFailure", " Erro ao Salvar Musicas: " + t.getMessage());
                                progressDialog.dismiss();
                            }
                        });

                    } else {
                        progressDialog.dismiss();
                    }
                }
            }

            progressDialog.dismiss();

            Toast.makeText(MainActivity.this, "Está tudo Atualizado ;)", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
