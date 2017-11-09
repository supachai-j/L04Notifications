package andriod.training.cat.com.l04notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by LqLconf on 11/9/2017.
 */

public class AutoNotifyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context c, Intent intent) {

        String current_time = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault()).format(new Date());
        String current_datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date());

        //create notification
        NotificationManager nm = (NotificationManager) c.getSystemService(NOTIFICATION_SERVICE);
        Intent notificationIntent;
        notificationIntent = new Intent(c, MainActivity.class);

        PendingIntent sub_intent = PendingIntent.getActivity(c, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(c);
        Notification notification = builder.setContentIntent(sub_intent)
                .setSmallIcon(R.drawable.ic_notify)
                .setTicker(c.getString(R.string.strXML_notify_ticker) + " " + current_time)
                .setWhen(System.currentTimeMillis())
                //clear old noti replace new noti.
                .setAutoCancel(true)
                //user clear noti bar true or false
                .setOngoing(true)
                .setContentTitle(c.getString(R.string.strXML_notify_title) + " " + current_time)
                .setContentText(c.getString(R.string.strXML_notify_text) + " " + current_datetime)
                .build();
        nm.notify(0, notification);
    }

}
