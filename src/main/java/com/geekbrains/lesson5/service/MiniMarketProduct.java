package com.geekbrains.lesson5.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class MiniMarketProduct {

    private Long id;
    private String title;
    private String image;
    private String imageType;

}
