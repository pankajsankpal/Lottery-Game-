package org.crce.accelerometer;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Random;
import java.lang.String;


import java.util.logging.LogRecord;

public class MainActivity extends ActionBarActivity implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 1500;
    private static int count=0, success=0;
   /* private int lucky;*/
    Bundle b=new Bundle();
    public int Success(){
        return success;
    }

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senSensorManager= (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //senAccelerometer=senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senAccelerometer=senSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        float distance=event.values[0];
        if(mySensor.getType()== Sensor.TYPE_PROXIMITY){
            float x= event.values[0];
            //float y=event.values[1];
            //float z =event.values[2];



            long curTime= System.currentTimeMillis();
            if((curTime - lastUpdate) > 100){

                long diffTime= (curTime-lastUpdate);
                lastUpdate=curTime;

               // float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                //if (speed > SHAKE_THRESHOLD) {
                if(distance==0){
                    getRandomNumber();
                    count++;
                    if(count==5){
                       // onPause();
                        count=0;
                        String a=String.valueOf(success);
                       new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String a=String.valueOf(success);
                                final Intent mainIntent = new Intent(getApplicationContext(), ResultActivity.class);
                                mainIntent.putExtra("key2",a);
                               startActivity(mainIntent);
                                success=0;
                            }
                       }, 1800);

                     //  Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                       // intent.putExtra("key2",a);
                        //startActivity(intent);
                        //success=0;
                        //startActivity(new Intent(MainActivity.this,ResultActivity.class));
                    }
                }

                //last_x = x;
               // last_y = y;
                //last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    protected void onPause(){
        super.onPause();

        senSensorManager.unregisterListener(this);
    }

    protected void onResume(){
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void getRandomNumber() {
        ArrayList numbersGenerated = new ArrayList();

        for (int i = 0; i < 6; i++) {
            Random randNumber = new Random();
            int iNumber = randNumber.nextInt(48) + 1;

            if(!numbersGenerated.contains(iNumber)) {
                numbersGenerated.add(iNumber);
            } else {
                i--;
            }
        }
       StartActivity sa=new StartActivity();
        System.out.print("SUCCESS"+sa.getRand());
        if(numbersGenerated.contains(Integer.parseInt(getIntent().getExtras().getString("key")))){
            success++;
        }


    TextView text = (TextView)findViewById(R.id.number_1);
    text.setText(""+numbersGenerated.get(0));



    text = (TextView)findViewById(R.id.number_2);
    text.setText(""+numbersGenerated.get(1));

    text = (TextView)findViewById(R.id.number_3);
    text.setText(""+numbersGenerated.get(2));

    text = (TextView)findViewById(R.id.number_4);
    text.setText("" + numbersGenerated.get(3));

        text = (TextView) findViewById(R.id.number_5);
    text.setText(""+numbersGenerated.get(4));

    text = (TextView)findViewById(R.id.number_6);
    text.setText("" + numbersGenerated.get(5));

    text=(TextView)findViewById(R.id.textView6);
     text.setText("GET:"+getIntent().getExtras().getString("key"));
    FrameLayout ball1 = (FrameLayout) findViewById(R.id.ball_1);
    ball1.setVisibility(View.INVISIBLE);

    FrameLayout ball2 = (FrameLayout) findViewById(R.id.ball_2);
    ball2.setVisibility(View.INVISIBLE);

    FrameLayout ball3 = (FrameLayout) findViewById(R.id.ball_3);
    ball3.setVisibility(View.INVISIBLE);

    FrameLayout ball4 = (FrameLayout) findViewById(R.id.ball_4);
    ball4.setVisibility(View.INVISIBLE);

    FrameLayout ball5 = (FrameLayout) findViewById(R.id.ball_5);
        ball5.setVisibility(View.INVISIBLE);

    FrameLayout ball6 = (FrameLayout) findViewById(R.id.ball_6);
    ball6.setVisibility(View.INVISIBLE);

    Animation a = AnimationUtils.loadAnimation(this, R.anim.move_down_ball_first);
    ball6.setVisibility(View.VISIBLE);
    ball6.clearAnimation();
    ball6.startAnimation(a);

    ball5.setVisibility(View.VISIBLE);
    ball5.clearAnimation();
    ball5.startAnimation(a);

    ball4.setVisibility(View.VISIBLE);
    ball4.clearAnimation();
    ball4.startAnimation(a);

    ball3.setVisibility(View.VISIBLE);
    ball3.clearAnimation();
    ball3.startAnimation(a);

    ball2.setVisibility(View.VISIBLE);
    ball2.clearAnimation();
    ball2.startAnimation(a);

    ball1.setVisibility(View.VISIBLE);
    ball1.clearAnimation();
    ball1.startAnimation(a);


}
}
