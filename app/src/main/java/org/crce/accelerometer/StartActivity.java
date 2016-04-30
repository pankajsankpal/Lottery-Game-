package org.crce.accelerometer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class StartActivity extends ActionBarActivity {

    MediaPlayer voice;
    int randnumber;

    @Override
    protected void onPause() {
        super.onPause();
        voice.pause();


    }

    public int getRand(){
        return randnumber;
    }
    Bundle bundle=new Bundle();
    MainActivity ma=new MainActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
       voice = MediaPlayer.create(this, R.raw.crismusic);

        voice.start();
        Button toastButton =(Button) this.findViewById(R.id.buttonplay);
        toastButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Random randNum = new Random();
                randnumber = randNum.nextInt(48) + 1;
                String a=String.valueOf(randnumber);
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("key",a);
                startActivity(intent);
                //startActivity(new Intent(StartActivity.this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "Get " + randnumber + " in 5 swipes", Toast.LENGTH_SHORT).show();

               // bundle.getInt(String.valueOf(randnumber));
            }


        });

        Button b = (Button)findViewById(R.id.buttonsetting);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(StartActivity.this, Pop.class));

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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

    public void gotoMain(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

   /* public void playMusic(View view){
        voice.start();
    }*/



}
