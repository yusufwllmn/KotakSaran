package com.yusuf.kotaksaran.Model;

import com.google.gson.annotations.SerializedName;

public class Subjek {
    private String id_bagian;
    private String bagian;

    public Subjek (String id_bagian, String bagian) {
        this.id_bagian = id_bagian;
        this.bagian = bagian;
    }

    @SerializedName("id_bagian")
    public String getId_bagian() {return id_bagian;}
    public void setId_bagian(String id_bagian) {
        this.id_bagian = id_bagian;
    }

    @SerializedName("bagian")
    public String getBagian() {
        return bagian;
    }
    public void setBagian(String bagian) {
        this.bagian = bagian;
    }
}
