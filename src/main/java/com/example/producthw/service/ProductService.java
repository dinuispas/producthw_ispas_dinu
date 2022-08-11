package com.example.producthw.service;

import com.example.producthw.model.Product;
import com.example.producthw.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void addProduct(Product product){
        System.out.println(product);
        productRepository.save(product);

    }

    public void updateProductStock(Integer productId,Integer initialStock){
        productRepository.updateProduct(productId,initialStock);
    }

    public void deleteProduct(Integer productId){
        productRepository.deleteById(productId);
    }

    //need to solve entity manager
    public List<Product> findAllProducts(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedProductFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Product> products =  productRepository.findAll();
        session.disableFilter("deletedProductFilter");
        return products;
    }

    public void updateStockPlus(Integer productId){
        productRepository.updateStockPlus(productId);
    }

    public void updateStock(Integer productId){
        productRepository.updateStock(productId);
    }



}
