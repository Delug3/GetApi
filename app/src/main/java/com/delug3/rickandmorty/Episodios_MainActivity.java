package com.delug3.rickandmorty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.delug3.rickandmorty.adapter.ListaEpisodiosAdapter;
import com.delug3.rickandmorty.handler.RecyclerTouchListener;
import com.delug3.rickandmorty.modelos.Episodios;
import com.delug3.rickandmorty.modelos.EpisodiosRespuesta;
import com.delug3.rickandmorty.modelos.PersonajesRespuesta;
import com.delug3.rickandmorty.services.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Episodios_MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String TAG = "RICKMORTY ";

    private RecyclerView recyclerViewEpisodios;
    private ListaEpisodiosAdapter listaEpisodiosAdapter;

    List<Episodios> episodiosList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__episodios);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recyclerViewEpisodios = (RecyclerView)findViewById(R.id.recyclerViewEpisodios);
        listaEpisodiosAdapter= new ListaEpisodiosAdapter(episodiosList);

        recyclerViewEpisodios.setAdapter(listaEpisodiosAdapter);

        recyclerViewEpisodios.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewEpisodios.setLayoutManager(layoutManager);

        recyclerViewEpisodios.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewEpisodios, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Episodio mas info de los caracteres
               Intent i = new Intent(Episodios_MainActivity.this, DetallesPersonajes_Activity.class);

                i.putExtra("EPISODIO_ID", episodiosList.get(position).getId());

                i.putExtra("EPISODIO_CODIGO", episodiosList.get(position).getEpisode());
                i.putExtra("EPISODIO_NOMBRE", episodiosList.get(position).getName());

                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));

        obtenerDatos();

    }

    private void obtenerDatos()
    {
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<EpisodiosRespuesta> episodiosRespuestaCall= service.obtenerListaEpisodios();
        //personajes(array url)??
        Call<PersonajesRespuesta> personajesRespuestaCall= service.obtenerListaPersonajes();

        episodiosRespuestaCall.enqueue(new Callback<EpisodiosRespuesta>() {
            @Override
            public void onResponse(Call<EpisodiosRespuesta> call, Response<EpisodiosRespuesta> response) {

                if (response.isSuccessful()) {

                    EpisodiosRespuesta episodiosRespuesta = response.body();
                    ArrayList<Episodios> listaEpisodios = episodiosRespuesta.getResults();

                    //Prueba listado episodios
                   for (int i = 0; i < listaEpisodios.size(); i++)
                    {
                        Episodios e = listaEpisodios.get(i);
                        Log.e(TAG, "Codigo: " + e.getEpisode());

                        Log.e(TAG, "Titulo: " + e.getName());

                    }

                    listaEpisodiosAdapter.adicionarListaEpisodios(listaEpisodios);

                }else{

                    Log.e(TAG, "onResponse: " + response.errorBody());


                }
            }

            @Override
            public void onFailure(Call<EpisodiosRespuesta> call, Throwable t) {

                Log.e(TAG,"onFailure"+ t.getMessage());



            }
        });

    }
}
