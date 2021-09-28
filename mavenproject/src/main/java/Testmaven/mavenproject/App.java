package Testmaven.mavenproject;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import javax.naming.spi.DirStateFactory.Result;

import com.ZirohLabMethods.*;
import com.ZirohLabMethods.ResultS;
import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;



public class App 
{
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
	 static void printResult(ResultS result) {
	        if (result.getErrCode() == 0) {
	            System.out.println("\n" + result.getShortMsg());
	        } else {
	            System.err.println("Task Failed");
	            System.out.println("Error Message: " + result.getErrMsg());
	            System.out.println("Description : " + result.getLongMsg());
	        }
	    }
	
	public static void main(String args[]) throws DbxException, IOException, InterruptedException, ExecutionException {
		ZirohLabsMethods Z = new ZirohLabsMethods();
		
		ResultS GetConnection = getTaskResult(Z.GetconnectionFuturetask());
		printResult(GetConnection);
	  }
}

