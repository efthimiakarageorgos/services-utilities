/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package  io.qio.qa.lib.S3Helpers;

import org.apache.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

public class MAbstractS3Helper {
    public static AWSCredentials credentials;
    public static AmazonS3 s3client;
    public String secretKey;
    public String accessKey;
    public String bucketName;
    final static Logger logger = Logger.getRootLogger();


    public void CONNECT(String secretKey, String accessKey) {
        try {
            credentials = new BasicAWSCredentials(accessKey, secretKey);
            s3client = new AmazonS3Client(credentials);
   
           logger.info("Connectd to s3 client");
           /*  for (Bucket bucket : s3client.listBuckets()) {
            	System.out.println(" - " + bucket.getName());
            }*/
        } catch (Exception e) {
        	logger.info("Unable to connect to s3 client");
        }

    }

    public void LS() {
        try {
            for (com.amazonaws.services.s3.model.Bucket bucket : s3client.listBuckets()) {
                //logger.info(bucket.getName() + " " + bucket.getCreationDate() + " " + bucket.getOwner());
            	logger.info(bucket.getName());
            	
            }
        } catch (Exception e) {
        	logger.info("Unable to list the s3 bucket-" + this.bucketName);
        }
    }

    public void DELETE() {
        try {
            s3client.deleteBucket(bucketName);
        } catch (Exception e) {
        	logger.info("Unable to delete the bucket");
        }
    }

}
