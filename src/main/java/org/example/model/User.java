package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString

public class User {
    private long chatId;
    private String step;
    private String name;
    private List<Product> products;
}
