package com.coletaneaicm.coletanea.coletaneaicm.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Categorias;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Colecoes;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Musicas;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cesar on 30/09/17.
 */
public class Repository extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private Context context;

    public Repository(Context context) {
        super(context, "Coletanea", null, 4);
        this.context = context;
        this.db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_drop_user = "DROP TABLE IF EXISTS user;";
        db.execSQL(sql_drop_user);

        String sql_drop = "DROP TABLE IF EXISTS colecoes;";
        db.execSQL(sql_drop);

        String sql_drop2 = "DROP TABLE IF EXISTS categorias;";
        db.execSQL(sql_drop2);

        String sql_drop3 = "DROP TABLE IF EXISTS musicas;";
        db.execSQL(sql_drop3);

        String sqlUser = "CREATE TABLE user (id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "avatar TEXT NULL, " +
                "email TEXT NOT NULL, " +
                "roles TEXT NULL, " +
                "cidade TEXT NULL, " +
                "uf TEXT NULL);";
        db.execSQL(sqlUser);

        String sql = "CREATE TABLE colecoes (id INTEGER PRIMARY KEY, nome TEXT NOT NULL);";
        db.execSQL(sql);
        String sql2 = "CREATE TABLE categorias (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, colecao INT NULL, qtde_musicas TEXT NULL);";
        db.execSQL(sql2);
        String sql3 = "CREATE TABLE musicas (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, categoria INT NULL, letra TEXT NULL, numero TEXT NULL, tom TEXT NULL);";
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String sql_drop_user = "DROP TABLE IF EXISTS user;";
        db.execSQL(sql_drop_user);

        String sql_drop = "DROP TABLE IF EXISTS colecoes;";
        db.execSQL(sql_drop);

        String sql_drop2 = "DROP TABLE IF EXISTS categorias;";
        db.execSQL(sql_drop2);

        String sql_drop3 = "DROP TABLE IF EXISTS musicas;";
        db.execSQL(sql_drop3);

        onCreate(db);
    }

    public User getUser(String email) {

        SQLiteDatabase db = getReadableDatabase();

        List<User> users = new ArrayList<User>();

        Cursor c = db.rawQuery("Select * from user where email = ? ", new String[] {email});

        if (0 == c.getCount()) {
            return null;
        }

        while(c.moveToNext()) {
            User user = new User();
            user.setId(c.getInt(c.getColumnIndex("id")));
            user.setNome(c.getString(c.getColumnIndex("nome")));
            user.setAvatar(c.getString(c.getColumnIndex("avatar")));
            user.setEmail(c.getString(c.getColumnIndex("email")));
            user.setRoles(c.getString(c.getColumnIndex("roles")));
            user.setCidade(c.getString(c.getColumnIndex("cidade")));
            user.setUf(c.getString(c.getColumnIndex("uf")));
            users.add(user);
        }

        c.close();

        if(users.isEmpty()) {
            return null;
        }


        return users.get(0);
    }

    public void criarUsuario (User user)
    {
        SQLiteDatabase db = getWritableDatabase();

        try {

            ContentValues valores = new ContentValues();

            valores.put("id", user.getId());
            valores.put("nome", user.getNome());
            valores.put("avatar", user.getAvatar());
            valores.put("email", user.getEmail());
            valores.put("roles", user.getRoles());
            valores.put("cidade", user.getCidade());
            valores.put("uf", user.getUf());

            db.insertOrThrow("user", null, valores);

            db.close();
        } catch (Exception e) {

        }
    }

    public List<Colecoes> getColecoes() {

        SQLiteDatabase db = getReadableDatabase();

        List<Colecoes> colecoes = new ArrayList<Colecoes>();

        Cursor c = db.rawQuery("Select * from colecoes", null);

        while(c.moveToNext()) {
            Colecoes colecao = new Colecoes();
            colecao.setId(c.getInt(c.getColumnIndex("id")));
            colecao.setNome(c.getString(c.getColumnIndex("nome")));
            colecoes.add(colecao);
        }

        c.close();

        return colecoes;
    }

    public Colecoes getColecao(Integer id) {

        SQLiteDatabase db = getReadableDatabase();

        List<Colecoes> colecoes = new ArrayList<Colecoes>();

        Cursor c = db.rawQuery("Select * from colecoes where id = ? ", new String[] {String.valueOf(id)});

        while(c.moveToNext()) {
            Colecoes colecao = new Colecoes();
            colecao.setId(c.getInt(c.getColumnIndex("id")));
            colecao.setNome(c.getString(c.getColumnIndex("nome")));
            colecoes.add(colecao);
        }

        c.close();


        return colecoes.get(0);
    }

    public void criarColecao(ArrayList<Colecoes> colecao) {

        SQLiteDatabase db = getWritableDatabase();

        try {

            for (int i = 0; i < colecao.size(); ++i) {
                ContentValues valores = new ContentValues();

                valores.put("id", colecao.get(i).getId());
                valores.put("nome", colecao.get(i).getNome());

                db.insertOrThrow("colecoes", null, valores);
            }

            db.close();
        } catch (Exception e) {
            Log.e("onInsert", e.getMessage());
        }

    }

    public List<Categorias> getCategorias(Colecoes colecao) {

        SQLiteDatabase db = getReadableDatabase();

        List<Categorias> categorias = new ArrayList<Categorias>();

        Cursor c = db.rawQuery("Select * from categorias where ? = ? ", new String[] {"colecao", String.valueOf(colecao.getId())});

        while(c.moveToNext()) {
            Categorias categoria = new Categorias();

            categoria.setId(c.getInt(c.getColumnIndex("id")));
            categoria.setNome(c.getString(c.getColumnIndex("nome")));
            categoria.setColecao(c.getInt(c.getColumnIndex("colecao")));
            categorias.add(categoria);
        }

        c.close();

        return categorias;
    }

    public List<Categorias> getAllCategorias() {

        SQLiteDatabase db = getReadableDatabase();

        List<Categorias> categorias = new ArrayList<Categorias>();

        Cursor c = db.rawQuery("Select * from categorias", new String[] {});

        while(c.moveToNext()) {
            Categorias categoria = new Categorias();
            categoria.setId(c.getInt(c.getColumnIndex("id")));
            categoria.setNome(c.getString(c.getColumnIndex("nome")));
            categorias.add(categoria);
        }

        c.close();

        return categorias;
    }


    public void criarCategoria(ArrayList<Categorias> categorias) {

        SQLiteDatabase db = getWritableDatabase();

        try {

            for (int i = 0; i < categorias.size(); ++i) {

                ContentValues valores = new ContentValues();

                valores.put("id", categorias.get(i).getId());
                valores.put("nome", categorias.get(i).getNome());
                valores.put("colecao", categorias.get(i).getColecao());

                db.insertOrThrow("categorias", null, valores);

                Log.i("onResponse", " Sucesso ao Salvar Categoria " + categorias.get(i).getNome());
            }

            db.close();
        } catch (Exception e) {
            Log.e("onInsert", "");
        }

    }

    public Categorias getCategoria(Integer id) {

        SQLiteDatabase db = getReadableDatabase();

        List<Categorias> categorias = new ArrayList<Categorias>();

        Cursor c = db.rawQuery("Select * from categorias where id = ? ", new String[] {String.valueOf(id)});

        while(c.moveToNext()) {
            Categorias categoria = new Categorias();
            categoria.setId(c.getInt(c.getColumnIndex("id")));
            categoria.setNome(c.getString(c.getColumnIndex("nome")));
            categoria.setColecao(c.getInt(c.getColumnIndex("colecao")));

            categorias.add(categoria);
        }

        c.close();

        return categorias.get(0);

    }

    public List<Musicas> getMusicas(Categorias categoria) {

        SQLiteDatabase db = getReadableDatabase();

        List<Musicas> musicas = new ArrayList<Musicas>();

        Cursor c = db.rawQuery("Select * from musicas where ? = ? ", new String[] {"categoria", String.valueOf(categoria.getId())});

        if (c.getCount() == 0) {
            return musicas;
        }

        while(c.moveToNext()) {
            Musicas musicas1 = new Musicas();
            musicas1.setId(c.getInt(c.getColumnIndex("id")));
            musicas1.setNome(c.getString(c.getColumnIndex("nome")));
            musicas1.setNumero(c.getString(c.getColumnIndex("numero")));
            musicas1.setCategoria(c.getColumnIndex("categoria"));
            musicas1.setLetra(c.getString(c.getColumnIndex("letra")));
            musicas1.setTom(c.getString(c.getColumnIndex("tom")));
            musicas.add(musicas1);
        }

        c.close();

        return musicas;
    }

    public void criarMusica(ArrayList<Musicas> musicas) {

        SQLiteDatabase db = getWritableDatabase();

        try {

            Log.i("onMusicas", "Size " + musicas.size());

            for (int i = 0; i < musicas.size(); i++) {

                ContentValues valores = new ContentValues();

                Log.i("criarMusica", "Sucesso "  + musicas.get(i).getNome());

                valores.put("id", musicas.get(i).getId());
                valores.put("nome", musicas.get(i).getNome());
                valores.put("categoria", musicas.get(i).getCategoria());
                valores.put("tom", musicas.get(i).getTom());
                valores.put("letra", musicas.get(i).getLetra());
                valores.put("numero", musicas.get(i).getNumero());

                db.insertOrThrow("musicas", null, valores);
            }

            db.close();
        } catch (Exception e) {
            Log.e("onInsert", "Ocorreu um erro musicas " + e.getMessage());
        }

    }

    public Musicas getMusica(Musicas id) {

        List<Musicas> musicas = new ArrayList<Musicas>();

        Cursor c = db.rawQuery("Select * from musicas where id = ? ", new String[] {String.valueOf(id)});

        while(c.moveToNext()) {
            Musicas musicas1 = new Musicas();
            musicas1.setId(c.getInt(c.getColumnIndex("id")));
            musicas1.setNome(c.getString(c.getColumnIndex("nome")));
            musicas1.setNumero(c.getString(c.getColumnIndex("numero")));
            musicas1.setCategoria(c.getColumnIndex("categoria"));
            musicas1.setLetra(c.getString(c.getColumnIndex("letra")));
            musicas1.setTom(c.getString(c.getColumnIndex("tom")));
            musicas.add(musicas1);
        }

        c.close();

        return musicas.get(0);

    }
}
