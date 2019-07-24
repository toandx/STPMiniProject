package com.example.to_do_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder1>{

    List<image_2> ar;
    Context context ;

    public RecyclerAdapter2(List<image_2> ar, Context context) {
        this.ar = ar;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_2,parent,false) ;
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
        holder.imageView.setImageResource(ar.get(position).getImage());
        holder.textView.setText(ar.get(position).getTen());
        holder.imageButton.setImageResource(ar.get(position).getHa());
    }

    @Override
    public int getItemCount() {
        return ar.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        ImageView imageView;
        ImageButton imageButton;
        TextView textView ;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.img_button);
            imageView   = itemView.findViewById(R.id.img_view) ;
            textView    = itemView.findViewById(R.id.txtView) ;
        }
    }
}
