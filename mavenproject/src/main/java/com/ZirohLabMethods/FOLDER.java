package com.ZirohLabMethods;
import java.util.ArrayList;
public class FOLDER extends FILE {
	
	    
	    //Array Subdirectories inside the current directoryat level 1 
	    public FOLDER []subDirCollection;
	    
	    public void setSubDirCollection(FOLDER []collection){
	        this.subDirCollection = collection;
	    }
	    
	    // Files inside the current directory atlevel 1
	    public FILE []fileCollection;
	    
	    public void setFileCollection(FILE []collection){
	        this.fileCollection = collection;
	    }    
	
}
