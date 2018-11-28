package com.example.antonpc.seminarv2;


import android.content.Context;

import android.util.Log;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;


import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;



/**
 Klasse Controller ist für die Kontroller der Liste mit Schülern zutändig
 Liest/Schreibt außderdem die Schüler aus/in CSV-Dateien im Internen Speicher

 Erstelllt von: Felix

 Verwendete Bibliothek: OpenCSV
 */

public class Controller {
    ArrayList<Schueler> schuelerArrayList = new ArrayList<>();
    public Context context;
    private String tag = "Controller";
    private String csvFile = "test.csv";


    public Controller(Context context){
        try {
            this.context = context;
            //createFile();
            readCSV();
            //testMethod();
            //writeCSV();

        }catch (Exception e){
            Log.e(tag, "controller nicht erstellt(context null ect.)");
        }



    }


    /*public void createFile(){
        try{
            FileOutputStream fileout = context.openFileOutput(csvFile, Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileout);
            outputStreamWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    public void writeCSV() throws Exception {
        FileOutputStream fileout = context.openFileOutput(csvFile, Context.MODE_PRIVATE);
        try(CSVWriter writer = new CSVWriter(new OutputStreamWriter(fileout))) {
            for (Schueler schueler : schuelerArrayList) {
                String Name = schueler.getName();
                String Vorname = schueler.getVorname();
                String Bewertung = Integer.toString(schueler.getBewertung());
                String Belegung = String.valueOf(schueler.getBelegung());
                String Kommentar1 = schueler.getKommentar1();
                String Kommentar2 = schueler.getKommentar2();
                String Kommentar3 = schueler.getKommentar3();
                String[] inhalt = {Name, Vorname, Bewertung, Belegung, Kommentar1, Kommentar2, Kommentar3};
                writer.writeNext(inhalt);
                String message = Name + " - Schüler geschrieben?";
                Log.d(tag, message);
            }
        }catch (IOException e){
            Log.e(tag, "schreiben error");
        }
    }

    public void readCSV() throws Exception{
        FileInputStream filein = context.openFileInput(csvFile);
        try(CSVReader reader = new CSVReader(new InputStreamReader(filein), ',', '"',0)){
            List<String[]> allRows = reader.readAll();
            //System.out.println("Anzahl an reihen = " + allRows.size());
            int currentRow = 0;
            for (String[] row : allRows) {
                try {
                    //System.out.println("Länge der Reihe = " + row.length);
                    String Name = row[0];
                    String Vorname = row[1];
                    int Bewertung = Integer.parseInt(row[2]);
                    boolean Belegung = row[3].equals("true");
                    String Kommentar1 = row[4];
                    String Kommentar2 = row[5];
                    String Kommentar3 = row[6];
                    Schueler schueler = new Schueler();
                    schueler.setName(Name);
                    schueler.setVorname(Vorname);
                    schueler.setBewertung(Bewertung);
                    schueler.setBelegung(Belegung);
                    schueler.setKommentar1(Kommentar1);
                    schueler.setKommentar2(Kommentar2);
                    schueler.setKommentar3(Kommentar3);

                    schuelerArrayList.add(schueler);
                    currentRow ++;
                }catch(ArrayIndexOutOfBoundsException e1){
                    System.out.println("Zeile " + (currentRow + 1) + " fehlerhaft");
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addSchueler(){
        Schueler schueler = new Schueler();
        schuelerArrayList.add(schueler);
    }


    public void removeSchueler(int position){
        schuelerArrayList.remove(position);
    }

    public void firstSchueler(){
        Schueler schueler = new Schueler();
        schueler.setName("");
        schueler.setName("");
        schuelerArrayList.add(schueler);
    }

    /*public void readFromCSVFile(Uri uri){
        File uriFile = new File(uri.getPath());
        //File file = new File(Environment.getExternalStorageDirectory(), uri.getPath());
        try(CSVReader reader = new CSVReader(new FileReader(), ';')){
            List<String[]> allRows = reader.readAll();
            for(String[] row : allRows){
                String Name = row[0];
                String Vorname = row[1];
                Log.d(tag, Name + Vorname);
                Schueler schueler = new Schueler();
                schueler.setVorname(Vorname);
                schueler.setName(Name);
                schuelerArrayList.add(schueler);
            }
        }catch (IOException e){
            Log.e(tag, e.toString());
        }

    }*/



}
