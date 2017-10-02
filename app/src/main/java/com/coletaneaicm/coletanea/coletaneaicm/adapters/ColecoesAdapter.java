package com.coletaneaicm.coletanea.coletaneaicm.adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.coletaneaicm.coletanea.coletaneaicm.ColecoesActivity;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Colecoes;
import com.coletaneaicm.coletanea.coletaneaicm.R;

import java.util.ArrayList;

/**
 * Created by cesar on 01/10/17.
 */
public class ColecoesAdapter extends BaseAdapter {

    private ArrayList<Colecoes> colecoes = new ArrayList<>();
    private final Context context;

    public ColecoesAdapter(Context context, ArrayList<Colecoes> colecoes) {
        this.colecoes = colecoes;
        this.context = context;
    }


    @Override
    public int getCount() {
        return this.colecoes.size();
    }

    @Override
    public Object getItem(int i) {
        return this.colecoes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        LayoutInflater inflater = LayoutInflater.from(this.context);
        v = inflater.inflate(R.layout.list_view_itens_colecao, null);
        TextView textView = (TextView) v.findViewById(R.id.colecao_nome);
        //ImageView imageView = v.findViewById(R.id.colecao_imagem);


        Log.i("Adapter", "" + this.colecoes.get(position).getNome());

        textView.setText(this.colecoes.get(position).getNome());
        //imageView.setImageResource(this.colecoes.get(position).);
        return v;

    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
