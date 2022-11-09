package org.example.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private  String name;
    private String type;
    private double price;
    private int amount;
    private String url;
    private String content;
}
