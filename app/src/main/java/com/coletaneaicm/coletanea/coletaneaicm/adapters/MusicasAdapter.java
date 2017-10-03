package com.coletaneaicm.coletanea.coletaneaicm.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Categorias;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Musicas;
import com.coletaneaicm.coletanea.coletaneaicm.R;

import java.util.ArrayList;

/**
 * Created by cesar on 01/10/17.
 */
public class MusicasAdapter extends BaseAdapter {

    private ArrayList<Musicas> musicas = new ArrayList<>();
    private final Context context;

    public MusicasAdapter(Context context, ArrayList<Musicas> musicas) {
        this.musicas = musicas;
        this.context = context;
    }


    @Override
    public int getCount() {
        return this.musicas.size();
    }

    @Override
    public Object getItem(int i) {
        return this.musicas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        LayoutInflater inflater = LayoutInflater.from(this.context);
        v = inflater.inflate(R.layout.list_view_itens_musica, null);
        TextView textView = (TextView) v.findViewById(R.id.musica_nome);
        TextView numero = v.findViewById(R.id.musica_numero);

        Log.i("Adapter", "" + this.musicas.get(position).getNome());

        textView.setText(this.musicas.get(position).getNome());
        numero.setText(this.musicas.get(position).getNumero());

        return v;

    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
