package com.ZirohLabMethods;

public class ResultS {
    String short_msg = null;
    String long_msg = null;
    int err_code = 5;
    String err_msg = null;
    
    //set short message about result
    public void setShortMsg(String msg){
        this.short_msg = msg;
    }
    
    //set long message about result
    public void setLongMsg(String msg){
        this.long_msg = msg;
    }
    
    //set error code about result
    public void setErrCode(int code){
        this.err_code = code;
    }
    
    //set error message about result
    public void setErrMsg(String msg){
        this.err_msg = msg;
    }
    
    //get short result message
    public String getShortMsg(){
        return short_msg;
    }
    
    //get long message about result
    public String getLongMsg(){
        return long_msg;
    }
    
    //get error code of error
    public int getErrCode(){
        return err_code;
    }
    
    //get error details
    public String getErrMsg(){
        return err_msg;
    }


	}   
