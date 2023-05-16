package jmu.model;

import java.io.Serializable;

public class Logistics implements Serializable {
    private Integer orderid;

    private String state;

    private String return_goods;

    private static final long serialVersionUID = 1L;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReturn_goods() {
        return return_goods;
    }

    public void setReturn_goods(String return_goods) {
        this.return_goods = return_goods;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderid=").append(orderid);
        sb.append(", state=").append(state);
        sb.append(", return_goods=").append(return_goods);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}