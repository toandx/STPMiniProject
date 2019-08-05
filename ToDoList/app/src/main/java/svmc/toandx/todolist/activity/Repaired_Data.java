package svmc.toandx.todolist.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import svmc.toandx.todolist.R;
import svmc.toandx.todolist.adapter.MyExpandableListView;
import svmc.toandx.todolist.model.Object1;

public class Repaired_Data extends AppCompatActivity {

    NestedScrollView nestedScrollView ;
    FloatingActionButton Bt_Fab ;
    List<Object1> listHeader ;
    HashMap<Object1,List<Object1>> listChild;
    CoordinatorLayout coordinatorLayout ;
    ExpandableListView expandableListView ;
    MyExpandableListView adapter;
    Toolbar toolbar ;
    EditText editText ;
    TextView textView ;
    CollapsingToolbarLayout collapsingToolbarLayout ;
    AppBarLayout appBarLayout ;
    ActionBar actionBar ;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repaired_data);
        coordinatorLayout = findViewById(R.id.coordi) ;
        toolbar = findViewById(R.id.toolBar) ;
        editText = findViewById(R.id.edtData) ;
        textView = findViewById(R.id.tv_View);
        nestedScrollView = findViewById(R.id.nest);
        collapsingToolbarLayout = findViewById(R.id.collapsing) ;
        appBarLayout = findViewById(R.id.app_bar);
        //  recyclerView1 = findViewById(R.id.recycler1);
        expandableListView = findViewById(R.id.expand_menu) ;
        setSupportActionBar(toolbar);
        Bt_Fab=(FloatingActionButton) findViewById(R.id.floating);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        listHeader = new ArrayList<>();
        listHeader.add(new Object1(R.drawable.ic_format_list_bulleted_black_24dp,"Notes",""));
        listHeader.add(new Object1(R.drawable.ic_format_list_bulleted_black_24dp,"Due Date","No reminder set")) ;
        listHeader.add(new Object1(R.drawable.ic_flag_black_24dp,"Priority","None")) ;
        listHeader.add(new Object1(R.drawable.ic_star_border_black_24dp,"Hightlight","Make this task stand out in the list view")) ;
        listHeader.add(new Object1(R.drawable.ic_check_box_outline_blank_black_24dp,"Completed",""));

        List<Object1>Remind = new ArrayList<>() ;
        listChild = new HashMap<Object1,List<Object1>>();
        Remind.add(new Object1(R.drawable.ic_notifications_active_black_24dp,"Reminder","Don't remind me")) ;
        Remind.add(new Object1(R.drawable.ic_notifications_active_black_24dp,"Reminder Type","Alarm")) ;
        Remind.add(new Object1(R.drawable.ic_refresh_black_24dp,"Repeat","Daily")) ;
        Remind.add(new Object1(R.drawable.ic_update_black_24dp,"Repeat Until",""));


        listChild.put(listHeader.get(0),new ArrayList<Object1>()) ;
        listChild.put(listHeader.get(1),Remind);
        listChild.put(listHeader.get(2),new ArrayList<Object1>()) ;
        listChild.put(listHeader.get(3),new ArrayList<Object1>()) ;
        listChild.put(listHeader.get(4),new ArrayList<Object1>()) ;
        adapter = new MyExpandableListView(this,listHeader,listChild);

        LinearLayoutManager layoutManager1= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        expandableListView.setAdapter(adapter);
        Bt_Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("title",editText.getText().toString()) ;
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}
