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
		// Connection To the Dropbox
		ResultS GetConnection = getTaskResult(Z.GetconnectionFuturetask());
		printResult(GetConnection);
		
		// Upload File
		 ResultS UploadFile = getTaskResult(Z.UploadFutureTask("UploadFile/UploadFile.txt","AABO0V5N1-XwJZd7tcDBrHhdtP9HamgcN6g"));
		 printResult(UploadFile);
		
		// Download File
		ResultS DownloadFile = getTaskResult(Z.DownloadFileFututreTask("oW4J0_hmN3kAAAAAAAAAZA"));
		printResult(DownloadFile);
		
		// Move File
		ResultS MoveFile = getTaskResult(Z.MoveFileFutureTask("oW4J0_hmN3kAAAAAAAAAZA", "/AbhiFolder"));
		printResult(MoveFile);
		// Move Folder
		ResultS MoveFolder = getTaskResult(Z.MoveFolderFutureTask("/TestFolder", "/AbhiFolder/TestFolder"));
		printResult(MoveFolder);
		//Copy File
		ResultS copyFile = getTaskResult(Z.copyFileFutureTask("/TestFolder/tset.txt", "/AbhiFolder/tset.txt"));
		printResult(copyFile);
		//copy folder
		
		ResultS copyFolder = getTaskResult(Z.copyFolderFutureTask("/TestFolder","/AbhiFolder/TestFolder"));
		printResult(copyFolder);
		//Delete Folder to Trash
		
		 ResultS deleteFolder =getTaskResult(Z.deleteFolderFutureTask("/AbhiFolder"));
		 printResult(deleteFolder);
		
		//Delete File to Trash
		 ResultS deleteFile =getTaskResult(Z.deleteFolderFutureTask("/TestFolder/tset.txt"));
		 printResult(deleteFile);
		
		//Delete File Permanently
		 ResultS deleteFilePermanently =getTaskResult(Z.deleteFilePermanentlyFutureTask("/TestFolder/tset.txt"));
		 printResult(deleteFilePermanently);
		 
		//Delete Folder Permanently
		 ResultS deleteFolderPermanently =getTaskResult(Z.deleteFolderPermanentlyFutureTask("/TestFolder"));
		 printResult(deleteFolderPermanently);
		
		//Get Cloud Directory 
		 ResultS getclouddir =getTaskResult(Z.GetClouldDir("/AbhiFolder"));
		 printResult(getclouddir);
		 
		//Get Storage Data 
		 ResultS getstoragedata =getTaskResult(Z.GetStorageQuota());
		 printResult(getstoragedata);
		
		//File Sharing 
		 ResultS getsharefile =getTaskResult(Z.FileSharingFutureTask("/AbhiFolder" , "AABO0V5N1-XwJZd7tcDBrHhdtP9HamgcN6g"));
		 printResult(getsharefile);
		 
		//folder Sharing 
		 ResultS getfoldershare =getTaskResult(Z.FolderSharingFutureTask("oW4J0_hmN3kAAAAAAAAAZA" , "AABO0V5N1-XwJZd7tcDBrHhdtP9HamgcN6g"));
		 printResult(getstoragedata);
	  }
}

