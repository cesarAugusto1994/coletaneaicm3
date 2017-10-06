package com.coletaneaicm.coletanea.coletaneaicm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Colecoes;
import com.coletaneaicm.coletanea.coletaneaicm.R;

import java.util.List;

public class ColecoesAdapter extends RecyclerView.Adapter<ColecoesAdapter.ColecoesViewHolder> {

    private List<Colecoes> colecoes;
    private Context context;
    ColecoesViewHolder colecoesViewHolder;

    public ColecoesAdapter(Context context, List<Colecoes> colecoes) {
        this.colecoes = colecoes;
        this.context = context;
    }

    @Override
    public ColecoesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_itens_colecao, parent, false);

        colecoesViewHolder = new ColecoesViewHolder(view);
        return colecoesViewHolder;
    }

    @Override
    public void onBindViewHolder(ColecoesViewHolder viewHolder, int position) {

        ColecoesViewHolder colecoesViewHolder = (ColecoesViewHolder) viewHolder;

        Colecoes colecao = colecoes.get(position);

        colecoesViewHolder.colecao_nome.setText(colecao.getNome());

    }

    @Override
    public int getItemCount() {
        return colecoes.size();
    }

    public class ColecoesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView colecao_nome;

        public ColecoesViewHolder(View itemView) {
            super(itemView);
            colecao_nome = (TextView) itemView.findViewById(R.id.colecao_nome_2);
        }

        @Override
        public void onClick(View v) {

        }
    }
}