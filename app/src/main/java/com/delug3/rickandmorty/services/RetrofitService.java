package com.delug3.rickandmorty.services;

import com.delug3.rickandmorty.modelos.EpisodiosRespuesta;
import com.delug3.rickandmorty.modelos.PersonajesRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    //query para dividirlos por seasons?SE01 - SE02????
    @GET("episode")
    Call<EpisodiosRespuesta> obtenerListaEpisodios();

    @GET("character")
    Call<PersonajesRespuesta> obtenerListaPersonajes();



}
