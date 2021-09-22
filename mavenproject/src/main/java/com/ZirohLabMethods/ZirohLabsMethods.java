package com.ZirohLabMethods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderBuilder;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

public class ZirohLabsMethods {
	private  final String ACCESS_TOKEN = "lRImaBLPV4IAAAAAAAAAAfd35gGGIhJQGcgLPs4ZOSJH_QETlpHa7xaA-tnSopLr";
	   final DbxRequestConfig config = new DbxRequestConfig("dropbox/testzirohinit", "en_US");
	   final DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
	   final String FolderName = "/TestFolder";
	   
	   
	   public Future<String> GetConnection(){
			  ExecutorService executor = Executors.newSingleThreadExecutor();
			  try {
	    			 final FullAccount account = client.users().getCurrentAccount();
	    			 Future<String> AccName = executor.submit(new Callable<String>() {
	    		         public String call() {
	    		             return account.getName().getDisplayName();
	    		         }});
	    			 return AccName;
	    		}
	    		catch (final Exception e)
	    		{
	    			Future<String> ErrorStr = executor.submit(new Callable<String>() {
	    		         public String call() {
	    		             return e.getMessage();
	    		         }});
	    			 return ErrorStr;
	    		}
	    		
	    	}
		 
		 
		 public void GetList() {
			 try {
				// Get files and folder metadata from Dropbox root directory
		        ListFolderResult result = client.files().listFolder("");
		        while (true) {
		            for (Metadata metadata : result.getEntries()) {
		                System.out.println(metadata.getPathLower());
		            }

		            if (!result.getHasMore()) {
		                break;
		            }

		            result = client.files().listFolderContinue(result.getCursor());
		        }
			 }
			 catch(Exception e){
				 System.out.print("Error While Getting folder List From DropBox:-" + e);
			 }
		 }
		 
		 public  void Upload() {
			 // Upload "test.txt" to Dropbox
			 try (InputStream in = new FileInputStream("tset.txt")) {
			           FileMetadata metadata = client.files().uploadBuilder("/tset.txt")
			                .uploadAndFinish(in);
			           System.out.println("Uploaded to dropbox Successfully!!!!!!!!!");
			       
			 }
			 catch(Exception e)
			 {
				 System.out.print("Error While Uploading to DropBox:-" + e);
			 }
			 
		 }
		 
		 public  void Download() {
			// download Files from dropbox
		        try
		        {
		        	 String localPath = "D://College//Projects//Ziroh Labs//ZirohDownload//Ziroh Labs Standard NDA_For _Individual.pdf";
			           OutputStream outputStream = new FileOutputStream(localPath);
			            FileMetadata metadata = client.files()
			                   .downloadBuilder("/Ziroh Labs Standard NDA_For _Individual.pdf")
			                   .download(outputStream);
			            
			            System.out.println("Downloaded from dropbox Successfully!!!!!!!!!!!!");
			    }
			    catch(Exception e)
			    {
			    	System.out.print("Error While Downloading File From DropBox:-" + e);
			    }
		 }
		 
		 public  void Delete()
		 {
			//delete file from the dropbox
		        
		        try {
		        	String FileName="/tset.txt";
		        	Metadata mb = client.files().delete(FileName);
		        	System.out.println("Deleted From dropbox Successfully!!!!!!!!!!!!!");
		        }
		        catch(Exception ex)
		        {
		        	System.out.print(ex);
		        }
		 }
		 
		 public  void CreateDir()
		 {
			// Creating folder on dropbox
				
				  try { String FolderName = "/TestFolder"; FolderMetadata FMT =
				 client.files().createFolder(FolderName);
				  System.out.println("Folder Created Successfully in drpobox!!!!!!!!!!!!!"); }
				  catch(Exception ex) { System.out.print(ex); }
				 
		 }
		 
		 public  void DisplayContents() {
			 try {
		        	
		        	ListFolderBuilder LFB = client.files().listFolderBuilder(FolderName);
		        	ListFolderResult res = LFB.withRecursive(true).start();
		        	
		        		if(res != null) {
		        			for(Metadata entry:res.getEntries())
		        			{
		        				if(entry instanceof FileMetadata) {
		        					System.out.println("Added file: "+entry.getName());
		        				}
		        			}
		        			
		        			try {
		        				ListFolderResult result = client.files().listFolder("");
		        				result = client.files().listFolderContinue(result.getCursor());
		        			} catch (DbxException e) {
		        				System.out.println("Couldn't get listFolderContinue");
		        			}
		        		}
		        	
		        }
		        catch(Exception ex) {
		        	System.out.println(ex);
		        }
		 }
		 
		 public  void DeleteFolder()
		 {
			 
				  // Deleteing folder on dropbox try { String FolderName = "/TestFolder";
			 try {
				 
				  Metadata FMT = client.files().delete(FolderName);
				  System.out.println("Folder Deleted Successfully in drpobox!!!!!!!!!!!!!"); }
			 
				  catch(Exception ex) { System.out.print(ex); }
				 
		 }
}
