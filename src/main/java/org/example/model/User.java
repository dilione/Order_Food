package org.example.model;

import lombok.*;

import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class User {
    private long chatId;
    private String step;
    private String name;
    private String msg;
    private List<Product> products;
}
