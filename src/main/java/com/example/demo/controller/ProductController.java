package com.example.demo.controller;

import com.example.demo.model.baseResponse.BaseResponse;
import com.example.demo.model.baseResponse.ResponeDelete;
import com.example.demo.model.entity.Product;
import com.example.demo.service.ProductService;
import com.sun.istack.Nullable;
import org.hibernate.JDBCException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<BaseResponse<List<Product>>> findAllProducts() {
        BaseResponse<List<Product>> base = new BaseResponse<>();
        List<Product> list = service.getProducts();
        base.setStatus(200);
        base.setMessage("Danh sach san pham");
        base.setData(list);
        return ResponseEntity.ok(base);
    }
    //tim theo id
    @GetMapping("/product/{id}")
    public ResponseEntity<BaseResponse<Product>> findProductById(@PathVariable int id) {
        BaseResponse<Product> base = new BaseResponse<>();
        Product product = service.getProductById(id);
        if (product == null) {
            String str = "No query results for model App|Product " + id;
            base.setStatus(404);
            base.setMessage(str);
            return new ResponseEntity<>(base, HttpStatus.NOT_FOUND);
        }
        base.setStatus(200);
        base.setMessage("Yêu cầu thành công" + id);
        base.setData(product);
        return ResponseEntity.ok(base);
    }

    //them san pham
    @PostMapping("/addProduct")
    public ResponseEntity<BaseResponse<Product>> addProduct(Product product) throws SQLException {
        String errorMessage = null;
        BaseResponse<Product> base = new BaseResponse<>();
        try {
            Product product1 = service.saveProduct(product);
            base.setStatus(200);
            base.setMessage("Tạo thành công");
            base.setData(product1);
            return ResponseEntity.ok(base);
        } catch (DataIntegrityViolationException ex) {
            base.setStatus(500);
            base.setMessage(ex.getRootCause().getMessage());
            base.setData(null);
            return new ResponseEntity<>(base, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //them danh sach san pham
    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }


    @PutMapping("/update")
    public ResponseEntity<BaseResponse<Product>> updateProduct(@RequestBody Product product) {
        BaseResponse<Product> base = new BaseResponse<>();
        Product product1 = service.updateProduct(product);
        base.setStatus(200);
        base.setMessage("Update thanh cong");
        base.setData(product1);
        return ResponseEntity.ok(base);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponeDelete<String>> deleteProduct(@PathVariable int id) {
        ResponeDelete<String> base = new ResponeDelete<>();
        String str = service.deleteProduct(id);
        if(str == null || str == "") {
            base.setStatus(404);
            base.setMessage("Không tìm thấy đường dẫn trong database");
            return new ResponseEntity<>(base, HttpStatus.NOT_FOUND);
        }
        base.setStatus(200);
        base.setMessage(str);
        return ResponseEntity.ok(base);
    }
}
