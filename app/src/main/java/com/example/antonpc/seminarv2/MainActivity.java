package com.example.antonpc.seminarv2;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    /**
     Organisiert alle Inhalte, Tabs usw.
     Erstellen des TabLayouts ect. zur Darstellung der Tabs

     Erstelllt von: Anton
     Kurzer Test für VCS
     Test2
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Tab1 tab1;
    private Tab2 tab2;
    Controller controller;
    ArrayList<Schueler> schuelerArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 1){
                    tab2.customAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        controller = new Controller(this);
        schuelerArrayList = controller.schuelerArrayList;
        Log.e("Main", Integer.toString(schuelerArrayList.size()));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                tab1.newSchueler();
                //TODO add Schueler: neuer Schüler, Activity SchuelerDetail öffnen mit Standard namen ect.

            }
        });

    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.csv_import){
            Log.d("Main", "csv import starten");
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("");
            startActivityForResult(intent, 2);
        }

        return super.onOptionsItemSelected(item);
    }*/


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    tab1 = new Tab1();
                    Log.d("Main", tab1.tag);
                    tab1.controller = controller;
                    return tab1;
                case 1:
                    tab2 = new Tab2();
                    tab2.controller = controller;
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
    @Override
    protected void onStop(){
        tab1.stopped();
        super.onStop();
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==2){
            if(resultCode==RESULT_OK){
                try{
                Uri pathHolder = data.getData();
                //Log.d("Main", pathHolder);
                controller.readFromCSVFile(pathHolder);
                tab1.customAdapter.notifyDataSetChanged();
            }catch (Exception e){
                    Log.e("Main", "pfad ist null");
                }
            }
        }
    }*/




}
