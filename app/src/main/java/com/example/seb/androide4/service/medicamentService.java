package com.example.seb.androide4.service;

/**
 * Created by christian on 06/11/2017.
 */

import com.example.seb.androide4.metier.FicheFrais;
import com.example.seb.androide4.metier.Medicaments;
import com.example.seb.androide4.metier.Visiteur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface medicamentService {

    public static  final String ENDPOINT = "http://192.168.1.26/ProjetWSGsb/public/api/";
    // requête d'appel des frais GET





    @GET("medicament/listeMedic")
    Call<List<Medicaments>> getMedic();

    @POST("medicament/insertion")
    Call<Medicaments>  postMedic(@Body Medicaments medic);
    @POST("medicament/updateMedic")
    Call<Medicaments> updateMedic(@Body Medicaments medic);

    // requête de suppression
    @POST("medicament/deleteMedic")
    Call<Medicaments> deleteMedic(@Body Medicaments medic);

 // requête de contrôle d'un visiteur
    @POST("getConnexion")
    Call<Visiteur> getConnexion(@Body Visiteur unV) ;

// requête d'ajout
// @GET("getListeFicheFrais/1")
    @GET("frais/listeFrais/1")
    Call<List<FicheFrais>> getFicheFrais();
    @POST("frais/addFicheFrais")
    Call<FicheFrais>  postFicheFrais(@Body FicheFrais ff);

// requête de mise à jour

    @POST("frais/updateFicheFrais")
    Call<FicheFrais> updateFicheFrais(@Body FicheFrais ff);

    // requête de suppression
    @POST("frais/deleteFicheFrais")
    Call<FicheFrais> deleteFicheFrais(@Body FicheFrais ff);
}
