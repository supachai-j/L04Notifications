package andriod.training.cat.com.l04notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by LqLconf on 11/9/2017.
 */

public class AutoStartupBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        setRepeatingAlarm(context, System.currentTimeMillis()+5000, 60000);

    }


    public static void setRepeatingAlarm(Context c, long timeMillisecond, long interval) {
            PendingIntent pi = PendingIntent.getBroadcast(c, 0, new Intent(c, AutoNotifyBroadcastReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, timeMillisecond, interval, pi);
    }

}
