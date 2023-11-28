package com.yusuf.kotaksaran.Model;

import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;

public class LaporanRequest {
    private String id_laporan;
    private String subjek_laporan;
    private String isi_laporan;
    private String tanggal_lapor;
    private Status status;
    private MultipartBody.Part dokumen;
    private User user;

    public LaporanRequest(String id_laporan, String subjek_laporan, String isi_laporan, String tanggal_lapor, Status status, MultipartBody.Part dokumen, User user){
        this.id_laporan = id_laporan;
        this.subjek_laporan = subjek_laporan;
        this.isi_laporan= isi_laporan;
        this.tanggal_lapor = tanggal_lapor;
        this.status = status;
        this.dokumen = dokumen;
        this.user = user;
    }

    @SerializedName("id_laporan")
    public String getId_laporan() {return id_laporan;}
    public void setId_laporan(String id_laporan) {this.id_laporan = id_laporan;}

    @SerializedName("subjek_laporan")
    public String getSubjek_laporan() {return subjek_laporan;}
    public void setSubjek_laporan(String subjek_laporan) {this.subjek_laporan = subjek_laporan;}

    @SerializedName("isi_laporan")
    public String getIsi_laporan() {return isi_laporan;}
    public void setIsi_laporan(String isi_laporan) {this.isi_laporan = isi_laporan;}

    @SerializedName("tanggal_lapor")
    public String getTanggal_lapor() {return tanggal_lapor;}
    public void setTanggal_lapor(String tanggal_lapor) {this.tanggal_lapor = tanggal_lapor;}

    @SerializedName("status")
    public Status getStatus() {return status;}
    public void setStatus(Status status) {this.status = status;}

    @SerializedName("dokumen")
    public MultipartBody.Part getDokumen() {return dokumen;}
    public void setDokumen(MultipartBody.Part dokumen) {this.dokumen = dokumen;}

    @SerializedName("user")
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
}
