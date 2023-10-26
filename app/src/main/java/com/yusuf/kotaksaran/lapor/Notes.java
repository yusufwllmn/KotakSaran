package com.yusuf.kotaksaran.lapor;

import android.telephony.mbms.StreamingServiceInfo;

import com.google.gson.annotations.SerializedName;

public class Notes {
    private String id_laporan;
    private String subjek_laporan;
    private String isi_laporan;
    private String tanggal_lapor;
    private String id_status;
    private String dokumen;
    private String id_pelapor;

    public Notes(String id_laporan, String subjek_laporan, String isi_laporan, String tanggal_lapor, String id_status, String dokumen, String id_pelapor){
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
    public String getSubjek_laporan() {return subjek_laporan;}
    public void setSubjek_laporan(String subjek_laporan) {this.subjek_laporan = subjek_laporan;}

    @SerializedName("isis_laporan")
    public String getIsi_laporan() {return isi_laporan;}
    public void setIsi_laporan(String isi_laporan) {this.isi_laporan = isi_laporan;}

    @SerializedName("tanggal_lapor")
    public String getTanggal_lapor() {return tanggal_lapor;}
    public void setTanggal_lapor(String tanggal_lapor) {this.tanggal_lapor = tanggal_lapor;}

    @SerializedName("id_status")
    public String getId_status() {return id_status;}
    public void setId_status(String id_status) {this.id_status = id_status;}

    @SerializedName("dokumen")
    public String getDokumen() {return dokumen;}
    public void setDokumen(String dokumen) {this.dokumen = dokumen;}

    @SerializedName("id_pelapor")
    public String getId_pelapor() {return id_pelapor;}
    public void setId_pelapor(String id_pelapor) {this.id_pelapor = id_pelapor;}
}
