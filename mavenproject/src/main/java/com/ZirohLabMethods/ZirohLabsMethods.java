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
		// Upload file to Dropbox
	    public FutureTask<ResultS> UploadFutureTask(String FilePath, String ParentDirectoryId) {
	    	ResultS UploadResult = new ResultS();
	    	FutureTask<ResultS> GetconnectionFuturetask = new FutureTask<ResultS>(() -> {
	    	try {
	    	InputStream in = new FileInputStream(FilePath);
	    	FileMetadata metadata = client.files().uploadBuilder("/UploadFile/UploadFile.txt").uploadAndFinish(in);
	    	System.out.println("Uploaad Successfully in dropbox!!!!!!!!!!!!!!");
	    	UploadResult.setErrCode(0);
	    	UploadResult.setShortMsg(metadata.getId());
	    	}
	    	catch (final Exception e)
	    	{
	    	UploadResult.setErrCode(1);
	    	UploadResult.setErrMsg(e.getMessage());
	    	}
	    	}, UploadResult);
	    	return GetconnectionFuturetask;
	    	}
	    
		
//		//Download file
		 public  FutureTask<ResultS> DownloadFileFututreTask(String FileId) {
			 ResultS DownloadResult = new ResultS();
			 FutureTask<ResultS>downloadfuturetask = new FutureTask<ResultS>(() -> {
				 try {
					 String localPath = "./DownloadFile/UploadFile.txt";
					 OutputStream outputStream = new FileOutputStream(localPath);
					 FileMetadata metadata = client.files().downloadBuilder("/UploadFile/UploadFile.txt").download(outputStream);
					 
					
					 System.out.println("Download file Successfully in drpobox!!!!!!!!!!!!!"); 
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
	 public FutureTask<ResultS>copyFileFutureTask(String from,String to){
		ResultS copyFileResult = new ResultS();
		FutureTask<ResultS>copyFileFutureTask = new FutureTask<ResultS>(() -> {
		try {	
			 FileMetadata metadata =(FileMetadata)client.files().copy(from,to);
			 System.out.println("File copied Successfully!!!!");
			 copyFileResult.setErrCode(0);
			 copyFileResult.setShortMsg(metadata.getId());
		   }
		catch (final Exception e)
		{	
			 copyFileResult.setErrCode(1);
			 copyFileResult.setErrMsg(e.toString());
			    		
	        }
	       }, copyFileResult);
				 
	 return copyFileFutureTask;
     }
			 public FutureTask<ResultS>copyFolderFutureTask(String from,String to){
				 ResultS copyFolderResult = new ResultS();
				 FutureTask<ResultS>copyFolderFutureTask = new FutureTask<ResultS>(() -> {
					 try {
						
						 FileMetadata metadata =(FileMetadata)client.files().copy(from,to);
						 System.out.println("Folder copied Successfully!!!!");
						 copyFolderResult.setErrCode(0);
						 copyFolderResult.setShortMsg(metadata.getId());
			    		}
			    		catch (final Exception e)
			    		{	
			    			copyFolderResult.setErrCode(1);
			    			copyFolderResult.setErrMsg(e.toString());
			    		
			    		}
			        }, copyFolderResult);
				 
				 return copyFolderFutureTask;
				 }
	
			public FutureTask<ResultS>deleteFolderFutureTask(String foldername){
			 ResultS deleteFolderResult = new ResultS();
			 FutureTask<ResultS>deleteFolderFutureTask = new FutureTask<ResultS>(() -> {
				 try {
					
					 Metadata FMT= client.files().delete(foldername);
					 System.out.println("Folder Deleted Successfully in drpobox!!!!!!!!!!!!!"); 
					 deleteFolderResult.setErrCode(0);
					 //deleteFolderResult.setShortMsg(metadata.getId());
		    		}
		    		catch (final Exception e)
		    		{	
		    			deleteFolderResult.setErrCode(1);
		    			deleteFolderResult.setErrMsg(e.toString());
		    		
		    		}
		        }, deleteFolderResult);
			 
			 return deleteFolderFutureTask;
			 }
			public FutureTask<ResultS>deleteFileFutureTask(String filename){
			 ResultS deleteFileResult = new ResultS();
			 FutureTask<ResultS>deleteFileFutureTask = new FutureTask<ResultS>(() -> {
				 try {
					
					 Metadata FMT= client.files().delete(filename);
					 System.out.println("Folder Deleted Successfully in drpobox!!!!!!!!!!!!!"); 
					 deleteFileResult.setErrCode(0);
					 //deleteFolderResult.setShortMsg(metadata.getId());
		    		}
		    		catch (final Exception e)
		    		{	
		    			deleteFileResult.setErrCode(1);
		    			deleteFileResult.setErrMsg(e.toString());
		    		
		    		}
		        }, deleteFileResult);
			 
			 return deleteFileFutureTask;
			 }
	
		 public FutureTask<ResultS>deleteFilePermanentlyFutureTask(String filename){
			 ResultS deleteFilePermanentlyResult = new ResultS();
			 FutureTask<ResultS>deleteFilePermanentlyFutureTask = new FutureTask<ResultS>(() -> {
				 try {
					
					 client.files().permanentlyDelete(filename);
					 System.out.println("File has been Deleted Successfully in drpobox!!!!!!!!!!!!!"); 
					 deleteFilePermanentlyResult.setErrCode(0);
					 //deleteFolderResult.setShortMsg(metadata.getId());
		    		}
		    		catch (final Exception e)
		    		{	
		    			deleteFilePermanentlyResult.setErrCode(1);
		    			deleteFilePermanentlyResult.setErrMsg(e.toString());
		    		
		    		}
		        }, deleteFilePermanentlyResult);
			 
			 return deleteFilePermanentlyFutureTask;
			 }
		 
		 public FutureTask<ResultS>deleteFolderPermanentlyFutureTask(String foldername){
			 ResultS deleteFolderPermanentlyResult = new ResultS();
			 FutureTask<ResultS>deleteFolderPermanentlyFutureTask = new FutureTask<ResultS>(() -> {
				 try {
					
					 client.files().permanentlyDelete(foldername);
					 System.out.println("Folder has been Deleted Successfully in drpobox!!!!!!!!!!!!!"); 
					 deleteFolderPermanentlyResult.setErrCode(0);
					 //deleteFolderResult.setShortMsg(metadata.getId());
		    		}
		    		catch (final Exception e)
		    		{	
		    			deleteFolderPermanentlyResult.setErrCode(1);
		    			deleteFolderPermanentlyResult.setErrMsg(e.toString());
		    		
		    		}
		        }, deleteFolderPermanentlyResult);
			 
			 return deleteFolderPermanentlyFutureTask;
			 }
	public FutureTask<ResultS>GetClouldDir(String FolderName)
			{
				ResultS getclouddir = new ResultS();
				 FutureTask<ResultS>getclouddirfuturetask = new FutureTask<ResultS>(() -> {
					 try {
						 FOLDER folder = new FOLDER();
						 List<FOLDER> DirectoriesList = new ArrayList<>();
		                 List<FILE> FileList = new ArrayList<>();
						 ListFolderBuilder listFolderBuilder = client.files().listFolderBuilder(FolderName);
						 ListFolderResult listFolderResult = listFolderBuilder.withRecursive(true).start();
						 
						 if(listFolderResult != null)
						 {
							 ListFolderResult result = client.files().listFolder("");
						        while (true) {
						            for (Metadata dir : result.getEntries()) {
						                 if(dir instanceof FolderMetadata) {
						                	 FOLDER Directory = new FOLDER();
						                	 Directory.setId(((FolderMetadata)dir).getId());
						                	 Directory.setName(((FolderMetadata)dir).getName());
						                	 DirectoriesList.add(Directory);
						                	 
						                 }
						            }

						            if (!result.getHasMore()) {
						                break;
						            }

						            result = client.files().listFolderContinue(result.getCursor());
						        }
							 for(Metadata file:listFolderResult.getEntries())
			        			{
			        				if(file instanceof FileMetadata) {
			        					FILE File = new FILE();
			                            //seted metadata for each file
			        					File.setId(((FileMetadata)file).getId());
			        					File.setName(file.getName());
			                            File.setFileSize(((FileMetadata)file).getSize());
			                            File.setLastModified(((FileMetadata)file).getServerModified());
			                            File.setAbsolutePath(file.getPathDisplay());
			                            File.setLastAccessed(((FileMetadata)file).getClientModified());
			                            
			                            //added to filesList: to return collection of all files of directories
			                            FileList.add(File);
			                            
			                            
			        				}
			        			}
						 }
						 else
						 {
							 getclouddir.setErrCode(0);
							 getclouddir.setErrMsg("Empty...!");
						 }
						 
						 folder.setSubDirCollection(DirectoriesList.toArray(new FOLDER[0]));
						 folder.setFileCollection(FileList.toArray(new FILE[0]));
						 getclouddir.setShortMsg("Cloud directory extracted successfully...");
						 getclouddir	.setErrCode(0);
			    		}
					 
			    		catch (final Exception e)
			    		{	
			    			getclouddir.setErrCode(1);
			    			getclouddir.setErrMsg(e.toString());
			    		
			    		}
			        }, getclouddir);
				 
				 return getclouddirfuturetask;
			}
			
			
			
			
			public FutureTask<ResultS>GetStorageQuota (){
				 ResultS getstorageresults = new ResultS();
				 StorageQuota SQ = new StorageQuota();
				 FutureTask<ResultS>getstroagequota = new FutureTask<ResultS>(() -> {
					 try {
						 SpaceUsage SU = client.users().getSpaceUsage();
						 SQ.setUsed(SU.getUsed());
						 SQ.setTotalSpace(SU.getAllocation().getIndividualValue().getAllocated());
						 
						 getstorageresults.setErrCode(0);
						 getstorageresults.setShortMsg("StorageQuota Calculated suuccessfully..");
			    		}
			    		catch (final Exception e)
			    		{	
			    			getstorageresults.setErrCode(1);
			    			getstorageresults.setErrMsg(e.toString());
			    		
			    		}
			        }, getstorageresults);
				 
				 return getstroagequota;
				 }
	
				public FutureTask<ResultS>FileSharingFutureTask (String FileName, String MemberID){
				 ResultS getfilesharingresult = new ResultS();
				 StorageQuota SQ = new StorageQuota();
				 FutureTask<ResultS>sharefile = new FutureTask<ResultS>(() -> {
					 try {
						 SharedLinkMetadata sharedLinkMetadata = (SharedLinkMetadata) client.sharing().addFileMember(FileName,null);
						 getfilesharingresult.setErrCode(0);
						 getfilesharingresult.setShortMsg(sharedLinkMetadata.getId());
			    		}
			    		catch (final Exception e)
			    		{	
			    			getfilesharingresult.setErrCode(1);
			    			getfilesharingresult.setErrMsg(e.toString());
			    		
			    		}
			        }, getfilesharingresult);
				 
				 return sharefile;
				 }
			
			public FutureTask<ResultS>FolderSharingFutureTask (String FolderID, String MemberID){
				 ResultS getfoldersharingresult = new ResultS();
				 StorageQuota SQ = new StorageQuota();
				 FutureTask<ResultS>sharefolder = new FutureTask<ResultS>(() -> {
					 try {
						 client.sharing().addFolderMember(FolderID ,null);
						 getfoldersharingresult.setErrCode(0);
						 getfoldersharingresult.setShortMsg("Folder Shared Successfully");
			    		}
			    		catch (final Exception e)
			    		{	
			    			getfoldersharingresult.setErrCode(1);
			    			getfoldersharingresult.setErrMsg(e.toString());
			    		
			    		}
			        }, getfoldersharingresult);
				 
				 return sharefolder;
				 }
}
