package com.contagion;

import java.io.IOException;
import java.nio.charset.Charset;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;

public class TagReader extends Activity{
	
	private static String TAG;
	
	public void onCreate(){
		   //Get the tag content
	    Tag tag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
	    try {
	    	TAG = readTag(tag);
	    	TAG = TAG.substring(0,6);
	    	showAlert(TAG);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

	}
	
	private String readTag(Tag tag) throws IOException {
        MifareUltralight mifare = MifareUltralight.get(tag);
        	try {
				mifare.connect();
				byte[] payload = mifare.readPages(4);
				return new String(payload, Charset.forName("US-ASCII"));
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

	//Creates an pop-up alert which displays a message 
	private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                     TagReader.this);
        builder.setMessage(message).setCancelable(true)
                     .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                   dialog.dismiss();
                            }
                     });
        AlertDialog alert = builder.create();
        alert.show();
	}
}
