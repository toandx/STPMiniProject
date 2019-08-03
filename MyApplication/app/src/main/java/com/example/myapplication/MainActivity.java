package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    ToggleButton toggleButton;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        alarmTimePicker = findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                long time;
                if (((ToggleButton)view).isChecked()) {
                    Toast.makeText(MainActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, AlarmReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                    time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                    if(System.currentTimeMillis() > time) {
                        if (Calendar.AM_PM == 0) time = time + (1000*60*60*12);
                        else time = time + (1000*60*60*24);
                    }
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
                    finish();
                }
                else {
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(MainActivity.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
