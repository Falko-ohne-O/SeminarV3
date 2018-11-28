package com.example.antonpc.seminarv2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 Gleiches Prinzip wie Tab1, keine Listener nötig, nur anzeigen/setzen des Adapters

 Erstelllt von: Anton
 */

public class Tab2 extends Fragment{
    private ListView mListView;
    public Controller controller;
    Context context;
    ArrayList<Schueler> schuelerArrayList;
    UbersichtAdapter customAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);
        mListView = rootView.findViewById(R.id.übersichtListVie);
        context = getActivity();
        schuelerArrayList = controller.schuelerArrayList;
        customAdapter = new UbersichtAdapter(context, schuelerArrayList);
        mListView.setAdapter(customAdapter);



        return rootView;
    }

}
