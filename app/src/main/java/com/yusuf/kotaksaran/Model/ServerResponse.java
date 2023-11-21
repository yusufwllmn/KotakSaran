package com.yusuf.kotaksaran.Model;

import java.util.List;

public class ServerResponse {
    private String message;
    private String token;
    private List<Laporan> laporan;
    private User user;
    private Pelapor pelapor;
    private List<Subjek> bagian;

    public List<Subjek> getBagian() {return bagian;}
    public void setBagian(List<Subjek> bagian) {this.bagian = bagian;}

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    public Pelapor getPelapor() {return pelapor;}
    public void setPelapor(Pelapor pelapor) {this.pelapor = pelapor;}

    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}

    public String getToken() {return token;}
    public void setToken(String token) {this.token = token;}

    public List<Laporan> getLaporan() {return laporan;}
    public void setLaporan(List<Laporan> laporan) {this.laporan = laporan;}
}
