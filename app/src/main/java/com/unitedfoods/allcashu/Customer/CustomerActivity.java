package com.unitedfoods.allcashu.Customer;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.unitedfoods.allcashu.Home.Home;
import com.unitedfoods.allcashu.R;

public class CustomerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        getSupportFragmentManager().beginTransaction().replace(R.id.contentCustomerFragment,new CustomerFragment()).addToBackStack(null).commit();
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
        getMenuInflater().inflate(R.menu.customer, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                CustomerFragment.customerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_add_customer)
        {
            Intent intent = new Intent(this, AddCustomerActivity.class);
            intent.putExtra("SalareaID",CustomerFragment.area);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_customer){
//            Intent intent = new Intent(Home.this, CustomerActivity.class);
//            startActivity(intent);
        }
        else if (id == R.id.nav_sell){
            Intent intent = new Intent(this,Home.class);
            intent.putExtra("page","Sale");
            startActivity(intent);
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

            Intent intent = new Intent(this,Home.class);
            intent.putExtra("page","Prepare");
            startActivity(intent);

        }
        return false;
    }


}
