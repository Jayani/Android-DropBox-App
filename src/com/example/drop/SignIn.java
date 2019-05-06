package com.example.drop;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends Activity implements TextWatcher
{
	EditText email,pass;
	Button login;
	LoginDataBaseAdapter loginDataBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
		
		 loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	     loginDataBaseAdapter=loginDataBaseAdapter.open();
		
		email=(EditText)findViewById(R.id.login_email);
		pass=(EditText)findViewById(R.id.login_pass);
		
		 login=(Button)findViewById(R.id.login_submit);
		 login.setEnabled(false);
	     email.addTextChangedListener(this);
	     signIn(email);
	}
	
	 public void launchRingDialog(View view) {
		
	      final ProgressDialog ringProgressDialog = ProgressDialog.show(SignIn.this, "Please Wait ...", "Login in Process ...", true);
		  ringProgressDialog.setCancelable(true);
		 
		 new Thread(new Runnable() {
		 
		             @Override
		 
		      public void run() {
		      try {
		 
		                     // Let the progress ring for 10 seconds...
		    	    Thread.sleep(30000);
	             } catch (Exception e) 
	             {}
		      ringProgressDialog.dismiss();
		    }
		 }).start();
		 
 }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
		return true;
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
		String sid=email.getText().toString();
		
		if(sid.indexOf("@")!=-1 && sid.indexOf(".")!=-1 && sid.lastIndexOf(".")!=-1)
		{
			login.setEnabled(true);
		}
		
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	
	public void signIn(View V)
    {
		final Context context = this;
               login.setOnClickListener(new View.OnClickListener() {
                 
                 public void onClick(View v) {
                     // TODO Auto-generated method stub
                     
                     // get The User name and Password 
                     String mail=email.getText().toString();
                     String password=pass.getText().toString();
                     
                     // fetch the Password form database for respective user name
                     String storeddata=loginDataBaseAdapter.getSinlgeEntry(mail);
                     
                     // check if the Stored password matches with  Password entered by user
                     if(password.equals(storeddata))
                     {
                        // Toast.makeText(SignIn.this, "Login Successfull", Toast.LENGTH_LONG).show();
                         launchRingDialog(v);
                         Intent intent=new Intent(context,My_Dropbox.class);
                         startActivity(intent);
                     }
                     
                     else
                     {
                         
                         Toast.makeText(SignIn.this, "Invalid Email or Password", Toast.LENGTH_LONG).show();
                     }
                     
                 }
             });
             
       
         }

 @Override
 protected void onDestroy() {
     // TODO Auto-generated method stub
     super.onDestroy();
     
     // Close The Database
     loginDataBaseAdapter.close();
 }
 
 
	
}


