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
import javax.swing.*;

import java.awt.image.*;
import java.awt.*;

/**
 * 
 * CommandLine barcode image files generator.
 * <p>
 * <b>Usage</b>:<br>
 * java jbars.BarcodeGenerator OutputFileName BarcodeType code sizeY
 * [-fontName=FontName] [-fontSize=FontSize] [-fontStyle=FontStyle]
 * [-transparent] [-rotation=rotation] [-barColor=BarColor]
 * [-textColor=TextColor] [-alignment=textAlignment] [-dontShowText]
 * <p>
 * <b>BarcodeType</b>: CODE128, CODE93,INTER25<br>
 * <b>code</b>: The barcode code to print<br>
 * <b>sizeY</b>: pixel height of the image(X size is calculated to the
 * non-distorting optimun size)<br>
 * *Optional* <b>fontName</b>: font to use in the text, must be a VM supported
 * font<br>
 * *Optional* <b>fontSize</b>: size of the font<br>
 * *Optional* <b>fontStyle</b>: PLAIN|BOLD|ITALIC<br>
 * *Optional* <b>transparent</b>: if present the backgroud will be transparent<br>
 * *Optional* <b>rotation</b>: degrees to rotate de image: 0,90,180 or 270<br>
 * *Optional* <b>barColor</b>: color to use for the bars in hexadecimal ARBG
 * format, 0x12C4A276 for example<br>
 * *Optional* <b>extColor</b>: color to use for the text in hexadecimal ARBG
 * format, 0x12C4A276 for example<br>
 * *Optional* <b>textAlignment</b>: text alignment: LEFT, RIGHT OR CENTER<br>
 * *Optional* <b>dontShowText</b>: if present no text will be shown<br>
 * 
 * 
 * @author Andres Ederra
 */
class BarcodeGenerator {

