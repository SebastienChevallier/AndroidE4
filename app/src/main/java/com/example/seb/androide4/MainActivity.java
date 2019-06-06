package com.example.seb.androide4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.seb.androide4.presentation.AfficheMedicament;
import com.example.seb.androide4.presentation.Connexion;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtMessage;
    private TextView txtInfo;
    private Button btAuthentifier;
    private Button btAppelWS;
    private Button btAjout;
    private boolean appel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage = (TextView) findViewById(R.id.textMessage);
        txtInfo = (TextView) findViewById(R.id.textInfo);
        txtInfo.setText("Gestion des Medicaments");
        txtMessage.setText("Vos medicaments en ligne sur votre smartphone");
        btAuthentifier = (Button) findViewById(R.id.bt_authentifier);
        btAuthentifier.setOnClickListener(this);
        btAppelWS = (Button) findViewById(R.id.btWebService);
        btAppelWS.setOnClickListener(this);
        btAjout = (Button) findViewById(R.id.btAjout);
        btAjout.setOnClickListener(this);

    }

    public void onClick(View v) {
        String information = "";
        if (v == btAuthentifier) {
            Intent intent = new Intent(MainActivity.this, Connexion.class);
            startActivityForResult(intent, 1);
        }
        else if (v == btAppelWS) {

            Intent intent = new Intent(MainActivity.this, AfficheMedicament.class);
            startActivityForResult(intent,1);
        }
        else
        if (v == btAjout) {// WebServer Request URL
            appel=true;
            Intent intent = new Intent(MainActivity.this, SaisieMedicament.class);
            startActivityForResult(intent,1);

        }

        else {
            // display error
            String erreur = "erreur!";
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            switch (resultCode) {
                case MainActivity.RESULT_OK: {
                    String information = (String) data.getExtras().get("nom");
                    information += " " + (String) data.getExtras().get("prenom");
                    txtMessage.setText(information);
                    // on rend l'appel au Web service accessible
                    btAppelWS = (Button) findViewById(R.id.btWebService);
                    btAppelWS.setOnClickListener(this);
                    btAjout = (Button) findViewById(R.id.btAjout);
                    btAjout.setOnClickListener(this);
                    break;
                }
                case  MainActivity.RESULT_CANCELED   :{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Erreur")
                            .setMessage((String) data.getExtras().get("nom"))
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //Bouton cliqué, on affiche
                                    //  Toast.makeText(MainActivity.this, "Il faut saisir des données ", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                    break;
                }

                case  MainActivity.BIND_ABOVE_CLIENT   :{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Erreur")
                            .setMessage((String) data.getExtras().get("nom"))
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //Bouton cliqué, on affiche
                                    //  Toast.makeText(MainActivity.this, "Il faut saisir des données ", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                    break;
                }
                case MainActivity.RESULT_FIRST_USER: {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Résultat Transaction")
                            .setMessage((String) data.getExtras().get("nom"))
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //Bouton cliqué, on affiche
                                    //  Toast.makeText(MainActivity.this, "Il faut saisir des données ", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }

            }
        }

    }// onActivityResult
}
