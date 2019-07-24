package com.example.to_do_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout ;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        textView = findViewById(R.id.text);
        drawerLayout = findViewById(R.id.lay_out);
        NavigationView navigationView = findViewById(R.id.nagvi);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        closeDrawer();
        switch (menuItem.getItemId()){
            case R.id.a :
                Toast.makeText(getApplicationContext(),"a",Toast.LENGTH_SHORT).show();
                break;
            case R.id.b :
                Toast.makeText(getApplicationContext(),"b",Toast.LENGTH_SHORT).show();
                break;
            case R.id.c :
                Toast.makeText(getApplicationContext(),"c",Toast.LENGTH_SHORT).show();
                break;
            case R.id.d :
                Toast.makeText(getApplicationContext(),"d",Toast.LENGTH_SHORT).show();
                break;
            case R.id.e :
                Toast.makeText(getApplicationContext(),"e",Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
    public void closeDrawer()
    {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView() ;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId() ;
        switch (id){
            case R.id.item_a :

                Toast.makeText(getApplicationContext(),"item_a",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.item_b:
                Toast.makeText(getApplicationContext(),"item_b",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.item_c:
                Toast.makeText(getApplicationContext(),"item_c",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            closeDrawer();
        }
        super.onBackPressed();
    }
}
