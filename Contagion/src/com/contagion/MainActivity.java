package com.contagion;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity{

		//COMPLEXITY RATING: 1
		//CODE QUALITY: 100
		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);			
			TabHost tabHost = getTabHost();
			
			
			// Tab for Home
			TabSpec homeSpec = tabHost.newTabSpec(getString(R.string.home_title));
			// setting Title and Icon for the Tab
			homeSpec.setIndicator(getString(R.string.home_title),
					getResources().getDrawable(R.drawable.ic_launcher));
			Intent homeIntent = new Intent(this, HomeActivity.class);
			homeSpec.setContent(homeIntent);

			// Tab for NFC Phone-to-Phone Feature [a.k.a.	Contagious Activity]
			TabSpec contagiousSpec = tabHost.newTabSpec(getString(R.string.contagious_title));
			contagiousSpec.setIndicator(getString(R.string.contagious_title),
					getResources().getDrawable(R.drawable.ic_action_search));
			Intent contagiousIntent = new Intent(this, ContagiousActivity.class);
			contagiousSpec.setContent(contagiousIntent);


			// Adding all TabSpec to TabHost
			tabHost.addTab(homeSpec); // Adding Home tab
			tabHost.addTab(contagiousSpec);//Adding Find-Seat tab
			


		}
	}