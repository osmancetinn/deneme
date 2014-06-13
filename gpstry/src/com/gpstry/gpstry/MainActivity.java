package com.gpstry.gpstry;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView textLat,textLong;
	
	//https://www.google.com/maps/place/Moda/
	//@40.986082,29.02551,

	
	float prevLat;
	float nextLat;
	GPSTracker GPS;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textLat=(TextView) findViewById(R.id.tvLat);
		textLong=(TextView) findViewById(R.id.tvLong);
		
			GPS=new GPSTracker(MainActivity.this);	
			if(GPS.canGetLocation()){
				prevLat=nextLat=(float)GPS.getLatitude();
				textLat.setText("LAT:"+GPS.getLatitude()+":");
				
				textLong.setText("LONG"+GPS.getLongitude()+":");
				
			}else {
				GPS.showSettingsAlert();
			}

	}
	public void reflesh(View e){
		GPS=new GPSTracker(MainActivity.this);	
		if(GPS.canGetLocation()){
			textLat.setText("LAT:"+GPS.getLatitude()+":");
			nextLat=(float)GPS.getLatitude();
			textLong.setText("LONG"+GPS.getLongitude()+":");
			
		}else {
			GPS.showSettingsAlert();
		}
		textLat.setText(prevLat+"-"+nextLat+"="+Math.abs((prevLat-nextLat)*111000)+"m");
		prevLat=nextLat;
	}

}
