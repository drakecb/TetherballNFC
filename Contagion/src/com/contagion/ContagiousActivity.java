package com.contagion;

import android.app.Activity;
import android.content.Context;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class ContagiousActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contagious);	
		// NfcAdapter adapter = manager.getDefaultAdapter();
		// boolean i = adapter.isEnabled();
		// System.out.println(i);
		//String id = tm.getDeviceId();
		//System.out.println("the tablet id is = "+id);
		// NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
	  //   if (nfcAdapter == null) return;  // NFC not available on this device
	     
	  //   NdefMessage ndefMessage = nfcAdapter.setNdefPushMessage(null, null, null);
	     
	  	   	}
}
