package com.obe.quranid2.module.qibla;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.obe.quranid2.R;

public class QiblaActivity extends AppCompatActivity implements SensorEventListener {
    private float compCurrentDegree=0f;
    private float qiblaCurrentDegree = 0f;
    private ImageView compImg,qiblaImg ;
    private SensorManager sensor;
    double latitude,longitude;
    double meccaLatitude = 21.422510;
    double meccaLongitude = 39.826168;
    float qiblaAngle;
    float compDegree,qiblaDegree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qibla);

        compImg =  findViewById(R.id.compassImg);
        qiblaImg = findViewById(R.id.qiblaImg);
        sensor = (SensorManager)getSystemService(SENSOR_SERVICE);

        GPSTracker gpsTracker = new GPSTracker(this);
        if (gpsTracker.getIsGPSTrackingEnabled()){
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
            qiblaAngle = getQiblaAngle(latitude, longitude, meccaLatitude, meccaLongitude);


        }else {
            gpsTracker.showSettingsAlert();
            //Toast.makeText(this, "toot", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensor.unregisterListener(this);
    }
    @Override
    protected void onResume(){
        super.onResume();
        sensor.registerListener(this, sensor.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        compDegree = Math.round(event.values[0]);
        RotateAnimation compRotate = new RotateAnimation(compCurrentDegree ,-compDegree,
                Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        compRotate.setDuration(210);
        compRotate.setFillAfter(true);
        compImg.startAnimation(compRotate);
        compCurrentDegree= -compDegree;

        qiblaDegree = Math.round(event.values[0])+qiblaAngle-90;
        RotateAnimation qiblaRotate = new RotateAnimation(qiblaCurrentDegree,-qiblaDegree,
                Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        qiblaRotate.setDuration(210);
        qiblaRotate.setFillAfter(true);
        qiblaImg.startAnimation(qiblaRotate);
        qiblaCurrentDegree = -qiblaDegree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public float getQiblaAngle(double lat1,double long1,double lat2,double long2){
        double angle,dy,dx;
        dy = lat2 - lat1;
        dx = Math.cos(Math.PI/ 180 * lat1) * (long2 - long1);
        angle = Math.atan2(dy, dx);
        angle = Math.toDegrees(angle);
        return (float)angle;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}