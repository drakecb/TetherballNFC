package com.contagion;


import java.io.IOException;
import java.nio.charset.Charset;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.onbarcode.barcode.android.AndroidColor;
import com.onbarcode.barcode.android.AndroidFont;
import com.onbarcode.barcode.android.EAN8;
import com.onbarcode.barcode.android.IBarcode;

public class HomeActivity extends Activity {
	 private EditText barcodeDataInputField; 		//TESTING COMPONENT:  allows barcode values to be input
	 private Button generateBtn;		//TESTING COMPTONENT:  allows values in barcodeId to be submitted for barcode generation
	 private String data;
	 private SurfaceView surfaceView;
	 private SurfaceHolder surfaceHolder;
	 private Callback callback;
	 private RectF rect;
	 private LinearLayout linearLayout2;
	 
	 

	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.home);
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
	    
	    
	    barcodeDataInputField = (EditText) findViewById(R.id.barcodeData);
	    generateBtn = (Button) findViewById(R.id.generateBtn);
	    linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
	    surfaceView = (SurfaceView)findViewById(R.id.surfaceView1);
	    surfaceHolder = surfaceView.getHolder();

	    
//	    data ="0000000";
//	    generateBarcode();
	    
	    
	    generateBtn.setOnClickListener(new OnClickListener(){
	    	
	    	public void onClick(View arg0) {
	    		
	    		data = barcodeDataInputField.getText().toString();
	    		System.out.println(data);
//	    		surfaceHolder.removeCallback(callback);
	    		linearLayout2.removeView(surfaceView);
	    		generateBarcode();
	    		
	    		linearLayout2.addView(surfaceView, 0);
	    		
	    		
	    	}
	    });

	}
	
		
	private void generateBarcode(){
		
		System.out.println("Enters:\tGENERATE_BARCODE");
		surfaceHolder.addCallback(new SurfaceHolder.Callback() {
			public void surfaceCreated(SurfaceHolder holder) {
				System.out.println("Enters:\tSURFACE_CREATED");
				Canvas can = null;
				try {
//					surfaceHolder
					can = holder.lockCanvas(null);
					synchronized(holder) {
						drawEAN8(can);

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if(can != null) {
						holder.unlockCanvasAndPost(can);
					}
				}
				System.out.println("Exits:\tSURFACE_CREATED");
			}

			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				System.out.println("Enters:\tSURFACE_CHANGED");
				holder.setFormat(format);
				System.out.println("Exits:\tSURFACE_CHANGED");
			}

			public void surfaceDestroyed(SurfaceHolder holder) {
				System.out.println("DESTROYED SURFACE!");

			}
		});
		
		System.out.println("Exitss:\tGENERATE_BARCODE");
	}
	
	
	
	@SuppressWarnings("deprecation")
	private void drawEAN8(Canvas canvas) throws Exception
    {
		canvas.restore();
        EAN8 barcode = new EAN8();
        System.out.println("Gets to drawEAN8");
        /*
           EAN 8 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           EAN 8 Valid data length: 7 digits only, excluding the last checksum digit
        */
        barcode.setData(data);

        // for EAN8 with supplement data (2 or 5 digits)
        /*
        barcode.setSupData("12");
        // supplement bar height vs bar height ratio
        barcode.setSupHeight(0.8f);
        // space between barcode and supplement barcode (in pixel)
        barcode.setSupSpace(15);
        */
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
//        barcode.setRotate(display.getRotation());
        // barcode bar module width (X) in pixel
        barcode.setX(4f);
        // barcode bar module height (Y) in pixel
        barcode.setY(180f);
//        barcode.setBarAlignment(2);
        // barcode image margins
        barcode.setLeftMargin(10f);
        barcode.setRightMargin(10f);
        barcode.setTopMargin(10f);
        barcode.setBottomMargin(10f);

//        barcode.setRotate(getRequestedOrientation());
        // barcode image resolution in dpi
        barcode.setResolution(display.getWidth());

        // disply barcode encoding data below the barcode
        barcode.setShowText(true);
        // barcode encoding data font style
        barcode.setTextFont(new AndroidFont("Arial", 0, 14));
        // space between barcode and barcode encoding data
        barcode.setTextMargin(6);
        barcode.setTextColor(AndroidColor.black);
        
        // barcode bar color and background color in Android device
        barcode.setForeColor(AndroidColor.black);
        barcode.setBackColor(AndroidColor.white);

        /*
        specify your barcode drawing area
	    */
        float xCenter = linearLayout2.getRight()-linearLayout2.getLeft();
        float yCenter = linearLayout2.getBottom()-linearLayout2.getTop();
	    RectF bounds = new RectF(xCenter / 8, yCenter / 4, (float) (xCenter)/2, (float) (yCenter));
//	    rect.c
//	    this.
	    
        barcode.drawBarcode(canvas, bounds);
    }
	
 
//	    data="1234567";
//	   
//	    generateBtn.setOnClickListener(new OnClickListener(){
//
//			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				data = barcodeDataInputField.getText().toString();
//				bitmap = BitmapFactory.decodeFile(barcode.createPNG(100, Color.BLACK, Color.BLACK));
//		        imageView.setImageBitmap(bitmap);
//		        linearLayout.addView(imageView);
//			}
//	    	
//	    	
//	    });
	
	
//	 private class WidgetUtil {
//
//		    public static ImageView createImageView(Context context, int resid) {
//		    ImageView view=new ImageView(context);
//		      
//		    view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), resid));
//		      return view;
//		    }
//	 }
}
