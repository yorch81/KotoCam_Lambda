package net.yorch.aws;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;

/**
 * UtilImg<br>
 * 
 * UtilImg Utils for manage images<br><br>
 * 
 * Copyright 2020 Jorge Alberto Ponce Turrubiates
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @version    1.0.0, 2020-17-06
 * @author     <a href="mailto:the.yorch@gmail.com">Jorge Alberto Ponce Turrubiates</a>
 */
public class UtilImg {
	/**
	 * Convert Base64 image String to BufferedImage
	 * 
	 * @param imageString String
	 * @return BufferedImage
	 */
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
	
	/**
	 * Gets unique file name
	 * 
	 * @return String
	 */
	public static String getFileName() {
		return UUID.randomUUID().toString().replace("-", "") + ".png";
	}
	
	/**
	 * Gets AWS S3 URL
	 * 
	 * @return String
	 */
	public static String getUrlS3() {
		return "https://kotocam.s3.us-east-2.amazonaws.com/";
	}
}
