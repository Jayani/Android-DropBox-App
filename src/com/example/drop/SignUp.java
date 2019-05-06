package com.example.drop;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {
	
	EditText f_name,l_name,email,pass;
	Button create;
	 LoginDataBaseAdapter loginDataBaseAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up); 
		
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        
        f_name=(EditText)findViewById(R.id.fname);
        l_name=(EditText)findViewById(R.id.lname);
        email=(EditText)findViewById(R.id.e_mail);
        pass=(EditText)findViewById(R.id.password);
        
        create=(Button)findViewById(R.id.login_submit);
        
        create.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				    String fName=f_name.getText().toString();
	                String lName=l_name.getText().toString();
	                String mail=email.getText().toString();
	                String pas=pass.getText().toString();
	                
	                // check if any of the fields are vaccant
	                if(fName.equals("")||lName.equals("")||mail.equals("")||pas.equals(""))
	                {
	                        Toast.makeText(getApplicationContext(), "Please Enter Field Value.", Toast.LENGTH_LONG).show();
	                        return;
	                }
	                // check if both password matches
	              
	                else
	                {
	                        // Save the Data in Database
	                        loginDataBaseAdapter.insertEntry(fName,lName,mail,pas);
	                        Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
	                }
	                 
			}
		});
	}
	@Override
	protected void onDestroy() {
	    // TODO Auto-generated method stub
	    super.onDestroy();
	    
	    loginDataBaseAdapter.close();
	}

	
 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

}
