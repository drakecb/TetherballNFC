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
 * The Original Code is 'iText, a free JAVA-PDF library'.
 * 
 * The Initial Developer of the Original Code is Bruno Lowagie. Portions created by
 * the Initial Developer are Copyright (C) 1999, 2000, 2001, 2002 by Bruno Lowagie.
 * All Rights Reserved.
 * Co-Developer of the code is Paulo Soares. Portions created by the Co-Developer
 * are Copyright (C) 2000, 2001, 2002 by Paulo Soares. All Rights Reserved.
 *
 * Derived Code is 'jbars, a free JAVA barcode generation library'
 * The initial developer of jbars is Andrés Ederra. Portions created by the initial
 * Developer of jbars are Copyright (C) 2003 by Andrés Ederra
 **/
package org.jbars;

import java.awt.Image;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.PNGEncodeParam;
import com.sun.media.jai.codec.JPEGEncodeParam;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.awt.Color;
import java.awt.Font;

/** Base class containing properties and methods commom to all barcode types.
 *
 * This class is based on iText "A Free Java-PDF library by Bruno Lowagie and Paulo Soares" using the Mozilla Public License(MPL)
 *
  @author Andres Ederra, Paulo Soares 
 */
public abstract class Barcode {
	/** A type of barcode */
	public static final int EAN13 = 1;
	/** A type of barcode */
	public static final int EAN8 = 2;
	/** A type of barcode */
	public static final int UPCA = 3;
	/** A type of barcode */
	public static final int UPCE = 4;
	/** A type of barcode */
	public static final int SUPP2 = 5;
	/** A type of barcode */
	public static final int SUPP5 = 6;
	/** A type of barcode */
	public static final int POSTNET = 7;
	/** A type of barcode */
	public static final int PLANET = 8;
	/** A type of barcode */
	public static final int CODE128 = 9;
	/** A type of barcode */
	public static final int CODE128_UCC = 10;
	/** A type of barcode */
	public static final int CODABAR = 11;
	/** A type of barcode */
	public static final int CODE93 = 12;
	/** A type of barcode */
	public static final int CODE128_RAW = 13;
	
	/** The minimum bar width.
	 */
	protected float x;

	/** The bar multiplier for wide bars or the distance between
	 * bars for Postnet and Planet.
	 */
	protected float n;

	/** The size of the text or the height of the shorter bar
	 * in Postnet.
	 */
	protected float size;

	/** The height of the bars.
	 */
	protected float barHeight;

	/** The text alignment. Can be <CODE>CoderBar.ALIGN_LEFT</CODE>,
	 * <CODE>Codebar.ALIGN_CENTER</CODE> or <CODE>Codebar.ALIGN_RIGHT</CODE>.
	 */
	protected int textAlignment;

	/** The optional checksum generation.
	 */
	protected boolean generateChecksum;

	/** Shows the generated checksum in the the text.
	 */
	protected boolean checksumText;

	/** Show the start and stop character '*' in the text for
	 * the barcode 39 or 'ABCD' for codabar.
	 */
	protected boolean startStopText;

	/** Generates extended barcode 39.
	 */
	protected boolean extended;

	/** The code to generate.
	 */
	protected String code = "";

	/** Show the guard bars for barcode EAN.
	 */
	protected boolean guardBars;

	/** The code type.
	 */
	protected int codeType;

