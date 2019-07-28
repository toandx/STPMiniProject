package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectsamsung.R;

import java.util.List;

import Model.data;

public class Recycler_data_0 extends RecyclerView.Adapter<Recycler_data_0.ViewHodel> {

    List<data> array;
    Context context;

    public Recycler_data_0(List<data> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_0,parent,false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        holder.imageView.setImageResource(array.get(position).getImg());
        holder.textView.setText(array.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder{

        TextView textView ;
        ImageView imageView ;
        EditText editText ;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.note);
            imageView = itemView.findViewById(R.id.tv_icon);
            editText = itemView.findViewById(R.id.data2);

        }
    }
}
