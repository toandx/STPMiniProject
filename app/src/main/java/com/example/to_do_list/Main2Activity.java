package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements onClicked {

    RecyclerView recyclerView  ;
    List<Option_Items> array ;
    RecyclerAdapter adapter ;
   // ListView listView ;
   // List<image_2> ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);

      //  listView = findViewById(R.id.a);
        recyclerView = findViewById(R.id.recycle) ;
        array = new ArrayList<>();

        array.add(new Option_Items(R.drawable.ic_add_alert_black_24dp,"Due Date"));
        array.add(new Option_Items(R.drawable.ic_add_alert_black_24dp,"Reminder"));
        array.add(new Option_Items(R.drawable.ic_flag_black_24dp,"Priority"));
        array.add(new Option_Items(R.drawable.ic_star_border_black_24dp,"Highlight"));
        array.add(new Option_Items(R.drawable.ic_check_box_outline_blank_black_24dp,"Completed"));
      //  array.add(new Option_Items(R.drawable.ic_card_travel_black_24dp,"Created"));

        adapter = new RecyclerAdapter(array,this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.onSelected(this);
    }


    @Override
    public void click(int postion) {
        Toast.makeText(getApplicationContext(),array.get(postion).title,Toast.LENGTH_SHORT).show();
    }
}
