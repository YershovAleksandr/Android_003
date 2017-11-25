package com.ssdd.lab3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityLoaderActivity extends Activity {
    
	static public final int GET_TEXT_REQUEST_CODE = 1;
	static private final String URL = "http://www.google.com";
	static private final String TAG = "Lab-Intents";
	public static final String STR_ID = "x42";
    
	// For use with app chooser
	static private final String CHOOSER_TEXT = "Load " + URL + " with:";
    
	// TextView that displays user-entered text from ExplicitlyLoadedActivity runs
	private TextView mUserTextView;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader_activity);
		
		// Get reference to the textView
		mUserTextView = (TextView) findViewById(R.id.textView1);
        
		// Declare and setup Explicit Activation button
		Button explicitActivationButton = (Button) findViewById(R.id.explicit_activation_button);
		explicitActivationButton.setOnClickListener(new OnClickListener() {
            
			// Call startExplicitActivation() when pressed
			@Override
			public void onClick(View v) {
				
				startExplicitActivation();
                
			}
		});
        
		// Declare and setup Implicit Activation button
		Button implicitActivationButton = (Button) findViewById(R.id.implicit_activation_button);
		implicitActivationButton.setOnClickListener(new OnClickListener() {
            
			// Call startImplicitActivation() when pressed
			@Override
			public void onClick(View v) {
                
				startImplicitActivation();
                
			}
		});
        
	}
    
	
	// Start the ExplicitlyLoadedActivity
	
	private void startExplicitActivation() {
        
		Log.i(TAG,"Entered startExplicitActivation()");
		
		// DO - Create a new intent to launch the ExplicitlyLoadedActivity class
		Intent explicitIntent = null;

		explicitIntent = new Intent(getBaseContext(), ExplicitlyLoadedActivity.class);

		// DO - Start an Activity using that intent and the request code defined above

		startActivityForResult(explicitIntent, GET_TEXT_REQUEST_CODE);

	}
    
	// Start a Browser Activity to view a web page or its URL
	
	private void startImplicitActivation() {
        
		Log.i(TAG, "Entered startImplicitActivation()");
        
		// DO - Create a base intent for viewing a URL
		// (HINT:  second parameter uses Uri.parse())

		Uri webpage = Uri.parse(URL);

        Intent baseIntent = null;

        baseIntent = new Intent(Intent.ACTION_VIEW, webpage);

		// DO - Create a chooser intent, for choosing which Activity
		// will carry out the baseIntent
		// (HINT: Use the Intent class' createChooser() method)
		Intent chooserIntent = null;

		chooserIntent = Intent.createChooser(baseIntent, CHOOSER_TEXT);
        
		Log.i(TAG,"Chooser Intent Action:" + chooserIntent.getAction());
        
		// DO - Start the chooser Activity, using the chooser intent

		startActivity(chooserIntent);
        
	}
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		Log.i(TAG, "Entered onActivityResult()");
		
		// DO - Process the result only if this method received both a
		// RESULT_OK result code and a recognized request code
		// If so, update the Textview showing the user-entered text.

		if (RESULT_OK == resultCode && GET_TEXT_REQUEST_CODE == requestCode)
		{
			String str = data.getStringExtra(STR_ID);

			mUserTextView.setText(str);
		}
    
    }
}
