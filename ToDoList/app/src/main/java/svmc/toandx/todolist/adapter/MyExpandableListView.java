package svmc.toandx.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import svmc.toandx.todolist.R;

import java.util.HashMap;
import java.util.List;

import svmc.toandx.todolist.model.Object1;

public class MyExpandableListView extends BaseExpandableListAdapter {
    Context context ;
    List<Object1> listHead ;
    HashMap<Object1,List<Object1>> listChild ;

    public MyExpandableListView(Context context, List<Object1> listHead, HashMap<Object1, List<Object1>> listChild) {
        this.context = context;
        this.listHead = listHead;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {
        return listHead.size();
    }

    @Override
    public int getChildrenCount(int i) {

        return listChild.get(listHead.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listHead.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listChild.get(listHead.get(i)).get(i1) ;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        Object1 temp = (Object1) getGroup(i);
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if(i == 0){

            view = inflater.inflate(R.layout.recyclerview_object2,null) ;
            ImageView imageView = view.findViewById(R.id.image2);
            TextView textView   = view.findViewById(R.id.view) ;
            EditText editText   = view.findViewById(R.id.et) ;
            imageView.setImageResource(temp.getImage());
            textView.setText(temp.getTitle());
        }else{
            view = inflater.inflate(R.layout.recyclerview_object1,null) ;
            ImageView imageView = view.findViewById(R.id.icon1);
            TextView textView   = view.findViewById(R.id.txtTitle);
            TextView textView1  = view.findViewById(R.id.txtName) ;
            imageView.setImageResource(temp.getImage());
            textView.setText(temp.getTitle());
            textView1.setText(temp.getName());
        }


        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        Object1 temp = (Object1) getChild(i,i1);
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_child,null) ;
        ImageView imageView = view.findViewById(R.id.icon2);
        TextView textView   = view.findViewById(R.id.txtTitle1);
        TextView textView1  = view.findViewById(R.id.txtName1) ;
        imageView.setImageResource(temp.getImage());
        textView.setText(temp.getTitle());
        textView1.setText(temp.getName());
        return view;
    }
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true ;
    }
}