	public BarcodeGenerator() {
		super();

	}

public static void main(String args[]) {
		if (args.length < 4) {
			System.out
					.println("Usage:\njava jbars.BarcodeGenerator  OutputFileName BarcodeType code sizeY [-fontName=FontName] [-fontSize=FontSize] [-fontStyle=FontStyle] [-transparent] [-rotation=rotation] [-barColor=BarColor] [-textColor=TextColor] [-alignment=textAlignment] [-dontShowText] [-imageFormat=imageFormat] [-quietZone=quietZoneWidth]");
			System.out.println("BarcodeType: CODE128, CODE93,INTER25");
			System.out.println("code: The barcode code to print");
			System.out
					.println("sizeY: pixel height of the image(X size is calculated to the non-distorting optimun size)");
			System.out
					.println("*Optional* fontName: font to use in the text, must be a VM supported font");
			System.out.println("*Optional* fontSize: size of the font");
			System.out.println("*Optional* fontStyle: PLAIN|BOLD|ITALIC");
			System.out
					.println("*Optional* transparent: if present the backgroud will be transparent");
			System.out
					.println("*Optional* rotation: degrees to rotate de image: 0,90,180 or 270");
			System.out
					.println("*Optional* barColor: color to use for the bars in hexadecimal ARBG format, 0x12C4A276 for example");
			System.out
					.println("*Optional* textColor: color to use for the text in hexadecimal ARBG format, 0x12C4A276 for example");
			System.out
					.println("*Optional* alignment: text alignment: LEFT, RIGHT OR CENTER");
			System.out
					.println("*Optional* dontShowText: if present no text will be shown");
			System.out
					.println("*Optional* imageFormat: format of the image to generate: PNG OR JPG (default is PNG)");
			System.out
					.println("*Optional* quietZone: number of blank bars to prepend and append as blank zone(quiet zone)");

			System.exit(1);
		}
		String fileName = args[0];
		String type = args[1];
		String code = args[2];
		String sizeY = args[3];
		String fontName = null;
		String fontSize = null;
		String fontStyle = null;
		String transparent = null;
		String rotation = null;
		String barColor = null;
		String textColor = null;
		String alignment = null;
		String dontShowText = null;
		String imageFormat = Barcode.PNG;
		String quietZone = null;

		for (int i = 4; i < args.length; i++) {
			if (args[i].startsWith("-fontName=")) {
				fontName = args[i].substring(args[i].indexOf("=") + 1);
			}
			if (args[i].startsWith("-fontSize=")) {
				fontSize = args[i].substring(args[i].indexOf("=") + 1);
			}
			if (args[i].startsWith("-fontStyle=")) {
				fontStyle = args[i].substring(args[i].indexOf("=") + 1);
			}
			if (args[i].startsWith("-transparent")) {
				transparent = "true";
			}
			if (args[i].startsWith("-rotation=")) {
				rotation = args[i].substring(args[i].indexOf("=") + 1);
			}
			if (args[i].startsWith("-barColor=")) {
				barColor = args[i].substring(args[i].indexOf("=") + 1);
			}
			if (args[i].startsWith("-textColor=")) {
				textColor = args[i].substring(args[i].indexOf("=") + 1);
			}
			if (args[i].startsWith("-alignment=")) {
				alignment = args[i].substring(args[i].indexOf("=") + 1);
			}
			if (args[i].startsWith("-dontShowText")) {
				dontShowText = "true";
			}
			if (args[i].startsWith("-imageFormat=")) {
				imageFormat = args[i].substring(args[i].indexOf("=") + 1);
				if(!imageFormat.equals(Barcode.PNG) && !imageFormat.equals(Barcode.JPG)){
					System.out.println("WARNING!    Unknow image format. Image formar must be "+Barcode.PNG+" or "+Barcode.JPG
							+"\nWARNING!    Using PNG format as a fallback");
					imageFormat=Barcode.PNG;
				}
			}
			if (args[i].startsWith("-quietZone")) {
				quietZone = args[i].substring(args[i].indexOf("=") + 1);
			}
		}

		// Create Barcode
		Barcode barcode = null;
		if (type.equalsIgnoreCase("CODE128")) {
			barcode = new Barcode128();
		}
		if (type.equalsIgnoreCase("CODE93")) {
			barcode = new Barcode93();
		}
		if (type.equalsIgnoreCase("INTER25")) {
			barcode = new BarcodeInter25();
		}
		if (barcode == null) {
			System.out.println("Unknow barcode type: " + type);
			System.exit(1);
		}
		if (alignment != null) {
			if (alignment.equalsIgnoreCase("CENTER")) {
				barcode.setTextAlignment(Barcode.ALIGN_CENTER);
			}
			if (alignment.equalsIgnoreCase("LEFT")) {
				barcode.setTextAlignment(Barcode.ALIGN_LEFT);
			}
			if (alignment.equalsIgnoreCase("RIGHT")) {
				barcode.setTextAlignment(Barcode.ALIGN_RIGHT);
			}
		} else {
			barcode.setTextAlignment(Barcode.ALIGN_CENTER);
		}
		if (dontShowText != null)
			barcode.setShowText(false);
		
		if (quietZone != null){
			barcode.setQuietZone(true);
			barcode.setQuietZoneX(Integer.parseInt(quietZone));
		}
		
		barcode.setCode(code);
		if (transparent != null)
			barcode.setTransparent(true);
		
		if (fontName != null)
			barcode.setFontName(fontName);
		
		if (fontSize != null)
			barcode.setFontSize(Integer.parseInt(fontSize));
		
		if (fontStyle != null){
			int style = Font.PLAIN;
			if (fontStyle.equalsIgnoreCase("BOLD")) {
				style = Font.BOLD;
			} else if (fontStyle.equalsIgnoreCase("ITALIC")) {
				style = Font.ITALIC;
			}
			barcode.setFontStyle(style);
		}
		

		Color barColorColor;
		if (barColor != null) {
			barColorColor = new Color(Integer.decode(barColor).intValue());
		} else {
			barColorColor = Color.black;
		}
		Color textColorColor;
		if (textColor != null) {
			textColorColor = new Color(Integer.decode(textColor).intValue());
		} else {
			textColorColor = Color.black;
		}
		double angle = 0;
		if (rotation != null) {
			if (rotation.equals("90")) {
				angle = Math.PI / 2;
			}
			if (rotation.equals("180")) {
				angle = Math.PI;
			}
			if (rotation.equals("270")) {
				angle = 3 * Math.PI / 2;
			}

		}
		try {
			byte[] file=null;
			if(imageFormat.equals(Barcode.PNG)){
				file= barcode.createPNG(Integer.parseInt(sizeY),
					barColorColor, textColorColor, angle);
			}
			else if(imageFormat.equals(Barcode.JPG)){
				file= barcode.createJPG(Integer.parseInt(sizeY),
						barColorColor, textColorColor, angle);
			}
			else{
				System.out.println("Unknow image format: " + imageFormat);
				System.exit(1);	
			}

			FileOutputStream os = new FileOutputStream(new File(fileName));
			os.write(file);
			os.flush();
			os.close();
		} catch (Exception e) {
			System.out.println("Error:" + e);
			e.printStackTrace();
		}
	}	/**
		 * Test method: it generates a CODE128 barcode with "2800618795811" as value 
		 * The barcode is painted as a java image at a swing panel
		 */
	private static void printCode128() {
		try {
			BufferedImage imagen = new BufferedImage(600, 275,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = (Graphics2D) imagen.createGraphics();
			g.setPaint(Color.green);
			g.fillRect(0, 0, 600, 275);

			org.jbars.Barcode128 code128 = new org.jbars.Barcode128();
			code128.setCodeType(Barcode.CODE128);
			code128.setCode("2800618795811");
			code128.placeBarcode(imagen, Color.black, Color.blue);

			JFrame f = new JFrame("codigos de barras");
			f.setSize(880, 200);
			ImageIcon icon = new ImageIcon(imagen);
			JLabel label = new JLabel(icon);
			f.getContentPane().add(label);
			f.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method: it generates a CODE93 barcode with "17" as value  
	 * The barcode is painted as a java image at a swing panel
	 */
	private static void printCode93() {

		try {

			Image imagen = org.jbars.Barcode.createImage(
					org.jbars.Barcode.TYPE_CODE93, "17");

			JFrame f = new JFrame("codigos de barras");
			f.setSize(880, 200);
			ImageIcon icon = new ImageIcon(imagen);
			JLabel label = new JLabel(icon);
			f.getContentPane().add(label);
			f.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method: it generates a CODE93 barcode with "17" as value at /tmp/barra.png 
	 * 
	 */
	private static void printCode93aPNG() {

		try {

			org.jbars.Barcode93 code93 = new org.jbars.Barcode93();
			code93.setCodeType(Barcode.CODE93);
			code93.setCode("17");
			code93.setTransparent(true);

			code93.setFontSize(10);
			byte[] file = code93.createPNG(100, 50,
					java.awt.image.BufferedImage.TYPE_INT_RGB, Color.black,
					Color.black, org.jbars.Barcode.ROTATE_270);

			try {
				FileOutputStream os = new FileOutputStream(new File(
						"/tmp/barra.png"));
				os.write(file);
				os.flush();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserte aquí la descripción del método. Fecha de creación: (27/02/2003
	 * 13:09:20)
	 */
	private static void printCodeInterleaved() {
		try {
			BufferedImage imagen = new BufferedImage(200, 150,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) imagen.createGraphics();
			g.setPaint(Color.white);
			g.fillRect(0, 0, 200, 150);

			org.jbars.BarcodeInter25 code25 = new org.jbars.BarcodeInter25();
			code25.setGenerateChecksum(true);
			code25.setQuietZone(false);
			code25.setFont(null);
			code25.setCode("41-1200076041-001");
			code25.placeBarcode(imagen, Color.black, Color.black);

			JFrame f = new JFrame("codigos de barras");
			f.setSize(880, 200);
			ImageIcon icon = new ImageIcon(imagen);
			JLabel label = new JLabel(icon);
			f.getContentPane().add(label);
			f.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
