package com.example.springbasicsecurity.Controller;

import com.example.springbasicsecurity.Entity.Product;
import com.example.springbasicsecurity.Entity.UserInfo;
import com.example.springbasicsecurity.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping("/welcome")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    //welcome endpoint is open to use by ADMIN and EMPLOYEE only.
    public String greeting(){
        return  "Welcome to Spring Security Example";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/new")
    //new endpoint is open to use by anyone.
    public String addNewUser(@RequestBody UserInfo userInfo){
        return productService.addUser(userInfo);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable("id") int id){
        return productService.getProductById(id);
    }
}
