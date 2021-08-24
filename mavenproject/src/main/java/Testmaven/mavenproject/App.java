package Testmaven.mavenproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

/**
 * Hello world!
 *
 */
public class App 
{
	  private static final String ACCESS_TOKEN = "lRImaBLPV4IAAAAAAAAAAfd35gGGIhJQGcgLPs4ZOSJH_QETlpHa7xaA-tnSopLr";

	    public static void main(String args[]) throws DbxException, IOException {
	        // Create Dropbox client
	        DbxRequestConfig config = new DbxRequestConfig("dropbox/testzirohinit", "en_US");
	        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

	        // Get current account info
	        FullAccount account = client.users().getCurrentAccount();
	        System.out.println(account.getName().getDisplayName());

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

	        // Upload "test.txt" to Dropbox
	        try (InputStream in = new FileInputStream("tset.txt")) {
	           FileMetadata metadata = client.files().uploadBuilder("/tset.txt")
	                .uploadAndFinish(in);
	           System.out.println("Uploaded to dropbox Successfully!!!!!!!!!");
	        }
	        catch(Exception ex)
	        {
	        	System.out.print(ex);
	        }
	        
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
		    catch(Exception ex)
		    {
		       System.out.print(ex);
		    }
	        
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
}
