package edu.drake.badday;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DeleteRant extends Activity {

	String path;
	int totalTime;
	
	public void openOptions(View view){
		Intent intent = new Intent(this, Options.class);
		intent.putExtra("path", path); //This bundles our outputFile path as a string and allows us to pass the string to the next activity
		intent.putExtra("time", totalTime);
		startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_rant);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		Intent intent3 = getIntent();                                                       //These two lines give a good idea
		path = intent3.getExtras().getString("path");                                       // of how to receive a passed string
		totalTime = intent3.getExtras().getInt("time");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.delete_rant, menu);
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
}
