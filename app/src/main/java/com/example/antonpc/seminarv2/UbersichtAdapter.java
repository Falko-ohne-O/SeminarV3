package com.example.antonpc.seminarv2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 Adapter für einzelne listView Elemente aus Tab2(Übersicht)

 Erstelllt von: Anton
 */

public class UbersichtAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    ArrayList<Schueler> mDataSource;

    public UbersichtAdapter(Context context, ArrayList<Schueler> items){
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){ return mDataSource.size();   }

    @Override
    public Object getItem(int position){return mDataSource.get(position);}

    @Override
    public long getItemId(int position){ return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.layout_ubersicht_item, parent, false);
            holder = new ViewHolder();
            holder.nameText = convertView.findViewById(R.id.ueber_name);
            holder.bewertungText = convertView.findViewById(R.id.ueber_bewertung);
            holder.komm1Text = convertView.findViewById(R.id.ueber_komm1);
            holder.komm2Text = convertView.findViewById(R.id.ueber_komm2);
            holder.komm3Text = convertView.findViewById(R.id.ueber_komm3);
            holder.belegungCheck = convertView.findViewById(R.id.ueber_belegung);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        TextView nameText = holder.nameText;
        TextView bewertungText = holder.bewertungText;
        TextView komm1Text = holder.komm1Text;
        TextView komm2Text = holder.komm2Text;
        TextView komm3Text = holder.komm3Text;
        CheckBox belegungCheck = holder.belegungCheck;

        final Schueler schueler = (Schueler) getItem(position);
        final String nameContent = schueler.getVorname() + " " + schueler.getName();
        String bewertungContent = Integer.toString(schueler.getBewertung());
        String komm1Content = schueler.getKommentar1();
        String komm2Content = schueler.getKommentar2();
        String komm3Content = schueler.getKommentar3();
        boolean belegungContent = schueler.getBelegung();

        nameText.setText(nameContent);
        bewertungText.setText(bewertungContent);
        komm1Text.setText(komm1Content);
        komm2Text.setText(komm2Content);
        komm3Text.setText(komm3Content);
        belegungCheck.setChecked(belegungContent);
        belegungCheck.setEnabled(false);

        return convertView;
    }

    private static class ViewHolder{
        public TextView nameText;
        public TextView bewertungText;
        public TextView komm1Text;
        public TextView komm2Text;
        public TextView komm3Text;
        public CheckBox belegungCheck;
    }


}
