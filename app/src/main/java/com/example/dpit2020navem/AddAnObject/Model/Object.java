package com.example.dpit2020navem.AddAnObject.Model;

import java.io.Serializable;

public class Object implements Serializable {
    private String objectName;
    private Integer objectRemainingDisinfectionTime;//seconds

    public Object(String objectName, Integer objectRemainingDisinfectionTime) {
        this.objectName = objectName;
        this.objectRemainingDisinfectionTime = objectRemainingDisinfectionTime;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getObjectRemainingDisinfectionTime() {
        return objectRemainingDisinfectionTime;
    }

    public void setObjectRemainingDisinfectionTime(Integer objectRemainingDisinfectionTime) {
        this.objectRemainingDisinfectionTime = objectRemainingDisinfectionTime;
    }
}
