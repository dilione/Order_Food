package org.example.service;

import org.example.dataBase.DataBase;
import org.example.model.Product;
import org.example.model.User;
import java.util.ArrayList;
import java.util.List;

import static org.example.dataBase.DataBase.*;

public class ProductService {


    public boolean addUserProducts(double chatId,Product product){
        if(users==null){
            return false;
        }
        for (User user : users) {
            if(user.getChatId() == chatId){
                if(user.getProducts()==null){
                    List<Product> productsTemp = new ArrayList<>();
                    productsTemp.add(product);
                    products=productsTemp;
                    user.setProducts(products);
                    DataBase.writerJsonUser(users);
                    return true;
                }
                products.add(product);
                user.setProducts(products);
                DataBase.writerJsonUser(users);
                return true;
            }
        }
        return false;
    }
    public boolean addFood(Product food){
//        products = readFileFood();
        if(products == null){
            List<Product> productsTemp = new ArrayList<>();
            productsTemp.add(food);
            products = productsTemp;
            DataBase.writerJsonFood(products);
            return true;
        }
        for (Product product1:products) {
            if(product1.getName().equals(food.getName())){
                return false;
            }
        }
        products.add(food);
        writerJsonFood(products);
        return true;
    }

    public List<Product> getUserProducts(double chatId){
        if(users == null){
            return null;
        }
        for (User user:users) {
            if(user.getChatId() == chatId){
                return user.getProducts();
            }
        }
        return null;
    }

}
