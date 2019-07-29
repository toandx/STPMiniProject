package Activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectsamsung.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.RecyclerView_Data_1;
import Adapter.Recycler_data_0;
import Model.data;

public class Repaired_Data extends AppCompatActivity {

    TextView Txt_Data ;
    EditText Edit_Data ;
    List<data> arr_2 ;
    List<data> arr_1 ;
    RecyclerView RV1 , RV2 ;
    RecyclerView_Data_1 adapter;
    Recycler_data_0 adapter1 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repaired_data);
        RV2 = findViewById(R.id.recycle_data_1);
        RV1 = findViewById(R.id.recycle_data) ;

        arr_1 = new ArrayList<>() ;
        arr_1.add(new data("Due Date","No reminder set",R.drawable.ic_notifications_active_black_24dp));
        arr_1.add(new data("Reminder","Don't reminder set",R.drawable.ic_notifications_off_black_24dp));
        arr_1.add(new data("Priority","None",R.drawable.ic_flag_black_24dp));
        arr_1.add(new data("Highlight","Make this task stand out in the list view ",R.drawable.ic_star_border_black_24dp));
        arr_1.add(new data("Completed","",R.drawable.ic_check_box_outline_blank_black_24dp));
        arr_1.add(new data("Created","",R.drawable.ic_insert_invitation_black_24dp));

        arr_2 = new ArrayList<>() ;
        arr_2.add(new data("Note","",R.drawable.ic_sort_black_24dp)) ;

        LinearLayoutManager l = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RV1.setLayoutManager(layoutManager);
       // RV1.setHasFixedSize(true);
        RV2.setLayoutManager(l);
        RV1.setNestedScrollingEnabled(false);
        RV2.setNestedScrollingEnabled(false);
      //  RV2.setHasFixedSize(true);
        adapter1 = new Recycler_data_0(arr_2,this);
        adapter = new RecyclerView_Data_1 (arr_1,this) ;

        RV1.setAdapter(adapter);
        RV2.setAdapter(adapter1);

    }
}
