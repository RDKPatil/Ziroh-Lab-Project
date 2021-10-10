package com.ZirohLabMethods;

import java.util.concurrent.FutureTask;

public interface ICloudIO {
	
	/* FutureTask<ResultS> GetconnectionFuturetask(); */
	
	FutureTask<ResultS> UploadFutureTask(String FilePath);
	
	FutureTask<ResultS> DownloadFileFututreTask(String FileId);
	
	FutureTask<ResultS>MoveFileFutureTask(String FileId, String MoveToPath);
	
	FutureTask<ResultS>MoveFolderFutureTask(String FileId, String MoveToPath);
	
	FutureTask<ResultS>copyFileFutureTask(String from,String to);
	
	FutureTask<ResultS>copyFolderFutureTask(String from,String to);
	
	FutureTask<ResultS>deleteFolderFutureTask(String foldername);
	
	FutureTask<ResultS>deleteFileFutureTask(String filename);
	
	FutureTask<ResultS>deleteFilePermanentlyFutureTask(String filename);
	
	FutureTask<ResultS>deleteFolderPermanentlyFutureTask(String foldername);
	
	FutureTask<ResultS>GetClouldDir(String FolderName);
	
	FutureTask<ResultS>GetStorageQuota ();
	
	FutureTask<ResultS>FileSharingFutureTask (String FileName, String MemberID);
	
	FutureTask<ResultS>FolderSharingFutureTask (String FolderID, String MemberID);

}
