package com.shopshoe.beans;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItem {
    private Long id;
    private String masp;
    private String pName;
    private int price;
    private int sell_price;
    private String description;
    private int quantity =1;
    private String img;

}
