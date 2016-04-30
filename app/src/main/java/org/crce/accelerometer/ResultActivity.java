package org.crce.accelerometer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ResultActivity extends ActionBarActivity {
    MediaPlayer endSound;
    @Override
    protected void onPause() {
        super.onPause();
        endSound.pause();

    }


MainActivity ma=new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

       TextView txt= (TextView)findViewById(R.id.textView4);
        txt.setText("Your Score is: " + getIntent().getExtras().getString("key2"));

        endSound = MediaPlayer.create(this, R.raw.endmusic);

        endSound.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }
    @Override
    public void onBackPressed(){
        //Intent i=new Intent(getApplicationContext(),StartActivity.class);
        /*Intent i=new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);*/
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),StartActivity.class));
        finish();
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

   /* public void gotoHome(){

        startActivity(new Intent(ResultActivity.this,StartActivity.class));
    }*/
}
