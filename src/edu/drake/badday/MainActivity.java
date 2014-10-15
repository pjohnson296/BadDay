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
	private boolean isRecording = true;
	private Button reset,startStop;
	double amplitude;
	int seconds1, seconds2, totalTime;
	boolean startButton = true;             //Controls whether start or stop will be called

	//When called, changes to next page
	public void sendMessage(View view){
		Intent intent = new Intent(this, SecondActivity.class);
		intent.putExtra("path", outputFile);                         //This bundles our outputFile path as a string and allows us to pass the string to the next activity
		intent.putExtra("time", totalTime);
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//reset =  (Button)findViewById(R.id.button5);
		startStop = (Button)findViewById(R.id.startStop);

		//Sets up the output file for the recording
		outputFile = Environment.getExternalStorageDirectory().
				getAbsolutePath() + "/myrecording.3gp";;

				myAudioRecorder = new MediaRecorder();
				myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
				myAudioRecorder.setOutputFile(outputFile);


	}

	//Starts and stops the recording
	public void start(View view){
			//Sets up Chronometer and calander to start timing of recording
			((Chronometer) findViewById(R.id.chronometer1)).start();
			Calendar startTime = Calendar.getInstance();
			startTime.getTime();
			seconds1 = startTime.get(Calendar.SECOND);

			//Actually controls the recording of the audio
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

	//public double getAmplitude() { 
	//	if (myAudioRecorder != null){
	//		return myAudioRecorder.getMaxAmplitude();
	//	}  
	//	else { 
	//		return 0;
	//	}
	//}


	//stops the recording
	   public void stop(View view){
		   //stops the timing of the recording
		   ((Chronometer) findViewById(R.id.chronometer1)).stop();
		   Calendar stopTime = Calendar.getInstance();
		   stopTime.getTime();
		   seconds2 = stopTime.get(Calendar.SECOND);
	
	      myAudioRecorder.stop();
	      myAudioRecorder.release();
	      myAudioRecorder  = null;
	      
	      Toast.makeText(getApplicationContext(), "Audio recorded successfully",
	      Toast.LENGTH_LONG).show();
	      
	      //calculates and prints out the time of the recording
	      totalTime = seconds2 - seconds1;
	      time = (TextView) findViewById(R.id.timeID);
	      time.setText(String.valueOf(totalTime));
	//      amplitude = getAmplitude();
	//      amp = (TextView) findViewById(R.id.dBID);
	//      amp.setText(String.valueOf(amplitude));
	   }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//starts and stops the recording by calling the start and stop methods
	public void startStop(View view){

		if (startButton == true){
			start(view);
			startStop.setText("Stop");
			startButton = false;
		}
		
		else if (startButton == false){
			stop(view);
			sendMessage(view);                   //sends user to next screen after recording is finished
			startButton = true;
			startStop.setEnabled(false);
		 } 
	}
	//public void reset(View view){
	//	play.setEnabled(false);
	//	reset.setEnabled(false);
		
//	}
	public void reset (View view) throws IOException
	{
	   isRecording = true;
	   startStop.setEnabled(true);
	   reset.setEnabled(false);
	   
	 //Sets up the output file for the recording
	 		outputFile = Environment.getExternalStorageDirectory().
	 				getAbsolutePath() + "/myrecording.3gp";;
	   try {
	   myAudioRecorder = new MediaRecorder();
		myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
		myAudioRecorder.setOutputFile(outputFile);
		myAudioRecorder.prepare();
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
        startButton=true;
	    startStop(view);

	  
	   		
	}
}