package net.yorch.aws;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * App<br>
 * 
 * App Main Class<br><br>
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
public class App {
	/**
	 * Principal class
	 * 
	 * @param args String[]
	 */
    public static void main( String[] args ) {
        System.out.println("Hello KotoCam on AWS !!!");
        
        try {
	        BufferedImage image = ImageIO.read(new File("C:/Code/img/mory.jpg"));
	        
	        KotoFilter kf = new KotoFilter("DoG");
	        
	        if (kf.isLoaded()) {
	        	BufferedImage imgTmp = kf.process(image, 30);
	        	
	        	String fileName = "C:/Code/img/kotocam_" + UtilImg.getFileName();;
				
				File imgFile = new File(fileName);
				
				ImageIO.write(imgTmp, "png", imgFile);
				
				System.out.println("Generated image: " + fileName);
	        }
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
}
