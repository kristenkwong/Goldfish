package com.example.kristen.goldfish;

import android.content.Context;
import android.graphics.Color;
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
    private Vibrator v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        EditText editText = (EditText) findViewById(R.id.itemName);
        editText.setBackgroundColor(Color.WHITE);

       // mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
//
//        mTriggerEventListener = new TriggerEventListener() {
//            @Override
//            public void onTrigger(TriggerEvent event) {
//                Toast.makeText(getApplicationContext(), "PINIA IS THE BEST", Toast.LENGTH_SHORT).show();
//            }
//        };



//
//        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//        mSensorEventListener = new SensorEventListener() {
//            @Override
//
//            public void onSensorChanged(SensorEvent sensorEvent) {
//                if (sensorEvent.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
//                {
//                    return;
//                }
//
//                if (sensorEvent.values[0] > 3.5 || sensorEvent.values[1] > 3.5 || sensorEvent.values[2] > 3.5 || sensorEvent.values[0] < -3.5 || sensorEvent.values[1] < -3.5 || sensorEvent.values[2] < -3.5) {
//                    Toast.makeText(getApplicationContext(), "PINIA IS THE BEST", Toast.LENGTH_SHORT).show();
//                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                    v.vibrate(500);
//                }
//            }
//
//            public void onAccuracyChanged(Sensor sensor, int i) {
//
//            }
//        };


        /*
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorEventListener = new SensorEventListener() {
            public void onSensorChanged(SensorEvent event) {
                // alpha is calculated as t / (t + dT)
                // with t, the low-pass filter's time-constant
                // and dT, the event delivery rate

                final float alpha = 0.8;
               // final float alpha = 0.8;

                double[] gravity = new double[3];
                gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
                gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
                gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

                double[] linear_acceleration = new float[3];
                linear_acceleration[0] = event.values[0] - gravity[0];
                linear_acceleration[1] = event.values[1] - gravity[1];
                linear_acceleration[2] = event.values[2] - gravity[2];
            }

            public void onAccuracyChanged(Sensor sensor, int i) {

float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

            if (speed > SHAKE_THRESHOLD) {
                getRandomNumber();
            }
            }

        };
        */







       // mSensorManager.registerListener(mSensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

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

                if (sensorEvent.values[0] > 3.5 || sensorEvent.values[1] > 3.5 || sensorEvent.values[2] > 3.5 || sensorEvent.values[0] < -3.5 || sensorEvent.values[1] < -3.5 || sensorEvent.values[2] < -3.5) {
                    Toast.makeText(getApplicationContext(), "Don't forget to " + itemName, Toast.LENGTH_SHORT).show();
                    v.vibrate(500);

                    Notification.notificatePush(getBaseContext(), 1, "goldfish", "Don't forget to..." + itemName, getIntent(), getWindow());


                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        mSensorManager.registerListener(mSensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // mSensorManager.requestTriggerSensor(mTriggerEventListener, mSensor);


        Toast.makeText(getApplicationContext(), "We gotchu", Toast.LENGTH_SHORT).show();
        v.vibrate(100);
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
    }


}
