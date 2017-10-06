package com.coletaneaicm.coletanea.coletaneaicm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Colecoes;
import com.coletaneaicm.coletanea.coletaneaicm.R;
import com.coletaneaicm.coletanea.coletaneaicm.helper.ColecoesViewHolder;

import java.util.List;

public class ColecoesAdapter extends RecyclerView.Adapter {

    private List<Colecoes> colecoes;
    private Context context;

    public ColecoesAdapter(Context context, List<Colecoes> colecoes) {
        this.colecoes = colecoes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_view_itens_colecao, parent, false);

        ColecoesViewHolder colecoesViewHolder = new ColecoesViewHolder(view);

        return colecoesViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        ColecoesViewHolder colecoesViewHolder = (ColecoesViewHolder) viewHolder;

        Colecoes colecao = colecoes.get(position);

        colecoesViewHolder.nome.setText(colecao.getNome());

    }

    @Override
    public int getItemCount() {
        return colecoes.size();
    }
}