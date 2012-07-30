/**Copyright 2003 by Andrés Ederra.
 * The contents of this file are subject to the Mozilla Public License
 *	Version 1.1 (the "License"); you may not use this file except in
 *	compliance with the License. You may obtain a copy of the License at
 *	http://www.mozilla.org/MPL/
 *
 *	Software distributed under the License is distributed on an "AS IS"
 *	basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 *	License for the specific language governing rights and limitations
 *	under the License.
 *
 * The Original Code is  'jbars, a free JAVA barcode generation library'
 * The initial developer is Andrés Ederra. Portions created by the initial
 * Developer of jbars are Copyright (C) 2003 by Andrés Ederra
 **/
package org.jbars;

import java.io.*;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.*;

/**
 * Barcode generator servlet A servlet that creates PNG image of a barcode
 * according to the parameters sent to a the servlet
 * 
 * Mandatory HTTP parameters:
 * <ul>
 * <li>barcodeType</li>
 * <li>barcodeValue</li>
 * <li>barcodeType</li>
 * </ul>
 * <p>
 * Parameter Values:
 * <ul>
 * <li>barcodeType: CODE128, CODE93 or INTERLEAVED2OF5</li>
 * <li>barcodeValue: Any text if code128 or code93, any integer number if
 * interleaved2of5</li>
 * <li>sizeY: height in pixels of the image(default value: 50)</li>
 * <li>rotation: angle to rotate the image: 0,90,180 or 270</li>
 * <li>barColor:color of the bars: black,green, etc</li>
 * <li>textColor:color of the text: black,green, etc</li>
 * <li>showText: Show the text true/false</li>
 * <li>transparent: Use transparent background true/false</li>
 * <li>alignment: Text alignment: CENTER, LEFT or RIGHT</li>
 * <li>fontName: anme of the font, for example Helvetica</li>
 * <li>fontSize:Font size</li>
 * <li>fontStyle: Font style: BOLD, ITALIC or PLAIN</li>
 * <li>imageFormat: Format to use for the image: PNG or JPG</li>
 * </ul>
 * A very basic form to post data to generate a barcode
 * 
 * <pre>
 *           &lt;form method=&quot;POST&quot; action=&quot;/JBars/BarcodeServlet&quot;&gt;
 *      Barcode Type
 *      &lt;input type=&quot;text&quot; name=&quot;barcodeType&quot; &gt;
 *      &lt;br&gt;
 *      Text to encode:
 *      &lt;input type=&quot;text&quot; name=&quot;barcodeValue&quot; &gt;
 *      &lt;br&gt;
 *      Size Y(in pixels):
 *      &lt;input type=&quot;text&quot; name=&quot;sizeY&quot; &gt;
 *      &lt;br&gt;
 *      Rotation:
 *      &lt;input type=&quot;text&quot; name=&quot;rotation&quot; &gt;
 *      &lt;br&gt;
 *      Bar Color:
 *      &lt;input type=&quot;text&quot; name=&quot;barColor&quot; &gt;
 *      &lt;br&gt;
 *      showText:
 *      &lt;input type=&quot;text&quot; name=&quot;showText&quot; &gt;
 *      &lt;br&gt;
 *      fontName:
 *      &lt;input type=&quot;text&quot; name=&quot;fontName&quot; &gt;
 *      &lt;br&gt;
 *      fontSize:
 *      &lt;input type=&quot;text&quot; name=&quot;fontSize&quot; &gt;
 *      &lt;br&gt;
 *      fontStyle:
 *      &lt;input type=&quot;text&quot; name=&quot;fontStyle&quot; &gt;
 *      &lt;br&gt;
 *      transparent:
 *      &lt;input type=&quot;text&quot; name=&quot;transparent&quot; &gt;
 *      &lt;br&gt;
 *      alignment:
 *      &lt;input type=&quot;text&quot; name=&quot;alignment&quot; &gt;
 *      &lt;br&gt;
 *      &lt;input type=&quot;text&quot; name=&quot;imageFormat&quot; &gt;
 *      &lt;br&gt;
 *      &lt;input type=&quot;submit&quot; value=&quot;Generator Barcode&quot;&gt;
 * </pre>
 * 
 * @author Andres Ederra
 */
