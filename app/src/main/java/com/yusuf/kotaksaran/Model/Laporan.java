package com.yusuf.kotaksaran.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Laporan {
    private String id_laporan;
    private List<Subjek> subjek_laporan;
    private String isi_laporan;
    private String tanggal_lapor;
    private List<Status> id_status;
    private String dokumen;
    private List<User> id_pelapor;

    public Laporan(String id_laporan, List<Subjek> subjek_laporan, String isi_laporan, String tanggal_lapor, List<Status> id_status, String dokumen, List<User> id_pelapor){
        this.id_laporan = id_laporan;
        this.subjek_laporan = subjek_laporan;
        this.isi_laporan= isi_laporan;
        this.tanggal_lapor = tanggal_lapor;
        this.id_status = id_status;
        this.dokumen = dokumen;
        this.id_pelapor = id_pelapor;
    }

    @SerializedName("id_laporan")
    public String getId_laporan() {return id_laporan;}
    public void setId_laporan(String id_laporan) {this.id_laporan = id_laporan;}

    @SerializedName("subjek_laporan")
    public List<Subjek> getSubjek_laporan() {return subjek_laporan;}
    public void setSubjek_laporan(List<Subjek> subjek_laporan) {this.subjek_laporan = subjek_laporan;}

    @SerializedName("isi_laporan")
    public String getIsi_laporan() {return isi_laporan;}
    public void setIsi_laporan(String isi_laporan) {this.isi_laporan = isi_laporan;}

    @SerializedName("tanggal_lapor")
    public String getTanggal_lapor() {return tanggal_lapor;}
    public void setTanggal_lapor(String tanggal_lapor) {this.tanggal_lapor = tanggal_lapor;}

    @SerializedName("id_status")
    public List<Status> getId_status() {return id_status;}
    public void setId_status(List<Status> status) {this.id_status = id_status;}

    @SerializedName("dokumen")
    public String getDokumen() {return dokumen;}
    public void setDokumen(String dokumen) {this.dokumen = dokumen;}

    @SerializedName("id_pelapor")
    public List<User> getId_pelapor() {return id_pelapor;}
    public void setId_pelapor(List<User> id_pelapor) {this.id_pelapor = id_pelapor;}
}