	/**Left alignment*/
	public final static int ALIGN_LEFT = 0;
	;
	/**Rigth alignment*/
	public final static int ALIGN_RIGHT = 1;
	/**Center alignment*/
	public final static int ALIGN_CENTER = 2;
	/**
	* The option to generate a starting and ending quiet zone
	**/
	protected boolean quietZone = false;
	/**
	*	The font to use in the text of the codebar
	**/
	protected Font font = null;
	/**
	* The width of the quiet zone expressed in multiples of x(the minimun width of the bar)
	**/
	protected int quietZoneX = 10;
	/**The property to show the text for barcode.*/
	protected boolean showText = true;
	protected boolean transparent = false;
	protected Color backgroundColor = Color.white;
	public static final String PNG = "PNG";
	public static final String JPG = "JPG";
	public final static double ROTATE_90 = Math.PI / 2;
	public final static double ROTATE_180 = Math.PI;
	public final static double ROTATE_270 = (3 * Math.PI) / 2;
	public final static double ROTATE_0 = 0;
	public final static java.lang.String TYPE_CODE128 = "CODE128";
	;
	public final static java.lang.String TYPE_CODE93 = "CODE93";
	;
	public final static java.lang.String TYPE_INTERLEAVED2OF5 =
		"INTERLEAVED2OF5";
	;
	/**
	 * Creates a image representing a codebar
	 * using a codebar type and the codebar code
	 * @param codeType String Codebar type(Barcode.TYPE_CODE128 or Barcode.TYPE_CODE93 or Barcode.INTERLEAVED2OF5)
	 * @param codeValue String The code to represent
	 */
	public static synchronized Image createImage(
		String codeType,
		String codeValue) {

		Barcode barcode = null;
		if (codeType.equalsIgnoreCase(Barcode.TYPE_CODE128)) {
			barcode = new Barcode128();
		} else if (codeType.equalsIgnoreCase(Barcode.TYPE_CODE93)) {
			barcode = new Barcode93();
		} else if (codeType.equalsIgnoreCase(Barcode.TYPE_INTERLEAVED2OF5)) {
			barcode = new BarcodeInter25();
		} else {
			return null;
		}

		barcode.setCode(codeValue);
		barcode.setTransparent(false);

		BufferedImage image =
			new BufferedImage(
				(int) barcode.getBarcodeWidth(),
				100,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setPaint(Color.white);
		g.fillRect(0, 0, (int) barcode.getBarcodeWidth(), 100);
		barcode.placeBarcode(image, Color.black, Color.black);
		return image;
	}
	/**
	 * Creates a image representing a codebar
	 * using a codebar type and the codebar code
	 * @param codeType String Codebar type(Barcode.TYPE_CODE128 or Barcode.TYPE_CODE93 or Barcode.INTERLEAVED2OF5)
	 * @param codeValue String The code to represent
	 * @param barHeight float The bar height in pixels
	 * @param fontName String The name of the font to use
	 * @param fontSize int The size of the font to use
	 * @param transparent boolean Use transparent background
	 * @param alignment int Text alignment :use Barcode.ALIGN_CENTER, Barcode.ALIGN_LEFT, Barcode.ALIGN_RIGHT
	 * @param fontStyle int The style of the font to use: BOLD, ITALIC OR PLAIN
	 * @param barColor Color Color to use in the bars
	 * @param textColor Color Color to use in the text
	 * @param orientacion int rotation angle of the image in radians: 0,PI/2, PI or (3/2)*PI
	 */
	public static synchronized Image createImage(
		String codeType,
		String codeValue,
		float barHeight,
		String fontName,
		int fontSize,
		boolean transparent,
		int alignment,
		int fontStyle,
		Color newBarColor,
		Color newTextColor,
		int orientacion) {

		Barcode barcode = null;
		if (codeType.equalsIgnoreCase(Barcode.TYPE_CODE128)) {
			barcode = new Barcode128();
		} else if (codeType.equalsIgnoreCase(Barcode.TYPE_CODE93)) {
			barcode = new Barcode93();
		} else if (codeType.equalsIgnoreCase(Barcode.TYPE_INTERLEAVED2OF5)) {
			barcode = new BarcodeInter25();
		} else {
			return null;
		}

		barcode.setBarHeight(barHeight);
		barcode.setFontName(fontName);
		barcode.setFontSize(fontSize);
		barcode.setCode(codeValue);
		barcode.setTransparent(transparent);
		barcode.setTextAlignment(alignment);
		barcode.setFontStyle(fontStyle);

		BufferedImage image =
			new BufferedImage(
				(int) barcode.getBarcodeWidth(),
				(int) barcode.getBarHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setPaint(Color.white);
		g.fillRect(0, 0, (int) barcode.getBarcodeWidth(), 100);

		barcode.placeBarcode(image, newBarColor, newTextColor);
		try {
			double angulo = 0;
			if (orientacion == 180)
				angulo = Math.PI;
			else if (orientacion == 90)
				angulo = Math.PI / 2;
			else if (orientacion == 270)
				angulo = 3 * Math.PI / 2;

			image = Barcode.rotateImage(image, angulo);
		} catch (Exception e) {
			System.out.println("Error rotating image: " + e.getMessage());
		}
		return image;
	}

	/**
	* Creates a PNG image data that represents the barcode.
	* The size of the barcode width will be the minimun posible but optimal for reading
	* @return byte[] A bytearray witht he data of the image
	* @param y The x size of the image
	* @param barColor Color to use for the bars
	* @param textColor Color to use for the text
	* @param angle Rotation angle in radians
	*/
	public byte[] createPNG(
		int y,
		Color barColor,
		Color textColor,
		double angle)
		throws Exception {
		int x = Math.round(getBarcodeWidth());
		int colorspace = BufferedImage.TYPE_INT_RGB;
		//Create the PNG

		return createPNG(x, y, colorspace, barColor, textColor, angle);

	}
	/**
	* Creates a PNG image data that represents the barcode.
	* The size of the barcode width will be the minimun posible but optimal for reading
	* @return byte[] A bytearray witht he data of the image
	* @param y The x size of the image
	* @param barColor Color to use for the bars
	* @param textColor Color to use for the text
	*/
	public byte[] createPNG(int y, Color barColor, Color textColor) {
		int x = Math.round(getBarcodeWidth());
		int colorspace = BufferedImage.TYPE_INT_RGB;
		//Create the PNG

		return createPNG(x, y, colorspace, barColor, textColor);

	}

	/**
	 * Creates a PNG image data that represents the barcode.
	 * @return byte[] A bytearray witht he data of the image
	 * @param x The x size of the image
	 * @param y The x size of the image
	 * @param colorspace The type of colorspace to use
	 */
	public byte[] createPNG(
		int x,
		int y,
		int colorspace,
		Color barColor,
		Color textColor) {

		//Create na image
		BufferedImage image = new BufferedImage(x, y, colorspace);

		//Paint the background of the image
		Graphics2D g = (Graphics2D) image.createGraphics();
		g.setPaint(backgroundColor);
		g.fillRect(0, 0, x, y);

		//Paint the barcode
		placeBarcode(image, barColor, textColor);

		//Encode the image into a PNG
		return encodePNG(image);

	}
	/**
	* Creates a PNG image data that represents the barcode,and rotates the image.
	* @return byte[] A bytearray witht he data of the image
	* @param x The x size of the image
	* @param y The x size of the image
	* @param colorscape The type of colorspace to use
	* @param angle Rotation angle in radians
	*/
	public byte[] createPNG(
		int x,
		int y,
		int colorspace,
		Color barColor,
		Color textColor,
		double angle)
		throws Exception {

		//Create the image to place the barcode and the resulting image after rotation
		BufferedImage image = new BufferedImage(x, y, colorspace);
		BufferedImage imageRotated = null;

		//Paint the background of the image
		Graphics2D g = (Graphics2D) image.createGraphics();
		g.setPaint(backgroundColor);
		g.fillRect(0, 0, x, y);

		//Paint the barcode
		placeBarcode(image, barColor, textColor);

		//Rotate the image
		imageRotated = Barcode.rotateImage(image, angle);

		//Encode the image into a PNG
		return encodePNG(imageRotated);
	}
	/**
	 * Creates a PNG image data that represents the barcode.
	 * The size of the barcode width will be the minimun posible but optimal for reading
	 * @return byte[] A bytearray witht he data of the image
	 * @param y The x size of the image
	 * @param barColor Color to use for the bars
	 * @param textColor Color to use for the text
	 * @param color The type of colorspace to use
	 */
	public byte[] createPNG(
		int y,
		Color barColor,
		Color textColor,
		int colorspace) {
		int x = Math.round(getBarcodeWidth());

		//Create the PNG

		return createPNG(x, y, colorspace, barColor, textColor);

	}
	/**
	 * Creates a PNG image data that represents the barcode.
	 * The size of the barcode will be the minimun posible but optimal for reading
	 * @return byte[] A bytearray witht he data of the image
	 * @param y The x size of the image
	 * @param barColor Color to use for the bars
	 * @param textColor Color to use for the text
	 * @param color The type of colorspace to use
	 * @param angle Rotation angle in radians
	 */
	public byte[] createPNG(
		int y,
		Color barColor,
		Color textColor,
		int colorspace,
		double angle)
		throws Exception {
		int x = Math.round(getBarcodeWidth());

		//Create the PNG
		return createPNG(x, y, colorspace, barColor, textColor, angle);

	}
	/**
	 * Encodes an image into a PNG according to the barcode parameters
	 * @return byte[] bytes of the PNG image
	 * @param image java.awt.image.BufferedImage The image to encode
	 */
	protected byte[] encodePNG(BufferedImage image) {

		ByteArrayOutputStream baoe = new ByteArrayOutputStream();

		//PNG encoding parameters
		PNGEncodeParam.RGB PNGparams = new PNGEncodeParam.RGB();

		//Apply tranparency if necesary
		if (isTransparent()) {
			int[] back =
				{
					backgroundColor.getRed(),
					backgroundColor.getGreen(),
					backgroundColor.getBlue()};
			PNGparams.setBackgroundRGB(back);
			PNGparams.setTransparentRGB(back);
		}

		//Activate interlacing. Mozilla reads better interlaced images
		PNGparams.setInterlacing(true);

		//Encode the image
		ImageEncoder encoder =
			ImageCodec.createImageEncoder("PNG", baoe, PNGparams);
		try {
			encoder.encode(image);

			baoe.flush();
			byte[] pngBuffer = baoe.toByteArray();
			baoe.close();

			return pngBuffer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * Gets the background color to use
	 * @return java.awt.Color
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	/** Gets the maximum width that the barcode will occupy.
	 *  The lower left corner is always (0, 0).
	 * @return the size the barcode occupies.
	 */
	protected abstract float getBarcodeWidth();
	/** Gets the height of the bars.
	 * @return the height of the bars
	 */
	public float getBarHeight() {
		return barHeight;
	}
	/** Gets the code to generate.
	 * @return the code to generate
	 */
	public String getCode() {
		return code;
	}
	/** Gets the code type.
	 * @return the code type
	 */
	public int getCodeType() {
		return codeType;
	}
	/**
	 * Gets the font to use in the text of the barcode
	 * @return java.awt.Font
	 */
	public java.awt.Font getFont() {
		return font;
	}
	/**
	 * Gets name of the font to use in the text of the barcode
	 	 * Return null if no text is displayed
	 * @return int
	 */
	public String getFontName() {
		if (font == null)
			return null;
		else
			return font.getName();
	}
	/**
	 * Gets size of the font to use in the text of the barcode
		 * Return -1 if no text is displayed
	 * @return int
	 */
	public int getFontSize() {
		if (font == null)
			return -1;
		else
			return font.getSize();
	}
	/**
	 * Gets style of the font to use in the text of the barcode
	 * Return -1 if no text is displayed
	 * @return int
	 */
	public int getFontStyle() {
		if (font == null)
			return -1;
		else
			return font.getStyle();
	}
	/** Gets the bar multiplier for wide bars.
	 * @return the bar multiplier for wide bars
	 */
	public float getN() {
		return n;
	}
	/**
	 * Get the width of the quiet zone
	 * @return int
	 */
	public int getQuietZoneX() {
		return quietZoneX;
	}
	/** Gets the size of the text.
	 * @return the size of the text
	 */
	public float getSize() {
		return size;
	}
	/** Gets the text alignment. Can be <CODE>Barcode.ALIGN_LEFT</CODE>,
	 * <CODE>Barcode.ALIGN_CENTER</CODE> or <CODE>Barcode.ALIGN_RIGHT</CODE>.
	 * @return the text alignment
	 */
	public int getTextAlignment() {
		return textAlignment;
	}
	/** Gets the minimum bar width.
	 * @return the minimum bar width
	 */
	public float getX() {
		return x;
	}
	/** Gets the property to show the generated checksum in the the text.
	 * @return value of property checksumText
	 */
	public boolean isChecksumText() {
		return checksumText;
	}
	/** Gets the property to generate extended barcode 39.
	 * @return value of property extended.
	 */
	public boolean isExtended() {
		return extended;
	}
	/** Gets the optional checksum generation.
	 * @return the optional checksum generation
	 */
	public boolean isGenerateChecksum() {
		return generateChecksum;
	}
	/** Gets the property to show the guard bars for barcode EAN.
	 * @return value of property guardBars
	 */
	public boolean isGuardBars() {
		return guardBars;
	}
	/**
	 * Gets the property to show the quiet zone at start and end of the bars.
	 * @return boolean
	 */
	public boolean isQuietZone() {
		return quietZone;
	}
	/**
	 * Gets the property to show the text for barcode.
	 * @return boolean
	 */
	public boolean isShowText() {
		return showText;
	}
	/** Sets the property to show the start and stop character '*' in the text for
	 * the barcode 39.
	 * @return value of property startStopText
	 */
	public boolean isStartStopText() {
		return startStopText;
	}
	/**
	 * Get the tranaparent background property
	 * @return boolean
	 */
	public boolean isTransparent() {
		return transparent;
	}
	/** Places the barcode in a BufferedImage. The
	 * The bars and text are written in the following colors:<p>
	 * <P><TABLE BORDER=1>
	 * <TR>
	 *    <TH><P><CODE>barColor</CODE></TH>
	 *    <TH><P><CODE>textColor</CODE></TH>
	 *    <TH><P>Result</TH>
	 *    </TR>
	 * <TR>
	 *    <TD><P><CODE>null</CODE></TD>
	 *    <TD><P><CODE>null</CODE></TD>
	 *    <TD><P>bars and text painted with current fill color</TD>
	 *    </TR>
	 * <TR>
	 *    <TD><P><CODE>barColor</CODE></TD>
	 *    <TD><P><CODE>null</CODE></TD>
	 *    <TD><P>bars and text painted with <CODE>barColor</CODE></TD>
	 *    </TR>
	 * <TR>
	 *    <TD><P><CODE>null</CODE></TD>
	 *    <TD><P><CODE>textColor</CODE></TD>
	 *    <TD><P>bars painted with current color<br>text painted with <CODE>textColor</CODE></TD>
	 *    </TR>
	 * <TR>
	 *    <TD><P><CODE>barColor</CODE></TD>
	 *    <TD><P><CODE>textColor</CODE></TD>
	 *    <TD><P>bars painted with <CODE>barColor</CODE><br>text painted with <CODE>textColor</CODE></TD>
	 *    </TR>
	 * </TABLE>
	 * @param image the <CODE>BufferedImage</CODE> where the barcode will be placed
	 * @param barColor the color of the bars. It can be <CODE>null</CODE>
	 * @param textColor the color of the text. It can be <CODE>null</CODE>
	 * @return void
	 */
	public abstract void placeBarcode(
		java.awt.image.BufferedImage i,
		Color barColor,
		Color textColor);
	/**
	 * Rotates a image 
	 * Rotations allowed: PI/2, PI, 3*PI/2 and 0
	 * @param image Image to rorate
	 * @param angle Angle to rotate the image(values allowed: PI/2, PI, 3*PI/2 and 0)
	 */
	private static BufferedImage rotateImage(BufferedImage image, double angle)
		throws Exception {
		if (angle == ROTATE_0) {
			return image;
		} else if (
			angle != ROTATE_90 && angle != ROTATE_180 && angle != ROTATE_270) {
			throw new Exception("Invalid rotation angle specificated. Supported angles are: PI/2,PI or (3*PI)/2");
		}

		//Create the image to place the barcode and the resulting image after rotation
		int x = image.getWidth();
		int y = image.getHeight();
		int colorSpace = image.getType();
		BufferedImage imageRotated = null;

		//Depending of the orientation of the rotating image the size of the image will differ
		if (angle == ROTATE_90 || angle == ROTATE_270) {
			imageRotated = new BufferedImage(y, x, colorSpace);

		} else if (angle == ROTATE_180) {
			imageRotated = new BufferedImage(x, y, colorSpace);
		}

		//To rotate we apply to affine transformations
		//One that rotates arround coordiante 0,0
		//An another that translates the image to begin in 0,0 coordinate

		//Creating rotation transform
		AffineTransform rotateTransform =
			AffineTransform.getRotateInstance(angle, 0, 0);

		//Creating tranlation transform
		AffineTransform translateTransform = null;
		if (angle == ROTATE_90) {
			translateTransform = AffineTransform.getTranslateInstance(y, 0);
		} else if (angle == ROTATE_180) {
			translateTransform = AffineTransform.getTranslateInstance(x, y);
		} else if (angle == ROTATE_270) {
			translateTransform = AffineTransform.getTranslateInstance(0, x);
		}
		//Combinate transforms
		translateTransform.concatenate(rotateTransform);

		//Apply transform
		AffineTransformOp rotateOp =
			new AffineTransformOp(
				translateTransform,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		rotateOp.filter(image, imageRotated);

		return imageRotated;
	}
	/**
	 * Scales a logical coordinate to fit a physical image coordinate
	 * Example: Having the logical dimension 200, and the logical coordinate 20.5 we want
	 * to scale the coordinate to a 300 pixel physical dimension.
	 * The call should be: int physicalCoordinate=scale(300,200,20.5);
	 * Fecha de creación: (26/02/2003 12:20:14)
	 * @return int
	 * @param imageSize The size of the image
	 * @param totalSize The logical size
	 * @param coordinate The logical coordinate
	 */
	public int scale(int imageSize, float totalSize, float coordinate) {
		return Math.round(
			(new Float(imageSize).floatValue() * coordinate) / totalSize);
	}
	/**
	 * Set the backgroud color od the barcode
	 * Default color is white
	 * @param newBackgroundColor java.awt.Color
	 */
	public void setBackgroundColor(Color newBackgroundColor) {
		backgroundColor = newBackgroundColor;
	}
	/** Sets the height of the bars.
	 * @param barHeight the height of the bars
	 */
	public void setBarHeight(float barHeight) {
		this.barHeight = barHeight;
	}
	/** Sets the code to generate.
	 * @param code the code to generate
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/** Sets the code type.
	 * @param codeType the code type
	 */
	public void setCodeType(int codeType) {
		this.codeType = codeType;
	}
	/** Sets the property to show the generated checksum in the the text.
	 * @param checksumText new value of property checksumText
	 */
	public void setChecksumText(boolean checksumText) {
		this.checksumText = checksumText;
	}
	/** Sets the property to generate extended barcode 39.
	 * @param extended new value of property extended
	 */
	public void setExtended(boolean extended) {
		this.extended = extended;
	}
	/**
	 * Sets the font to use
	 * null font prints no text.
	 * default value is Helvetica,Plain, size 20
	 * @param newFont java.awt.Font
	 */
	public void setFont(java.awt.Font newFont) {
		font = newFont;
	}
	/**
	 * Modifies the font name
	 * @param newFontStyle int
	 */
	public void setFontName(String newFontName) {
		if (font != null)
			font = new Font(newFontName, font.getStyle(), font.getSize());
	}
	/**
	* Modifies the font size
	  * @param newFontSize int
	 */
	public void setFontSize(int newFontSize) {
		if (font != null)
			font = new Font(font.getName(), font.getStyle(), newFontSize);
	}
	/**
	 * Modifies the font style
	 * @param newFontType int
	 */
	public void setFontStyle(int newFontStyle) {
		if (font != null)
			font = new Font(font.getName(), newFontStyle, font.getSize());
	}
	/** Setter for property generateChecksum.
	 * @param generateChecksum New value of property generateChecksum.
	 */
	public void setGenerateChecksum(boolean generateChecksum) {
		this.generateChecksum = generateChecksum;
	}
	/** Sets the property to show the guard bars for barcode EAN.
	 * @param guardBars new value of property guardBars
	 */
	public void setGuardBars(boolean guardBars) {
		this.guardBars = guardBars;
	}
	/** Sets the bar multiplier for wide bars.
	 * @param n the bar multiplier for wide bars
	 */
	public void setN(float n) {
		this.n = n;
	}
	/**
	 * Enables/Disables quiet zone usage
	 * @param newQuietZone boolean
	 */
	public void setQuietZone(boolean newQuietZone) {
		quietZone = newQuietZone;
	}
	/**
	 * Set the width of the quiet zone
	 * @param newQuietZoneX int
	 */
	public void setQuietZoneX(int newQuietZoneX) {
		quietZoneX = newQuietZoneX;
	}
	/**
	 * Sets the property to show the text for barcode.
	 * @param newShowText boolean
	 */
	public void setShowText(boolean newShowText) {
		showText = newShowText;
	}
	/** Sets the size of the text.
	 * @param size the size of the text
	 */
	public void setSize(float size) {
		this.size = size;
	}
	/** Gets the property to show the start and stop character '*' in the text for
	 * the barcode 39.
	 * @param startStopText new value of property startStopText
	 */
	public void setStartStopText(boolean startStopText) {
		this.startStopText = startStopText;
	}
	/** Sets the text alignment. Can be <CODE>Element.ALIGN_LEFT</CODE>,
	 * <CODE>Element.ALIGN_CENTER</CODE> or <CODE>Element.ALIGN_RIGHT</CODE>.
	 * @param textAlignment the text alignment
	 */
	public void setTextAlignment(int textAlignment) {
		this.textAlignment = textAlignment;
	}
	/**
	 * Sets the tranaparent background property
	 * Default tranparency is false
	 * @param newTransparent boolean
	 */
	public void setTransparent(boolean newTransparent) {
		transparent = newTransparent;
	}
	/** Sets the minimum bar width.
	 * @param x the minimum bar width
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	* Creates a JPG image data that represents the barcode.
	* The size of the barcode width will be the minimun posible but optimal for reading
	* @return byte[] A bytearray witht he data of the image
	* @param y The x size of the image
	* @param barColor Color to use for the bars
	* @param textColor Color to use for the text
	* @param angle Rotation angle in radians
	*/
	public byte[] createJPG(
		int y,
		Color barColor,
		Color textColor,
		double angle)
		throws Exception {
		int x = Math.round(getBarcodeWidth());
		int colorspace = BufferedImage.TYPE_INT_RGB;
		//Create the JPG

		return createJPG(x, y, colorspace, barColor, textColor, angle);

	}
	/**
	 * Creates a PNG image data that represents the barcode.
	 * The size of the barcode width will be the minimun posible but optimal for reading
	 * @return byte[] A bytearray witht he data of the image
	 * @param y The x size of the image
	 * @param barColor Color to use for the bars
	 * @param textColor Color to use for the text
	 * @param color The type of colorspace to use
	 */
	public byte[] createJPG(
	    int y,
	    Color barColor,
	    Color textColor,
	    int colorspace) {
	    int x = Math.round(getBarcodeWidth());
	
	    //Create the PNG
	
	    return createJPG(x, y, colorspace, barColor, textColor);
	
	}
	/**
	 * Creates a PNG image data that represents the barcode.
	 * The size of the barcode will be the minimun posible but optimal for reading
	 * @return byte[] A bytearray witht he data of the image
	 * @param y The x size of the image
	 * @param barColor Color to use for the bars
	 * @param textColor Color to use for the text
	 * @param color The type of colorspace to use
	 * @param angle Rotation angle in radians
	 */
	public byte[] createJPG(
	    int y,
	    Color barColor,
	    Color textColor,
	    int colorspace,
	    double angle)
	    throws Exception {
	    int x = Math.round(getBarcodeWidth());
	
	    //Create the PNG
	
	    return createJPG(x, y, colorspace, barColor, textColor, angle);
	
	}
	/**
	 * Creates a PNG image data that represents the barcode.
	 * @return byte[] A bytearray witht he data of the image
	 * @param x The x size of the image
	 * @param y The x size of the image
	 * @param colorspace The type of colorspace to use
	 */
	public byte[] createJPG(
	    int x,
	    int y,
	    int colorspace,
	    Color barColor,
	    Color textColor) {
	
	    //Create a image
	    BufferedImage image = new BufferedImage(x, y, colorspace);
	
	    //Paint the background of the image
	    Graphics2D g = (Graphics2D) image.createGraphics();
	    g.setPaint(backgroundColor);
	    g.fillRect(0, 0, x, y);
	
	    //Paint the barcode
	    placeBarcode(image, barColor, textColor);
	
	    //Encode the image into a PNG
	    return encodeJPG(image);
	
	}
	/**
	* Creates a PNG image data that represents the barcode,and rotates the image.
	* @return byte[] A bytearray witht he data of the image
	* @param x The x size of the image
	* @param y The x size of the image
	* @param colorscape The type of colorspace to use
	* @param angle Rotation angle in radians
	*/
	public byte[] createJPG(
	    int x,
	    int y,
	    int colorspace,
	    Color barColor,
	    Color textColor,
	    double angle)
	    throws Exception {
	
	    //Create the image to place the barcode and the resulting image after rotation
	    BufferedImage image = new BufferedImage(x, y, colorspace);
	    BufferedImage imageRotated = null;
	
	    //Paint the background of the image
	    Graphics2D g = (Graphics2D) image.createGraphics();
	    g.setPaint(backgroundColor);
	    g.fillRect(0, 0, x, y);
	
	    //Paint the barcode
	    placeBarcode(image, barColor, textColor);
	
	    //Rotate the image
	    imageRotated = Barcode.rotateImage(image, angle);
	
	    //Encode the image into a PNG
	    return encodeJPG(imageRotated);
	}
	/**
	 * Encodes an image into a PNG according to the barcode parameters
	 * @return byte[] bytes of the PNG image
	 * @param image java.awt.image.BufferedImage The image to encode
	 */
	protected byte[] encodeJPG(BufferedImage image) {
	
	    ByteArrayOutputStream baoe = new ByteArrayOutputStream();
	
	    //PNG encoding parameters
	    JPEGEncodeParam JPGparams = new JPEGEncodeParam();
	
	
	
	
	    //Encode the image
	    ImageEncoder encoder = ImageCodec.createImageEncoder("JPEG", baoe, JPGparams);
	    try {
	        encoder.encode(image);
	
	        baoe.flush();
	        byte[] jpgBuffer = baoe.toByteArray();
	        baoe.close();
	
	        return jpgBuffer;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	
	}
}
