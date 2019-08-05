package svmc.toandx.todolist.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import svmc.toandx.todolist.R;

public class ShowDialog_Add extends AppCompatActivity {

    LinearLayout linearLayout ;
    ImageView imageView ;
    EditText editText ;
    Button btCancel ;
    Button btSave ;
    Bitmap bitmap ;
    int r , g , b ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_dialog);
        Mapping();
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == motionEvent.ACTION_DOWN || motionEvent.getAction() == motionEvent.ACTION_MOVE){
                    bitmap = imageView.getDrawingCache();
                    int pixel = bitmap.getPixel((int)motionEvent.getX(), (int)motionEvent.getY());
                    r = Color.red(pixel);
                    g = Color.green(pixel);
                    b = Color.blue(pixel) ;
                    linearLayout.setBackgroundColor(Color.rgb(r,g,b));
                }
                return false;
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = editText.getText().toString();
                Toast.makeText(getApplicationContext(),temp,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data",temp) ;
                intent.putExtra("red",r) ;
                intent.putExtra("green",g) ;
                intent.putExtra("blue",b) ;
                setResult(RESULT_OK,intent);
                finish();
            }
        });


    }
    void Mapping()
    {
        linearLayout = findViewById(R.id.layout1);
        imageView = findViewById(R.id.image);
        editText = findViewById(R.id.edt_data);
        btCancel = findViewById(R.id.btn_cancel);
        btSave   = findViewById(R.id.btn_save);
    }
}