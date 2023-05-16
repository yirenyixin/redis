package com.example.mybatisgeneratortest.model;

import java.io.Serializable;

public class CommodityReport implements Serializable {
    private String productname;

    private String num;

    private String profit;

    private static final long serialVersionUID = 1L;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productname=").append(productname);
        sb.append(", num=").append(num);
        sb.append(", profit=").append(profit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}