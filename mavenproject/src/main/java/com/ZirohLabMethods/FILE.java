package com.ZirohLabMethods;

import java.util.Date;
import java.util.List;

public class FILE {

private String id;
    
    //Name(title) of the file without extension
    private String name;
    
    //File size
    private long size;
   
    //is file starred in cloud or not
    private boolean isStarred;
    
    //MIME(Multipurpose Internet Mail Extension) type of file
    private String mimeType;
    
    //common extension of file
    private String extension;
    
    //perents id's
    private List<String> parentId;
    
    //time when the file is created. we followunix-time-convention
    private double createdOn;
    
    //time at which the ?le is last modi?ed. 
    private Date lastModified;
    
    //time at which ?le was last accessed. this is different from modi?ed last. modi?ed last is applied when a ?le is last updated.
    private Date lastAccessed;
    
    //the absolute path of the file at the source.source may be your desktop, laptop or a mobile phone device.
    private String originalAbsolutePath;
    
    //device name from where the filele originated
    private String originDeviceName;
    
    //Information on where the file is currently stored.
    //CloudStorageInfo StoredInCloud;
    
    // Absolute path with Ziroh MyFile System
    private String absolutePath;
    
    // User id's with the file is shared
    private String[] sharedUserIDCollection;
    
    //the thumbnail bytes
    private String thumbnailLink;
    
    //the migration source data
    //MigrateData migrationSourceData;
    
    //gets or sets the parent identifier to restore from Trash(recycle bean)
    //restore parent identifier
    private String restoreParentId;
    
    //Version id of the item, when last updated.
    private String version;
    
    //the mount file shares
    private String mountedCloudName;
    
    //expiry date for file share
    private long sharedExpiryDate;
    
    ///getters and setters
    
    //to set file or folder unique ID
    public void setId(String id){
        this.id = id;
    }
    //to get file or folder unique ID
    public String getId(){
        return id;
    }
    
    //set the name to the file
    public void setName(String nm){
        this.name = nm;
    }
    
    //get the name of file
    public String getName(String nm){
        return name;
    }
    
    //set file size
    public void setFileSize(long sz){
        this.size = sz;
    }
    
    //get Filesize
    public long getFileSize(){
        return size;
    }
    
    //set if file is starred or not
    public void setStarred(boolean is){
        this.isStarred = is;
    }
    
    //get if file is statrred or not
    public boolean getStarred(){
        return isStarred;
    }
    
    //set the file mimetype
    public void setMimeType(String type){
        this.mimeType = type;
    }
    
    
    //get the file MIME type of file
    public String getMimeType(){
        return mimeType;
    }
  
    //set file extension
    public void setExtension(String exte){
        this.extension = exte;
    }
    
    //get file extension
    public String getExtension(){
        return extension;
    }
    
    //set parent directory id
    public void setParentId(List<String> pId){
        this.parentId = pId;
    }
    
    //get file parent directory ID
    public List<String> getParentId(){
        return parentId;
    }
    
    //set craeted on date and time
    public void setCreatedOn(double dt){
        this.createdOn = dt;
    }
    
    //get craeted on date and time
    public double getCreatedOn(){
        return createdOn;
    }
    
    //set lastModified date and time
    public void setLastModified(java.util.Date date){
        this.lastModified = (Date) date;
    }
    
    //get last modified date and time
    public Date getLastModified(){
        return lastModified;
    }
    
    //set last accessed date and time
    public void setLastAccessed(java.util.Date date){
        this.lastAccessed = (Date) date;
    }
    
    //get last accessed date and time
    public Date getLastAccessed(){
        return lastAccessed;
    }
    
    //set absolute path of the file at the source.source may be your desktop, laptop or a mobile phone device.
    public void setoOiginalAbsolutePath(String oAbsPath){
        this.originalAbsolutePath = oAbsPath;
    }
    
    //get absolute path of the file at the source.source may be your desktop, laptop or a mobile phone device.
    public String setoOiginalAbsolutePath(){
        return originalAbsolutePath;
    }
    
    //set device name from where the file originated
    public void setoriginDeviceName(String name){
        this.originDeviceName = name;
    }
    
    //get device name from where the file originated
    public String setoriginDeviceName(){
        return originDeviceName;
    }
    
    //set Absolute path with Ziroh MyFile System
    public void setAbsolutePath(String path){
        this.absolutePath = path;
    }
        
    //get Absolute path with Ziroh MyFile System
    public String getAbsolutePath(){
        return absolutePath;
    }
    
    //set User id's with the file is shared
    public void setSharedUserIDCollection(String[] users){
        this.sharedUserIDCollection = users;
    }
    
    //get User id's with the file is shared
    public String[] getSharedUserIDCollection(){
        return sharedUserIDCollection;
    }
    
    //set the thumbnail bytes
    public void setThumbnailLink(String thumByte ){
        this.thumbnailLink = thumByte;
    }
    
    //get the thumbnail bytes
    public String getThumbnailLink(){
        return thumbnailLink;
    }
    
    //set the restore parent identifier
    public void setRestoreParentId(String parentId){
        this.restoreParentId = parentId;
    }
    
    //get the restore parent identifier
    public String setRestoreParentId(){
        return restoreParentId;
    }
    
    //set Version id of the item, when last updated.
    private void setVersion(String  ver){
        this.version = ver;
    }
    
    //get Version id of the item, when last updated.
    private String setVersion(){
        return version;
    }
    
    //set the mount file shares
    public void setMountedCloudName(String mountedName){
        this.mountedCloudName = mountedName;
    }
    
    //get the mount file shares
    public String setMountedCloudName(){
        return mountedCloudName;
    }
    
    //set expiry date for file share
    public void setSharedExpiryDate(long dt){
        this.sharedExpiryDate = dt;
    }
    
    //get expiry date for file share
    public long getSharedExpiryDate(){
        return sharedExpiryDate;
    }
}
