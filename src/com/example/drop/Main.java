package com.example.drop;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	Button signIn,signUp;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		 
		signIn=(Button)findViewById(R.id.signin);
		signUp=(Button)findViewById(R.id.signup);
		addListenerOnButton() ;
		
}
	
	public void addListenerOnButton() {
		
		final Context context = this;
		 
		signIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, SignIn.class);
                startActivity(intent); 
			}
		});
		
		signUp.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, SignUp.class);
                startActivity(intent); 
				
			}
		});
	
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

