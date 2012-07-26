package com.contagion;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends Activity {
	 private EditText barcodeDataInputField; 		//TESTING COMPONENT:  allows barcode values to be input
	 private Button generateBtn;		//TESTING COMPTONENT:  allows values in barcodeId to be submitted for barcode generation
	 private String data;
	 
	 private BarcodeGen barcode;
	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
//	    setContentView(R.layout.home);
	    setContentView(new BarcodeGen(this));

	}
	
}
