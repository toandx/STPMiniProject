package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectsamsung.R;

import java.util.List;

import Model.Object;

public class ListViewAdapter2 extends BaseAdapter {

    List<Object> arr ;
    Context context ;

    public ListViewAdapter2(List<Object> arr, Context context) {
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

    public class ViewHodel{
        TextView tvIcon ;
        TextView getData;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodel hodel ;
        if (view == null){
            hodel = new ViewHodel();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_topic,viewGroup,false);
            hodel.getData = view.findViewById(R.id.get_data);
            hodel.tvIcon  = view.findViewById(R.id.icon_1) ;
            view.setTag(hodel);
        }else{
            hodel = (ViewHodel) view.getTag();
        }
        hodel.getData.setText(arr.get(i).getTitle());
        return view;
    }
}
