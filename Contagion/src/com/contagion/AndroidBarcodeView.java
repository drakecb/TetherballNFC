package com.contagion;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;

import com.onbarcode.barcode.android.AndroidColor;
import com.onbarcode.barcode.android.AndroidFont;
import com.onbarcode.barcode.android.Codabar;
import com.onbarcode.barcode.android.Code11;
import com.onbarcode.barcode.android.Code128;
import com.onbarcode.barcode.android.Code25;
import com.onbarcode.barcode.android.Code39;
import com.onbarcode.barcode.android.Code93;
import com.onbarcode.barcode.android.DataMatrix;
import com.onbarcode.barcode.android.EAN128;
import com.onbarcode.barcode.android.EAN13;
import com.onbarcode.barcode.android.EAN8;
import com.onbarcode.barcode.android.IBarcode;
import com.onbarcode.barcode.android.ISBN;
import com.onbarcode.barcode.android.ISSN;
import com.onbarcode.barcode.android.ITF14;
import com.onbarcode.barcode.android.Identcode;
import com.onbarcode.barcode.android.Interleaved25;
import com.onbarcode.barcode.android.Leitcode;
import com.onbarcode.barcode.android.MSI;
import com.onbarcode.barcode.android.Onecode;
import com.onbarcode.barcode.android.PDF417;
import com.onbarcode.barcode.android.Planet;
import com.onbarcode.barcode.android.Postnet;
import com.onbarcode.barcode.android.QRCode;
import com.onbarcode.barcode.android.RM4SCC;
import com.onbarcode.barcode.android.UPCA;
import com.onbarcode.barcode.android.UPCE;

public class AndroidBarcodeView extends View
{

