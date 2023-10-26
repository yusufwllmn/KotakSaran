package com.yusuf.kotaksaran.lapor;

import com.google.gson.annotations.SerializedName;

public class PostPutDelNotes {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    Notes mNotes;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Notes getNotes() {
        return mNotes;
    }
    public void setNotes(Notes Notes) {
        mNotes = Notes;
    }
}
