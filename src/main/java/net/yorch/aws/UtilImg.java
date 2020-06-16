package net.yorch.aws;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;

public class UtilImg {
	public static BufferedImage getBufferedImage(String imageString) {        
        BufferedImage image = null;
        byte[] imageByte;

        BASE64Decoder decoder = new BASE64Decoder();

        try {
			imageByte = decoder.decodeBuffer(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
	        image = ImageIO.read(bis);
	        bis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
		return image;
	}
	
	public static String getFileName() {
		return UUID.randomUUID().toString().replace("-", "") + ".png";
	}
	
	public static String getUrlS3() {
		return "https://kotocam.s3.us-east-2.amazonaws.com/";
	}
}
