package andriod.training.cat.com.l04notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = this;

        cancelRepeatingAlarm(c); // reset at first

        Switch sw_alarm = (Switch) findViewById(R.id.lo_sw_alarm);
        sw_alarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setRepeatingAlarm(System.currentTimeMillis(), 60000);
                }else{
                    cancelRepeatingAlarm(c);
                }
            }
        });

    }
    public  void setRepeatingAlarm(long timeMillisecond, long interval) {
        PendingIntent pi = PendingIntent.getBroadcast(c, 0, new Intent(c, AutoNotifyBroadcastReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, timeMillisecond, interval, pi);
    }
    public static void cancelRepeatingAlarm(Context c) {
        Intent intent = new Intent(c, AutoNotifyBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(c, 0, intent, 0);
        AlarmManager am = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);
    }

}
