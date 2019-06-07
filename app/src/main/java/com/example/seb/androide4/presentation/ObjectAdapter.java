package com.example.seb.androide4.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seb.androide4.R;
import com.example.seb.androide4.metier.Medicaments;

import java.util.List;

public class ObjectAdapter extends ArrayAdapter<Medicaments> {

    private List<Medicaments> mesMedics;
    private Context context;
    private LayoutInflater layoutInflater;

    public ObjectAdapter(Context context, int vg, List<Medicaments> mesMedic){
        super(context,vg,mesMedic);
        this.context = context;
        mesMedics = mesMedic;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.mesMedics.size();
    }

    @Override
    public Medicaments getItem(int position) {
        return this.mesMedics.get(position);
    }
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the current list item
        final Medicaments item = mesMedics.get(position);
        // Get the layout for the list item
        final LinearLayout itemLayout = (LinearLayout)
                LayoutInflater.from(context).inflate(R.layout.header, parent, false);
        // Set the text label as defined in our list item

        TextView txtidMedic = (TextView) itemLayout.findViewById(R.id.txtIdMedic);
        txtidMedic.setText(Integer.toString(item.getId_medicament()));

        TextView txtIdFamille = (TextView) itemLayout.findViewById(R.id.txtIdFamille);
        txtIdFamille.setText(String.valueOf(item.getId_famille()));

        TextView txtNomCommercial = (TextView) itemLayout.findViewById(R.id.txtNomCommercial);
        txtNomCommercial.setText(item.getNom_commercial());

        TextView txtPrix = (TextView) itemLayout.findViewById(R.id.txtPrix);
        txtPrix.setText(String.valueOf(item.getPrix_echantillon()));

        return itemLayout;

    }
}
