package com.contagion;

import java.nio.charset.Charset;
import java.util.Locale;

import android.app.Activity;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ContagiousActivity extends Activity{

	private NfcAdapter nfcAdapter;
	private TextView textView;
	private RadioGroup couponSelector;
	private RadioButton currentCoupon;
	private NdefMessage ndefMessage;
	private NdefRecord uriRecord;
	private String DATA;


	@Override
	
	//Allows for phone-to-phone transfers via "BEAM" [Host phone performs functions of an NFC Tag]
	//Currently, changes to the RadioGroup selection are only recognized by switching to the Home Activity and back to Contagious Activity
	//This problem will resolve upon findiing a method to "force" and Activity-wide refresh w/out recreating it
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);

		setContentView(R.layout.contagious);
		textView = (TextView) findViewById(R.id.ndefTextView);
		couponSelector = (RadioGroup) findViewById(R.id.radioGroup);
		currentCoupon = (RadioButton) findViewById(couponSelector.getCheckedRadioButtonId());
		DATA = new String(currentCoupon.getText().toString());

		if (nfcAdapter != null) {
			textView.setText("Tap another Android phone with NFC to push 'NDEF Push Sample'");
		} else {
			textView.setText("This phone is not NFC enabled.");
		}


		couponSelector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup rg, int checkedId) {
				currentCoupon = (RadioButton) rg.findViewById(checkedId);
				DATA = currentCoupon.getText().toString();

			}
		});  
		
	}
	//Resume occurs after the activity is started AND after the devices "BEAM"
	@Override
	public void onResume() {
		super.onResume();
		
		
		//If a NFC connectinon has been made, the URI String within "DATA" is transferred 
		if (nfcAdapter != null) nfcAdapter.setNdefPushMessage(newNdefMessage(), this); 
		
	}

	//Pause occurs when devices prepare to "BEAM"
	@Override
	public void onPause() {
		super.onPause();
		//Save states between activities
	}

	
	public void onStop(){
		//Short-Term Data Caching [save states between uses]
		super.onStop();
	}
	
	public void onDestroy(){
		//Long-Term Data Caching [coupons, user data, etc...]
		super.onDestroy();
	}




	public static NdefRecord newTextRecord(String text, Locale locale, boolean encodeInUtf8) {
		byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));

		Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") : Charset.forName("UTF-16");
		byte[] textBytes = text.getBytes(utfEncoding);

		int utfBit = encodeInUtf8 ? 0 : (1 << 7);
		char status = (char) (utfBit + langBytes.length);

		byte[] data = new byte[1 + langBytes.length + textBytes.length]; 
		data[0] = (byte) status;
		System.arraycopy(langBytes, 0, data, 1, langBytes.length);
		System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
		//        NdefFormatable.class
		return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
	}

	public NdefMessage newNdefMessage(){
		currentCoupon = (RadioButton) findViewById(couponSelector.getCheckedRadioButtonId());
		uriRecord = NdefRecord.createUri(DATA);

		// Create an NDEF message with some sample text
		try {
			ndefMessage = new NdefMessage(uriRecord.toByteArray());
		} catch (FormatException e) {
			e.printStackTrace();
		}
		return ndefMessage;
	}
	//	
	//	public void onCreate(Bundle savedInstanceState) {
	//		super.onCreate(savedInstanceState);
	//		setContentView(R.layout.contagious);
	//		String data = "TEST NDEF!";
	//		ndefMessage = new NdefMessage(new NdefRecord[]{
	//				NdefRecord.createUri(data)
	//		});
	//		NfcAdapter.getDefaultAdapter(this).setNdefPushMessage(msg, this);
	//		
	//		// NfcAdapter adapter = manager.getDefaultAdapter();
	//		// boolean i = adapter.isEnabled();
	//		// System.out.println(i);
	//		//String id = tm.getDeviceId();
	//		//System.out.println("the tablet id is = "+id);
	//		// NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
	//	  //   if (nfcAdapter == null) return;  // NFC not available on this device
	//	     
	//	  //   NdefMessage ndefMessage = nfcAdapter.setNdefPushMessage(null, null, null);
	//	     
	//	  	   	}
}
