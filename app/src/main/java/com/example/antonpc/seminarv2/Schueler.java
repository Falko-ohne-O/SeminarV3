package com.example.antonpc.seminarv2;

import android.util.Log;

import java.io.Serializable;

/**
  Klasse einees Schülers
 setzten/geben der einzelnen Attribute
 Serilizable--> Java Beans(Struktur zum Speichern von Daten)

 Erstelllt von: Felix
 */

public class Schueler implements Serializable {

    private String Name = "";
    private String Vorname = "";
    private int Bewertung = 0;
    private boolean Belegung;
    private String Kommentar1 = "";
    private String Kommentar2 = "";
    private String Kommentar3 = "";

    public String getName(){return Name;}
    public String getVorname(){return Vorname;}
    public int getBewertung(){return Bewertung;}
    public boolean getBelegung(){return Belegung;}
    public String getKommentar1(){return Kommentar1;}
    public String getKommentar2(){return Kommentar2;}
    public String getKommentar3(){return Kommentar3;}


    public void setName(String Name){this.Name = Name;
        Log.d("Schüler", "Name gespeichert");}
    public void setVorname(String Vorname){this.Vorname = Vorname;}
    public void setBewertung(int Bewertung){this.Bewertung = Bewertung;}
    public void setBelegung(boolean Belegung){this.Belegung = Belegung;}
    public void setKommentar1(String Kommentar){this.Kommentar1 = Kommentar;}
    public void setKommentar2(String Kommentar){this.Kommentar2 = Kommentar;}
    public void setKommentar3(String Kommentar){this.Kommentar3 = Kommentar;}

    @Override
    public String toString() {
        return String.format("Schüler[Name = %s, vorname = %s, Bewertung = %d]", Name, Vorname, Bewertung);
    }
}

