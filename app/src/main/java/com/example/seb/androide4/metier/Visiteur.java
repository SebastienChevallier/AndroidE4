package com.example.seb.androide4.metier;

/**
 * Created by christian on 14/11/2017.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id_visiteur",
        "nom_visiteur",
        "prenom_visiteur",
        "adresse_visiteur",
        "cp_visiteur",
        "ville_visiteur",
        "date_embauche",
        "login_visiteur",
        "pwd_visiteur",
        "type_visiteur",
        "id_secteur",
        "id_laboratoire"
})

public class Visiteur {

    @JsonProperty("id_visiteur")
    private String id_visiteur;
    @JsonProperty("nom_visiteur")
    private String nom_visiteur;
    @JsonProperty("prenom_visiteur")
    private String prenom_visiteur;
    @JsonProperty("adresse_visiteur")
    private String adresse_visiteur;
    @JsonProperty("cp_visiteur")
    private String cp_visiteur;
    @JsonProperty("ville_visiteur")
    private String ville_visiteur;
    @JsonProperty("date_embauche")
    private String date_embauche;
    @JsonProperty("login_visiteur")
    private String login_visiteur;
    @JsonProperty("pwd_visiteur")
    private String pwd_visiteur;
    @JsonProperty("type_visiteur")
    private String type_visiteur;
    @JsonProperty("id_secteur")
    private String id_secteur;
    @JsonProperty("id_laboratoire")
    private String id_laboratoire;


    @JsonProperty("id_visiteur")
    public String getId_visiteur() {
        return id_visiteur;
    }

    @JsonProperty("id_visiteur")
    public void setId_visiteur(String id) {
        this.id_visiteur = id;
    }

    @JsonProperty("nom_visiteur")
    public String getNom_visiteur() {
        return nom_visiteur;
    }

    @JsonProperty("nom_visiteur")
    public void setNom_visiteur(String nom_visiteur) {
        this.nom_visiteur = nom_visiteur;
    }

    @JsonProperty("prenom_visiteur")
    public String getPrenom_visiteur() {
        return prenom_visiteur;
    }

    @JsonProperty("prenom_visiteur")
    public void setPrenom_visiteur(String prenom_visiteur) {
        this.prenom_visiteur = prenom_visiteur;
    }

    @JsonProperty("adresse_visiteur")
    public String getAdresse_visiteur() {
        return adresse_visiteur;
    }

    @JsonProperty("adresse_visiteur")
    public void setAdresse_visiteur(String adresse_visiteur) {
        this.adresse_visiteur = adresse_visiteur;
    }

    @JsonProperty("cp_visiteur")
    public String getCp_visiteur() {
        return cp_visiteur;
    }

    @JsonProperty("cp_visiteur")
    public void setCp_visiteur(String cp_visiteur) {
        this.cp_visiteur = cp_visiteur;
    }

    @JsonProperty("ville_visiteur")
    public String getVille_visiteur() {
        return ville_visiteur;
    }

    @JsonProperty("ville_visiteur")
    public void setVille_visiteur(String ville_visiteur) {
        this.ville_visiteur = ville_visiteur;
    }

    @JsonProperty("date_embauche")
    public String getDate_embauche() {
        return date_embauche;
    }

    @JsonProperty("date_embauche")
    public void setDate_embauche(String date_embauche) {
        this.date_embauche = date_embauche;
    }

    @JsonProperty("login_visiteur")
    public String getLogin_visiteur() {
        return login_visiteur;
    }

    @JsonProperty("login_visiteur")
    public void setLogin_visiteur(String login_visiteur) {
        this.login_visiteur = login_visiteur;
    }

    @JsonProperty("pwd_visiteur")
    public String getPwd_visiteur() {
        return pwd_visiteur;
    }

    @JsonProperty("pwd_visiteur")
    public void setPwd_visiteur(String pwd_visiteur) {
        this.pwd_visiteur = pwd_visiteur;
    }

    @JsonProperty("type_visiteur")
    public String gettTpe_visiteur() {
        return type_visiteur;
    }

    @JsonProperty("type_visiteur")
    public void settTpe_visiteur(String role) {
        this.type_visiteur = type_visiteur;
    }

    @JsonProperty("id_secteur")
    public String getId_secteur() {
        return id_secteur;
    }

    @JsonProperty("id_secteur")
    public void setId_secteur(String id_secteur) {
        this.id_secteur = id_secteur;
    }

    @JsonProperty("id_laboratoire")
    public String getId_laboratoire() {
        return id_laboratoire;
    }

    @JsonProperty("id_laboratoire")
    public void setId_laboratoire(String id_laboratoire) {
        this.id_laboratoire = id_laboratoire;
    }

}