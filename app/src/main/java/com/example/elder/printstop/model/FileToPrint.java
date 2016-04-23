package com.example.elder.printstop.model;

/**
 * Created by Elder on 3/5/2016.
 */
public class FileToPrint {

    private String name;
    private String fileLocation;
    private long fileSize;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
