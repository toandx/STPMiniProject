package com.example.to_do_list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<Option_Items> array;
    Context context ;
    onClicked on ;

    public void onSelected(onClicked on) {
        this.on = on;
    }

    public RecyclerAdapter(List<Option_Items> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_items,parent,false);
            return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(array.get(position).getImage());
        holder.textView.setText(array.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        TextView textView ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (itemView).findViewById(R.id.img_icon);
            textView  = (itemView).findViewById(R.id.tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(on != null){
                        int postition = getAdapterPosition();
                        if(postition != RecyclerView.NO_POSITION){
                            on.click(postition);
                        }
                    }
                }
            });
        }
    }
}
