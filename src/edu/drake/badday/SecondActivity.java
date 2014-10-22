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
import android.widget.ImageButton;
import android.widget.Toast;

public class SecondActivity extends Activity {

	private String path;
	private MediaPlayer m;
	//private boolean playback = true;                   \\only used for when I was trying to put everything on one button
	private Button play,stopPlaying, startStopPlaying;
	//boolean startbutton=true;
	int totalTime;
	ImageButton scale;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		play = (Button)findViewById(R.id.play);
		scale = (ImageButton)findViewById(R.id.imageButton1);								//Sets up our scale image
		stopPlaying = (Button)findViewById(R.id.stopPlaying);
		//startStopPlaying = (Button)findViewById(R.id.startStopPlaying);    
		Intent intent = getIntent();                                                       //These two lines give a good idea
		path = intent.getExtras().getString("path");                                       // of how to receive a passed string
		totalTime = intent.getExtras().getInt("time");
		System.out.println("Time of recording = " + totalTime + " seconds");
		if (totalTime < 5){																	// v These lines set the scale to whatever image is appropriate v
			System.out.println("very short, eh?");
			scale.setBackgroundResource(R.drawable.blue);
		}
		else if(totalTime> 5 && totalTime < 10){
			scale.setBackgroundResource(R.drawable.cloudandblue);
		}
		else{
			scale.setBackgroundResource(R.drawable.childishfrank);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
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
	
	public void openShare(View view){
		Intent intent = new Intent(this, ShareScreen.class);
		startActivity(intent);
	}
	public void deleterant(View view){
		Intent intent = new Intent(this, DeleteRant.class);
		startActivity(intent);
	}
	public void openOptions(View view){
		Intent intent = new Intent(this, Options.class);
		startActivity(intent);
	}
	public void openArchive(View view){
		Intent intent = new Intent(this, Archive.class);
		startActivity(intent);
	}
	//Allows for the playing of the recording
	public void play(View view) throws IllegalArgumentException,   
	SecurityException, IllegalStateException, IOException{

		m = new MediaPlayer();
		m.setDataSource(path);
		m.prepare();
		m.start();
		Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();

	}
	
	//Allows for user to stop the playback of the recording
		public void stopPlay(View view) {
			try {
				if (m != null) {
					m.stop();
					m.release();
					m = null;
					// reset.setEnabled(true);              //enables the reset of the recording, we don't have that yet
					Toast.makeText(getApplicationContext(), "Stop playing the recording...",	   
							Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 


		}
		
		//This is to put starting and stopping of the recording on one button, but can't get it working right now
		
		//public void startStopPlaying(View view) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException{

		//	if (playback == true){
		//		play(view);
		//		startStopPlaying.setText("Stop Playing");
		//		playback = false;

		//	}
		//	else if (playback == false){
		//		stopPlay(view);                 
		//		playback = true;
		//		startStopPlaying.setText("Play");
		//	 } 
		//}
		/*public void playstop(View view)throws IllegalArgumentException,   
		SecurityException, IllegalStateException, IOException{

			if (startbutton == true){
				play(view);
				//startStop.setText("Stop");
				startbutton = false;
			}
			
			else if (startbutton == false){
				stopPlay(view);
				//sendMessage(view);                   //sends user to next screen after recording is finished
				startbutton = true;
				//startStop.setText("Start");
				play.setEnabled(false);
				
			 } 
		}*/
}
