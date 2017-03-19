package com.example.kristen.goldfish;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.util.Log;

/**
 * Created by kristen on 2017-03-18.
 */

public class SensorActivity extends Activity {
        private final SensorManager mSensorManager;
        private final Sensor mSigMotion;

        public SensorActivity() {
            mSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
            mSigMotion = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
           }


        protected void onResume() {
            super.onResume();
            mSensorManager.requestTriggerSensor(mListener, mSigMotion);
        }

        protected void onPause() {
            super.onPause();
            // Call disable to ensure that the trigger request has been canceled.
            mSensorManager.cancelTriggerSensor(mListener, mSigMotion);
        }



   TriggerEventListener mListener = new TriggerEventListener() {
        @Override
        public void onTrigger(TriggerEvent event) {

            if (event.values[0] == 1) {
                Log.d("Goldfish2","I am here");
            }
        }
    };

//    mSensorManager.requestTriggerSensor(mTriggerEventListener, mSensor);


}
