package com.app.ecommerce.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.ecommerce.Repository.ProductRepository;
import com.app.ecommerce.entity.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
}
