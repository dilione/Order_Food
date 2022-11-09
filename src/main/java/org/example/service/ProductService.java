package org.example.service;

import org.example.model.Product;

import java.util.List;

public class ProductService implements BaseService<Product,Product>{

    @Override
    public boolean add(Product product) {
        productList.add(product);
        return true;
    }

    @Override
    public List<Product> getList() {
        return productList;
    }
}
