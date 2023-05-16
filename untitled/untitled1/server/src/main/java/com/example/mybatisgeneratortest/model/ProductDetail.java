package com.example.mybatisgeneratortest.model;

import java.io.Serializable;
import java.util.Date;

public class ProductDetail implements Serializable {
    private Integer id;

    private Integer product_id;

    private Integer product_active;

    private String product_packed;

    private Long user_id;

    private Date creat_time;

    private Date rob_time;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getProduct_active() {
        return product_active;
    }

    public void setProduct_active(Integer product_active) {
        this.product_active = product_active;
    }

    public String getProduct_packed() {
        return product_packed;
    }

    public void setProduct_packed(String product_packed) {
        this.product_packed = product_packed;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Date creat_time) {
        this.creat_time = creat_time;
    }

    public Date getRob_time() {
        return rob_time;
    }

    public void setRob_time(Date rob_time) {
        this.rob_time = rob_time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", product_id=").append(product_id);
        sb.append(", product_active=").append(product_active);
        sb.append(", product_packed=").append(product_packed);
        sb.append(", user_id=").append(user_id);
        sb.append(", creat_time=").append(creat_time);
        sb.append(", rob_time=").append(rob_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}