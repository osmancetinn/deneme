package com.example.tryy;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.hardware.*;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    
	private SensorManager sensorManager;
    private TextView count;
    boolean activityRunning;
    TextView tvisim;
   /****/
    private float acceleration;
    private int numSteps;
    private float prevY;
    private float currentY;
    private int threshold;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = (TextView) findViewById(R.id.step_value);
        tvisim=(TextView) findViewById(R.id.tvisim);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        
        threshold=5;
        prevY=0;
        currentY=0;
        numSteps=0;
        
        acceleration=0.0f;
        enableAccMeterListener();
    }
	private void enableAccMeterListener() {
		sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(SensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),sensorManager.SENSOR_DELAY_NORMAL);
		
	}
	private SensorEventListener SensorEventListener = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
		
			currentY=event.values[1];
			if(Math.abs(currentY-prevY)>threshold){
				numSteps++;
				tvisim.setText(numSteps+"");
					
			}
			
			prevY=event.values[1];
		
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};
    
    /*
    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
        // if you unregister the last listener, the hardware will stop detecting step events
//        sensorManager.unregisterListener(this); 
    }
    int x=0;
    @Override
    public void onSensorChanged(SensorEvent event) {
    	x++;
        if (activityRunning) {
            count.setText("Bu Gun:"+String.valueOf(event.values[0]));
            tvisim.setText("Şimdi:"+x);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }*/
}
