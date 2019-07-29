package Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectsamsung.R;

public class Fragment_Data extends Fragment {
    EditText Edt_Data ;
    ImageButton Img_Bt ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data,container,false);
        Edt_Data = view.findViewById(R.id.edt_data);
        Img_Bt = view.findViewById(R.id.bt_send);
        Img_Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = Edt_Data.getText().toString() ;
                MainActivity mt = (MainActivity) getActivity();
                mt.GetData(temp);
            }
        });
        return  view;
    }
}
