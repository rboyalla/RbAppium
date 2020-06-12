/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.boyalla.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;
import static org.bytedeco.javacpp.tesseract.RIL_WORD;
import org.bytedeco.javacpp.tesseract.ResultIterator;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

/**
 *
 * @author raveendraboyalla
 */
public class FwOcrUtil {

    public static String getText(String imageString) {

        tesseract.TessBaseAPI ocrInstance = new tesseract.TessBaseAPI();
        ocrInstance.Init("/Users/raveendraboyalla/rb/proj/fwNew/data", "eng");
        lept.PIX image = lept.pixRead(imageString);
        ocrInstance.SetImage(image);
        BytePointer bytePointer = ocrInstance.GetUTF8Text();
        String text = bytePointer.getString();
        System.out.println("com.rb.tfw.core.ocr.FwOcrUtil.getText() text=" + text);
        return text;
    }

    public static void getTextFromImagePath() {
        System.out.println("com.rb.tfw.core.ocr.FwOcrUtil.main()" + getTextFromImagePath("/Users/raveendraboyalla/rb/proj/fwNew/data/Image1.jpg"));

        System.out.println("com.rb.tfw.core.ocr.FwOcrUtil.main()" + getTextFromImagePath("/Users/raveendraboyalla/rb/proj/fwNew/data/Image2.jpg"));

    }

    public static String getTextFromImagePath(String iamgePath) {

        tesseract.TessBaseAPI ocrInstance = new tesseract.TessBaseAPI();
        ocrInstance.Init("/Users/raveendraboyalla/rb/proj/fwNew/data", "eng");
        lept.PIX image = lept.pixRead(iamgePath);

        ocrInstance.SetImage(image);
        BytePointer bytePointer = ocrInstance.GetUTF8Text();
        String text = bytePointer.getString();
        System.out.println("com.rb.tfw.core.ocr.FwOcrUtil.getText() text=" + text);
        return text;
    }

    public static void OCRText( BytePointer outText) {
       

        TessBaseAPI api = new TessBaseAPI();
//        // Initialize tesseract-ocr with English, without specifying tessdata path
//
//        if (api.Init(null, "eng") != 0) {
//            System.err.println("Could not initialize tesseract.");
//            System.exit(1);
//        }
//
//        // Open input image with leptonica library
//        org.bytedeco.javacpp.lept.PIX image = pixRead("testimage.png");
//
//        // Get OCR result
       // outText = api.GetUTF8Text();
        System.out.println("OCR output:\n" + outText.getString());

        final ResultIterator ri = api.GetIterator();

        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        IntPointer coord1 = new IntPointer(x1);
        IntPointer coord2 = new IntPointer(y1);
        IntPointer coord3 = new IntPointer(x2);
        IntPointer coord4 = new IntPointer(y2);

        ri.Begin();

        if (ri != null) {

            do {
                BytePointer word = ri.GetUTF8Text(RIL_WORD);
                float conf = ri.Confidence(RIL_WORD);
                boolean box = ri.BoundingBox(RIL_WORD, coord1, coord2, coord3, coord4);

                System.out.println(word.getString());
                System.out.println(conf);
                System.out.println(box);
                System.out.println(coord1.get());
                System.out.println(coord2.get());

            } while (ri.Next(RIL_WORD));

        }

        api.End();
        outText.deallocate();
        // pixDestroy(image);
    }

    public static String getTextFromImage(BufferedImage imgBuff) {
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>> com.rb.tfw.core.ocr.FwOcrUtil.getText() text010");
            tesseract.TessBaseAPI ocrInstance = null;
            try {
                ocrInstance = new tesseract.TessBaseAPI();
            } catch (Throwable ex) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>> com.rb.tfw.core.ocr.FwOcrUtil.getText() text010A");

                ex.printStackTrace();
            }

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>> com.rb.tfw.core.ocr.FwOcrUtil.getText() text012");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>> com.rb.tfw.core.ocr.FwOcrUtil.getText() text012");

                ImageIO.write(imgBuff, "png", baos);
            } catch (IOException ex) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>> com.rb.tfw.core.ocr.FwOcrUtil.getText() text013");

                ex.printStackTrace();
            }
            byte[] imageBytes = baos.toByteArray();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>> com.rb.tfw.core.ocr.FwOcrUtil.getText() text014");
            ByteBuffer imgBB = ByteBuffer.wrap(imageBytes);

            //PIX image = pixReadMem(imgBB, imageBytes.length);
            //api.SetImage(image);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>> com.rb.tfw.core.ocr.FwOcrUtil.getText() text2");

            ocrInstance.Init("/Users/raveendraboyalla/rb/proj/fwNew/data", "eng");
            lept.PIX image = lept.pixReadMem(imgBB, imageBytes.length);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>> com.rb.tfw.core.ocr.FwOcrUtil.getText() text3");
            ocrInstance.SetImage(image);
            BytePointer bytePointer = ocrInstance.GetUTF8Text();
            
            
            
             final ResultIterator ri = ocrInstance.GetIterator();

        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        IntPointer coord1 = new IntPointer(x1);
        IntPointer coord2 = new IntPointer(y1);
        IntPointer coord3 = new IntPointer(x2);
        IntPointer coord4 = new IntPointer(y2);

        ri.Begin();

        if (ri != null) {

            do {
                BytePointer word = ri.GetUTF8Text(RIL_WORD);
                float conf = ri.Confidence(RIL_WORD);
                boolean box = ri.BoundingBox(RIL_WORD, coord1, coord2, coord3, coord4);

                System.out.println(word.getString());
                System.out.println(conf);
                System.out.println(box);
                System.out.println(coord1.get());
                System.out.println(coord2.get());

            } while (ri.Next(RIL_WORD));

        }

        
        
//            ocrInstance.getGetWords(bytePointer);
//
//            for (Word word : ) {
//                Rectangle rect = word.getBoundingBox();
//
//                System.out.println(rect.getMinX() + "," + rect.getMaxX() + "," + rect.getMinY() + "," + rect.getMaxY()
//                        + ": " + word.getText());
//            }


//OCRText(bytePointer);
            String text = bytePointer.getString();
            System.out.println("com.rb.tfw.core.ocr.FwOcrUtil.getText() text=" + text);
            return text;
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println("com.rb.tfw.core.ocr.FwOcrUtil.main()" + getTextFromImagePath("/Users/raveendraboyalla/rb/proj/fwNew/data/Image2.jpg"));

    }

}
