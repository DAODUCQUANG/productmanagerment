package com.example.demo.service;

import com.example.demo.model.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    public String deleteProduct(int id) {
        try{
            repository.deleteById(id);
        } catch (Exception ex) {
            return null;
        }
        return "product removed !!"+id;
    }

    public Product updateProduct(Product product) {
        Product exitstingProduct = repository.findById(product.getId()).orElse(null);
        exitstingProduct.setName(product.getName());
        exitstingProduct.setPrice(product.getPrice());
        exitstingProduct.setCategory(product.getCategory());
        return repository.save(exitstingProduct);
    }

}
