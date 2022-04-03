package com.geekbrains.spoonacular.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductView {

    private Long id;

    private String title;

    private String image;

    private String imageType;


}
