package net.yorch.aws;

import java.awt.image.BufferedImage;

import com.jhlabs.image.TritoneFilter;

/**
 * Tritone<br>
 * 
 * Tritone Implementation of Filter<br><br>
 * 
 * Copyright 2015 Jorge Alberto Ponce Turrubiates
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
 * @version    1.0.0, 2015-17-03
 * @author     <a href="mailto:the.yorch@gmail.com">Jorge Alberto Ponce Turrubiates</a>
 */
public class Tritone extends Filter {

	/**
	 * Apply Filter to BufferedImage
	 * 
	 * @param img BufferedImage Original Image
	 * @return BufferedImage Processed Image
	 */
	@Override
	public BufferedImage process(BufferedImage img) {
		return process(img, 50);
	}

	/**
	 * Apply Filter to BufferedImage with Scale Percent
	 * 
	 * @param img BufferedImage Original Image
	 * @param scale int Percent 0 to 100
	 * @return BufferedImage Processed Image
	 */
	@Override
	public BufferedImage process(BufferedImage img, int scale) {
		TritoneFilter filter = new TritoneFilter();
		filter.setHighColor(scale * 20);
		BufferedImage imgFilter = filter.filter(img, null);
		
		filter = null;
		
		return imgFilter;
	}
}
