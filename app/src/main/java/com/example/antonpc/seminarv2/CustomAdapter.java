package com.example.antonpc.seminarv2;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 Implementiert Adapter, zuständig für die Darstellung/Aufbereitung der Daten die in der ListView angezeigt werden sollen

 Erstelllt von: Anton
 */

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    final private ArrayList<Schueler> mDataSource;

    public CustomAdapter(Context context, ArrayList<Schueler> items){
        mContext = context;
        mDataSource = items;
        if(mDataSource == null){
            Log.d("Adapter", "dataSource ist null");
        }
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){ return mDataSource.size();   }

    @Override
    public Object getItem(int position){return mDataSource.get(position);}

    @Override
    public long getItemId(int position){ return position;}

    @Override
    public View getView(final int positon, View convertView, ViewGroup parent){
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.auswertung_litem_v2, parent, false);

            holder = new ViewHolder();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.übersichtListView_Name);
            holder.bewertungTextView = (TextView) convertView.findViewById(R.id.übersichtListView_Bewertung);
            holder.plusButton = (Button) convertView.findViewById(R.id.übersichtListView_button);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        TextView nameTextView = holder.nameTextView;
        final TextView bewertungTextView = holder.bewertungTextView;
        Button plusButton = holder.plusButton;

        final Schueler schueler = (Schueler) getItem(positon);
        final String nameContent = schueler.getVorname() + " " + schueler.getName();
        String bewertungContent = Integer.toString(schueler.getBewertung());
        nameTextView.setText(nameContent);
        bewertungTextView.setText(bewertungContent);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schueler.setBewertung(schueler.getBewertung() + 1);
                mDataSource.set(positon, schueler);
                bewertungTextView.setText(Integer.toString(schueler.getBewertung()));
            }
        });

        return convertView;
    }

    private static class ViewHolder{
        public TextView nameTextView;
        public TextView bewertungTextView;
        public Button plusButton;
    }
}
