package com.yusuf.kotaksaran.lapor;

import java.util.List;

public class GetNotes {
    String status;
    String message;
    List<Notes> data;

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

    public List<Notes> getData() {

        return data;
    }

    public void setData(List<Notes> data) {

        this.data = data;
    }
}
