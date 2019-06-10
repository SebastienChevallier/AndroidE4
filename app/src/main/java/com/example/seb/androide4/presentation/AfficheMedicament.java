package com.example.seb.androide4.presentation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.util.Log;

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

    private static final String DEFAULT_QUERY = "Android";
    private static final String TAG = "Main Activity";
    private static final String NO_ERROR_VALUE = "0";
    private Button btRetour;
    private EditText txtIdMedic;
    private EditText txtIdFamille;
    private EditText txtNomCommercial;
    private EditText txtPrix;
    private EditText txtNom;
    private List<Medicaments> mesMedics = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On se relie au design
        setContentView(R.layout.affichemedicament);
        loadMedic();

    }

    @Override
    protected void onStart() {

        String information = "";
        super.onStart();
    }


    public void loadMedic(){
        Retrofit  retrofit = RetrofitClient.getClient(medicamentService.ENDPOINT);

        // On crée un adapteur rest sur l'url
        medicamentService unMedicamentService = retrofit.create(medicamentService.class);



        Call<List<Medicaments>> call = unMedicamentService.getMedic();
        // appel asynchrone
        call.enqueue(new Callback<List<Medicaments>>() {
            @Override
            public void onResponse(Call<List<Medicaments>> call, Response<List<Medicaments>> uneReponse) {
                if (uneReponse.isSuccessful()) {
                    //Recupérer le corps de la reponse que Retrofit s'est chargé de désérialiser à notre place l'aide du convertor Gson
                    if (uneReponse.body() != null) {
                        mesMedics = uneReponse.body();
                        affiche(mesMedics);
                    } else {
                        Toast.makeText(AfficheMedicament.this, "Erreur d'appel!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(AfficheMedicament.this, "Erreur rencontrée", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onResponse =>>> code = " + uneReponse.code());

                }
            }

            @Override
            public void onFailure(Call<List<Medicaments>> call, Throwable t) {
                Toast.makeText(AfficheMedicament.this, t.toString(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
                call.cancel();
            }

        });
    }


    protected void affiche(List<Medicaments> result){
        ListView listViewData = (ListView) findViewById(R.id.ListViewMedic);
        String data = "";
        TextView txtnom = (TextView) findViewById(R.id.txtnom);
        ProgressDialog Dialog = new ProgressDialog(AfficheMedicament.this);
        String Error = null;
        mesMedics = result;
        Dialog.dismiss();

        if(Error != null){
            txtnom.setText("Output : " + Error);
            Toast.makeText(AfficheMedicament.this, "ici l'erreur ", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(AfficheMedicament.this, "pas d'erreur", Toast.LENGTH_LONG).show();
            txtnom.setText("Liste des medicaments : ");
            if(result != null){
                listViewData.clearAnimation();
                LayoutInflater inflater = getLayoutInflater();
                ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header, listViewData, false);
                if(listViewData.getHeaderViewsCount() == 0)
                    listViewData.addHeaderView(header, null, false);
                final ObjectAdapter adapter = new ObjectAdapter(AfficheMedicament.this, android.R.layout.simple_list_item_1, mesMedics);
                listViewData.setAdapter(adapter);

                listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Medicaments unMedoc = mesMedics.get(position - 1); // on récupère l'objet selectionné
                        // action
                        Intent intent = new Intent(AfficheMedicament.this, ModifSupMedic.class);
                        intent.putExtra("unMedic", unMedoc);
                        setResult(MainActivity.RESULT_FIRST_USER, intent);
                        startActivityForResult(intent, 1);

                    }
                });
            }
        }
    }
}
