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
import java.util.concurrent.FutureTask;
import com.ZirohLabMethods.*;
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

public class ZirohLabsMethods extends ResultS {
	private  final String ACCESS_TOKEN = "lRImaBLPV4IAAAAAAAAAAfd35gGGIhJQGcgLPs4ZOSJH_QETlpHa7xaA-tnSopLr";
	   final DbxRequestConfig config = new DbxRequestConfig("dropbox/testzirohinit", "en_US");
	   final DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
	   final String FolderName = "/AbhiFolder";
	   
	    public FutureTask<ResultS> GetconnectionFuturetask() {
	        
	        ResultS GetConnectionResult = new ResultS();

	        FutureTask<ResultS> GetconnectionFuturetask = new FutureTask<ResultS>(() -> {
	          
	        	try {
	        		FullAccount account = client.users().getCurrentAccount();
	        		GetConnectionResult.setErrCode(0);
	    	        GetConnectionResult.setShortMsg(account.getAccountId());
	    	        
	    		}
	    		catch (final Exception e)
	    		{
	    			GetConnectionResult.setErrCode(1);
	    		}
	        }, GetConnectionResult);
	        return GetconnectionFuturetask;
	    }
		 
		 public Future<String> GetList() {
			 ExecutorService executor = Executors.newSingleThreadExecutor();
			 try {
		
				 final ListFolderResult result = client.files().listFolder(FolderName);
				 Future<String> fileName = executor.submit(new Callable<String>() {
				 public String call() {
			        while (true) {
			            for (Metadata metadata : result.getEntries()) {
			                System.out.println(metadata.getPathLower() + metadata.getParentSharedFolderId());
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
		// Upload "test.txt" to Dropbox
		 public  FutureTask<ResultS> UploadFutureTask(String FilePath, String ParentDirectoryId) {
			 
			ResultS UploadResult = new ResultS();
			FutureTask<ResultS> GetconnectionFuturetask = new FutureTask<ResultS>(() -> {
		          
	        	try {
	        		try (InputStream in = new FileInputStream(FilePath)) {
				           FileMetadata metadata = client.files().uploadBuilder(FolderName + "/" +FilePath )
				                .uploadAndFinish(in);
				            UploadResult.setErrCode(0);
			    	        UploadResult.setShortMsg(metadata.getName());
	        		}
		 		   
	    		}
	    		catch (final Exception e)
	    		{
	    			UploadResult.setErrCode(1);
	    			UploadResult.setErrMsg(e.getMessage());
	    		
	    		}
	        }, UploadResult);
	        return GetconnectionFuturetask; 
		 }
		 
		 public  FutureTask<ResultS> DownloadFileFututreTask(String FileId) {
			 ResultS DownloadResult = new ResultS();
			 FutureTask<ResultS>downloadfuturetask = new FutureTask<ResultS>(() -> {
				 try {
					 String localPath = "D://College//Projects//Ziroh Labs//ZirohDownload//tset.txt";
					 OutputStream outputStream = new FileOutputStream(localPath);
					 FileMetadata metadata = client.files().downloadBuilder("/TestFolder/tset.txt").download(outputStream);
					 DownloadResult.setErrCode(0);
					 DownloadResult.setShortMsg(metadata.getId());
		    		}
		    		catch (final Exception e)
		    		{
		    			DownloadResult.setErrCode(1);
		    			DownloadResult.setErrMsg(e.getMessage());
		    		
		    		}
		        }, DownloadResult);
			 
			 return downloadfuturetask;
			 }
			 
		
		 
		  public FutureTask<ResultS>MoveFileFutureTask(String FileId, String MoveToPath){
			 ResultS moveResult = new ResultS();
			 FutureTask<ResultS>movefilefuturetask = new FutureTask<ResultS>(() -> {
				 try {
					 String FilePath = "/abhi.txt";
					 String MovePath = "/AbhiFolder/abhi.txt";
					 FileMetadata metadata =  (FileMetadata)client.files().move(FilePath, MovePath);
					 moveResult.setErrCode(0);
					 moveResult.setShortMsg(metadata.getId());
		    		}
		    		catch (final Exception e)
		    		{	
		    			moveResult.setErrCode(1);
		    			moveResult.setErrMsg(e.toString());
		    		
		    		}
		        }, moveResult);
			 
			 return movefilefuturetask;
			 }
		 
		 public FutureTask<ResultS>MoveFolderFutureTask(String FileId, String MoveToPath){
			 ResultS moveFolderResult = new ResultS();
			 FutureTask<ResultS>movefolderfuturetask = new FutureTask<ResultS>(() -> {
				 try {
					 String FilePath = "/TestFolder";
					 String MovePath = "/AbhiFolder/TestFolder";
					 FolderMetadata metadata =  (FolderMetadata)client.files().move(FilePath, MovePath);
					 moveFolderResult.setErrCode(0);
					 moveFolderResult.setShortMsg(metadata.getId());
		    		}
		    		catch (final Exception e)
		    		{	
		    			moveFolderResult.setErrCode(1);
		    			moveFolderResult.setErrMsg(e.toString());
		    		
		    		}
		        }, moveFolderResult);
			 
			 return movefolderfuturetask;
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
								    
							  String ID = FMT.getId();
							  return ID;
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
		        					System.out.println(entry.getName());
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
