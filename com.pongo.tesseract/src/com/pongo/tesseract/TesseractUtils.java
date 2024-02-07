package com.pongo.tesseract;

import java.awt.image.BufferedImage;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


public class TesseractUtils {
	
	public static String imageToText(BufferedImage image) throws TesseractException {
		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath("/usr/share/tesseract-ocr");
		
		return tesseract.doOCR(image);
	}
}
