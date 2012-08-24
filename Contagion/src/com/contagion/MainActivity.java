package com.contagion;

import java.io.IOException;
import java.nio.charset.Charset;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressLint("WorldReadableFiles")
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity{
	
	public static String TAG;

		/** Called when the activity is first created. */

		@Override
		public void onCreate(Bundle savedInstanceState) {
			  
			SharedPreferences opener = getPreferences(MODE_PRIVATE);
			//opener.edit().putString("", value)
		//	String str = opener.toString();
		//	System.out.println(str);	
			// String str =  myPrefs.toString();
			//String first = "";
	        //first = myPrefs.getString("first", null);
	        //System.out.println(first);

			
		 //   return value;

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
		
		@Override
		protected void onResume() {
			super.onResume();
			   Tag tag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
			     System.out.println(tag!=null ? "tag NOT null" : "tag NULL!");
			     try {
						TAG = readTag(tag);
			     } catch (Exception e) {
						e.printStackTrace();
					}
		}
		@SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi" })
		public String readTag(Tag tag) throws IOException {
	        MifareUltralight mifare = MifareUltralight.get(tag);
	        	try {
					mifare.connect();
					byte[] payload = mifare.readPages(4);
					String s =  new String(payload, Charset.forName("ASCII"));
					s = s.substring(9,16);
					System.out.println("READS TAG");
					return s;
	        	} catch (IOException e) {
	        }
	        finally {
	            if (mifare != null) {
	            	try {
	                   mifare.close();
	            	}
	            	catch (IOException e) {}
	            }
	        }
	        return null;
	    }	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	//	private boolean first_time_check() {
	        /* 
	         * Checking Shared Preferences if the user had pressed 
	         * the remember me button last time he logged in
	         * */
	     /*   String first = uPreferences.getString("first", null);
	        if((first == null)){
	            return false;
	        }
	        else 
	            return true;
	    }
*/



}