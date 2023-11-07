package com.yusuf.kotaksaran.Model;

import java.util.List;

public class ServerResponse {
    private String message;
    List<Laporan> laporan;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Laporan> getLaporan() {return laporan; }

    public void setLaporan(List<Laporan> laporan) {
        this.laporan = laporan;
    }
}
