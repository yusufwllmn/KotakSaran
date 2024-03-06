package com.yusuf.kotaksaran.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pelapor {
    private String id_pelapor;
    private String id_identitas;
    private String nama;
    private String id_kategori;
    private Kategori kategori;
    private String alamat;
    private String telephone;
    private String avatar;
    private User user;

    public Pelapor(String id_pelapor, String id_identitas, String nama, String id_kategori, Kategori kategori, String alamat, String telephone, String avatar, User user){
        this.id_pelapor = id_pelapor;
        this.id_identitas = id_identitas;
        this.nama = nama;
        this.nama = id_kategori;
        this.kategori = kategori;
        this.alamat = alamat;
        this.telephone = telephone;
        this.avatar = avatar;
        this.user = user;
    }

    @SerializedName("id_pelapor")
    public String getId_pelapor() {return id_pelapor;}
    public void setId_pelapor(String id_pelapor) {this.id_pelapor = id_pelapor;}

    @SerializedName("id_identitas")
    public String getId_identitas() {return id_identitas;}
    public void setId_identitas(String id_identitas) {this.id_identitas = id_identitas;}

    @SerializedName("nama")
    public String getNama() {return nama;}
    public void setNama(String nama) {this.nama = nama;}

    @SerializedName("id_kategori")
    public String getId_kategori() {return id_kategori;}
    public void setId_kategori(String id_kategori) {this.id_kategori = id_kategori;}

    @SerializedName("kategori")
    public Kategori getKategori() {return kategori;}
    public void setKategori(Kategori kategori) {this.kategori = kategori;}

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
