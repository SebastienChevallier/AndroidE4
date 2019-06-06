package com.example.seb.androide4.presentation;

/**
 * Created by christian on 25/01/2017.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seb.androide4.MainActivity;
import com.example.seb.androide4.R;
import com.example.seb.androide4.mesErreurs.MonException;
import com.example.seb.androide4.metier.*;
import com.example.seb.androide4.service.medicamentService;
import com.example.seb.androide4.service.Outil;
import com.example.seb.androide4.service.RetrofitClient;
import com.example.seb.androide4.R;
import com.example.seb.androide4.metier.Medicaments;
import com.google.gson.JsonSyntaxException;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModifSupMedic extends AppCompatActivity implements View.OnClickListener {


    private EditText txtIdMedicament;
    private EditText txtIdFamille;
    private EditText txtNomCommercial;
    private EditText txtPrix;

    private Button btModif;
    private Button btAnnuler;
    private Button btSupp;

    private String nomCommercial;
    private float prix;
    private int idMedicament;
    private int idFamille;


    private Medicaments unMedic;
    private final int MODIF = 5;

    static final int CODE_REQUEST = 1;  // The request code
    private static final String TAG = "Main Activity";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On se relie au design
        setContentView(R.layout.modif_medic);
        //  on récupère les informations de la fiche de frais
        txtIdMedicament = (EditText) findViewById(R.id.edIdMedicament);
        txtIdFamille = (EditText) findViewById(R.id.edIdFamille);
        txtNomCommercial = (EditText) findViewById(R.id.edNomCommercial);
        txtPrix = (EditText) findViewById(R.id.edPrix);

        btModif = (Button) findViewById(R.id.btModif);
        btModif.setOnClickListener(this);
        btSupp = (Button) findViewById(R.id.btSupp);
        btSupp.setOnClickListener(this);
        btAnnuler = (Button) findViewById(R.id.btAnnuler);
        btAnnuler.setOnClickListener(this);

        // transfet de données entre fenêtres
        unMedic = (Medicaments) getIntent().getExtras().getSerializable("unMedic");
        idMedicament = unMedic.getId_medicament();
        txtIdMedicament.setText(String.valueOf(idMedicament));
        txtIdFamille.setText(String.valueOf(unMedic.getId_famille()));
        txtNomCommercial.setText(String.valueOf(unMedic.getNom_commercial()));
        txtPrix.setText(String.valueOf(unMedic.getPrix_echantillon()));

          }


    @Override
    public void onClick(View v) {

        this.idMedicament = Integer.parseInt(txtIdMedicament.getText().toString());
        this.nomCommercial = txtNomCommercial.getText().toString();
        this.idFamille = Integer.parseInt(txtIdFamille.getText().toString());
        this.prix = Float.parseFloat(txtPrix.getText().toString());


        if (v == btModif) {
            // Contrôle  de l'utilisateur
            if (this.idMedicament >= 0) {
                // la fiche est déjà crée
                // il faut l'initialiser avec les nouvelles données
                unMedic.initialise(idMedicament,idFamille,nomCommercial,prix);
                modification(unMedic);
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("nom", "Modification réussie !");
                setResult(MainActivity.RESULT_FIRST_USER, intent);
                startActivityForResult(intent, 1);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(ModifSupMedic.this);
                builder.setTitle("Erreur")
                        .setMessage(" Données absentes ")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Bouton cliqué, on affiche
                                Toast.makeText(ModifSupMedic.this, "Il faut saisir des données ", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        }

        else

        if (v == btSupp) {
            // Contrôle  de l'utilisateur
            if (this.idMedicament >= 0) {
                // la fiche est déjà crée
                // il faut l'initialiser avec les nouvelles données
                unMedic.initialise(idMedicament,idFamille,nomCommercial,prix);
                suppression(unMedic);
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("nom", "Suppression réussie !");
                setResult(MainActivity.RESULT_FIRST_USER, intent);
                startActivityForResult(intent, 1);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(ModifSupMedic.this);
                builder.setTitle("Erreur")
                        .setMessage(" Données absentes ")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Bouton cliqué, on affiche
                                Toast.makeText(ModifSupMedic.this, "Il faut saisir des données ", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        }

        else
        {
            Intent intent = new Intent(this, MainActivity.class);
            setResult(MainActivity.RESULT_CANCELED, intent);
            startActivityForResult(intent, 1);
        }
    }




    public void modification(Medicaments unMedic) {

        Retrofit  retrofit = RetrofitClient.getClient(medicamentService.ENDPOINT);
        //
        // On crée un adapteur rest sur l'url
        medicamentService unMedicamentService = retrofit.create(medicamentService.class);

        try {
            // appel asynchrone
         Call<Medicaments> call = unMedicamentService.updateMedic(unMedic);
            call.enqueue(new Callback<Medicaments>() {
                @Override
                public void onResponse(Call<Medicaments> call, Response<Medicaments> uneReponse) {
                    if (uneReponse.isSuccessful()) {
                        //Recupérer le corps de la reponse que Retrofit s'est chargé de désérialiser à notre place l'aide du convertor Gson
                        if (uneReponse.body() != null) {
                            Toast.makeText(ModifSupMedic.this, "Modification réussie!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ModifSupMedic.this, "Erreur d'appel!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        //Toast.makeText(MainActivity.this, "Erreur rencontrée", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onResponse =>>> code = " + uneReponse.code());
                    }
                }
                @Override
                public void onFailure(Call<Medicaments> call, Throwable t) {
                    Toast.makeText(ModifSupMedic.this, "Erreur d'appel modification !", Toast.LENGTH_LONG).show();
                }
            });
        }
        catch (IllegalStateException | JsonSyntaxException exception){
            new MonException(exception.getMessage(), "Erreur Appel WS Modification");
        }

        catch (Exception e) {
            new MonException(e.getMessage(), "Erreur Appel WS Modification");
        }

    }


    public void suppression(Medicaments unMedic) {

        Retrofit  retrofit = RetrofitClient.getClient(medicamentService.ENDPOINT);
        //
        // On crée un adapteur rest sur l'url
        medicamentService unMedicamentService = retrofit.create(medicamentService.class);

        try {
            // appel asynchrone
            Call<Medicaments> call = unMedicamentService.deleteMedic(unMedic);
            call.enqueue(new Callback<Medicaments>() {
                @Override
                public void onResponse(Call<Medicaments> call, Response<Medicaments> uneReponse) {
                    if (uneReponse.isSuccessful()) {
                        //Recupérer le corps de la reponse que Retrofit s'est chargé de désérialiser à notre place l'aide du convertor Gson
                        if (uneReponse.body() != null) {
                            Toast.makeText(ModifSupMedic.this, "Suppression réussie!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ModifSupMedic.this, "Erreur d'appel!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        //Toast.makeText(MainActivity.this, "Erreur rencontrée", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onResponse =>>> code = " + uneReponse.code());
                    }
                }
                @Override
                public void onFailure(Call<Medicaments> call, Throwable t) {
                    Toast.makeText(ModifSupMedic.this, "Erreur d'appel suppression !", Toast.LENGTH_LONG).show();
                }
            });
        }
        catch (IllegalStateException | JsonSyntaxException exception){
            new MonException(exception.getMessage(), "Erreur Appel WS Suppression");
        }

        catch (Exception e) {
            new MonException(e.getMessage(), "Erreur Appel WS SZuppression");
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }
}
