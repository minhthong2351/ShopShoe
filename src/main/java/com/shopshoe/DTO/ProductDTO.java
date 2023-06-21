package com.shopshoe.DTO;

import com.shopshoe.beans.Brand;
import com.shopshoe.beans.Categories;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String masp;
    private String name;
    private String description;
    private int price;
    private int sell_price;
    private int quantity;
    private String img;
    private int quantity_sold;
    private String brand_name;
    private String categories_name;
    private Long brand_id;
    private Long categories_id;
}
