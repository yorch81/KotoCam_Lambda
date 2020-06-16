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

public class LambdaMethodHandler implements RequestHandler<InputImg, OutImg> {
	@Override
	public OutImg handleRequest(InputImg input, Context context) {
        Regions clientRegion = Regions.US_EAST_2;
        String bucketName = "kotocam";
        String filter = input.getFilter();
        int scale = Integer.valueOf(input.getScale()).intValue();
        String fileObjKeyName = filter + "_" +  UtilImg.getFileName();
                
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
            image = kf.process(image, scale);
            
            // Write s3
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            
            ImageIO.write(image, "png", os);
            
            byte[] buffer = os.toByteArray();
            
            InputStream is = new ByteArrayInputStream(buffer);
            
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/png");
            metadata.addUserMetadata("KotoCam", "Solarize");
            metadata.setContentLength(buffer.length);
            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, is, metadata);
            
            s3Client.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (AmazonServiceException e) {
        	context.getLogger().log(e.getMessage());
        } catch (SdkClientException e) {
        	context.getLogger().log(e.getMessage());
        }  catch (IOException e) {
        	context.getLogger().log(e.getMessage());
        }
        
        OutImg s3Image = new OutImg();
        s3Image.setS3Img(UtilImg.getUrlS3() + fileObjKeyName);
        
        return s3Image;
    }
}

