package com.guanshaoye.glglteacher.bean;

import java.io.Serializable;

/**
 * Created by karl on 2017/5/28.
 */

public class ImageBean implements Serializable {

    public String parentName = "";
    public long size;
    public String displayName = "";
    public String path = "";
    public boolean isChecked;
    public String thumbnailPath = "";
    public String url;
    public int status; //0 1 -1
    private int position;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ImageBean() {
        super();
    }

    public ImageBean(String path) {
        super();
        this.path = path;
    }

    public ImageBean(String parentName, long size, String displayName,
                     String path, boolean isChecked) {
        super();
        this.parentName = parentName;
        this.size = size;
        this.displayName = displayName;
        this.path = path;
        this.isChecked = isChecked;
    }

    public ImageBean(String parentName, long size, String displayName,
                     String path, boolean isChecked,String thumbnailPath) {
        super();
        this.parentName = parentName;
        this.size = size;
        this.displayName = displayName;
        this.path = path;
        this.isChecked = isChecked;
        this.thumbnailPath = thumbnailPath;
    }

    public boolean isVideo(){
        return this.path.toLowerCase().endsWith("3gp") || this.path.toLowerCase().endsWith("mp4")
                || this.path.toLowerCase().endsWith("ts") || this.path.toLowerCase().endsWith("mkv")
                || this.path.toLowerCase().endsWith("webm") ? true : false;
    }

    public boolean isPhoto(){
        return this.path.toLowerCase().endsWith("jpeg") || this.path.toLowerCase().endsWith("jpg")
                || this.path.toLowerCase().endsWith("gif") || this.path.toLowerCase().endsWith("png")
                || this.path.toLowerCase().endsWith("bmp") || this.path.toLowerCase().endsWith("webp") ? true : false;
    }

    @Override
    public String toString() {
        return "ImageBean [parentName=" + parentName + ", size=" + size
                + ", displayName=" + displayName + ", path=" + path
                + ", isChecked=" + isChecked + "]";
    }

}