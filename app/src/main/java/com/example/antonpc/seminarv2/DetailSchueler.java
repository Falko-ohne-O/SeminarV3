package com.example.antonpc.seminarv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;


/**
 Diese Activity ist für die Darstellung eines einzelnen Schülers zuständig
 Sämtliche Attribute des Schülers lassen sich bearbeiten/werden angezeigt

 Erstelllt von: Anton
 */

public class DetailSchueler extends AppCompatActivity{

    Schueler schueler;
    int position;
    boolean finishedCancelled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_ac_v2);
        if(this.getIntent().getSerializableExtra("Schüler") != null) {
            schueler = (Schueler) this.getIntent().getSerializableExtra("Schüler");
            position = this.getIntent().getIntExtra("Position", -1);
            String title = schueler.getVorname() + " " + schueler.getName();
            Toolbar toolbar = findViewById(R.id.detail_toolbar);
            setSupportActionBar(toolbar);
            toolbar.setTitle(title);


            //Buttons, TextViews ect. erstellen
            EditText editVorname = findViewById(R.id.editText_vorname);
            EditText editNachname = findViewById(R.id.editText_nachname);
            Button buttonPlus = findViewById(R.id.button_plus);
            Button buttonMinus = findViewById(R.id.button_minus);
            Button buttonSave = findViewById(R.id.button_save);
            Button buttonDelete = findViewById(R.id.button_delete);
            final EditText editKommentar1 = findViewById(R.id.editText_kommentar1);
            final EditText editKommentar2 = findViewById(R.id.editText_kommentar2);
            final EditText editKommentar3 = findViewById(R.id.editText_kommentar3);
            CheckBox checkBelegung = findViewById(R.id.checkBox_belegung);
            final TextView textBewertung = findViewById(R.id.textView_bewertung);

            //Texte setzen
            editVorname.setText(schueler.getVorname());
            editNachname.setText(schueler.getName());
            if(!schueler.getKommentar1().isEmpty()){editKommentar1.setText(schueler.getKommentar1());}
            if(!schueler.getKommentar2().isEmpty()){editKommentar2.setText(schueler.getKommentar2());}
            if(!schueler.getKommentar3().isEmpty()){editKommentar3.setText(schueler.getKommentar3());}

            checkBelegung.setChecked(schueler.getBelegung());
            textBewertung.setText(String.format("%d", schueler.getBewertung()));

            //Listener setzen
            editVorname.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    schueler.setVorname(editable.toString());
                }
            });

            editNachname.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    schueler.setName(editable.toString());
                }
            });

            editKommentar1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    schueler.setKommentar1(editable.toString());
                }
            });

            editKommentar2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    schueler.setKommentar2(editable.toString());
                }
            });

            editKommentar3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    schueler.setKommentar3(editable.toString());
                }
            });

            checkBelegung.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    schueler.setBelegung(b);
                    Log.d("deail", schueler.toString());
                }
            });

            buttonPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = schueler.getBewertung();
                    schueler.setBewertung(i + 1);
                    textBewertung.setText(Integer.toString(schueler.getBewertung()));
                    Log.d("deail", schueler.toString());

                }
            });

            buttonMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = schueler.getBewertung();
                    schueler.setBewertung(i - 1);
                    textBewertung.setText(Integer.toString(schueler.getBewertung()));
                }
            });

            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finishedCancelled = false;
                    finish();
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finishedCancelled = true;
                    finish();
                }
            });

        }
        else{
            Log.e("DetailSchueler", "Schüler ist null");
        }
    }

    @Override
    public void finish(){
        Intent giveSchueler = new Intent();
        giveSchueler.putExtra("Schüler", schueler);
        giveSchueler.putExtra("Position", position);
        if(!finishedCancelled) {
            setResult(Activity.RESULT_OK, giveSchueler);
            super.finish();
        }else{
            setResult(Activity.RESULT_CANCELED, giveSchueler);
            super.finish();
        }
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
