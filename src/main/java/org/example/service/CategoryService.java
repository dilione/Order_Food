package org.example.service;

import org.example.model.Category;

import java.util.List;

public class CategoryService implements BaseService<Category,Category>{

    @Override
    public boolean add(Category category) {
        categoryList.add(category);
        return true;
    }

    @Override
    public List<Category> getList() {
        return categoryList;
    }
}
