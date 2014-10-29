package edu.drake.badday;

import java.io.IOException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Archive extends Activity {
	private String path;
	int totalTime;
	private MediaPlayer m;
	boolean playback = false;
	Button play;
	//boolean clickable = false;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		play = (Button)findViewById(R.id.play);
		Intent intent = getIntent();                                                       //These two lines give a good idea
		path = intent.getExtras().getString("path");                                       // of how to receive a passed string
		totalTime = intent.getExtras().getInt("time");
		//TextView tv1 = (TextView)(findViewById(R.id.name));
		//TextView time = (TextView)(findViewById(R.id.time));
		//time.setText(totalTime);
		//tv1.setText("My Recording");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive, menu);
		return true;
	}
	
	public void play(View view) throws IllegalArgumentException,   
	SecurityException, IllegalStateException, IOException{
		if (playback == false){
		m = new MediaPlayer();
		m.setDataSource(path);
		m.prepare();
		m.start();
		Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();
		playback = true;
		play.setBackgroundResource(R.drawable.stopp);
		}
		
		else if (playback == true){
			try {
				if (m != null) {
					m.stop();
					m.release();
					m = null;
					// reset.setEnabled(true);              //enables the reset of the recording, we don't have that yet
					Toast.makeText(getApplicationContext(), "Stop playing the recording...",	   
							Toast.LENGTH_SHORT).show();
					playback = false;
					play.setBackground(null);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
