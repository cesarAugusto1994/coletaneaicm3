package com.coletaneaicm.coletanea.coletaneaicm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    private SharedPreferences prefs;

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



        prefs = PreferenceManager.getDefaultSharedPreferences(PerfilActivity.this);

        String usuarioNome = prefs.getString("usuario_nome","");
        String usuarioEmail = prefs.getString("usuario_email","");
        String usuarioAvatar = prefs.getString("usuario_avatar","");
        String usuarioCidade = prefs.getString("usuario_cidade","");
        String usuarioUf = prefs.getString("usuario_uf","");

        TextView nomePerfil = (TextView) findViewById(R.id.nome_perfil);
        user = (User) getIntent().getSerializableExtra("user");
        nomePerfil.setText(usuarioNome);

        ImageView foto_perfil = (ImageView) findViewById(R.id.foto_perfil);

        Picasso.with(this).load(user.getAvatar()).into(foto_perfil);
    }

}
