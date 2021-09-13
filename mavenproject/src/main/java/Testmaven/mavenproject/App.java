package Testmaven.mavenproject;

import java.io.IOException;


import com.ZirohLabMethods.*;
import com.dropbox.core.DbxException;

public class App 
{
	  public static void main(String args[]) throws DbxException, IOException {
	    	ZirohLabsMethods Methods = new ZirohLabsMethods();
	    	Methods.GetConnection();
	    	Methods.GetList();
	    
	  }
}

