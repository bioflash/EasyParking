package com.easyparking;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class EasyParkingTrackingService extends Service {
    private static int  UPDATE_INTERVAL= 30000;
    private static int INITIAL_DELAY=5000;
    private Timer timer = null;
    @Override
    public IBinder onBind(Intent arg0) {
      return null;
   }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        //Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        if (timer!=null){
            timer.cancel();
        }else{
            timer = new Timer();
        }
        timer.scheduleAtFixedRate(new UpdateTask(), INITIAL_DELAY, UPDATE_INTERVAL);
        Log.i("ParkingTrackingService", "Server started");
        return START_STICKY;
    }

    class UpdateTask extends TimerTask{
        private int index = 0;
        private List<String> positions = Arrays.asList(new String[]{"24.962633,121.232148",//中原家樂福
                "24.953923, 121.225805", //中壢火車站
                "24.994259, 121.300267"//中壢圖書館
        });

        private void fireNavigationIntent(String position){
            Uri intentURI = Uri.parse("google.navigation:q="+position+"&mode=d");
            Log.i("ParkingTrackingService", "Directing to position: "+position);
            Intent directionIntent = new Intent(Intent.ACTION_VIEW, intentURI);
            directionIntent.setPackage("com.google.android.apps.maps");
            directionIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(directionIntent);
        }
        @Override
        public void run() {
            this.fireNavigationIntent(positions.get(index%3));
            this.index++;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        if (timer!=null){
            //Stop the timer
            timer.cancel();
        }
        Log.i("ParkingTrackingService", "Service destroyed!");
   }
}