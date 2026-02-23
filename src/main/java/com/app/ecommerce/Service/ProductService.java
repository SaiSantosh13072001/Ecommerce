package com.app.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.ecommerce.entity.Product;
import com.app.ecommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
