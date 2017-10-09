package com.coletaneaicm.coletanea.coletaneaicm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.User;
import com.squareup.picasso.Picasso;

public class PerfilActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
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

        TextView nomePerfil = (TextView) findViewById(R.id.nome_perfil);
        user = (User) getIntent().getSerializableExtra("user");
        nomePerfil.setText(" cesar");

        ImageView foto_perfil = (ImageView) findViewById(R.id.foto_perfil);

        Picasso.with(this).load("https://coletaneaicm.com/web/assets/blog/img/avatar/51497447b9ca6aa0a595a46d7216070f..jpg").into(foto_perfil);
    }

}
