package com.yusuf.kotaksaran.Model;

import com.google.gson.annotations.SerializedName;

public class Kategori {
    private String id_kategori;
    private String kategori;

    public Kategori(String id_kategori, String kategori){
        this.id_kategori = id_kategori;
        this.kategori = kategori;
    }

    @SerializedName("id_kategori")
    public String getId_kategori() {return id_kategori;}
    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    @SerializedName("kategori")
    public String getKategori() {return kategori;}
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
