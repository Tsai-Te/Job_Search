package com.te.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.net.URL;


public class StorageService {
    private AmazonS3 s3;
    private String bucket;

    public StorageService(){}

    public StorageService(AmazonS3 s3){
        this.s3=s3;
    }

    public void putObject (String S3key, File file) {
        s3.putObject(bucket,S3key,file);
    }

    public void putObject (String bucket, String S3key, File file) { //overload
        s3.putObject(bucket, S3key, file);
    }

    public S3Object getObject(String S3key){
        if (S3key==null){
            return null;
        } else {
            return s3.getObject(bucket, S3key);
        }
    }

    public S3Object getObject (String bucket, String S3key){

        return s3.getObject(bucket, S3key);
    }

    public String getObjectUrl (String S3key){
        URL urlObject=s3.getUrl(bucket,S3key);
        return urlObject.toString();
    }

    public void setBucket(String bucket){
        this.bucket=bucket;
    }
}
