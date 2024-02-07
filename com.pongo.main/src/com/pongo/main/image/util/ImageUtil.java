package com.pongo.main.image.util;

import java.io.InputStream;
import javafx.scene.image.Image;


public class ImageUtil {
	
	public Image loadImage(String imagePath) {
	    InputStream imageStream = getClass().getResourceAsStream(imagePath);
	    if (imageStream != null) {
	        return new Image(imageStream); 
	    } else {
	        throw new RuntimeException("Image not found: " + imagePath);
	    }
	}
}
