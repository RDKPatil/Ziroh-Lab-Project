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
import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;



public class App 
{
	public static void main(String args[]) throws DbxException, IOException, InterruptedException, ExecutionException {
	    
	    ExecutorService ES = Executors.newSingleThreadExecutor();
	    
	    Future<String>Task = new ZirohLabsMethods().GetConnection();
	    while (!(Task.isDone())) {
	        System.out.println(
	          String.format(
	            "future1 is %s", 
	            Task.isDone() ? "done" : "not done"
	          
	          )
	        );
	        Thread.sleep(300);
	    }
	    
	    String Name = Task.get();
	    System.out.print(Name);
	  }
}

