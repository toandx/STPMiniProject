package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectsamsung.R;

public class ShowDialog extends AppCompatActivity {
    Button btCancel, btSave ;
    EditText edtData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_dialog);
        btCancel = findViewById(R.id.btCancel);
        btSave   = findViewById(R.id.btSave)  ;
        edtData  = findViewById(R.id.task);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = edtData.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("data",temp);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
