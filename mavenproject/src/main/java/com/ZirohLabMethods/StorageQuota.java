package com.ZirohLabMethods;
import com.dropbox.core.v2.users.SpaceUsage;
public class StorageQuota extends ResultS{
    //total cloud storage space 
    private long totalSpace;
    //the storage space utilized by user
    private long used;
    //we get the total storage space 
    
    //the storage usege in cloud i.e. without including trash
    private long usageInCloud;
    
    //the storage usege in trash i.e. total trashed space storage
    private long usageInTrash;
    
    
    
    public long getTotalSpace(){
        return totalSpace;
    }
    //set the total storage space 
    public void setTotalSpace(long tSpace){
        this.totalSpace = tSpace;
    }
    //we get the utized storage space info
    public long getUsed(){
        return used;
    }
    //sets the cloud storage utilized info
    public void setUsed(long utilizedSpace){
        this.used = utilizedSpace;
    } 
    
    public void setUsageInCloud(long cloudUsage){
        this.usageInCloud = cloudUsage;
    }
    
    public long getUsageInCloud(){
        return usageInCloud;
    }
    
    public void setUsageInTrash(long trashUsage){
        this.usageInTrash = trashUsage;
    }
    
    public long getUsageInTrash(){
        return usageInTrash;
    }
}
