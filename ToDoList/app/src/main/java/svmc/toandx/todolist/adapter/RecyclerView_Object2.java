package svmc.toandx.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import svmc.toandx.todolist.R;

import java.util.List;

import svmc.toandx.todolist.model.Object2;

public class RecyclerView_Object2 extends RecyclerView.Adapter<RecyclerView_Object2.ViewHolder> {

    List<Object2>array ;
    Context context ;

    public RecyclerView_Object2(List<Object2> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_object2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(array.get(position).getTitle());
        holder.imageView.setImageResource(array.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        TextView textView ;
        EditText editText ;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image2);
            textView  = itemView.findViewById(R.id.view);
            editText  = itemView.findViewById(R.id.et) ;
        }
    }
}