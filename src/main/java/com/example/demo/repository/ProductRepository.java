package com.example.demo.repository;

import com.example.demo.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
;
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);

//    @Query(
//            value = "UPDATE product_tbl SET name = ?2, price = ?3, category = ?4 WHERE id = ?1",
//            nativeQuery = true)
//    Product updateProductNT(int id,String name, int price, String category);
//
//    @Query(
//            value = "SELECT * FROM product_tbl p WHERE p.name = ?1",
//            nativeQuery = true)
//            Product finhByNameNativeQuery(String name);
//
//    @Query(
//            value = "DELETE FROM product_tbl p WHERE p.id = ?1",
//            nativeQuery = true)
//    String deleteById1(int id);

}
