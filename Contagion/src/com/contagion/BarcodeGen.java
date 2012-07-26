package com.contagion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.View;

import com.onbarcode.barcode.android.AndroidColor;
import com.onbarcode.barcode.android.AndroidFont;
import com.onbarcode.barcode.android.EAN8;
import com.onbarcode.barcode.android.IBarcode;

public class BarcodeGen extends View{

	public BarcodeGen(Context context){
		super(context);

	}



	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		try{
			createEAN8("2345678", canvas);
			//testCODABAR(canvas);
			//testCODE11(canvas);
			//testCODE2OF5(canvas);
			//testCODE39(canvas);
			//testCODE93(canvas);
			//testEAN8(canvas);
			//testEAN13(canvas);
			//testISBN(canvas);
			//testISSN(canvas);
			//testITF14(canvas);
			//testINTERLEAVED25(canvas);
			//testIDENTCODE(canvas);
			//testLEITCODE(canvas);
			//testMSI(canvas);
			//testONECODE(canvas);
			//testPLANET(canvas);
			//testPOSTNET(canvas);
			//testRM4SCC(canvas);
			//testUPCA(canvas);
			//testUPCE(canvas);
			//testCODE128(canvas);
			//testEAN128(canvas);
			//
			//testDataMatrix(canvas);
			//testPDF417(canvas);
			//testQRCode(canvas);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//		public void setBarcodeData(String data){
	//			this.data = data;
	//			
	//		}
	private void createEAN8(String data, Canvas canvas) throws Exception
	{
		EAN8 barcode = new EAN8();

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

		// Unit of Measure, pixel, cm, or inch
		barcode.setUom(IBarcode.UOM_PIXEL);
		// barcode bar module width (X) in pixel
		barcode.setX(2f);
		// barcode bar module height (Y) in pixel
		barcode.setY(90f);

		// barcode image margins
		barcode.setLeftMargin(10f);
		barcode.setRightMargin(10f);
		barcode.setTopMargin(10f);
		barcode.setBottomMargin(10f);

		// barcode image resolution in dpi
		barcode.setResolution(72);

		// disply barcode encoding data below the barcode
		barcode.setShowText(true);
		// barcode encoding data font style
		barcode.setTextFont(new AndroidFont("Arial", 0, 10));
		// space between barcode and barcode encoding data
		barcode.setTextMargin(6);
		barcode.setTextColor(AndroidColor.black);

		// barcode bar color and background color in Android device
		barcode.setForeColor(AndroidColor.black);
		barcode.setBackColor(AndroidColor.white);

		/*
	        specify your barcode drawing area
		 */
		RectF bounds = new RectF(30, 30, 0, 0);
		barcode.drawBarcode(canvas, bounds);

	}
}