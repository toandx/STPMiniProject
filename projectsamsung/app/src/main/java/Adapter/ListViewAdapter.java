package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectsamsung.R;

import java.util.List;

import Model.Object;

public class ListViewAdapter extends BaseAdapter {
    List<Object> array ;
    Context context ;

    public ListViewAdapter(List<Object> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return array.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class  ViewHolder{
        TextView tvColor;
        TextView tvData;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
        if(view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.object,viewGroup,false);
            holder.tvColor = view.findViewById(R.id.color);
            holder.tvData = view.findViewById(R.id.view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.tvData.setText(array.get(i).getTitle());
        return view;
    }
}
