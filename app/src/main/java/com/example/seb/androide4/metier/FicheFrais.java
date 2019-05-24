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
        "id",
        "anneeMois",
        "dateModification",
        "montantValide",
        "nbJustificatifs",
        "visitorId",
        "etat"
})
public class FicheFrais implements java.io.Serializable {

    @JsonProperty("id")
    private int id;
    @JsonProperty("anneeMois")
    private String anneeMois;
    @JsonProperty("dateModification")
    private Date dateModification;
    @JsonProperty("montantValide")
    private float montantValide;
    @JsonProperty("nbJustificatifs")
    private int  nbJustificatifs;
    @JsonProperty("visitorId")
    private int visitorId;
    @JsonProperty("etat")
    private Etat etat;


    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("anneeMois")
    public String getAnneeMois() {
        return anneeMois;
    }

    @JsonProperty("anneeMois")
    public void setAnneeMois(String anneeMois) {
        this.anneeMois = anneeMois;
    }

    @JsonProperty("dateModification")
    public Date getDateModification() {
        return dateModification;
    }

    @JsonProperty("dateModification")
    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    @JsonProperty("montantValide")
    public float getMontantValide() {
        return montantValide;
    }

    @JsonProperty("montantValide")
    public void setMontantValide(float montantValide) {
        this.montantValide = montantValide;
    }

    @JsonProperty("nbJustificatifs")
    public int getNbJustificatifs() {
        return nbJustificatifs;
    }

    @JsonProperty("nbJustificatifs")
    public void setNbJustificatifs(int nbJustificatifs) {
        this.nbJustificatifs = nbJustificatifs;
    }

    @JsonProperty("visitorId")
    public int getVisitorId() {
        return visitorId;
    }

    @JsonProperty("visitorId")
    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    @JsonProperty("etat")
    public Etat getEtat() {
        return etat;
    }

    @JsonProperty("etat")
    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    ///// on initialise les propriété de l'objet
///
    public void initialise(int  id, String anneeMois, Date dateModification, float montantValide, int nbJustificatifs, int visitorId, Etat etat) {
        this.id = id;
        this.anneeMois = anneeMois;
        this.dateModification = dateModification;
        this.montantValide = montantValide;
        this.nbJustificatifs = nbJustificatifs;
        this.visitorId = visitorId;
        this.etat = etat;

    }

    public FicheFrais(String anneeMois, Date dateModification, float montantValide, int nbJustificatifs, int visitorId, Etat etat) {
        this.anneeMois = anneeMois;
        this.dateModification = dateModification;
        this.montantValide = montantValide;
        this.nbJustificatifs = nbJustificatifs;
        this.visitorId = visitorId;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                "anneeMois='" + anneeMois + '\'' +
                ", dateModification='" + dateModification + '\'' +
                ", montantValide=" + montantValide +
                ", nbJustificatifs=" + nbJustificatifs +
                ", visitorId=" + visitorId +
                ", etat=" + etat +
                '}';
    }


}
