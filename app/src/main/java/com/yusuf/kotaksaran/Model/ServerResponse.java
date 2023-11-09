package com.yusuf.kotaksaran.Model;

import java.util.List;

public class ServerResponse {
    private String message;
    private List<Laporan> laporan;
    private List<User> user;
    private List<Pelapor> pelapor;

    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}

    public List<Laporan> getLaporan() {return laporan;}
    public void setLaporan(List<Laporan> laporan) {this.laporan = laporan;}

    public List<User> getUser() {return user;}
    public void setUser(List<User> user) {this.user = user;}

    public List<Pelapor> getPelapor() {return pelapor;}
    public void setPelapor(List<Pelapor> pelapor) {this.pelapor = pelapor;}
}
