package com.example.myapplication;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

public class AlarmReceiver extends BroadcastReceiver {
    public static Ringtone ringtone;
    public static Vibrator vibrator;
    @Override
    public void onReceive(Context context, Intent intent) {
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] mVibratePattern = new long[]{0, 1000, 1500, 1000};
        if (vibrator.hasVibrator()) vibrator.vibrate(mVibratePattern, -0);
        ringtone.play();
        Intent intent1 = new Intent(context, WhenAlarmGoOffActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
