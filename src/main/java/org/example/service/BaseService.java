package org.example.service;

import org.example.model.Category;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface BaseService<T,R> {

    List<Category> categoryList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    boolean add(T t);
    List<R> getList();
}
