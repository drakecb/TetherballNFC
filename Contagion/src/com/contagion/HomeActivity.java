package com.contagion;

import android.app.Activity;
import android.os.Bundle;

public class HomeActivity extends Activity {
//	 private EditText barcodeDataInputField; 		//TESTING COMPONENT:  allows barcode values to be input
//	 private Button generateBtn;		//TESTING COMPTONENT:  allows values in barcodeId to be submitted for barcode generation
//	 private String data;
//	 private Barcode barcode;
//	 private Bitmap bitmap;
//	 private ImageView imageView;
//	 private LinearLayout linearLayout;
//	 private U barcode;
	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
//	    setContentView(R.layout.home);
	    setContentView(new BarcodeGen(this));
//	    
//	    barcodeDataInputField = (EditText) findViewById(R.id.barcodeData);
//	    generateBtn = (Button) findViewById(R.id.generateBtn);
//	    data="";
//	    
//	    imageView = new ImageView(this);
//        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        
//	    
//	    generateBtn.setOnClickListener(new OnClickListener(){
//
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				data = barcodeDataInputField.getText().toString();
//				barcode.setCode(data);
////				bitmap = BitmapFactory.decodeFile(barcode.createPNG(100, Color.BLACK, Color.BLACK));
//		        imageView.setImageBitmap(bitmap);
//		        linearLayout.addView(imageView);
//			}
//	    	
//	    	
//	    });
//	}
//	
//	 private class WidgetUtil {
//
//		    public static ImageView createImageView(Context context, int resid) {
//		    ImageView view=new ImageView(context);
//		      
//		    view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), resid));
//		      return view;
//		    }
	 }
}
