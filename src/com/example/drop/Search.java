package com.example.drop;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Search extends Activity {
	private MyCounter mCounter;
	private Intent mIntent;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		
		Intent i=new Intent(this,My_Dropbox.class);
		startActivity(i);
			
		mCounter = new MyCounter(1000, 1000);
		mCounter.start();
	}
	
	private class MyCounter extends CountDownTimer {

		public MyCounter(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			mIntent = new Intent(Search.this, My_Dropbox.class);
			startActivity(mIntent);
			finish();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
		
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
