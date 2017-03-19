package com.example.kristen.goldfish;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.util.Log;

/**
 * Created by kristen on 2017-03-18.
 */

public class TriggerEventListener extends android.hardware.TriggerEventListener{

    public void onTrigger(TriggerEvent event) {

        Log.d("Goldfish2","I am here");
        // send notification + buzz

        // As it is a one shot sensor, it will be canceled automatically.
        // SensorManager.requestTriggerSensor(this, mSigMotion); needs to
        // be called again, if needed.
    }
}
