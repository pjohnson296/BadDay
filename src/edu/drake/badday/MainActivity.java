package edu.drake.badday;

import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
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
	private String outputFile = null;
	private Button start,stop,play;
	double amplitude;
	int seconds1, seconds2;
	
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      start = (Button)findViewById(R.id.button1);
      stop = (Button)findViewById(R.id.button2);
      play = (Button)findViewById(R.id.button3);

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

      start.setEnabled(false);
      stop.setEnabled(true);
      Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
   }

   public double getAmplitude() { 
	   if (myAudioRecorder != null){
		  return myAudioRecorder.getMaxAmplitude();
	   }  
	   else { 
		  return 0;
	   }
   }
   


   public void stop(View view){
	   ((Chronometer) findViewById(R.id.chronometer1)).stop();
	   Calendar stopTime = Calendar.getInstance();
	   stopTime.getTime();
	   seconds2 = stopTime.get(Calendar.SECOND);

      myAudioRecorder.stop();
      myAudioRecorder.release();
      myAudioRecorder  = null;
      stop.setEnabled(false);
      play.setEnabled(true);
      Toast.makeText(getApplicationContext(), "Audio recorded successfully",
      Toast.LENGTH_LONG).show();
      
      int totalTime = seconds2 - seconds1;
      time = (TextView) findViewById(R.id.timeID);
      time.setText(String.valueOf(totalTime));
      amplitude = getAmplitude();
      amp = (TextView) findViewById(R.id.dBID);
      amp.setText(String.valueOf(amplitude));
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
   public void play(View view) throws IllegalArgumentException,   
   SecurityException, IllegalStateException, IOException{
   
   MediaPlayer m = new MediaPlayer();
   m.setDataSource(outputFile);
   m.prepare();
   m.start();
   Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();

   }
}