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
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderBuilder;
import com.dropbox.core.v2.files.ListFolderContinueErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadErrorException;
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
		 
		 
		 public Future<String> GetList() {
			 ExecutorService executor = Executors.newSingleThreadExecutor();
			 try {
		
				 final ListFolderResult result = client.files().listFolder(FolderName);
				 Future<String> fileName = executor.submit(new Callable<String>() {
				 public String call() {
			        while (true) {
			            for (Metadata metadata : result.getEntries()) {
			                System.out.println(metadata.getPathLower());
			            }

			            if (!result.getHasMore()) {
			                break;
			            }

			            //result = client.files().listFolderContinue(result.getCursor());
			        }
			        return "";
				 }});
    			 return fileName;
		            
		       
			 }
			 catch(final Exception e){
					Future<String> ErrorStr = executor.submit(new Callable<String>() {
	    		         public String call() {
	    		             return e.getMessage();
	    		         }});
	    			 return ErrorStr;
			 }
			
		 }
		 
		 public  Future<String> Upload() {
			 // Upload "test.txt" to Dropbox
			 ExecutorService executor = Executors.newSingleThreadExecutor();
			 try  {
				 		Future<String> uploadFile = executor.submit(new Callable<String>() {
				 		public String call() throws UploadErrorException, DbxException, IOException {
				 			InputStream in = new FileInputStream("abhi.txt");
				 			FileMetadata metadata = client.files().uploadBuilder("/abhi.txt").uploadAndFinish(in);
				 			
				 			return "Uploaded to dropbox Successfully!!!!!!!!!";
				 }});
				 		return uploadFile;
			 }
			 catch(final Exception e)
			 {
				 Future<String> ErrorStr = executor.submit(new Callable<String>() {
    		         public String call() {
    		             return e.getMessage();
    		         }});
    			 return ErrorStr;
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
		 
		 public  Future<String> Delete()
		 {
			//delete file from the dropbox
			 ExecutorService executor = Executors.newSingleThreadExecutor();
		        try {
		        	Future<String> deleteFile = executor.submit(new Callable<String>() {
		        		public String call() throws DeleteErrorException, DbxException {
		        			String FileName="/abhi.txt";
				        	Metadata mb = client.files().delete(FileName);
				        	
		        			
		        		return "Deleted From dropbox Successfully!!!!!!!!!!!!!";
		        	}});
		        return deleteFile;
		        }
		        catch(final Exception ex)
		        {
		        	Future<String> ErrorStr = executor.submit(new Callable<String>() {
	    		         public String call() {
	    		             return ex.getMessage();
	    		         }});
	    			 return ErrorStr;
		        }
		 }
		 
		 public   Future<String> CreateDir()
		 {
			// Creating folder on dropbox
			 	ExecutorService executor = Executors.newSingleThreadExecutor();
				  try { 
					  Future<String> createDir = executor.submit(new Callable<String>() {
						  public String call() throws CreateFolderErrorException, DbxException {
							  String FolderName = "/AbhiFolder"; 
							  FolderMetadata FMT =client.files().createFolder(FolderName);
								     
							  FMT.getName();
							  return "Folder Created Successfully in drpobox!!!!!!!!!!!!!";
						  }});
					  
				      return createDir;
				     }
				  catch(final Exception ex) {
					  Future<String> ErrorStr = executor.submit(new Callable<String>() {
		    		         public String call() {
		    		             return ex.getMessage();
		    		         }});
		    			 return ErrorStr;
				}
				 
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
