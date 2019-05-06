package com.te.service;

import com.amazonaws.services.s3.AmazonS3;
import com.te.config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;

import static org.mockito.Mockito.*;

@WebAppConfiguration
@ContextConfiguration(classes={AppConfig.class}) //also in web.xml
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class StorageServiceTest {
    private String s3key="test";
    private String s3key2=null;
    private File file=new File("/Users/tsai_te/Desktop/testjpg.png");

    @InjectMocks
    @Autowired
    private StorageService storageService;

    @Value("#{databaseProperties['amazon.s3.bucket']}")
    protected String s3Bucket;

    @Mock
    private AmazonS3 client= Mockito.mock(AmazonS3.class);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        validateMockitoUsage();
    }

    @Test
    public void getObjectTest(){
        storageService.getObject(s3key);
        verify(client,times(1))
                .getObject(s3Bucket,s3key);
        storageService.getObject(s3key2);
        verify(client,times(1))
                .getObject(s3Bucket,s3key);
        storageService.getObject(s3Bucket,s3key);
        verify(client,times(2))
                .getObject(s3Bucket,s3key);
    }

    @Test
    public void putObjectTest(){
        storageService.putObject(s3Bucket,s3key,file);
        verify(client,times(1))
                .putObject(s3Bucket,s3key,file);
        storageService.putObject(s3Bucket,s3key2,file);
        verify(client,times(1))
                .putObject(s3Bucket,s3key2,file);
        storageService.putObject(s3key,file);
        verify(client,times(2))
                .putObject(s3Bucket,s3key,file);
    }
}
