package com.yusuf.kotaksaran.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pelapor {
    private String id_pelapor;
    private String id_identitas;
    private String nama;
    private Kategori kategori;
    private String alamat;
    private String telephone;
    private String avatar;
    private User user;

    public Pelapor(String id_pelapor, String id_identitas, String nama, Kategori kategori, String alamat, String telephone, String avatar, User user){
        this.id_pelapor = id_pelapor;
        this.id_identitas = id_identitas;
        this.nama = nama;
        this.kategori = kategori;
        this.alamat = alamat;
        this.telephone = telephone;
        this.avatar = avatar;
        this.user = user;
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

    @SerializedName("kategori")
    public Kategori getkategori() {return kategori;}
    public void setkategori(Kategori kategori) {this.kategori = kategori;}

    @SerializedName("alamat")
    public String getAlamat() {return alamat;}
    public void setAlamat(String alamat) {this.alamat = alamat;}

    @SerializedName("telephone")
    public String getTelephone() {return telephone;}
    public void setTelephone(String telephone) {this.telephone = telephone;}

    @SerializedName("avatar")
    public String getAvatar() {return avatar;}
    public void setAvatar(String avatar) {this.avatar = avatar;}

    @SerializedName("user")
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
}
