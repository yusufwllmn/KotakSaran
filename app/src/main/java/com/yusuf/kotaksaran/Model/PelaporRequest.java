package com.yusuf.kotaksaran.Model;

import com.google.gson.annotations.SerializedName;

public class PelaporRequest {
    private String id_pelapor;
    private String id_identitas;
    private String nama;
    private String id_kategori;
    private String alamat;
    private String telephone;

    public PelaporRequest(String id_pelapor, String id_identitas, String nama, String id_kategori, String alamat, String telephone){
        this.id_pelapor = id_pelapor;
        this.id_identitas = id_identitas;
        this.nama = nama;
        this.id_kategori = id_kategori;
        this.alamat = alamat;
        this.telephone = telephone;
    }

    @SerializedName("id_laporan")
    public String getId_pelapor() {return id_pelapor;}
    public void setId_pelapor(String id_pelapor) {this.id_pelapor = id_pelapor;}

    @SerializedName("id_identitas")
    public String getId_identitas() {return id_identitas;}
    public void setId_identitas(String id_identitas) {this.id_identitas = id_identitas;}

    @SerializedName("nama")
    public String getNama() {return nama;}
    public void setNama(String nama) {this.nama = nama;}

    @SerializedName("id_kategori")
    public String getid_kategori() {return id_kategori;}
    public void setid_kategori(String id_kategori) {this.id_kategori = id_kategori;}

    @SerializedName("alamat")
    public String getAlamat() {return alamat;}
    public void setAlamat(String alamat) {this.alamat = alamat;}

    @SerializedName("telephone")
    public String getTelephone() {return telephone;}
    public void setTelephone(String telephone) {this.telephone = telephone;}
}
