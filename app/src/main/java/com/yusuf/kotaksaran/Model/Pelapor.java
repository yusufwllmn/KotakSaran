package com.yusuf.kotaksaran.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pelapor {
    private String id_pelapor;
    private String id_identitas;
    private String nama;
    private List<Kategori> id_kategori;
    private String alamat;
    private String telephone;
    private String avatar;
    private List<User> id_user;

    public Pelapor(String id_pelapor, String id_identitas, String nama, List<Kategori> id_kategori, String alamat, String telephone, String avatar, List<User> id_user){
        this.id_pelapor = id_pelapor;
        this.id_identitas = id_identitas;
        this.nama = nama;
        this.id_kategori = id_kategori;
        this.alamat = alamat;
        this.telephone = telephone;
        this.avatar = avatar;
        this.id_user = id_user;
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
    public List<Kategori> getId_kategori() {return id_kategori;}
    public void setId_kategori(List<Kategori> id_kategori) {this.id_kategori = id_kategori;}

    @SerializedName("alamat")
    public String getAlamat() {return alamat;}
    public void setAlamat(String alamat) {this.alamat = alamat;}

    @SerializedName("telephone")
    public String getTelephone() {return telephone;}
    public void setTelephone(String telephone) {this.telephone = telephone;}

    @SerializedName("avatar")
    public String getAvatar() {return avatar;}
    public void setAvatar(String avatar) {this.avatar = avatar;}

    @SerializedName("id_user")
    public List<User> getId_user() {return id_user;}
    public void setId_user(List<User> id_user) {this.id_user = id_user;}
}
