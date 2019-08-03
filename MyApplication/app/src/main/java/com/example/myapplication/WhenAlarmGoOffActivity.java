package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class WhenAlarmGoOffActivity extends AppCompatActivity {
    ImageButton button_stop, button_snooze;
    TextView textView;
    Animation animation;
    Context context;
    Window window;
    AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_when_alarm_go_off);
        context = this;
        animation = AnimationUtils.loadAnimation(context, R.anim.shakeanim);
        window = ((WhenAlarmGoOffActivity) context).getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        button_stop = findViewById(R.id.stop_btn);
        button_snooze = findViewById(R.id.snooze_btn);
        textView = findViewById(R.id.current_time_tv);
        Calendar now = Calendar.getInstance();
        textView.setText(String.format("%d : %d", now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE)));
        button_stop.startAnimation(animation);
        button_snooze.startAnimation(animation);
        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmReceiver.ringtone.stop();
                AlarmReceiver.vibrator.cancel();
                finish();
            }
        });
        button_snooze.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 60000, pendingIntent);
                AlarmReceiver.ringtone.stop();
                AlarmReceiver.vibrator.cancel();
                Toast.makeText(context, "Alarm will go off again after 1 minute!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
