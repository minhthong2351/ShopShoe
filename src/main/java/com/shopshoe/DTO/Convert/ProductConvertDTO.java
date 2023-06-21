package com.shopshoe.DTO.Convert;

import com.shopshoe.DTO.ProductDTO;
import com.shopshoe.beans.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConvertDTO {
    public ProductDTO convert(Product product){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());

        productDTO.setMasp(product.getMasp());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setSell_price(product.getSell_price());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setImg(product.getImg());
        productDTO.setQuantity_sold(product.getQuantity_sold());
        productDTO.setBrand_name(product.getBrand().getName());
        productDTO.setCategories_name(product.getCategories().getName());
        productDTO.setBrand_id(product.getBrand().getId());
        productDTO.setCategories_id(product.getCategories().getId());
        return productDTO;
    }
}
