/**Copyright 2003 by Andr�s Ederra.
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
 * The initial developer of jbars is Andr�s Ederra. Portions created by the initial
 * Developer of jbars are Copyright (C) 2003 by Andr�s Ederra
 **/
package org.jbars;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;



/** Implements the code interleaved 2 of 5. The text can include
 * non numeric characters that are printed but do not generate bars.
 * The default parameters are:
 * <pre>
 * x = 1f;
 * n = 2;
 * font=new Font("Helvetica", Font.PLAIN, 20)
 * textAlignment = ALIGN_CENTER;
 * generateChecksum = false;
 * checksumText = false;
 * transparent = true;
 * shotText = true
 * </pre>
 *
 * This class is based on iText "A Free Java-PDF library by Bruno Lowagie and Paulo Soares" using the Mozilla Public License(MPL)
 *
 * @author Andres Ederra, Paulo Soares 
 */
public class BarcodeInter25 extends Barcode {

    /** The bars to generate the code.
     */
    static byte BARS[][] = { { 0, 0, 1, 1, 0 }, {
            1, 0, 0, 0, 1 }, {
            0, 1, 0, 0, 1 }, {
            1, 1, 0, 0, 0 }, {
            0, 0, 1, 0, 1 }, {
            1, 0, 1, 0, 0 }, {
            0, 1, 1, 0, 0 }, {
            0, 0, 0, 1, 1 }, {
            1, 0, 0, 1, 0 }, {
            0, 1, 0, 1, 0 }
    };

    /** Creates new BarcodeInter25 */
    public BarcodeInter25() {
		//Wide to Narrow bar ratio is defined as follows in code93 standard:
		//Between 2:1 to 3:1 for sizes smaller than 0.020 inches
		//Between 2.2:1 to 3:1 for sizes larger than 0.020 inches
		//As we are working in a discrete environment, we can only use 2:1 or 3:1 ratio
		//We choose 3:1 ratio because it is valid for all sizes, and it is less error prone when doing image scalation operations
        x = 1f;
        n = 3;
        setFont(new Font("Helvetica", Font.PLAIN, 20));
        textAlignment = Barcode.ALIGN_CENTER;
        generateChecksum = false;
        checksumText = false;

    }
    /** Gets the maximum width that the barcode will occupy.
     *  The lower left corner is always (0, 0).
     * @return the size the barcode occupies.
     */
    public float getBarcodeWidth() {

        String fullCode = keepNumbers(code);
        int len = fullCode.length();
        if (generateChecksum)
            ++len;
        float fullWidth = len * (3 * x + 2 * x * n) + (6 + n) * x;

        int quietZone = 0;
        if (isQuietZone()) {
            quietZone = Math.round(quietZoneX * x);
            fullWidth = fullWidth + (quietZoneX * 2 * x);
        }

        return fullWidth;
    }
    /** Creates the bars for the barcode.
     * @param text the text. It can contain non numeric characters
     * @return the barcode
     */
    public static byte[] getBarsInter25(String text) {
        text = keepNumbers(text);
        byte bars[] = new byte[text.length() * 5 + 7];
        int pb = 0;
        bars[pb++] = 0;
        bars[pb++] = 0;
        bars[pb++] = 0;
        bars[pb++] = 0;
        int len = text.length() / 2;
        for (int k = 0; k < len; ++k) {
            int c1 = text.charAt(k * 2) - '0';
            int c2 = text.charAt(k * 2 + 1) - '0';
            byte b1[] = BARS[c1];
            byte b2[] = BARS[c2];
            for (int j = 0; j < 5; ++j) {
                bars[pb++] = b1[j];
                bars[pb++] = b2[j];
            }
        }
        bars[pb++] = 1;
        bars[pb++] = 0;
        bars[pb++] = 0;
        return bars;
    }
    /** Calculates the checksum.
     * @param text the numeric text
     * @return the checksum
     */
    public static char getChecksum(String text) {
        int mul = 3;
        int total = 0;
        for (int k = text.length() - 1; k >= 0; --k) {
            int n = text.charAt(k) - '0';
            total += mul * n;
            mul ^= 2;
        }
        return (char) (((10 - (total % 10)) % 10) + '0');
    }
    /** Deletes all the non numeric characters from <CODE>text</CODE>.
     * @param text the text
     * @return a <CODE>String</CODE> with only numeric characters
     */
    public static String keepNumbers(String text) {
        StringBuffer sb = new StringBuffer();
        for (int k = 0; k < text.length(); ++k) {
            char c = text.charAt(k);
            if (c >= '0' && c <= '9')
                sb.append(c);
        }
        return sb.toString();
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
    public void placeBarcode(
        BufferedImage image,
        Color barColor,
        Color textColor) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        int imageX = image.getWidth();
        int imageY = image.getHeight();
        String fullCode = code;
        int fontHeight = 0;
        int descent = 0;

        float fontX = 0;
        float fontY = 0;
        if (isShowText() && getFont() != null) {
            g.setFont(getFont());

            if (generateChecksum && checksumText)
                fullCode += getChecksum(fullCode);

            FontMetrics metrics = g.getFontMetrics();
            fontX = metrics.stringWidth(fullCode);
            fontY = imageY - metrics.getMaxDescent();
            fontHeight =
                metrics.getMaxAscent() + metrics.getMaxDescent() + metrics.getLeading();
            descent = metrics.getMaxDescent();
        }

        barHeight = imageY - fontHeight;

        String bCode = keepNumbers(code);
        if (generateChecksum)
            bCode += getChecksum(bCode);
        int len = bCode.length();
        float fullWidth = len * (3 * x + 2 * x * n) + (6 + n) * x;
        float barStartX = 0;
        float textStartX = 0;
        switch (textAlignment) {
            case Barcode.ALIGN_LEFT :
                break;
            case Barcode.ALIGN_RIGHT :
                textStartX = imageX - fontX;
                break;
            default :
                textStartX = (imageX - fontX) / 2;
                break;
        }

        float barStartY = 0;
        float textStartY = 0;
        textStartY = imageY - descent;

        int quietZone = 0;
        if (isQuietZone()) {
        	fullWidth = fullWidth + (quietZoneX * 2 * x);
            quietZone = scale(imageX, fullWidth, quietZoneX * x);
        }

        byte bars[] = getBarsInter25(bCode);
        boolean print = true;
        if (barColor != null)
            g.setPaint(barColor);
        for (int k = 0; k < bars.length; ++k) {
            float w = (bars[k] == 0 ? x : x * n);
            if (print) {
                g.fill(
                    new java.awt.Rectangle(
                        quietZone + scale(imageX, fullWidth, barStartX),
                        scale(imageY, barHeight, barStartY),
                        scale(imageX, fullWidth, w),
                        Math.round(barHeight)));
            }
            print = !print;
            barStartX += w;
        }
        if (isShowText() && getFont() != null) {
            g.drawString(fullCode, textStartX, textStartY);
        }
        return;
    }
}
