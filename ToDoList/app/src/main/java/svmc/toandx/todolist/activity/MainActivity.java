package svmc.toandx.todolist.activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import svmc.toandx.todolist.adapter.SubListAdapter;
import svmc.toandx.todolist.adapter.TaskAdapter;
import svmc.toandx.todolist.database.CustomDataBase;
import svmc.toandx.todolist.model.SubList;
import svmc.toandx.todolist.model.Task;
import svmc.toandx.todolist.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText editText ;
    ImageButton imageButton ;
    FloatingActionButton floatingActionButton ;
    Toolbar toolbar;
    TextView textView ;
    DrawerLayout drawerLayout ;
    NavigationView navigationView ;
    ListView listView ,listView1 ;
    ArrayList<SubList> subLists;
    TaskAdapter taskAdapter;
    SubListAdapter subListAdapter;
    ArrayList<Task> tasks;
    CustomDataBase customDataBase;
    Boolean hideCompleted;
    String taskOrder;
    int subListID;
    TaskAdapter.OnClickItem onClickItem;
    Task taskUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    public void init()
    {
        subListID=-1;
        hideCompleted=false;
        taskOrder=null;
        customDataBase=CustomDataBase.getInstance(this);
        subLists = customDataBase.getAllSubList();
        tasks = customDataBase.getAllTasks(subListID,taskOrder,hideCompleted);
        drawerLayout = findViewById(R.id.layout);
        editText = findViewById(R.id.get_data) ;
        imageButton = findViewById(R.id.img_icon);
        floatingActionButton = findViewById(R.id.float0_but);
        floatingActionButton.setVisibility(View.GONE);
        editText.setVisibility(View.GONE);
        imageButton.setVisibility(View.GONE);
        listView = findViewById(R.id.list_work);
        textView = findViewById(R.id.title1) ;
        listView1 = findViewById(R.id.list_task);
        navigationView = findViewById(R.id.navig);
        toolbar = findViewById(R.id.tool_bar) ;
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close) ;
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        /* Lay du lieu */
        subListAdapter=new SubListAdapter(subLists,this);
        listView.setAdapter(subListAdapter);
        onClickItem = new TaskAdapter.OnClickItem() {
            @Override
            public void onClickItem(Task task) {
                task.status=1-task.status;
                customDataBase.updateTask(task.id,task);
                tasks=customDataBase.getAllTasks(subListID,taskOrder,hideCompleted);
                taskAdapter.notifyDataSetChanged();
                Log.d("toandz","Listview onClick");
            }
        };
        taskAdapter = new TaskAdapter(tasks,this, onClickItem);
        listView1.setAdapter(taskAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toolbar.setBackgroundColor(Color.rgb(subLists.get(i).colorR,subLists.get(i).colorG,subLists.get(i).colorB));
                toolbar.setTitle(subLists.get(i).title);
                drawerLayout.closeDrawer(GravityCompat.START);
                subListID=subLists.get(i).id;
                tasks=customDataBase.getAllTasks(subListID,taskOrder,hideCompleted);
                taskAdapter.notifyDataSetChanged();
                floatingActionButton.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.VISIBLE);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = editText.getText().toString();
                if(!temp.isEmpty()){
                    customDataBase.addTask(new Task(temp,"",1,1,1,1,subListID,1));
                    tasks=customDataBase.getAllTasks(subListID,taskOrder,hideCompleted);
                    taskAdapter.notifyDataSetChanged();
                }
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Repaired_Data.class);
                startActivityForResult(intent,101);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_search,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sort_name:
                taskOrder=CustomDataBase.COLUMN_TITLE+" ASC";
                tasks=customDataBase.getAllTasks(subListID,taskOrder,hideCompleted);
                break;
            case R.id.sort_duetime:
                taskOrder=CustomDataBase.COLUMN_DUETIME+" ASC";
                tasks=customDataBase.getAllTasks(subListID,taskOrder,hideCompleted);
                break;
            case R.id.completed_all:
                customDataBase.completedAll(subListID);
                tasks=customDataBase.getAllTasks(subListID,taskOrder,hideCompleted);
                taskAdapter.notifyDataSetChanged();
                break;
            case R.id.hide_completed:
                hideCompleted=!hideCompleted;
                tasks=customDataBase.getAllTasks(subListID,taskOrder,hideCompleted);
                taskAdapter.notifyDataSetChanged();
                break;
            case R.id.remove_completed:
                customDataBase.removeCompleteTask();
                tasks=customDataBase.getAllTasks(subListID,taskOrder,hideCompleted);
                taskAdapter.notifyDataSetChanged();
                break;
            case R.id.delete_list:
                if (subListID>0)
                {
                    customDataBase.deleteSubList(subListID);
                    tasks=customDataBase.getAllTasks(-1,taskOrder,hideCompleted);
                    subLists=customDataBase.getAllSubList();
                    taskAdapter.notifyDataSetChanged();
                    subListAdapter.notifyDataSetChanged();
                    toolbar.setTitle("All Task");
                    subListID=-1;
                    floatingActionButton.setVisibility(View.GONE);
                    editText.setVisibility(View.GONE);
                    imageButton.setVisibility(View.GONE);

                }
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.new_list:
                floatingActionButton.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                addList();
                break;
            case R.id.setting:
                sortByName();
                Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
    public void addList() {
        Intent intent = new Intent(this, ShowDialog_Add.class);
        startActivityForResult(intent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000 && data != null){
            String title = data.getStringExtra("data") ;
            int red = data.getIntExtra("red",1);
            int green = data.getIntExtra("green",2);
            int blue = data.getIntExtra("blue",3);
            if(!title.isEmpty()){
                toolbar.setBackgroundColor(Color.rgb(red,green,blue));
                toolbar.setTitle(title);
                drawerLayout.closeDrawer(GravityCompat.START);
                customDataBase.addSubList(new SubList(title,red,green,blue));
                subLists=customDataBase.getAllSubList();
                subListID=subLists.get(subLists.size()-1).id;
                subListAdapter.notifyDataSetChanged();
                tasks.clear();
                taskAdapter.notifyDataSetChanged();
            }
        }
        if(requestCode==101 && data != null){
            String title = data.getStringExtra("title") ;
            if(true){
                customDataBase.addTask(new Task(title,"",1,1,1,0,subListID,1));
                //tasks=customDataBase.getTasksFromList(subListID,taskOrder,hideCompleted);
                tasks=customDataBase.getAllTasks(subListID,taskOrder,hideCompleted);
                taskAdapter.notifyDataSetChanged();
            }
        }
    }
    public void getData(String data){
        customDataBase.addTask(new Task(data,"",1,1,1,0,subListID,1));
        tasks=customDataBase.getAllTasks(subListID,taskOrder,hideCompleted);
        taskAdapter.notifyDataSetChanged();
    }
    public void deleteList()
    {

    }
    public void sortByName()
    {
        taskOrder=CustomDataBase.COLUMN_TITLE+" DESC";
        tasks=customDataBase.getAllTasks(subListID,taskOrder,hideCompleted);
        taskAdapter.notifyDataSetChanged();
    }
}