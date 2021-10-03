package Testmaven.mavenproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;

import com.ZirohLabMethods.ResultS;
import com.ZirohLabMethods.ZirohLabsMethods;


import junit.framework.Assert;

public class AppTest 
{
	 static void printResult(ResultS result) {
	        if (result.getErrCode() == 0) {
	            System.out.println("\n" + result.getShortMsg());
	        } else {
	            System.err.println("Task Failed");
	            System.out.println("Error Message: " + result.getErrMsg());
	            System.out.println("Description : " + result.getLongMsg());
	        }
	    }

	 private static ResultS getTaskResult(FutureTask<ResultS> Result) throws InterruptedException, ExecutionException {
	        new Thread(Result).start();

	        new Thread(() -> {
	            try {
	                Thread.sleep(1100);
	            } catch (InterruptedException ignore) {
	            }

	            while (!Result.isDone()) {
	                try {
	                    Thread.sleep(500);
	                    if (!Result.isDone()) {
	                        System.out.print("Loading...\n");
	                    }
	                } catch (Exception e) {
	                }
	            }
	        }).start();
	        System.out.println("\nTask started..");
	        return Result.get();
	    }
	 @Test
	 public void GetconnectionFuturetask() throws InterruptedException, ExecutionException
	 {
	 ZirohLabsMethods app = new ZirohLabsMethods();



	 ResultS GetConnectionResult = new ResultS();
     FutureTask<ResultS> GetconnectionFuturetask = app.GetconnectionFuturetask();
     GetConnectionResult = getTaskResult(GetconnectionFuturetask);

     Assert.assertEquals(GetConnectionResult.getErrCode(), 0);
	 System.out.println("Connected......!!!!!!");
}
	 @Test
	    public void UploadFileTest()
	    {
		    ZirohLabsMethods app = new ZirohLabsMethods();
	        
	        String localPath = "/UploadFile";
	        String fileName = "UploadFile"+Math.abs(new Random().nextInt());

	        try {
	            PrintWriter writer = new PrintWriter(localPath + fileName);
	            writer.write("This is the test file for the UploadFile() function.");
	            writer.close();
	            File localFile = new File(localPath + fileName);

	       	    ResultS UploadResult = new ResultS();
	            FutureTask<ResultS> GetconnectionFuturetask = app.GetconnectionFuturetask();
	            UploadResult = getTaskResult(GetconnectionFuturetask);

	   
	            Assert.assertEquals(UploadResult.getErrCode(),0);
	            System.out.println("UploadFile is working fine");
	            

	          
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	    }
	 
	 

	    @Test
	    public void DownloadFileTest()
	    {
	       
	    	 ZirohLabsMethods app = new ZirohLabsMethods();
	        String localPath = "./DownloadFile/";
	       
	        String fileName = "UploadFile"+Math.abs(new Random().nextInt());
	        

	        
	        try {
	            PrintWriter writer = new PrintWriter(localPath + fileName);
	            writer.write("This is the test file for the DownloadFile() function.");
	            writer.close();
	            File localFile = new File(localPath + fileName);
	            ResultS DownloadResult = new ResultS();
	            FutureTask<ResultS> downloadfuturetask = app.DownloadFileFututreTask(fileName);
	            DownloadResult = getTaskResult(downloadfuturetask);
	            
	            Assert.assertEquals(DownloadResult.getErrCode(),0);
	            System.out.println("DownloadFile is working fine");
	            
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	    }

	 
}
