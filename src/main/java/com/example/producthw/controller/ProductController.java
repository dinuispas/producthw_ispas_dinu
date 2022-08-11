package com.example.producthw.controller;

import com.example.producthw.model.Product;
import com.example.producthw.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @PostMapping("/updateProduct/{id}/{initialStock}")
    public void updateProduct(@RequestBody @PathVariable("id") Integer productId,
                              @PathVariable("initialStock") Integer initialStock){
        productService.updateProductStock(productId,initialStock);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable("id") Integer productId){
        productService.deleteProduct(productId);
    }

    @PostMapping("/updateProductByOne/{id}")
    public void updateProductPlus(@PathVariable("id") Integer productId){
        productService.updateStockPlus(productId);
    }

    @PostMapping("/updateProductByMinusOne/{id}")
    public void updateProductMinus(@PathVariable("id") Integer productId){
        productService.updateStock(productId);
    }

    @GetMapping("/deleted")
    public List<Product> getAllProductsAndDeleted(boolean isDeleted){
        return productService.findAllProducts(isDeleted);
    }







}
