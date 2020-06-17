package net.yorch.aws;

/**
 * OutImg<br>
 * 
 * OutImg Output JSON data from Lambda Function<br><br>
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
public class OutImg {
	/**
	 * AWS S3 URL image
	 */
	String s3Img;

	/**
	 * Gets AWS S3 URL image
	 * 
	 * @return String
	 */
	public String getS3Img() {
		return s3Img;
	}

	/**
	 * Sets AWS S3 URL image
	 * 
	 * @param s3Img String
	 */
	public void setS3Img(String s3Img) {
		this.s3Img = s3Img;
	}
}
