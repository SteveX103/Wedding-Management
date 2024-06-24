package com.javabackend.Backend.Service;

import com.javabackend.Backend.Entity.Product;
import com.javabackend.Backend.Interface.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        // Perform any necessary validation or business logic here
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductPrice(updatedProduct.getProductPrice());
            existingProduct.setProductAbout(updatedProduct.getProductAbout());
            existingProduct.setProductImageUrl(updatedProduct.getProductImageUrl());
            return productRepository.save(existingProduct);
        } else {
            return null; // Return null if product with given ID is not found
        }
    }

    public boolean deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false; // Return false if product with given ID is not found
        }
    }
}
