package pojo;

import lombok.Data;
import lombok.ToString;



@Data
@ToString
public class ProductDto {

    private String shopId;
    private String productName;
    private String size;
    private String type;
    private String color;
    private String img;
    //指定多少人抢
    private String price;

    //指定总金额-单位为分
    private String stock;
}
