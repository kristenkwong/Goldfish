package com.example.kristen.goldfish;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    // ADDED
    private Sensor mSensorAcc;
    private TriggerEventListener mTriggerEventListener;
    private SensorEventListener mSensorEventListener;
    // ADDED
    private SensorEventListener mSensorAccEventListener;
    private String itemName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
//
//        mTriggerEventListener = new TriggerEventListener() {
//            @Override
//            public void onTrigger(TriggerEvent event) {
//                Toast.makeText(getApplicationContext(), "PINIA IS THE BEST", Toast.LENGTH_SHORT).show();
//            }
//        };




        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorEventListener = new SensorEventListener() {
            @Override

            public void onSensorChanged(SensorEvent sensorEvent) {
//                if (sensorEvent.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
//                {
//                    return;
//                }

                if (sensorEvent.values[0] > 3.5 || sensorEvent.values[1] > 3.5 || sensorEvent.values[2] > 3.5 || sensorEvent.values[0] < -3.5 || sensorEvent.values[1] < -3.5 || sensorEvent.values[2] < -3.5) {
                    Toast.makeText(getApplicationContext(), "PINIA IS THE BEST", Toast.LENGTH_SHORT).show();
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500);
                }
            }

            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

            mSensorAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorAccEventListener = new SensorEventListener() {
                @Override

                public void onSensorChanged(SensorEvent sensorEvent) {
//                if (sensorEvent.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
//                {
//                    return;
//                }

                    if (sensorEvent.values[0] > 3.5 || sensorEvent.values[1] > 3.5 || sensorEvent.values[2] > 3.5 || sensorEvent.values[0] < -3.5 || sensorEvent.values[1] < -3.5 || sensorEvent.values[2] < -3.5) {
                        Toast.makeText(getApplicationContext(), "VIVIAN IS THE BEST", Toast.LENGTH_SHORT).show();
                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(500);
                    }
                }

                @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };




        mSensorManager.registerListener(mSensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

       // mSensorManager.requestTriggerSensor(mTriggerEventListener, mSensor);


    }

    public void setReminder(View view) {
        EditText itemNameEdit = (EditText) findViewById(R.id.itemName);
        itemName = itemNameEdit.getText().toString();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        mSensorEventListener = new SensorEventListener() {
            @Override

            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
                {
                    return;
                }

                if (sensorEvent.values[0] > 5 || sensorEvent.values[1] > 5 || sensorEvent.values[2] > 5) {
                    Toast.makeText(getApplicationContext(), "PINIA IS THE BEST", Toast.LENGTH_SHORT).show();
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };


        mSensorManager.registerListener(mSensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // mSensorManager.requestTriggerSensor(mTriggerEventListener, mSensor);



        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);


//        startMotionSensor();


//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
    }


}
