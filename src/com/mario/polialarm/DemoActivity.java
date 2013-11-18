package com.mario.polialarm;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;

public class DemoActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);	
				
		/** Creating an Alert Dialog Window */
		AlertDemo alert = new AlertDemo();
		
		/** Opening the Alert Dialog Window */
		alert.show(getSupportFragmentManager(), "AlertDemo");		
	}
}
