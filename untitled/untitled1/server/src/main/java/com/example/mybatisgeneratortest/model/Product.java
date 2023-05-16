package com.example.mybatisgeneratortest.model;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer id;

    private String name;

    private String img;

    private String size;

    private String type;

    private String price;

    private String shopid;

    private String color;

    private String stock;

    private String produc_packed;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getProduc_packed() {
        return produc_packed;
    }

    public void setProduc_packed(String produc_packed) {
        this.produc_packed = produc_packed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", img=").append(img);
        sb.append(", size=").append(size);
        sb.append(", type=").append(type);
        sb.append(", price=").append(price);
        sb.append(", shopid=").append(shopid);
        sb.append(", color=").append(color);
        sb.append(", stock=").append(stock);
        sb.append(", produc_packed=").append(produc_packed);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}