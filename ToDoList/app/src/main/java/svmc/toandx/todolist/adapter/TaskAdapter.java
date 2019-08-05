package svmc.toandx.todolist.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import svmc.toandx.todolist.model.Task;
import svmc.toandx.todolist.R;

public class TaskAdapter extends BaseAdapter {
    ArrayList<Task> arr ;
    Context context ;

    public interface  OnClickItem{
        void onClickItem(Task task);
    }

    OnClickItem onClickItem;

    public TaskAdapter(ArrayList<Task> arr, Context context, OnClickItem listener) {
        this.arr = arr;
        this.context = context;
        this.onClickItem = listener;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return arr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView textViewTitle;
        TextView textViewDueTime;
        CheckBox checkBox;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder ;
        if(view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_listview,viewGroup,false);
            holder.checkBox = view.findViewById(R.id.cb_status);
            holder.textViewTitle = view.findViewById(R.id.tv_title);
            holder.textViewDueTime = view.findViewById(R.id.tv_due_time);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.textViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClickItem(arr.get(i));
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arr.get(i).status==1) holder.textViewTitle.setTextColor(Color.parseColor("#00FF22")); else
                    holder.textViewTitle.setTextColor(Color.parseColor("#000000"));
                onClickItem.onClickItem(arr.get(i));
            }
        });
        holder.textViewTitle.setText(arr.get(i).title);
        if (arr.get(i).status==1)
        {
            holder.checkBox.setChecked(true);
            holder.textViewTitle.setPaintFlags(holder.textViewTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            holder.checkBox.setChecked(false);
            holder.textViewTitle.setPaintFlags(holder.textViewTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
        holder.textViewDueTime.setText("27/08/1998");
        return view;
    }
}