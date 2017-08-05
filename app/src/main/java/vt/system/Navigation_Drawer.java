package vt.system;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import vt.system.Activity.ActivityConsulta;
import vt.system.Activity.ActivityScanner;
import vt.system.Activity.FragmentConsulta;
import vt.system.Activity.FragmentRegistro;
import vt.system.Activity.ActivityRegistro;
import vt.system.Activity.Volley;
import vt.system.Activity.prueba;

public class Navigation_Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentRegistro.OnFragmentInteractionListener,
        FragmentConsulta.OnFragmentInteractionListener{

    FloatingActionButton fab_plus,fab_1,fab_2;
    Animation FabOpen,FabClose,FabRClockwise,FabRanticlockwise;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.navigation__drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_plus = (FloatingActionButton)findViewById(R.id.fab_plus);
        fab_1 = (FloatingActionButton)findViewById(R.id.fab_1);
        fab_2= (FloatingActionButton)findViewById(R.id.fab_2);
        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRanticlockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen){
                    fab_2.startAnimation(FabClose);
                    fab_1.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRanticlockwise);
                    fab_1.setClickable(false);
                    fab_2.setClickable(false);
                    isOpen = false;
                }
                else {
                    fab_2.startAnimation(FabOpen);
                    fab_1.startAnimation(FabOpen);
                    fab_plus.startAnimation(FabRClockwise);
                    fab_1.setClickable(true);
                    fab_2.setClickable(true);
                    isOpen = true;

                }
            }
        });

        fab_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation__drawer, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        android.app.FragmentManager fm = getFragmentManager();

        int id = item.getItemId();

        boolean isStartup = true;
        if(isStartup) {
            ((FrameLayout) findViewById(R.id.content_frame)).removeAllViews();
            isStartup = false;
        }

        boolean FragmentTransaction = false;
        Fragment fragment = null;

        if (id == R.id.nav_camera) {

            Intent mainIntent = new Intent().setClass(
                    Navigation_Drawer.this, ActivityRegistro.class);
            startActivity(mainIntent);

        } else if (id == R.id.nav_gallery) {

            Intent mainIntent = new Intent().setClass(
                    Navigation_Drawer.this, ActivityConsulta.class);
            startActivity(mainIntent);

        } else if (id == R.id.nav_slideshow) {
            Intent mainIntent = new Intent().setClass(
                    Navigation_Drawer.this, ActivityScanner.class);
            startActivity(mainIntent);


        } else if (id == R.id.nav_manage) {
            Intent mainIntent = new Intent().setClass(
                    Navigation_Drawer.this, Volley.class);
            startActivity(mainIntent);

        } else if (id == R.id.nav_share) {
            fragment = new FragmentConsulta();
            FragmentTransaction = true;
        } else if (id == R.id.nav_send) {

        }

        if(FragmentTransaction){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_navigation_drawer, fragment)
                    .commit();

            item.setCheckable(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
