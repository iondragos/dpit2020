package com.example.dpit2020navem.AddAnObject.Model;

import android.graphics.drawable.Drawable;
import android.media.Image;

import java.io.Serializable;
import java.util.List;

public class ObjectType implements Serializable {
    private String objectTypeName;
    private int objectTypePicture;
    private Integer objectTypeDisinfectionTime;//seconds
    private List<Object> objectList;

    public ObjectType(String objectTypeName, int objectTypePicture, Integer objectTypeDisinfectionTime, List<Object> objectList) {
        this.objectTypeName = objectTypeName;
        this.objectTypePicture = objectTypePicture;
        this.objectTypeDisinfectionTime = objectTypeDisinfectionTime;
        this.objectList = objectList;
    }

    public String getObjectTypeName() {
        return objectTypeName;
    }

    public void setObjectTypeName(String objectTypeName) {
        this.objectTypeName = objectTypeName;
    }

    public int getObjectTypePicture() {
        return objectTypePicture;
    }

    public void setObjectTypePicture(int objectTypePicture) {
        this.objectTypePicture = objectTypePicture;
    }

    public Integer getObjectTypeDisinfectionTime() {
        return objectTypeDisinfectionTime;
    }

    public void setObjectTypeDisinfectionTime(Integer objectTypeDisinfectionTime) {
        this.objectTypeDisinfectionTime = objectTypeDisinfectionTime;
    }

    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }
}
