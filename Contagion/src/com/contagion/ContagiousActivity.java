package com.contagion;

import java.nio.charset.Charset;
import java.util.Locale;

import android.app.Activity;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.widget.TextView;

public class ContagiousActivity extends Activity{
	
	private NfcAdapter nfcAdapter;
	private TextView textView;
	private NdefMessage ndefMessage;
	
	
	@Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        setContentView(R.layout.contagious);
        textView = (TextView) findViewById(R.id.ndefTextView);
        if (nfcAdapter != null) {
            textView.setText("Tap another Android phone with NFC to push 'NDEF Push Sample'");
        } else {
            textView.setText("This phone is not NFC enabled.");
        }
        
//        NdefRecord uriRecord = new NdefRecord(
//        	    NdefRecord.TNF_ABSOLUTE_URI ,
//        	    "http://developer.android.com/index.html".getBytes(Charset.forName("US-ASCII")),
//        	    new byte[0], new byte[0]);

        NdefRecord uriRecord = NdefRecord.createUri("http://www.tetherball360.com");
        // Create an NDEF message with some sample text
        try {
			ndefMessage = new NdefMessage(uriRecord.getPayload());
		} catch (FormatException e) {
			e.printStackTrace();
		}
		
        
        //        new NdefRecord[] { newTextRecord("7654321", Locale.ENGLISH, true)});        
    }

	@Override
    public void onResume() {
        super.onResume();
        if (nfcAdapter != null) nfcAdapter.setNdefPushMessage(ndefMessage, this); //(this, ndefMessage);
    }

	
    @Override
    public void onPause() {
        super.onPause();
//        if (nfcAdapter != null) nfcAdapter.disableForegroundNdefPush(this);
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
