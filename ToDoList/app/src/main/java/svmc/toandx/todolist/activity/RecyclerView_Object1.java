package svmc.toandx.todolist.activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import svmc.toandx.todolist.R;
import java.util.List;
import svmc.toandx.todolist.model.Object1;

public class RecyclerView_Object1 extends RecyclerView.Adapter<RecyclerView_Object1.ViewHolder>{

    List<Object1> array ;
    Context context ;

    public RecyclerView_Object1(List<Object1> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_object1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setBackgroundResource(array.get(position).getImage());
        holder.title.setText(array.get(position).getTitle());
        holder.name.setText(array.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView ;
        TextView title ;
        TextView name ;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.icon1);
            title     = itemView.findViewById(R.id.txtTitle);
            name      = itemView.findViewById(R.id.txtName);
        }
    }
}