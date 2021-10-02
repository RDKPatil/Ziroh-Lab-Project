package com.ZirohLabMethods;

public class FileUploadResult extends ResultS {
	   // The generated Id of the ?le or directory thatgot uploaded
    public String id;
    
    public void setId(String uid){
        this.id = uid;
    }
    
    public String getId(){
        return id;
    } 
}
