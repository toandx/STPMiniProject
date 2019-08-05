package svmc.toandx.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import svmc.toandx.todolist.R;

import java.util.List;

import svmc.toandx.todolist.model.Object;

public class ListViewAdapter extends BaseAdapter {
    List<Object> arr ;
    Context context ;

    public ListViewAdapter(List<Object> arr, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_listview,viewGroup,false);
            holder.imageView = view.findViewById(R.id.icon);
            holder.textView = view.findViewById(R.id.tv);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(arr.get(i).getTitle());
        return view;
    }
}