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
    }

    public void setReminder(View view) {
        EditText itemNameEdit = (EditText) findViewById(R.id.itemName);
        itemName = itemNameEdit.getText().toString();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        mSensorEventListener = new SensorEventListener() {
            @Override

            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {return;}

                if (sensorEvent.values[0] > 3 || sensorEvent.values[1] > 3 || sensorEvent.values[2] > 3 || sensorEvent.values[0] < -3 || sensorEvent.values[1] < -3 || sensorEvent.values[2] < -3) {
                    Toast.makeText(getApplicationContext(), "Don't forget to " + itemName, Toast.LENGTH_SHORT).show();
                    v.vibrate(500);

                    Notification.notificatePush(getBaseContext(), 1, "goldfish", "Don't forget to " + itemName, getIntent(), getWindow());
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        mSensorManager.registerListener(mSensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        Toast.makeText(getApplicationContext(), "We gotchu", Toast.LENGTH_SHORT).show();
        v.vibrate(100);
    }

}
