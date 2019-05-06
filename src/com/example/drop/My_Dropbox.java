package com.example.drop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemLongClickListener;
import android.view.View;

import android.widget.ArrayAdapter;

import android.widget.ListView;


@SuppressLint("NewApi")
public class My_Dropbox extends Activity
{
	final Context context = this;
	List<String> li;
	ListView list;
	ArrayAdapter<String> adp;
	ArrayList<String> arraylist;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my__dropbox);
		 
		 li=new ArrayList<String>();
	     list=(ListView) findViewById(R.id.list);
	     registerForContextMenu(list);
	     list.setTextFilterEnabled(true);
	     add(); 
	        
	     list.setOnItemLongClickListener(new OnItemLongClickListener() {
	            // setting onItemLongClickListener and passing the position to the function
	                      @Override
	            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
	                    int position, long arg3) {
	                
	                fileop(position);
	                
	                return true;
	            }
	        });
	  }
	
	private void fileop(int position)
	{
		  list.showContextMenu();
		 final int Position = position;
		  final CharSequence[] options = { "Delete File","Rename File","Share File" };
		  
	        AlertDialog.Builder builder = new AlertDialog.Builder(My_Dropbox.this);
	        builder.setTitle("Upload Items!");
	        builder.setItems(options, new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int item) {
	               
	                 if (options[item].equals("Delete File"))
	                {
	                	 removeItemFromList(Position);
	                	  
	 
	                }
	                 if (options[item].equals("Rename File"))
		                {
	                	 renameItemFromList(Position);
	                	
		 
		                }
	                else if (options[item].equals("Share File")) {
	                	
	                }
	            }
	        });
	        builder.show();
	}
	
	 protected void removeItemFromList(int position) {
	        final int deletePosition = position;
	        
	        AlertDialog.Builder alert = new AlertDialog.Builder(
	                My_Dropbox.this);
	    
	        alert.setTitle("Delete");
	        alert.setMessage("Do you want delete this item?");
	        alert.setPositiveButton("YES", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TOD O Auto-generated method stub
	                    
	                    // main code on after clicking yes
	                    li.remove(deletePosition);
	                    adp.notifyDataSetChanged();
	                    adp.notifyDataSetInvalidated();
	      
	            }
	        });
	        alert.setNegativeButton("CANCEL", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TODO Auto-generated method stub
	                dialog.dismiss();
	            }
	        });
	      
	        alert.show();
	      
	    }
	 
	 protected void renameItemFromList(int position)
	 {
		 final int renamePosition = position;
	 AlertDialog.Builder alert = new AlertDialog.Builder(this);
	 alert.setTitle("Rename item");
	 alert.setMessage("Enter new name for selected item");
	 final EditText input = new EditText(this);
	  alert.setView(input);
	   alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	                   String value = input.getText().toString();
	                 li.set(renamePosition, value);
	                  adp.notifyDataSetChanged();
	        }
	  });
	 alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int whichButton) {}
	    });
	  alert.show();

}
	
	private void select()
	{
		  final CharSequence[] options = { "Upload Photoes","Upload Media","Upload File" };
		  
	        AlertDialog.Builder builder = new AlertDialog.Builder(My_Dropbox.this);
	        builder.setTitle("Upload Items!");
	        builder.setItems(options, new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int item) {
	               
	                 if (options[item].equals("Upload Photoes"))
	                {
	                	
	                	 selectImage();
	 
	                }
	                 if (options[item].equals("Upload Media"))
		                {
		                	
	                	 selectMedia();
		 
		                }
	                else if (options[item].equals("Upload File")) {
	                	
	                }
	            }
	        });
	        builder.show();
	}
	
	private void selectImage() {
		 
        final CharSequence[] options = { "Choose from Gallery","Cancel" };
 
        AlertDialog.Builder builder = new AlertDialog.Builder(My_Dropbox.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
               
                 if (options[item].equals("Choose from Gallery"))
                {
                	
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
 
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
	
	
	
	private void selectMedia() {
		 
        final CharSequence[] options = {"Select media file","Cancel" };
 
        AlertDialog.Builder builder = new AlertDialog.Builder(My_Dropbox.this);
        builder.setTitle("Add Media!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
               
                 if (options[item].equals("Select media file"))
                {
                	
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
 
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
	
	
	 public void add()
	    {
	    	 adp=new ArrayAdapter<String> 
			(getBaseContext(),R.layout.list_single,R.id.text,li);
		list.setAdapter(adp);
		
	   
		list.setOnItemClickListener(new OnItemClickListener() {
	 
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
			}
		});
	    }
	 

	   public void makenewfolder()
	   	{
		   LayoutInflater LI = LayoutInflater.from(context);
			View promptsView = LI.inflate(R.layout.prompts, null);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);

			// set prompts.xml to alertdialog builder
			alertDialogBuilder.setView(promptsView);

			final EditText userInput = (EditText) promptsView
					.findViewById(R.id.edit);

			// set dialog message
			alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
					
				    	li.add(userInput.getText().toString());
						userInput.setText(null);
						add();
				    }
				  })
				.setNegativeButton("Cancel",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				    }
				  });
             AlertDialog alertDialog = alertDialogBuilder.create();
             alertDialog.show();
          }
	
	   private void sort()
		{
			 Collections.sort(li);
             adp=new ArrayAdapter<String>
                      (getBaseContext(),R.layout.list_single,R.id.text,li);
             list.setAdapter(adp);
    // Toast.makeText(My_Dropbox.this, "Sorting in Ascending Order", Toast.LENGTH_LONG).show();
		}
		
	   public boolean onOptionsItemSelected(MenuItem item)
		{
	      switch (item.getItemId())
			{
			case R.id.upload_data:
				select();
				
			
				return true;
				
			case R.id.folder:
				makenewfolder();
					
				return true;
		   
			case R.id.sort:
				sort();
				return true;
		
			default:
				return false;
		}
			
	  }
	 
		 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		        super.onActivityResult(requestCode, resultCode, data);
		        if (resultCode == RESULT_OK) {
		           
		             if (requestCode == 1) {
		 
		                Uri selectedImage = data.getData();
		                String[] filePath = { MediaStore.Images.Media.TITLE,
		                                      MediaStore.Images.Media.SIZE,
		                                      
		                                    };
		                
		                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
		                
		                while(c.moveToNext())
		                {
		                int columnIndex = c.getColumnIndex(filePath[0]);
		                String picturePath = c.getString(columnIndex);
		                li.add(picturePath);
		                add();
		               
		                }
		                c.close();
		              }
		             
		             if (requestCode == 2) {
		            	 
			                Uri selectedMedia = data.getData();
			                String[] filePath = { MediaStore.Audio.Media.TITLE };
			                Cursor c = getContentResolver().query(selectedMedia,filePath, null, null, null);
			                
			                while(c.moveToNext())
			                {
			                int columnIndex = c.getColumnIndex(filePath[0]);
			                String mediaPath = c.getString(columnIndex);
			                li.add(mediaPath);
			                add();
			               
			                }
			                c.close();
			              }
		        }
		    } 
		 
     

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	   MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.my__dropbox, menu);
		
		 SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

	            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
	            searchView.setIconifiedByDefault(false);   

	        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() 
	        {
	            @Override
	            public boolean onQueryTextChange(String newText) 
	            {
	                // this is your adapter that will be filtered
	                adp.getFilter().filter(newText);
	                System.out.println("on text chnge text: "+newText);
	                return true;
	            }
	            @Override
	            public boolean onQueryTextSubmit(String query) 
	            {
	                // this is your adapter that will be filtered
	                adp.getFilter().filter(query);
	                System.out.println("on query submit: "+query);
	                return true;
	            }
	        };
	        searchView.setOnQueryTextListener(textChangeListener);

	        return super.onCreateOptionsMenu(menu);

	
	}
	
	  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	        // add contextmenu items
	        super.onCreateContextMenu(menu, v, menuInfo);
	    }

	    @Override
	    public boolean onContextItemSelected(MenuItem item) {
	        // todo some logic...
	        return super.onContextItemSelected(item);
	    }
	
}