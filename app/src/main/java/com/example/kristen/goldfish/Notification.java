package com.example.kristen.goldfish;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

        public static void notificatePush(Context context, int notificationId, String contentTitle, String contentText, Intent intent, Window window) {
            NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.dinia)
                    .setContentTitle(contentTitle)
                    .setContentText(contentText)
                    .setVisibility(visibility);
                  //  .setTicker(tickerText);

            // Because clicking the notification opens a new ("special") activity, there's no need to create an artificial back stack.
            PendingIntent resultPendingIntent = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_ONE_SHOT);
            mBuilder.setContentIntent(resultPendingIntent);
            mBuilder.setAutoCancel(true);
           // mBuilder.setOngoing(true);
            mBuilder.setOnlyAlertOnce(true);

            // Gets an instance of the NotificationManager service
            NotificationManager notifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // Builds the notification and issues it.
            notifyMgr.notify(notificationId, mBuilder.build());

            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//            PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
//                    "MyWakelockTag");
//            wakeLock.acquire();
//
//            PowerManager.WakeLock screenLock = ((PowerManager)getSystemService(POWER_SERVICE)).newWakeLock(
//                    PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
//            screenLock.acquire();

            // V ADDED START
            PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
            boolean isScreenOn = pm.isScreenOn();
            Log.e("screen on.............", ""+isScreenOn);
            if(isScreenOn==false)
            {
                PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.ON_AFTER_RELEASE,"MyLock");
                wl.acquire(10000);
                PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MyCpuLock");

                wl_cpu.acquire(10000);
            }
            // V ADDED END

            // V COMMENTED OUT BEGINNING
            /*
            window.addFlags( WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                  //  WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON );
            */
            // V COMMENTED OUT END
//           window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);



        }

    /*
    NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.dinia)
                    .setContentTitle("goldfish")
                    .setContentText("Hello World!");
    // Creates an explicit intent for an Activity in your app
    Intent resultIntent = new Intent(this, ResultActivity.class);

    // The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
stackBuilder.addParentStack(ResultActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
stackBuilder.addNextIntent(resultIntent);
    PendingIntent resultPendingIntent =
            stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
mBuilder.setContentIntent(resultPendingIntent);
    NotificationManager mNotificationManager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
mNotificationManager.notify(mId, mBuilder.build());
*/

}
