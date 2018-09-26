package com.delug3.rickandmorty.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delug3.rickandmorty.R;
import com.delug3.rickandmorty.modelos.Episodios;

import java.util.ArrayList;
import java.util.List;

public class ListaEpisodiosAdapter extends RecyclerView.Adapter<ListaEpisodiosAdapter.ViewHolder> {

    private List<Episodios> episodiosList;


    public ListaEpisodiosAdapter(List<Episodios> episodiosList) {
        this.episodiosList = episodiosList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episodios, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Episodios e = episodiosList.get(position);
        holder.codigoTextView.setText(e.getEpisode());
        holder.nombreTextView.setText(e.getName());

    }

    @Override
    public int getItemCount() {
        return episodiosList.size();
    }

    public void adicionarListaEpisodios(ArrayList<Episodios> listaEpisodios) {

        episodiosList.addAll(listaEpisodios);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView codigoTextView;
        private TextView nombreTextView;


        public ViewHolder(View itemView) {

            super(itemView);
            codigoTextView = (TextView) itemView.findViewById(R.id.codigoTextView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);

        }


    }
}


