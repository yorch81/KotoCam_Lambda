package net.yorch.aws;

/**
 * InputImg<br>
 * 
 * InputImg Input JSON data to Lambda Function<br><br>
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
public class InputImg {
	/**
	 * Filter name
	 */
	String filter;
	
	/**
	 * Base64 image string
	 */
	String img;
	
	/**
	 * Filter scale 0 - 100
	 */
	String scale;
	
	/**
	 * Gets filter name
	 * 
	 * @return String
	 */
    public String getFilter() {
		return filter;
	}
    
    /**
     * Sets filter name
     * 
     * @param filter String
     */
	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	/**
	 * Gets Base64 image
	 * 
	 * @return String
	 */
	public String getImg() {
		return img;
	}
	
	/**
	 * Sets Base64 image
	 * 
	 * @param img String
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	/**
	 * Gets scale image
	 * 
	 * @return String
	 */
	public String getScale() {
		return scale;
	}
	
	/**
	 * Sets scale image
	 * 
	 * @param scale String
	 */
	public void setScale(String scale) {
		this.scale = scale;
	}
}
