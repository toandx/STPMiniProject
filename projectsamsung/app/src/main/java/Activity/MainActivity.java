package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectsamsung.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.ListViewAdapter;
import Adapter.ListViewAdapter2;
import Model.Object;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ListView List_View , List_View_Data;
    FloatingActionButton Fab ;
    Toolbar Menu_bar ;
    DrawerLayout Draw_Layout ;
    NavigationView Navi_View ;
    String [][] Data_Save;
    List<Object> array1 ;
    List<Object> array;
    ListViewAdapter adapter ;
    ListViewAdapter2 adapter2 ;
    Fragment_Data fragment_data;
    FragmentTransaction ft;
    int col = 0, row = 0 ;
    int dem =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        Log.d("dcm","oke");
        fragment_data = new Fragment_Data();
        ft = getSupportFragmentManager().beginTransaction();

        XuLy();

        if(array1.size() > 0 ) {
            Log.d("dcm","oke");
            ft.replace(R.id.fragment_data, fragment_data);
            ft.commit();
        }
    }

    public void AnhXa() {
        Fab = findViewById(R.id.floating_add);
        Menu_bar = findViewById(R.id.Tool_Bar) ;
        Draw_Layout = findViewById(R.id.draw_layout);
        Navi_View = findViewById(R.id.navig) ;
        List_View = findViewById(R.id.list_chude);
        List_View_Data = findViewById(R.id.list_view_data);
        array = new ArrayList<>();
        array1 = new ArrayList<>();
        adapter = new ListViewAdapter(array,this);
        adapter2= new ListViewAdapter2(array1,MainActivity.this);
        List_View_Data.setAdapter(adapter);
        List_View.setAdapter(adapter2);

    }

    public void XuLy() {
        setSupportActionBar(Menu_bar);
        ActionBarDrawerToggle Action = new ActionBarDrawerToggle(this,Draw_Layout,Menu_bar,R.string.drawer_open,R.string.drawer_close);
        Draw_Layout.addDrawerListener(Action);
        Action.syncState();
        Navi_View.setNavigationItemSelectedListener(this);
        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Repaired_Data.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.del:

                break;
            case R.id.update:

                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.a :
                AddList();
                break;
            case R.id.b:
                break;
        }
        return true;
    }

    public void AddList() {
        Intent intent = new Intent(this,ShowDialog.class);
        Draw_Layout.closeDrawer(GravityCompat.START);
        startActivityForResult(intent,1000);
    }
    public void GetData(String Data) {
        array.add(new Object(Data));
        adapter.notifyDataSetChanged();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null){
            String temp = data.getStringExtra("data");
            if(!temp.isEmpty()){
                array1.add(new Object(temp));
                adapter2.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"ok "+array.size(),Toast.LENGTH_LONG).show();

                if(array1.size() > 0 ) {
                    ft.replace(R.id.fragment_data, fragment_data,"xoa");
                    ft.commit();
                }
            }
        }
    }
    public void EvenListView(){
        List_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
