package com.coletaneaicm.coletanea.coletaneaicm.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.coletaneaicm.coletanea.coletaneaicm.R;

/**
 * Created by cesar on 05/10/17.
 */
public class ColecoesViewHolder extends RecyclerView.ViewHolder {

    public TextView nome;

    public ColecoesViewHolder(View itemView) {
        super(itemView);
        nome = (TextView) itemView.findViewById(R.id.colecao_nome_2);
    }
}
