package com.example.seb.androide4.metier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "libEtat"
})
public class Etat implements java.io.Serializable {



    @JsonProperty("id")
    private int id;
    @JsonProperty("libEtat")
    private String libEtat;


    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("libEtat")
    public String getLibEtat() {
        return libEtat;
    }

    @JsonProperty("libEtat")
    public void setLibEtat(String libEtat) {
        this.libEtat = libEtat;
    }


    public Etat(int id) {
        this.id = id;
    }
}

