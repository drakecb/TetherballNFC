package com.contagion;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.widget.ImageView;

import com.onbarcode.barcode.android.AbstractBarcode;
import com.onbarcode.barcode.android.BarcodeFactory;

public class HomeActivity extends Activity {
	public Bitmap mybitmap,newbmp,bitmap,bmp;
	ImageView imageView;

	Paint paint;

	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);

	    setContentView(R.layout.home);
	    AndroidBarcodeView androidBarcodeView = new AndroidBarcodeView(getApplicationContext());
	    androidBarcodeView.
	}


	protected void onDraw(Canvas canvas) throws Exception
	{
	        
	    
	    NfcManager nfcManager = (NfcManager) getSystemService(NFC_SERVICE.toString());
	    NfcAdapter nfcAdapter = nfcManager.getDefaultAdapter();
	    AbstractBarcode abstractBarcode = BarcodeFactory.createBarcode(472290);
	    RectF rectF = new RectF(100, 100, 300, 200);
	    abstractBarcode.drawBarcode(canvas, rectF);
	    
	}
	
	
}
