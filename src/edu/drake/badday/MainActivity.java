package edu.drake.badday;

import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView time, amp;
	private MediaRecorder myAudioRecorder;
	private MediaPlayer m;
	private String outputFile = null;
	private Button start,stop,play,stopPlay,reset, newPage;
	double amplitude;
	int seconds1, seconds2;
	boolean startButton = true;
	Button startStop = (Button)findViewById(R.id.startStop);

	public void sendMessage(View view){
		Intent intent1 = new Intent(this, SecondActivity.class);
		startActivity(intent1);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start = (Button)findViewById(R.id.button1);
		stop = (Button)findViewById(R.id.button2);
		play = (Button)findViewById(R.id.button3);
		stopPlay = (Button)findViewById(R.id.button4);
		reset =  (Button)findViewById(R.id.button5);
		newPage =  (Button)findViewById(R.id.button6);

		stop.setEnabled(false);
		play.setEnabled(false);
		outputFile = Environment.getExternalStorageDirectory().
				getAbsolutePath() + "/myrecording.3gp";;

				myAudioRecorder = new MediaRecorder();
				myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
				myAudioRecorder.setOutputFile(outputFile);


	}

	public void start(View view){

		if (startButton == true){
			startStop.setText("Stop");
			startButton = false;
			((Chronometer) findViewById(R.id.chronometer1)).start();
			Calendar startTime = Calendar.getInstance();
			startTime.getTime();
			seconds1 = startTime.get(Calendar.SECOND);

			try {
				myAudioRecorder.prepare();
				myAudioRecorder.start();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
		}



		if (startButton == false){
			startStop.setText("Start");
			startButton = true;
			((Chronometer) findViewById(R.id.chronometer1)).stop();
			Calendar stopTime = Calendar.getInstance();
			stopTime.getTime();
			seconds2 = stopTime.get(Calendar.SECOND);

			myAudioRecorder.stop();
			myAudioRecorder.release();
			myAudioRecorder  = null;
			stop.setEnabled(false);
			play.setEnabled(true);
			newPage.setEnabled(true);

			Toast.makeText(getApplicationContext(), "Audio recorded successfully",
					Toast.LENGTH_LONG).show();

			int totalTime = seconds2 - seconds1;
			time = (TextView) findViewById(R.id.timeID);
			time.setText(String.valueOf(totalTime));
			amplitude = getAmplitude();
			amp = (TextView) findViewById(R.id.dBID);
			amp.setText(String.valueOf(amplitude));

		}  
	}

	public double getAmplitude() { 
		if (myAudioRecorder != null){
			return myAudioRecorder.getMaxAmplitude();
		}  
		else { 
			return 0;
		}
	}



	//   public void stop(View view){
	//	   ((Chronometer) findViewById(R.id.chronometer1)).stop();
	//	   Calendar stopTime = Calendar.getInstance();
	//	   stopTime.getTime();
	//	   seconds2 = stopTime.get(Calendar.SECOND);
	//
	//      myAudioRecorder.stop();
	//      myAudioRecorder.release();
	//      myAudioRecorder  = null;
	//      stop.setEnabled(false);
	//      play.setEnabled(true);
	//      newPage.setEnabled(true);
	//      
	//      Toast.makeText(getApplicationContext(), "Audio recorded successfully",
	//      Toast.LENGTH_LONG).show();
	//      
	//      int totalTime = seconds2 - seconds1;
	//      time = (TextView) findViewById(R.id.timeID);
	//      time.setText(String.valueOf(totalTime));
	//      amplitude = getAmplitude();
	//      amp = (TextView) findViewById(R.id.dBID);
	//      amp.setText(String.valueOf(amplitude));
	//   }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void play(View view) throws IllegalArgumentException,   
	SecurityException, IllegalStateException, IOException{

		m = new MediaPlayer();
		m.setDataSource(outputFile);
		m.prepare();
		m.start();
		play.setEnabled(false);
		stopPlay.setEnabled(true);
		Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();

	}

	public void stopPlay(View view) {
		try {
			if (m != null) {
				m.stop();
				m.release();
				m = null;
				play.setEnabled(true);
				stopPlay.setEnabled(false);
				reset.setEnabled(true);
				Toast.makeText(getApplicationContext(), "Stop playing the recording...",	   
						Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 


	}
	public void reset(View view){
		play.setEnabled(false);
		reset.setEnabled(false);
		start.setEnabled(true);


	}
}