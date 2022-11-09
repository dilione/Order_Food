package org.example;

import org.example.dataBase.DataBase;
import org.example.model.Product;
import org.example.model.User;
import org.example.service.ProductService;
import org.example.service.UserService;

import java.util.Scanner;

public class Main {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerNum = new Scanner(System.in);

    static UserService userService = new UserService();
    static ProductService productService = new ProductService();
    public static void main(String[] args) {


        byte var = 1;
        while (var!=0){
            System.out.println("add user -->1 add user product -->2 user products -->3 add food -->4 show foods-->5");
            var = scannerNum.nextByte();
            switch (var){
                case 1->{
                    User user = new User();

                    System.out.println("enter user chat Id");
                    user.setChatId(scannerNum.nextLong());

                    System.out.println("enter user name");
                    user.setName(scannerStr.nextLine());

                    userService.addUser(user);
                }
                case 2->{
                    Product product = new Product();
                    System.out.println("enter user chat Id");
                    double chatId = scannerNum.nextDouble();

                    System.out.println("enter product name");
                    product.setName(scannerStr.nextLine());

                    System.out.println("enter product price");
                    product.setPrice(scannerNum.nextDouble());

                    productService.addUserProducts(chatId,product);
                }
                case 3->{
                    System.out.println("enter user chat Id");
                    double chatId = scannerNum.nextDouble();
                    System.out.println(productService.getUserProducts(chatId));
                }
                case 4->{
                    Product product = new Product();
                    System.out.println("enter food name");
                    product.setName(scannerStr.nextLine());

                    System.out.println("enter food price");
                    product.setPrice(scannerNum.nextDouble());

                    productService.addFood(product);

                }
                case 5->{
                    System.out.println(DataBase.readFileFood());
                }


            }
        }
    }
}