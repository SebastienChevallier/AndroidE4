package com.example.seb.androide4.presentation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;

import java.util.List;

import com.example.seb.androide4.MainActivity;
import com.example.seb.androide4.R;
import com.example.seb.androide4.metier.Medicaments;
import com.example.seb.androide4.service.medicamentService;
import com.example.seb.androide4.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AfficheMedicament extends AppCompatActivity {

    private EditText txtIdMedic;
    private EditText txtIdFamille;
    private EditText txtNomCommercial;
    private EditText txtPrix;
    private EditText txtNom;
    List<Medicaments> mesMedics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On se relie au design
        setContentView(R.layout.connexion);

        Retrofit  retrofit = RetrofitClient.getClient(medicamentService.ENDPOINT);
        //
        // On crée un adapteur rest sur l'url
        medicamentService unMedicamentService = retrofit.create(medicamentService.class);


            Call<List<Medicaments>> call = unMedicamentService.getMedic();

            // appel asynchrone
            call.enqueue(new Callback<List<Medicaments>>() {
                int retour;
                @Override
                public void onResponse(Call<List<Medicaments>> call, Response<List<Medicaments>> uneReponse) {
                    if (uneReponse.isSuccessful()) {
                        //Recupérer le corps de la reponse que Retrofit s'est chargé de désérialiser à notre place l'aide du convertor Gson
                        if (uneReponse.body() != null) {
                            mesMedics = uneReponse.body();
                            affiche(mesMedics);
                        } else {
                            Toast.makeText(AfficheMedicament.this, "Erreur d'appel!", Toast.LENGTH_LONG).show();
                            retour = MainActivity.RESULT_CANCELED;
                        }
                    } else {
                        Toast.makeText(AfficheMedicament.this, "Erreur rencontrée", Toast.LENGTH_LONG).show();
                        //Log.d(TAG, "onResponse =>>> code = " + uneReponse.code());

                    }
                }

                @Override
                public void onFailure(Call<List<Medicaments>> call, Throwable t) {
                    System.out.println("fail");
                    t.printStackTrace();
                    call.cancel();
                }

            });

    }

    protected void affiche(List<Medicaments> result){
        ListView listViewData = (ListView) findViewById(R.id.ListViewMedic);
        String data = "";
        TextView txtMessage = (TextView) findViewById(R.id.txtnom);
        ProgressDialog Dialog = new ProgressDialog(AfficheMedicament.this);
        String Error = null;
        mesMedics = result;
        Dialog.dismiss();

        if(Error != null){
            txtMessage.setText("Output : " + Error);
        }else{
            txtMessage.setText("Liste des medicaments");
            if(result != null){
                listViewData.clearAnimation();
                LayoutInflater inflater = getLayoutInflater();
                ViewGroup header = (ViewGroup) inflater.inflate(R.layout.affichemedicament, listViewData, false);
                if(listViewData.getHeaderViewsCount() == 0)listViewData.addHeaderView(header, null, false);
                final ObjectAdapter adapter = new ObjectAdapter(AfficheMedicament.this, android.R.layout.simple_list_item_1, mesMedics);
                listViewData.setAdapter(adapter);
            }
        }
    }
}
