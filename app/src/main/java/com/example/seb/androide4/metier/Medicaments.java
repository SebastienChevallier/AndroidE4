package com.example.seb.androide4.metier;

/**
 * Created by christian on 13/01/2017.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id_medicament",
        "id_famille",
        "nom_commercial",
        "prix_echentillon"
})
public class Medicaments implements java.io.Serializable {
    List<Medicaments> medicaments;
    @JsonProperty("id_medicament")
    @SerializedName("id_medicament")
    @Expose
    private int id_medicament;
    @SerializedName("id_famille")
    @Expose
    @JsonProperty("id_famille")
    private int id_famille;
    @JsonProperty("nom_commercial")
    @SerializedName("nom_commercial")
    @Expose
    private String nom_commercial;
    @JsonProperty("prix_echantillon")
    @SerializedName("prix_echantillon")
    @Expose
    private float prix_echantillon;



    @JsonProperty("id_medicament")
    public int getId_medicament() {
        return id_medicament;
    }

    @JsonProperty("id_medicament")
    public void setId_medicament(int id) {
        this.id_medicament = id;
    }

    @JsonProperty("id_famille")
    public int getId_famille() {
        return id_famille;
    }

    @JsonProperty("id_famille")
    public void setId_famille(int id_famille) {
        this.id_famille = id_famille;
    }


    @JsonProperty("nom_commercial")
    public String getNom_commercial() {
        return nom_commercial;
    }

    @JsonProperty("nom_commercial")
    public void setNom_commercial(String nom_commercial) {
        this.nom_commercial = nom_commercial;
    }


    @JsonProperty("prix_echantillon")
    public float getPrix_echantillon() {
        return prix_echantillon;
    }

    @JsonProperty("prix_echantillon")
    public void setPrix_echantillon(float prix_echantillon) {
        this.prix_echantillon = prix_echantillon;
    }

    ///// on initialise les propriété de l'objet
///
    public void initialise(int id_medicament, int id_famille , String nom_commercial, float prix_echantillon) {
        this.id_medicament = id_medicament;
        this.id_famille = id_famille;
        this.nom_commercial = nom_commercial;
        this.prix_echantillon = prix_echantillon;

    }

    public Medicaments(int id_famille,  String nom_commercial, float prix_echantillon) {
        this.id_famille = id_famille;

        this.nom_commercial = nom_commercial;

        this.prix_echantillon = prix_echantillon;
    }

    @Override
    public String toString() {

        return "Post{" +
                "id=" + id_medicament +
                "id_famille='" + id_famille + '\'' +
                ", nom_commercial=" + nom_commercial +
                ", prix_echantillon=" + prix_echantillon +
                '}';
    }


}
