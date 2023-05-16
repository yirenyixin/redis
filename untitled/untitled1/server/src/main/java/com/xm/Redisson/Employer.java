package com.xm.Redisson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer  implements Serializable {

    private String putTime;

    private String packed;
    private String userid;
    private String product_details_productID;

    public void setPutTime() {
        this.putTime = new SimpleDateFormat("hh:mm:ss").format(new Date());
    }
}