public class BarcodeServlet extends HttpServlet {
	// Default barcode heigth in pixels
	private static final int DEFAULT_BARCODE_HEIGTH = 50;

	/**
	 * HTTP GET handler Fecha de creación: (10/03/2003 13:28:37)
	 * 
	 * @param rew
	 *            javax.servlet.http.HttpServletRequest
	 * @param res
	 *            javax.servlet.http.HttpServletResponse
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			// Get parameters
			String barcodeType = req.getParameter("barcodeType");
			String barcodeValue = req.getParameter("barcodeValue");
			String sizeX = req.getParameter("sizeX");
			String sizeY = req.getParameter("sizeY");
			String rotation = req.getParameter("rotation");
			String barColor = req.getParameter("barColor");
			String backColor = req.getParameter("backColor");
			String showText = req.getParameter("showText");
			String fontName = req.getParameter("fontName");
			String fontSize = req.getParameter("fontSize");
			String fontStyle = req.getParameter("fontStyle");
			String transparent = req.getParameter("transparent");
			String alignment = req.getParameter("alignment");
			String imageFormat = req.getParameter("imageFormat");
			String quietZone = req.getParameter("quietZone");
			int x = 0;
			int y = 0;
			int quietZoneWidth = 0;

			// Check required parameters
			boolean error = false;
			String errorDescription = null;
			if (barcodeType == null) {
				error = true;
				errorDescription = "Error!\nBarcode type parameter(barcodeType) is missing.";
			} else if (barcodeValue == null) {
				error = true;
				errorDescription = "Error!\nBarcode value parameter(barcodeValue) is missing.";

			} else if (sizeX == null) {
				x = 0;

			} else {
				try {
					x = Integer.parseInt(sizeX);

				} catch (NumberFormatException nfe) {
					error = true;
					errorDescription = "Error!\nBarcode size isn't expresed in a numeric format.\nDetails:"
							+ nfe;

				}

			}
			if (sizeY == null) {
				y = 0;

			} else {
				try {
					y = Integer.parseInt(sizeY);

				} catch (NumberFormatException nfe) {
					error = true;
					errorDescription = "Error!\nBarcode size isn't expressed in a numeric format.\nDetails:"
							+ nfe;

				}

			}

			if (quietZone != null && quietZone.trim().equals("")) {
				try {
					quietZoneWidth = Integer.parseInt(quietZone);

				} catch (NumberFormatException nfe) {
					error = true;
					errorDescription = "Error!\nBarcode quiet zone width isn't expresed in a numeric format or as a empty string.\nDetails:"
							+ nfe;

				}
			} else {
				quietZoneWidth = -1;
			}

			if (imageFormat == null) {
				// Default encoding is PNG
				imageFormat = Barcode.PNG;
			}
			if (error) {
				try {
					res.sendError(HttpServletResponse.SC_BAD_REQUEST,
							errorDescription);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			if (imageFormat.equals(Barcode.PNG))
				res.setContentType("image/x-png");
			else if (imageFormat.equals(Barcode.JPG))
				res.setContentType("image/jpeg");

			Barcode barCode = null;

			if (barcodeType.equalsIgnoreCase("CODE128")) {
				barCode = new Barcode128();
				barCode.setCodeType(Barcode.CODE128);
			} else if (barcodeType.equalsIgnoreCase("CODE93")) {
				barCode = new Barcode93();
			} else if (barcodeType.equalsIgnoreCase("INTERLEAVED2OF5")) {
				barCode = new BarcodeInter25();
			}
			barCode.setCode(barcodeValue);

			if (x == 0) {
				x = Math.round(barCode.getBarcodeWidth());
			}

			if (y == 0) {
				y = DEFAULT_BARCODE_HEIGTH;

			}

			if (quietZoneWidth >= 0) {
				barCode.setQuietZone(true);
				barCode.setQuietZoneX(quietZoneWidth);
			} else if (quietZoneWidth < 0) {
				barCode.setQuietZone(false);
			}

			if (showText != null && showText.equalsIgnoreCase("false")) {
				barCode.setShowText(false);
			}

			try {
				if (fontName != null)
					barCode.setFontName(fontName);

				if (fontSize != null)
					barCode.setFontSize(Integer.parseInt(fontSize));

				if (fontStyle != null) {
					int style = Font.PLAIN;
					if (fontStyle.equalsIgnoreCase("BOLD")) {
						style = Font.BOLD;
					} else if (fontStyle.equalsIgnoreCase("ITALIC")) {
						style = Font.ITALIC;
					}
					barCode.setFontStyle(style);
				}
			} catch (Exception e) {
				try {
					res.sendError(HttpServletResponse.SC_BAD_REQUEST, e
							.toString());
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}

			if (transparent != null && transparent.equalsIgnoreCase("false")) {
				barCode.setTransparent(false);
			}

			if (alignment != null) {
				if (alignment.equalsIgnoreCase("CENTER")) {
					barCode.setTextAlignment(Barcode.ALIGN_CENTER);
				}
				if (alignment.equalsIgnoreCase("LEFT")) {
					barCode.setTextAlignment(Barcode.ALIGN_LEFT);
				}
				if (alignment.equalsIgnoreCase("RIGHT")) {
					barCode.setTextAlignment(Barcode.ALIGN_RIGHT);
				}
			}
			byte[] imageData = null;
			if (rotation != null && !rotation.equalsIgnoreCase("0")) {
				double angle = 0;
				if (rotation.equalsIgnoreCase("90")) {
					angle = Math.PI / 2;
				} else if (rotation.equalsIgnoreCase("180")) {
					angle = Math.PI;
				} else if (rotation.equalsIgnoreCase("270")) {
					angle = (3 * Math.PI) / 2;
				}
				try {
					if (imageFormat.equals(Barcode.PNG)) {
						imageData = barCode.createPNG(x, y,
								BufferedImage.TYPE_INT_RGB, Color.black,
								Color.black, angle);
					} else if (imageFormat.equals(Barcode.JPG)) {
						imageData = barCode.createJPG(x, y,
								BufferedImage.TYPE_INT_RGB, Color.black,
								Color.black, angle);
					} else {
						res.sendError(HttpServletResponse.SC_BAD_REQUEST,
								"Cannot process image format:" + imageFormat
										+ "\tValid formats are PNG and JPG");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} else {
				if (imageFormat.equals(Barcode.PNG)) {
					imageData = barCode.createPNG(x, y,
							BufferedImage.TYPE_INT_RGB, Color.black,
							Color.black);
				} else if (imageFormat.equals(Barcode.JPG)) {
					imageData = barCode.createJPG(x, y,
							BufferedImage.TYPE_INT_RGB, Color.black,
							Color.black);
				} else {
					res.sendError(HttpServletResponse.SC_BAD_REQUEST,
							"Cannot process image format:" + imageFormat
									+ "\tValid formats are PNG and JPG");
				}
			}
			res.setContentLength(imageData.length);
			try {
				OutputStream os = res.getOutputStream();
				os.write(imageData);
				os.flush();
				os.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
				try {
					res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							ioe.toString());
				} catch (IOException ioe2) {
					ioe2.printStackTrace();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * HTTP POST handler points to the HTTP GET handler Fecha de creación:
	 * (10/03/2003 13:28:37)
	 * 
	 * @param rew
	 *            javax.servlet.http.HttpServletRequest
	 * @param res
	 *            javax.servlet.http.HttpServletResponse
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		doGet(req, res);
	}
}
