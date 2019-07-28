package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectsamsung.R;

import java.util.List;

import Model.data;

public class RecyclerView_Data_1 extends RecyclerView.Adapter<RecyclerView_Data_1.ViewHolder> {

    List<data> array ;
    Context context;

    public RecyclerView_Data_1(List<data> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_title.setText(array.get(position).getTitle());
        holder.tv_hint.setText(array.get(position).getName());
        holder.Img_Icon.setImageResource(array.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title , tv_hint ;
        ImageView Img_Icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.title1);
            tv_hint  = itemView.findViewById(R.id.not_hint);
            Img_Icon = itemView.findViewById(R.id.img_icon) ;
        }
    }
}
