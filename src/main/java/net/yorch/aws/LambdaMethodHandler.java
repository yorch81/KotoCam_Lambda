package net.yorch.aws;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * LambdaMethodHandler<br>
 * 
 * LambdaMethodHandler Java implementation for AWS Lambda<br><br>
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
public class LambdaMethodHandler implements RequestHandler<InputImg, OutImg> {
	
	/**
	 * Entry point for Lambda
	 * 
	 * @param input Input for Lambda Function
	 * @param context Context for Lambda Function
	 * @return OutImg Lambda response
	 */
	@Override
	public OutImg handleRequest(InputImg input, Context context) {
        Regions clientRegion = Regions.US_EAST_2;
        String bucketName = "kotocam";
        String filter = input.getFilter();
        int scale = Integer.valueOf(input.getScale()).intValue();
        String fileObjKeyName = filter + "_" +  UtilImg.getFileName();
        
        OutImg s3Image = new OutImg();
        
        try {
        	String awsKey = System.getenv("AWS_KEY");
        	String awsSecret = System.getenv("AWS_SECRET");
        	
        	BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsKey, awsSecret);
            
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                                    .withRegion(clientRegion)
                                    .build();
            
            // Convert String to BufferredImage
            String imageString = input.getImg();
            
            BufferedImage image = UtilImg.getBufferedImage(imageString);
            
            // Apply filter
            KotoFilter kf = new KotoFilter(filter);
            
            if (kf.isLoaded()) {
            	image = kf.process(image, scale);
            	s3Image.setS3Img(UtilImg.getUrlS3() + fileObjKeyName);
            	
            	// Write s3
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                
                ImageIO.write(image, "png", os);
                
                byte[] buffer = os.toByteArray();
                
                InputStream is = new ByteArrayInputStream(buffer);
                
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType("image/png");
                metadata.addUserMetadata("KotoCam", filter);
                metadata.setContentLength(buffer.length);
                PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, is, metadata);
                
                s3Client.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
            }
            else
            	s3Image.setS3Img("The selected filter does not exists");
        } catch (AmazonServiceException e) {
        	s3Image.setS3Img(e.getMessage());
        	context.getLogger().log(e.getMessage());
        } catch (SdkClientException e) {
        	s3Image.setS3Img(e.getMessage());
        	context.getLogger().log(e.getMessage());
        }  catch (IOException e) {
        	s3Image.setS3Img(e.getMessage());
        	context.getLogger().log(e.getMessage());
        }
        
        return s3Image;
    }
}

