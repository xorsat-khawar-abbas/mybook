package net.xorsat.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import net.xorsat.mybook.HomeActivity;
import net.xorsat.mybook.R;

/**
 * Created by xorsat on 4/9/16.
 */
public class GCMService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    public GCMService() {
        super("GCMService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // do your work here

        Bundle mBundle = intent.getExtras();
        //Log.i("bundle: ", mBundle.toString());
        String strMessage = mBundle.getString("m");
        Log.i("message: ", strMessage);
        sendNotification(strMessage);
    }

    private void sendNotification(String msg) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, HomeActivity.class), 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.common_plus_signin_btn_icon_light_normal)
                .setContentTitle("Message Received")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setSound(uri).setContentText(msg);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
