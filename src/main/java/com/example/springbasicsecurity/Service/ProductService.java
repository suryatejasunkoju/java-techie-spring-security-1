package com.example.springbasicsecurity.Service;

import com.example.springbasicsecurity.Entity.Product;
import com.example.springbasicsecurity.Entity.UserInfo;
import com.example.springbasicsecurity.Repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class ProductService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    List<Product> productList;
    @PostConstruct
    public void loadAllProducts(){
        productList = IntStream
                .rangeClosed(1,100)
                .mapToObj(i -> Product.builder()
                        .id(i)
                        .name("product"+i)
                        .price(new Random().nextInt(10))
                        .quantity(new Random().nextInt(5000))
                        .build()
                ).toList();
    }
    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductById(int id) {
        return productList
                .stream()
                .filter(product -> product.getId()==id)
                .findAny()
                .orElseThrow(()->new RuntimeException("product id:"+id+" not found"));
    }
    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added successfully";
    }
}
