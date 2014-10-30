package edu.drake.badday;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TitlePage extends Activity {

	int seconds1, seconds2, totalTime;
	int DELAY=2000;
	
	public void sendMessage(View view){
		Intent intent = new Intent(TitlePage.this, MainActivity.class);
		startActivity(intent);}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title_page);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		 Handler handler = new Handler();
		    //handler.postDelayed(new Runnable() { 
			new Handler().postDelayed(new Runnable(){
			public void run(){
				Intent intent = new Intent(TitlePage.this, MainActivity.class);
				startActivity(intent);
				finish();
				}
		 },DELAY);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.title_page, menu);
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
	//@Override
	/*public void switchScreen(View view){
		((Chronometer) findViewById(R.id.chronometer1)).start();
		Calendar startTime = Calendar.getInstance();
		startTime.getTime();
		seconds1 = startTime.get(Calendar.SECOND);
		seconds2 = 0;
		
		while(totalTime < 3){
			Calendar stopTime = Calendar.getInstance();
			seconds2 = stopTime.get(Calendar.SECOND);
			totalTime = seconds2 - seconds1;
		}
		sendMessage(view);
	}*/
}
