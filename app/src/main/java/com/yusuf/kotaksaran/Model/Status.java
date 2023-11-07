package com.yusuf.kotaksaran.Model;

import com.google.gson.annotations.SerializedName;

public class Status {
    private String id_status;
    private String status;

    public Status (String id_status,String status) {
        this.id_status = id_status;
        this.status = status;
    }

    @SerializedName("id_status")
    public String getId_status() {return id_status;}
    public void setId_status(String id_status) {
        this.id_status = id_status;
    }

    @SerializedName("status")
    public String getStatus() {return status;}
    public void setStatus(String status) {
        this.status = status;
    }
}
