package Testmaven.mavenproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
}
