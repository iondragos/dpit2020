package com.example.dpit2020navem.AddAnObject.Model;

import java.io.Serializable;

public class Object implements Serializable {
    private String objectName;
    private Integer objectDisinfectionTime;//seconds

    public Object(String objectName, Integer objectDisinfectionTime) {
        this.objectName = objectName;
        this.objectDisinfectionTime = objectDisinfectionTime;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getObjectDisinfectionTime() {
        return objectDisinfectionTime;
    }

    public void setObjectDisinfectionTime(Integer objectDisinfectionTime) {
        this.objectDisinfectionTime = objectDisinfectionTime;
    }
}
