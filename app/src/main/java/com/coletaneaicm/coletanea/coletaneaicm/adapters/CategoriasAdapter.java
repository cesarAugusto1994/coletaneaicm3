package com.coletaneaicm.coletanea.coletaneaicm.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Categorias;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Colecoes;
import com.coletaneaicm.coletanea.coletaneaicm.R;

import java.util.ArrayList;

/**
 * Created by cesar on 01/10/17.
 */
public class CategoriasAdapter extends BaseAdapter {

    private ArrayList<Categorias> categorias = new ArrayList<>();
    private final Context context;

    public CategoriasAdapter(Context context, ArrayList<Categorias> categorias) {
        this.categorias = categorias;
        this.context = context;
    }


    @Override
    public int getCount() {
        return this.categorias.size();
    }

    @Override
    public Object getItem(int i) {
        return this.categorias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        LayoutInflater inflater = LayoutInflater.from(this.context);
        v = inflater.inflate(R.layout.list_view_itens_categoria, null);
        TextView textView = (TextView) v.findViewById(R.id.categoria_nome);

        Log.i("Adapter", "" + this.categorias.get(position).getNome());

        textView.setText(this.categorias.get(position).getNome());

        return v;

    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
