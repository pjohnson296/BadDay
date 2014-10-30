package edu.drake.badday;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Options extends Activity {

	MediaRecorder myAudioRecorder;
	private String path;
	private MediaPlayer m;
	private String outputFile = null;
	private boolean playback = false;                   //only used for when I was trying to put everything on one button
	private Button play,stopPlaying, startStopPlaying;
	int totalTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		outputFile = Environment.getExternalStorageDirectory().
				getAbsolutePath() + "/myrecording.3gp";;

				myAudioRecorder = new MediaRecorder();
				myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
				myAudioRecorder.setOutputFile(outputFile);
				
		Intent intent = getIntent();                                                       //These two lines give a good idea
		path = intent.getExtras().getString("path");                                       // of how to receive a passed string
		totalTime = intent.getExtras().getInt("time");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}

	public void openEat(View view){										//goes to food options 				
		Intent intent = new Intent(this, Eat.class);
		startActivity(intent);
	}
	public void openArchive(View view){									//Opens the archive page
		Intent intent = new Intent(this, Archive.class);
		intent.putExtra("path", outputFile); //This bundles our outputFile path as a string and allows us to pass the string to the next activity
		intent.putExtra("time", totalTime);
		startActivity(intent);
	}
	
	public void rantAgain(View view){									//sends back to the initial rant page when you select "rant" from the options
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
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
