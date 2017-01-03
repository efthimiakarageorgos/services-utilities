/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package  io.qio.qa.lib.S3Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

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

    public ArrayList<String> LS() {
        try {
        	ArrayList<String> bucketList = new ArrayList<String>();
            for (com.amazonaws.services.s3.model.Bucket bucket : s3client.listBuckets()) {
                //logger.info(bucket.getName() + " " + bucket.getCreationDate() + " " + bucket.getOwner());
            	logger.info(bucket.getName());
            	bucketList.add(bucket.getName());
               }
            return bucketList;
        } catch (Exception e) {
        	logger.info("Unable to list the s3 bucket-" + this.bucketName);
        	return null;
        }
    }
    
    public Map<String, String> ListBucketObject(String bucketName) {
    	ObjectListing listing = s3client.listObjects(new ListObjectsRequest().withBucketName(bucketName));
    	//ArrayList<String> objectList = new ArrayList<String>();
    	Map<String, String> objectList = new HashMap<String,String>();
    	for (S3ObjectSummary objectSummary : listing.getObjectSummaries()) {
    		//objectList.add(objectSummary.getKey());
    		objectList.put(objectSummary.getKey(), Long.toString(objectSummary.getSize()));
    		//System.out.println(" -> " + objectSummary.getKey() + "  " + "(size = " + objectSummary.getSize()/1024 + " KB)");
    		}	
    	return objectList;
    }
    
    public boolean checkForObjectInBucket(String bucketName, String fileName) {
    	Map<String, String> objectList=ListBucketObject(bucketName);
    	for (Map.Entry<String, String> entry : objectList.entrySet()){
    		if (entry.getKey().contains(fileName)){
    			return  true;
    		}
    	}
    	return false;
    }
    
    public String getFilesize(String bucketName, String fileName) {
    	Map<String, String> objectList=ListBucketObject(bucketName);
    	for (Map.Entry<String, String> entry : objectList.entrySet()){
    		if (entry.getKey().contains(fileName)){
    			return entry.getValue();
    		
    		}		
    	}
    	
    	return null;
    }
    
    public void DELETE() {
        try {
            s3client.deleteBucket(bucketName);
        } catch (Exception e) {
        	logger.info("Unable to delete the bucket");
        }
    }
}