	public AndroidBarcodeView(Context context) {
	    super(context);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		try {
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
	        testUPCA(canvas);
	        //testUPCE(canvas);
	        //testCODE128(canvas);
	        //testEAN128(canvas);
	
	        //testDataMatrix(canvas);
	        //testPDF417(canvas);
//	        testQRCode(canvas);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testCODABAR(Canvas canvas) throws Exception
    {
        Codabar barcode = new Codabar();

        // barcode data to encode
        /*
	           Codabar Valid data char set:
		            - (Dash), $ (Dollar), : (Colon), / (Slash), . (Point), + (Plus)
		            0, 1, 2, 3, 4, 5, 6, 7, 8, 9
	        */
        barcode.setData("112233445566");

        // Codabar Start & Stop Char, Valid values are 'A', 'B', 'C', 'D'
        barcode.setStartChar('A');
        barcode.setStopChar('A');

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testCODE11(Canvas canvas) throws Exception
    {
        Code11 barcode = new Code11();

        // barcode data to encode
        /*
	           Code-11 Valid data char set:
		            0, 1, 2, 3, 4, 5, 6, 7, 8, 9
                    - (Dash)
	        */
        barcode.setData("0123456789-");

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testCODE2OF5(Canvas canvas) throws Exception
    {
        Code25 barcode = new Code25();

        /*
           Code 2 of 5 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
        */
        barcode.setData("0123456789");

        // Code 2 of 5 Wide Narrow bar Ratio
        // Valid value is from 2.0 to 3.0 inclusive.
        barcode.setN(3.0f);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testCODE39(Canvas canvas) throws Exception
    {
        Code39 barcode = new Code39();

        /*
           Code39 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
                A - Z (Uppercase letters)
                - (Dash), $ (Dollar), % (Percentage), (Space), . (Point), / (Slash), + (Plus)

           Code39 extension Valid data char set:
                All ASCII 128 characters
        */
        // Code39 encodes upper case chars only, for lower case chars, use Code 39 extension
        barcode.setData("123456789012");

        barcode.setExtension(false);

        barcode.setAddCheckSum(true);

        // Code 39 Wide Narrow bar Ratio
        // Valid value is from 2.0 to 3.0 inclusive.
        barcode.setN(3.0f);
        // The space between 2 characters in code 39; This a multiple of X; The default is 1.;
        // Valid value is from 1.0 (inclusive) to 5.3 (exclusive)
        barcode.setI(1.0f);
        barcode.setShowStartStopInText(true);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testCODE93(Canvas canvas) throws Exception
    {
        Code93 barcode = new Code93();

        /*
           Code 93 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
                A - Z (Uppercase letters)
                - (Dash), $ (Dollar), % (Percentage), (Space), . (Point), / (Slash), + (Plus)
        */
        barcode.setData("CODE93DATA");

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testEAN8(Canvas canvas) throws Exception
    {
        EAN8 barcode = new EAN8();

        /*
           EAN 8 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           EAN 8 Valid data length: 7 digits only, excluding the last checksum digit
        */
        barcode.setData("7777777");

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
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(45f);

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

    private static void testEAN13(Canvas canvas) throws Exception
    {
        EAN13 barcode = new EAN13();

        /*
           EAN 13 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           EAN 13 Valid data length: 12 digits only, excluding the last checksum digit
        */
        barcode.setData("012345678901");

        // for EAN13 with supplement data (2 or 5 digits)
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
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(45f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 10));
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

    private static void testISBN(Canvas canvas) throws Exception
    {
        ISBN barcode = new ISBN();

        /*
           ISBN Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           ISBN Valid data length: 12 digits only, excluding the last checksum digit
            and it must start with "978" or "979"
        */
        barcode.setData("978047082163");

        // for ISBN with supplement data (2 or 5 digits)
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
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(45f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 10));
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

    private static void testISSN(Canvas canvas) throws Exception
    {
        ISSN barcode = new ISSN();

        /*
           ISSN Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           ISSN Valid data length: 9 digits only, excluding starting "977" and the last checksum digit
        */
        barcode.setData("345678000");

        // for ISSN with supplement data (2 or 5 digits)
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
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(45f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 10));
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

    private static void testITF14(Canvas canvas) throws Exception
    {
        ITF14 barcode = new ITF14();

        /*
           ITF 14 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           ITF 14 Valid data length: 13 digits only, excluding the last checksum digit
        */
        barcode.setData("0123456789012");

        // ITF-14 Wide Narrow bar Ratio
        // Valid value is from 2.0 to 3.0 inclusive.
        barcode.setN(3.0f);

        // ITF-14 bearer bar size vs bar module (X) size ratio
        // Valid values are 0-10 which are a multiple of X.
        barcode.setBearerBarHori(1);
        barcode.setBearerBarVert(1);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testINTERLEAVED25(Canvas canvas) throws Exception
    {
        Interleaved25 barcode = new Interleaved25();

        /*
           Interleaved 2 of 5 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
        */
        barcode.setData("112233445566");

        barcode.setAddCheckSum(true);

        // Interleaved 2 of 5 Wide Narrow bar Ratio
        // Valid value is from 2.0 to 3.0 inclusive.
        barcode.setN(3.0f);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testIDENTCODE(Canvas canvas) throws Exception
    {
        Identcode barcode = new Identcode();

        /*
           Identcode Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           Identcode Valid data length: 11 digits only, excluding the last checksum digit
        */
        barcode.setData("01234567890");

        // Identcode Wide Narrow bar Ratio
        // Valid value is from 2.0 to 3.0 inclusive.
        barcode.setN(3.0f);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testLEITCODE(Canvas canvas) throws Exception
    {
        Leitcode barcode = new Leitcode();

        /*
           Leitcode Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           Leitcode Valid data length: 13 digits only, excluding the last checksum digit
        */
        barcode.setData("0123456789012");

        // Leitcode Wide Narrow bar Ratio
        // Valid value is from 2.0 to 3.0 inclusive.
        barcode.setN(3.0f);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testMSI(Canvas canvas) throws Exception
    {
        MSI barcode = new MSI();

        /*
           MSI Plessey Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
        */
        barcode.setData("112233445566");

        barcode.setChecksumType(MSI.CHECKSUM_AUTO);

        // addCheckSum is for MSI.CHECKSUM_AUTO,
        // not valid for  CHECKSUM_10, CHECKSUM_11, CHECKSUM_1010, CHECKSUM_1110.
        barcode.setAddCheckSum(true);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testONECODE(Canvas canvas) throws Exception
    {
        Onecode barcode = new Onecode();

        /*
           USPS OneCode Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           USPS OneCode Valid data length: 20, 25, 29, 31 digits only
        */
        barcode.setData("01234567890123456789");

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testPLANET(Canvas canvas) throws Exception
    {
        Planet barcode = new Planet();

        /*
           PLANET Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           PLANET Valid data length: 11 or 13 digits only, excluding the last checksum digit
        */
        barcode.setData("01234567890");

        // PLANET Short bar vs high bar ratio
        barcode.setShortTallRatio(0.4f);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testPOSTNET(Canvas canvas) throws Exception
    {
        Postnet barcode = new Postnet();

        /*
           POSTNET Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           POSTNET Valid data length: 5, 6, 9, 11 digits only, excluding the last checksum digit
        */
        barcode.setData("01234");

        // POSTNET Short bar vs high bar ratio
        barcode.setShortTallRatio(0.4f);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testRM4SCC(Canvas canvas) throws Exception
    {
        RM4SCC barcode = new RM4SCC();

        /*
           RM4SCC Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
                A - Z (Uppercase letters)
        */
        barcode.setData("123");

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testUPCA(Canvas canvas) throws Exception
    {
        UPCA barcode = new UPCA();

        /*
           UPC-A Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           UPC-A Valid data length: 11 digits only, excluding the last checksum digit
        */
        barcode.setData("472290");

        // for UPC-A with supplement data (2 or 5 digits)
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
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(45f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 10));
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

    private static void testUPCE(Canvas canvas) throws Exception
    {
        UPCE barcode = new UPCE();

        /*
           UPC-E Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           UPC-E Valid data length: 6 digits only, excluding the first UPCE number system and the last checksum digit
        */
        barcode.setData("012345");

        // UPCE number system: 0 or 1
        barcode.setUpceNumber(0);

        // for UPC-E with supplement data (2 or 5 digits)
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
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(45f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 10));
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

    private static void testCODE128(Canvas canvas) throws Exception
    {
        Code128 barcode = new Code128();

        /*
           Code 128 Valid data char set:
                all 128 ASCII characters (Char from 0 to 127)
        */
        barcode.setData("112233445566");

        //  Set the processTilde property to true, if you want use the tilde character "~"
        //  to specify special characters in the input data. Default is false.
        //  1) All 128 ISO/IEC 646 characters, i.e. characters 0 to 127 inclusive, in accordance with ISO/IEC 646.
        //       NOTE This version consists of the G0 set of ISO/IEC 646 and the C0 set of ISO/IEC 6429 with values 28 - 31
        //       modified to FS, GS, RS and US respectively.
        //  2) Characters with byte values 128 to 255 may also be encoded.
        //  3) 4 non-data function characters.
        //  4) 4 code set selection characters.
        //  5) 3 Start characters.
        //  6) 1 Stop character.
        barcode.setProcessTilde(false);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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



    private static void testEAN128(Canvas canvas) throws Exception
    {
        EAN128 barcode = new EAN128();

        /*
           GS1-128 / EAN-128 Valid data char set:
                all 128 ASCII characters (Char from 0 to 127)

           to encode Application Identifier (AI), add "()" around the AI code, and followed by the AI data
           such as: (00)350123451234567894(21)01234567
        */
        barcode.setData("(00)350123451234567894");

        //  Set the processTilde property to true, if you want use the tilde character "~"
        //  to specify special characters in the input data. Default is false.
        //  1) All 128 ISO/IEC 646 characters, i.e. characters 0 to 127 inclusive, in accordance with ISO/IEC 646.
        //       NOTE This version consists of the G0 set of ISO/IEC 646 and the C0 set of ISO/IEC 6429 with values 28 - 31
        //       modified to FS, GS, RS and US respectively.
        //  2) Characters with byte values 128 to 255 may also be encoded.
        //  3) 4 non-data function characters.
        //  4) 4 code set selection characters.
        //  5) 3 Start characters.
        //  6) 1 Stop character.
        barcode.setProcessTilde(true);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(1f);
        // barcode bar module height (Y) in pixel
        barcode.setY(75f);

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
        barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
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

    private static void testDataMatrix(Canvas canvas) throws Exception
    {
        DataMatrix barcode = new DataMatrix();

        /*
           Data Matrix Valid data char set:
                ASCII values 0 - 127 in accordance with the US national version of ISO/IEC 646
	            ASCII values 128 - 255 in accordance with ISO 8859-1. These are referred to as extended ASCII.

        */
        barcode.setData("112233445566");

        barcode.setDataMode(DataMatrix.M_AUTO);

        // if your selected format mode doesnot have enough space to encode your data,
        // the library will choose the right format mode for you automatically.
        barcode.setFormatMode(DataMatrix.F_10X10);

        //  Set the processTilde property to true, if you want use the tilde character "~" to specify special characters in the input data. Default is false.
        //  1-byte character: ~ddd (character value from 0 ~ 255)
        //  ASCII (with EXT): from ~000 to ~255
        //  2-byte character: ~6ddddd (character value from 0 ~ 65535)
        //  Unicode: from ~600000 to ~665535
        //  ECI: from ~7000000 to ~7999999
        barcode.setProcessTilde(true);
        
        //  if you want to encode GS1 compatible Data Matrix, you need set FNC1 mode to IBarcode.FNC1_ENABLE
        barcode.setFnc1Mode(IBarcode.FNC1_NONE);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(3f);

        barcode.setLeftMargin(10f);
        barcode.setRightMargin(10f);
        barcode.setTopMargin(10f);
        barcode.setBottomMargin(10f);
        // barcode image resolution in dpi
        barcode.setResolution(72);
        
        // barcode bar color and background color in Android device
        barcode.setForeColor(AndroidColor.black);
        barcode.setBackColor(AndroidColor.white);

        /*
        specify your barcode drawing area
	    */
	    RectF bounds = new RectF(30, 30, 0, 0);
        barcode.drawBarcode(canvas, bounds);
    }

    private static void testPDF417(Canvas canvas) throws Exception
    {
        PDF417 barcode = new PDF417();

        /*
           PDF-417 Valid data char set:
               1. Text Compaction mode permits all printable ASCII characters to be encoded,
                i.e. values 32 - 126 inclusive in accordance with ISO/IEC 646 (IRV),
                as well as selected control characters.
               2. Byte Compaction mode permits all 256 possible 8-bit byte values to be encoded.
                This includes all ASCII characters value 0 to 127 inclusive and provides for international character set support.
               3. Numeric Compaction mode permits efficient encoding of numeric data strings.
               4. Up to 811 800 different character sets or data interpretations.
               5. Various function codewords for control purposes.
        */
        barcode.setData("112233445566");

        // PDF 417 Error Correction Level
        barcode.setEcl(PDF417.ECL_2);
        barcode.setRowCount(30);
        barcode.setColumnCount(5);
        barcode.setDataMode(PDF417.M_AUTO);

        barcode.setTruncated(false);

        //  Set the processTilde property to true, if you want use the tilde character "~" to specify special characters in the input data. Default is false.
        //  1-byte character: ~ddd (character value from 0 ~ 255)
        //  ASCII (with EXT): from ~000 to ~255
        //  2-byte character: ~6ddddd (character value from 0 ~ 65535)
        //  Unicode: from ~600000 to ~665535
        //  ECI: from ~7000000 to ~7999999
        barcode.setProcessTilde(true);

        /*
        // for macro PDF 417
        barcode.setMacro(false);
        barcode.setMacroSegmentIndex(0);
        barcode.setMacroSegmentCount(0);
        barcode.setMacroFileIndex(0);
        */

        // unit of measure for X, Y, LeftMargin, RightMargin, TopMargin, BottomMargin
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode module width in pixel
        barcode.setX(1f);
        barcode.setXtoYRatio(0.3f);

        barcode.setLeftMargin(10f);
        barcode.setRightMargin(10f);
        barcode.setTopMargin(10f);
        barcode.setBottomMargin(10f);
        // barcode image resolution in dpi
        barcode.setResolution(72);
        
        // barcode bar color and background color in Android device
        barcode.setForeColor(AndroidColor.black);
        barcode.setBackColor(AndroidColor.white);

        /*
        specify your barcode drawing area
	    */
	    RectF bounds = new RectF(30, 30, 0, 0);
        barcode.drawBarcode(canvas, bounds);
    }

    private static void testQRCode(Canvas canvas) throws Exception
    {
        QRCode barcode = new QRCode();

        /*
           QRCode Valid data char set:
                numeric data (digits 0 - 9);
                alphanumeric data (digits 0 - 9; upper case letters A -Z; nine other characters: space, $ % * + - . / : );
                byte data (default: ISO/IEC 8859-1);
                Kanji characters
        */
                       //BIZCARD:N:Kelly;X:Goto;T:Design Ethnographer;C:gotomedia LLC;A:2169 Folsom Street M302;B:4158647007;F:4158647004;M:4159907005;E:kelly@gotomedia.com;;
        barcode.setData("BIZCARD:N:Kelly;X:Goto;T:Design Ethnographer;C:gotomedia LLC;A:2169 Folsom Street M302;B:4158647007;F:4158647004;M:4159907005;E:kelly@gotomedia.com;;");
        barcode.setDataMode(QRCode.M_AUTO);
        barcode.setVersion(1);
        barcode.setEcl(QRCode.ECL_L);
        
        //  if you want to encode GS1 compatible QR Code, you need set FNC1 mode to IBarcode.FNC1_ENABLE
        barcode.setFnc1Mode(IBarcode.FNC1_NONE);

        //  Set the processTilde property to true, if you want use the tilde character "~" to specify special characters in the input data. Default is false.
        //  1-byte character: ~ddd (character value from 0 ~ 255)
        //  ASCII (with EXT): from ~000 to ~255
        //  2-byte character: ~6ddddd (character value from 0 ~ 65535)
        //  Unicode: from ~600000 to ~665535
        //  ECI: from ~7000000 to ~7999999
        //  SJIS: from ~9ddddd (Shift JIS 0x8140 ~ 0x9FFC and 0xE040 ~ 0xEBBF)
        barcode.setProcessTilde(false);

        // unit of measure for X, Y, LeftMargin, RightMargin, TopMargin, BottomMargin
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode module width in pixel
        barcode.setX(3f);

        barcode.setLeftMargin(50f);
        barcode.setRightMargin(50f);
        barcode.setTopMargin(50f);
        barcode.setBottomMargin(50f);
        // barcode image resolution in dpi
        barcode.setResolution(72);
        
        // barcode bar color and background color in Android device
        barcode.setForeColor(AndroidColor.black);
        barcode.setBackColor(AndroidColor.white);

        /*
        specify your barcode drawing area
	    */
	    RectF bounds = new RectF(0, 0, 0, 0);
        barcode.drawBarcode(canvas, bounds);
    }

}
