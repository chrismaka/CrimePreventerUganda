package com.example.chris.crimepreventeruganda;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    //these variables are used in different methods in this class
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    NavigationView navigationView;
    private int currentposition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //if a previous instance of the app exists, find the last item to be view, start activity and set title of actionbar to that, else, set home fragment to be first thing to be view
        if(savedInstanceState != null) {
            currentposition = savedInstanceState.getInt("position");
            setActionBarTitle(currentposition);
        }
            else {
            Fragment fragment = new Home();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            setActionBarTitle(0);

        }
        //get a reference to navigation drawer layout
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //get a reference to navigation view in drawer
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //set the view to its listner which this activity implements
        navigationView.setNavigationItemSelectedListener(this);

        //the drawer toggle which is the listner that works on the action bar of drawer
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawer.setDrawerListener(toggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    //saved the last state of the application before it is destroyed
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", currentposition);
    }
    //works with the toggle to sync the state of the drawer after the app starts
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }
    //works with the toggle to sync the state of the drawer after the app starts
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

//not used
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }
////works with the toggle to sync the state of the drawer after the app starts
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawer.isDrawerOpen(navigationView);
        menu.findItem(R.id.action_share).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    //works with the toggle to sync the state of the drawer after the app starts
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //handling clicks in the navigation view within the navigation drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.home) {
            fragment = new Home();
            //getSupportActionBar().setTitle(item.getTitle());
            setActionBarTitle(0);

            // Handle the camera action
        } else if (id == R.id.report_crime) {
            fragment = new CrimeFragment();
           // getSupportActionBar().setTitle(item.getTitle());
            setActionBarTitle(1);

        } else if (id == R.id.contact_officers) {
            fragment = new OfficerFragment();
            //getSupportActionBar().setTitle(item.getTitle());
            setActionBarTitle(2);

        } else if (id == R.id.station_near) {
            fragment = new StationsFragment();
          // getSupportActionBar().setTitle(item.getTitle());
            setActionBarTitle(3);
        }
        else if(id == R.id.my_way) {
            fragment = new MywayFragment();

            // getSupportActionBar().setTitle(item.getTitle());
            setActionBarTitle(4);
        }
        else if(id == R.id.lost_stuff) {
            fragment = new ReportLostFragment();

            // getSupportActionBar().setTitle(item.getTitle());
            setActionBarTitle(5);
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //method to handle set the title of the action bar
    public void setActionBarTitle(int position){
        String title = null;
        if(position == 0){
            title = "CrimePreventerUganda";
        }
        else if(position == 1){
            title = "Report Crime";
        }
        else if(position == 2){
            title = "Contact Police Officers";
        }
        else if(position == 3){
            title = "Stations Near You";
        }
        else if(position == 4){
            title = "My way";
        }
        else if(position == 5){
            title = "Report Lost Property";
        }
        getSupportActionBar().setTitle(title);
    }
}








