package com.example.kristen.goldfish;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.PowerManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Window;

import static android.app.Notification.VISIBILITY_PUBLIC;

/**
 * Created by vivianlam on 2017-03-19.
 */

public class Notification {

        private static int visibility = VISIBILITY_PUBLIC;
        public static final String TAG = "NotificationUtils";
        private static final int NOTIFICATION_DEFAULT_ON = 1000;
        private static final int NOTIFICATION_DEFAULT_OFF = 4000;
        private static final int NOTIFICATION_DEFAULT_COLOR = Color.YELLOW;
        private static NotificationManager notifyMgr;



    public static void notificatePush(Context context, int notificationId, String contentTitle, String contentText, Intent intent, Window window) {


            NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                    .setPriority(1)
                    .setSmallIcon(R.drawable.almost_new_goldfish)
                    .setContentTitle(contentTitle)
                    .setContentText(contentText)
                    .setVisibility(visibility);

            PendingIntent resultPendingIntent = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_ONE_SHOT);
            mBuilder.setContentIntent(resultPendingIntent);
            mBuilder.setAutoCancel(true);
            mBuilder.setOnlyAlertOnce(true);

        Bitmap notificationLargeIconBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.almost_new_goldfish);
        mBuilder.setLargeIcon(notificationLargeIconBitmap);

        PendingIntent dismissIntent = NotificationActivity.getDismissIntent(notificationId, context);
        mBuilder.addAction(R.drawable.denia, "dismiss", dismissIntent);

        // Gets an instance of the NotificationManager service
        notifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Builds the notification and issues it.
        notifyMgr.notify(notificationId, mBuilder.build());

        // V ADDED START
        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = pm.isScreenOn();
        Log.e("screen on.............", ""+isScreenOn);
        if(isScreenOn==false)
        {PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.ON_AFTER_RELEASE,"MyLock");
            wl.acquire(10000);
            PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MyCpuLock");

            wl_cpu.acquire(10000);
        }

        }
}
