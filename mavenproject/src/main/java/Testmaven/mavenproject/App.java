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
	            "future is %s", 
	            Task.isDone() ? "done" : "not done"
	          
	          )
	        );
	        Thread.sleep(300);
	    }
	    
	    String Name = Task.get();
	    System.out.print(Name);
		
	    //getlist
		 Future<String>Task1 = new ZirohLabsMethods().GetList();
		 while(!(Task1.isDone())) { 
			 System.out.println(
			String.format( "\nfuture is %s",
			 Task1.isDone() ? "done" : "not done"
		 
		 ) ); Thread.sleep(300); }
		
		 String Name1 = Task1.get();
		 System.out.print(Name1);
		 
		//upload
		 Future<String>Task2 = new ZirohLabsMethods().Upload();
		 while(!(Task1.isDone())) { 
			 System.out.println(
			String.format( "\nfuture is %s",
			 Task2.isDone() ? "done" : "not done"
		 
		 ) ); Thread.sleep(300); }
		
		 String Name2 = Task2.get();
		 System.out.print(Name2);
		
		 //delete
		 
		 Future<String>Task3 = new ZirohLabsMethods().Delete();
		 while(!(Task3.isDone())) { 
			 System.out.println(
			String.format( "\nfuture is %s",
			 Task3.isDone() ? "done" : "not done"
		 
		 ) ); Thread.sleep(300); }
		
		 String Name3 = Task3.get();
		 System.out.print(Name3);
		
		 //createfolder
		 
		 Future<String>Task4 = new ZirohLabsMethods().CreateDir();
		 while(!(Task4.isDone())) { 
			 System.out.println(
			String.format( "\nfuture is %s",
			 Task4.isDone() ? "done" : "not done"
		 
		 ) ); Thread.sleep(300); }
		
		 String Name4 = Task4.get();
		 System.out.print(Name4);
		 
		 ZirohLabsMethods z=new ZirohLabsMethods();
		 z.DisplayContents();
	    
	  }
}

