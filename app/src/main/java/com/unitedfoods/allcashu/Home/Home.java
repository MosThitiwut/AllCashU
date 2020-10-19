package com.unitedfoods.allcashu.Home;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;
import com.unitedfoods.allcashu.Customer.CustomerActivity;
import com.unitedfoods.allcashu.Login.LoginActivity;
import com.unitedfoods.allcashu.Prepare.PrepareFragment;
import com.unitedfoods.allcashu.R;
import com.unitedfoods.allcashu.Sale.SaleFragment;
import com.unitedfoods.allcashu.Settings.SettingFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String page = "";
    private static final String TAG = "Home";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().getStringExtra("page") != null)
        {
            switch (getIntent().getStringExtra("page")) {
                case "Prepare":
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentHomeFragment, new PrepareFragment()).addToBackStack(null).commit();
                    break;
                case "Sale":
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentHomeFragment, new SaleFragment()).addToBackStack(null).commit();
            }

        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_logout){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.action_settings){
            this.startActivity(getSupportFragmentManager().beginTransaction().replace(R.id.contentHomeFragment,new SettingFragment()).addToBackStack(null).commit());
        }

        return super.onOptionsItemSelected(item);
    }

    private void startActivity(int commit) {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        if (id == R.id.nav_customer){
            Intent intent = new Intent(Home.this, CustomerActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_sell){

            this.startActivity(getSupportFragmentManager().beginTransaction().replace(R.id.contentHomeFragment, new SaleFragment()).addToBackStack(null).commit());

        }
        else if (id == R.id.nav_edit_sell)
        {

        }
        else if (id == R.id.nav_change){

        }
        else if (id == R.id.nav_withdraw)
        {

        }
        else if (id == R.id.nav_conclude)
        {

        }
        else if (id == R.id.nav_announce){

        }
        else if (id == R.id.nav_stock){

        }
        else if (id == R.id.nav_send){

        }
        else if (id == R.id.nav_to_prepare){

            this.startActivity(getSupportFragmentManager().beginTransaction().replace(R.id.contentHomeFragment,new PrepareFragment()).addToBackStack(null).commit());

        }
        return false;
    }
}
