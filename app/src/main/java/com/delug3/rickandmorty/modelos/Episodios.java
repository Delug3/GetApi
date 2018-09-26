package com.delug3.rickandmorty.modelos;

import java.util.ArrayList;
import java.util.List;

public class Episodios {

    private String episode;
    private String name;
   // private List<Characters> characters = new ArrayList<>();
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public List<Characters> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Characters> characters) {
        this.characters = characters;
    }
    */
}
