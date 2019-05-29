package com.example.seb.androide4.metier;

/**
 * Created by christian on 13/01/2017.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id_medicament",
        "id_famille",
        "depot_legal",
        "nom_commercial",
        "effets",
        "contre_indication",
        "prix_echentillon"
})
public class Medicaments implements java.io.Serializable {

    @JsonProperty("id_medicament")
    private int id_medicament;
    @JsonProperty("id_famille")
    private int id_famille;
    @JsonProperty("depot_legal")
    private String depot_legal;
    @JsonProperty("nom_commercial")
    private String nom_commercial;
    @JsonProperty("effets")
    private String effets;
    @JsonProperty("contre_indication")
    private String contre_indication;
    @JsonProperty("prix_echantillon")
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

    @JsonProperty("depot_legal")
    public String getDepot_legal() {
        return depot_legal;
    }

    @JsonProperty("depot_legal")
    public void setDepot_legal(String depot_legal) {
        this.depot_legal = depot_legal;
    }

    @JsonProperty("nom_commercial")
    public String getNom_commercial() {
        return nom_commercial;
    }

    @JsonProperty("nom_commercial")
    public void setNom_commercial(String nom_commercial) {
        this.nom_commercial = nom_commercial;
    }

    @JsonProperty("effets")
    public String getEffets() {
        return effets;
    }

    @JsonProperty("effets")
    public void setEffets(String effets) {
        this.effets = effets;
    }

    @JsonProperty("contre_indication")
    public String getContre_indication() {
        return contre_indication;
    }

    @JsonProperty("contre_indication")
    public void setContre_indication(String contre_indication) {
        this.contre_indication = contre_indication;
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
    public void initialise(int id_medicament, int id_famille, String depot_legal, String nom_commercial, String effets, String contre_indication, float prix_echantillon) {
        this.id_medicament = id_medicament;
        this.id_famille = id_famille;
        this.depot_legal = depot_legal;
        this.nom_commercial = nom_commercial;
        this.effets = effets;
        this.contre_indication = contre_indication;
        this.prix_echantillon = prix_echantillon;

    }

    public Medicaments(int id_famille, String depot_legal, String nom_commercial, String effets, String contre_indication, float prix_echantillon) {
        this.id_famille = id_famille;
        this.depot_legal = depot_legal;
        this.nom_commercial = nom_commercial;
        this.effets = effets;
        this.contre_indication = contre_indication;
        this.prix_echantillon = prix_echantillon;
    }

    @Override
    public String toString() {

        return "Post{" +
                "id=" + id_medicament +
                "id_famille='" + id_famille + '\'' +
                ", depot_legal='" + depot_legal + '\'' +
                ", nom_commercial=" + nom_commercial +
                ", effets=" + effets +
                ", contre_indication=" + contre_indication +
                ", prix_echantillon=" + prix_echantillon +
                '}';
    }


}
