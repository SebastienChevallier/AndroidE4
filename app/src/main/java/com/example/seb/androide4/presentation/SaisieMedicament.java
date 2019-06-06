package com.example.seb.androide4.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seb.androide4.MainActivity;
import com.example.seb.androide4.R;
import com.example.seb.androide4.mesErreurs.MonException;
import com.example.seb.androide4.metier.*;
import com.example.seb.androide4.service.medicamentService;
import com.example.seb.androide4.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by christian on 29/11/2016.
 */

public class SaisieMedicament extends AppCompatActivity implements View.OnClickListener {


    private EditText txtIdMedicament;
    private EditText txtIdFamille;
    private EditText txtNomCommercial;
    private EditText txtPrix;


    private Button btAjouter;
    private Button btAnnuler;


    private String nomCommercial;
    private float prix;
    private int idMedicament;
    private int idFamille;


    private static final int CODE_REQUEST = 1;  // The request code
    private static final String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On se relie au design
        setContentView(R.layout.saisie_medicament);
        //  on récupère les informations de la fiche de frais
        txtIdMedicament = (EditText) findViewById(R.id.edIdMedicament);
        txtIdFamille = (EditText) findViewById(R.id.edIdFamille);
        txtNomCommercial = (EditText) findViewById(R.id.edNomCommercial);
        txtPrix = (EditText) findViewById(R.id.edPrix);


        btAjouter = (Button) findViewById(R.id.btAjouter);
        btAjouter.setOnClickListener(this);
        btAnnuler = (Button) findViewById(R.id.btAnnuler);
        btAnnuler.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        this.idMedicament = Integer.parseInt(txtIdMedicament.getText().toString());
        this.nomCommercial = txtNomCommercial.getText().toString();
        this.idFamille = Integer.parseInt(txtIdFamille.getText().toString());
        this.prix = Float.parseFloat(txtPrix.getText().toString());


        if (v == btAjouter) {
            // Contrôle  de l'utilisateur
            if (this.idMedicament >= 0) {
                Medicaments unMedic = new Medicaments(idFamille,nomCommercial,prix);
                addMedicament(unMedic);
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("nom", "Insertion réussie !");
                setResult(MainActivity.RESULT_FIRST_USER, intent);
                startActivityForResult(intent, 1);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(SaisieMedicament.this);
                builder.setTitle("Erreur")
                        .setMessage(" Données absentes ")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Bouton cliqué, on affiche
                                Toast.makeText(SaisieMedicament.this, "Il faut saisir des données ", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("nom", "Annulation  !");
            setResult(MainActivity.RESULT_CANCELED, intent);
            startActivityForResult(intent, 1);
        }
    }

    public void addMedicament(Medicaments unMedic) {
        String message = "";

        Retrofit  retrofit = RetrofitClient.getClient(medicamentService.ENDPOINT);
        //
        // On crée un adapteur rest sur l'url
        medicamentService unMedicament = retrofit.create(medicamentService.class);

        try {
            // appel asynchrone
            Call<Medicaments> call = unMedicament.postMedic(unMedic);
            call.enqueue(new Callback<Medicaments>() {
                @Override
                public void onResponse(Call<Medicaments> call, Response<Medicaments> uneReponse) {
                    if (uneReponse.isSuccessful()) {
                        //Recupérer le corps de la reponse que Retrofit s'est chargé de désérialiser à notre place l'aide du convertor Gson
                        if (uneReponse.body() != null) {
                            Toast.makeText(SaisieMedicament.this, "Insertion réussie!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SaisieMedicament.this, "Erreur d'appel!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        //Toast.makeText(MainActivity.this, "Erreur rencontrée", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onResponse =>>> code = " + uneReponse.code());
                    }
                }

                @Override
                public void onFailure(Call<Medicaments> call, Throwable t) {
                    //for getting error in network put here Toast, so get the error on network
                }
            });
        } catch (Exception e) {
            new MonException(e.getMessage(), "Erreur Appel WS Ajout");
        }
    }
}

