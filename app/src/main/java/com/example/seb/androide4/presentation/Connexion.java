package com.example.seb.androide4.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seb.androide4.MainActivity;
import com.example.seb.androide4.R;
import com.example.seb.androide4.mesErreurs.MonException;
import com.example.seb.androide4.metier.Visiteur;
import com.example.seb.androide4.service.FraisService;
import com.example.seb.androide4.service.RetrofitClient;
import com.google.gson.JsonSyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by christian on 07/11/2017.
 */

public class Connexion extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNom;
    private EditText txtPwd;
    private Button btValider;
    private String nom;
    private String pwd;
    private static final String TAG = "Connexion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On se relie au design
        setContentView(R.layout.connexion);
        // on récupère le nom et le mot de passe
        txtNom = (EditText) findViewById(R.id.edt_nom);
        txtPwd = (EditText) findViewById(R.id.edt_pwd);
        btValider = (Button) findViewById(R.id.bt_valider);
        btValider.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.nom = txtNom.getText().toString();
        this.pwd = txtPwd.getText().toString();
        Visiteur unV = new Visiteur();
        unV.setLogin_visiteur(this.nom);
        unV.setPwd_visiteur(this.pwd);

        Intent intent = new Intent(Connexion.this, MainActivity.class);
        int retour;
        if (v == btValider) {
            // Contrôle  de l'utilisateur
          try {
              controleVisiteur(unV);
              retour = MainActivity.RESULT_OK;
          }
          catch ( MonException e)
          {
              intent.putExtra("nom", "Mauvaise Authntification !!");
              retour = MainActivity.RESULT_CANCELED;
          }
            setResult(retour, intent);
            startActivityForResult(intent, 1);

        }
    }

    public void controleVisiteur(Visiteur unV) throws MonException{
        boolean retour = false;
        Retrofit  retrofit = RetrofitClient.getClient(FraisService.ENDPOINT);
        //
        // On crée un adapteur rest sur l'url
        FraisService unFraisService = retrofit.create(FraisService.class);

        try {

            Call<Visiteur> call = unFraisService.getConnexion(unV);
            // appel asynchrone
            call.enqueue(new Callback<Visiteur>() {

                @Override
                public void onResponse(Call<Visiteur> call, Response<Visiteur> uneReponse) {
                    if (uneReponse.isSuccessful()) {
                        //Recupérer le corps de la reponse que Retrofit s'est chargé de désérialiser à notre place l'aide du convertor Gson
                        if (uneReponse.body() != null) {
                            Toast.makeText(Connexion.this, "Authentification réussie !!!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Connexion.this, "Erreur d'appel!", Toast.LENGTH_LONG).show();

                        }
                    } else {
                        //Toast.makeText(MainActivity.this, "Erreur rencontrée", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onResponse =>>> code = " + uneReponse.code());

                    }
                }

                @Override
                public void onFailure(Call<Visiteur> call, Throwable t) {
                    Toast.makeText(Connexion.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
                }

            });

        } catch (IllegalStateException | JsonSyntaxException exception) {
            new MonException(exception.getMessage(), "Erreur Appel WS Modification");
        } catch (Exception e) {
            new MonException(e.getMessage(), "Erreur Appel WS Modification");
        }
    }


    public int  controleStatique ()
    {
        int retour =0;
        if (nom.equals("AUCHON"))
            if (pwd.equals("secret"))
                retour = MainActivity.RESULT_OK;
             else
                retour = MainActivity.BIND_ABOVE_CLIENT;
         else
            retour = MainActivity.RESULT_CANCELED;

        return retour;
    }


}