package com.example.drop;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class LoginDataBaseAdapter {
	  static final String DATABASE_NAME = "login.db";
      
      static final int DATABASE_VERSION = 1;

      public static final int NAME_COLUMN = 1;
      // TODO: Create public field for each column in your table.
      // SQL Statement to create a new database.
      public static final String DATABASE_CREATE = "create table "+"LOGIN"+
                                   "( " +"FNAME text,LNAME text,EMAIL text,PASSWORD text); ";
                                   
      // Variable to hold the database instance
      public  SQLiteDatabase db;
      // Context of the application using the database.
      private final Context context;
      // Database open/upgrade helper
      private  DataBaseHelper  dbHelper;
      public  LoginDataBaseAdapter(Context _context) 
      {
              context = _context;
              dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
      }

       // Method to openthe Database  
      public  LoginDataBaseAdapter open() throws SQLException 
      {
              db = dbHelper.getWritableDatabase();
              return this;
      }
   
      // Method to close the Database  
      public void close() 
      {
              db.close();
      }

       // method returns an Instance of the Database 
      public  SQLiteDatabase getDatabaseInstance()
      {
              return db;
      }

        // method to insert a record in Table
      public void insertEntry(String fname,String lname,String mail,String password)
      {
                 
               
                 ContentValues newValues = new ContentValues();
                  // Assign values for each column.
                  newValues.put("FNAME", fname);
                  newValues.put("LNAME", lname);
                  newValues.put("EMAIL", mail);
                  newValues.put("PASSWORD",password);
                 
                 
                 
                  // Insert the row into your table
                  db.insert("LOGIN", null, newValues);
                 // Toast.makeText(context, "User Info Saved", Toast.LENGTH_LONG).show();
     
 
      }
     
     // method to delete a Record of UserName
      public int deleteEntry(String Mail)
      {
               
             String where="EMAIL=?";
             int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{Mail}) ;
             Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
          return numberOFEntriesDeleted;
         
      }
 
 // method to get the password  of userName
  public String getSinlgeEntry(String mail)
  {
     
         
          Cursor cursor=db.query("LOGIN", null, " EMAIL=?", new String[]{mail}, null, null, null);
          if(cursor.getCount()<1) // UserName Not Exist
              return "NOT EXIST";
          cursor.moveToFirst();
          String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
          return password;
         
     
  }

// Method to Update an Existing Record 
  public void  updateEntry(String mail,String password)
  {
          //  create object of ContentValues
          ContentValues updatedValues = new ContentValues();
          // Assign values for each Column.
          updatedValues.put("EMAIL", mail);
          updatedValues.put("PASSWORD",password);
         
          String where="EMAIL = ?";
          db.update("LOGIN",updatedValues, where, new String[]{mail});
        
  }
 
}
