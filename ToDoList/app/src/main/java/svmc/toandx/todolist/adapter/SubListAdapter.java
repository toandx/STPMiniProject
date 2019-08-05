package svmc.toandx.todolist.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import svmc.toandx.todolist.model.SubList;
import svmc.toandx.todolist.R;

import java.util.ArrayList;
public class SubListAdapter extends BaseAdapter {
    ArrayList<SubList> arr ;
    Context context ;

    public SubListAdapter(ArrayList<SubList> arr, Context context) {
        this.arr = arr;
        this.context = context;
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
        TextView textView ;
        ImageView imageView;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
        if(view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_draw_menu,viewGroup,false);
            holder.imageView = view.findViewById(R.id.icon);
            holder.textView = view.findViewById(R.id.tv);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(arr.get(i).title);
        return view;
    }
}