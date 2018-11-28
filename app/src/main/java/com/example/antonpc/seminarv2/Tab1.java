package com.example.antonpc.seminarv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;


/**
    Darstellen aller Inhalte von Tab1(Auswerten)
 setzen der Listener usw.
 Aufrufen des CustomAdapters zur Aufbereitung der anzuzeigenden Daten

 Erstelllt von: Anton
 */

public class Tab1 extends Fragment {
    private ListView mListView;
    public Controller controller;
    private File csvFile;
    public final String tag = "Tab1";
    ArrayList<Schueler> schuelerArrayList;
    CustomAdapter customAdapter;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);
        mListView = rootView.findViewById(R.id.auswertung_listView);
        /*try{
            createController();
        } catch (Exception e){
            e.printStackTrace();
        }*/
        Log.d(tag, "created");
        schuelerArrayList = controller.schuelerArrayList;
        context = getActivity();
        customAdapter = new CustomAdapter(context, schuelerArrayList);
        mListView.setAdapter(customAdapter);
        mListView.setLongClickable(false);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                startDetail(position);
            }
        });

        /*mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteSchueler(i);
                return true;
            }
        });*/








        //String[] listItems = {"Schüler1", "Schüler2", "Schüler3", "Schüler4", "Schüler5", "Schüler6", "Schüler7"}; //Testdata





        /*TextView textView = (TextView) rootView.findViewById(R.id.übersichttextview);
        textView.setText(controller.testString);*/


        return rootView;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent schueler){
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                Schueler editedSchueler = (Schueler) schueler.getSerializableExtra("Schüler");
                int position = schueler.getIntExtra("Position", -1);
                schuelerArrayList.set(position, editedSchueler);
                customAdapter.notifyDataSetChanged();
            }else if(resultCode == Activity.RESULT_CANCELED){
                int posiion = schueler.getIntExtra("Position", -1);
                deleteSchueler(posiion);
            }
        }
    }




    public void createController()throws Exception{
        controller = new Controller(getActivity());
    }

    public void newSchueler(){
        controller.addSchueler();
        startDetail(schuelerArrayList.size()-1);
    }

    public void startDetail(int position){
            Schueler selectedSchueler = schuelerArrayList.get(position);
            Intent detailIntent = new Intent(context, DetailSchueler.class);
            detailIntent.putExtra("Schüler", selectedSchueler);
            detailIntent.putExtra("Position", position);
            startActivityForResult(detailIntent, 1);

    }

    public void deleteSchueler(int position){
        controller.removeSchueler(position);
        customAdapter.notifyDataSetChanged();
    }

    public void stopped(){
        try{
            Log.d(tag, "app stopped");
            controller.writeCSV();
        }catch(Exception e){
            Log.e(tag, e.toString());
        }
    }

    public void started(){
        try {
            controller.readCSV();
        }catch (Exception e){
            Log.e(tag, e.toString());
        }
    }
}