package com.example.loblawdigital.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Entry implements Serializable {
    @SerializedName("code")
    private String code;
    @SerializedName("image")
    private String image;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("type")
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
