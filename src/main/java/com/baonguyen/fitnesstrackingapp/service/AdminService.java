package com.baonguyen.fitnesstrackingapp.service;

import com.baonguyen.fitnesstrackingapp.dto.ProductRequest;
import com.baonguyen.fitnesstrackingapp.dto.ProductResponse;
import com.baonguyen.fitnesstrackingapp.dto.UserResponse;
import com.baonguyen.fitnesstrackingapp.entity.Product;
import com.baonguyen.fitnesstrackingapp.repository.ProductRepository;
import com.baonguyen.fitnesstrackingapp.repository.UserRepository;
import com.baonguyen.fitnesstrackingapp.util.Convertor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final Convertor convertor;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    /**
     * Get all products in the database
     * @return a list of products
     */
    public List<ProductResponse> getAllProduct() {
        var products = productRepository.findAll();
        return convertor.productMapper(products);
    }

    /**
     * Get information of all users
     * @return a list of users
     */
    public List<UserResponse> getUsersInfo() {
        var users = userRepository.findAll();
        return convertor.userMapper(users);
    }

    /**
     * Add a new product to the database
     * @param request the product request
     * @return HTTP status CREATED if successful
     */
    public ResponseEntity<?> addProduct(ProductRequest request) {
        var product = productRepository.findByCode(request.code());
        if (product.isPresent()) {
            throw new IllegalStateException("Product already exists");
        }
        var newProduct = Product.builder()
                .description(request.description())
                .name(request.name())
                .calories(request.calories())
                .protein(request.protein())
                .carbs(request.carbs())
                .fat(request.fat())
                .code(request.code())
                .build();
        productRepository.save(newProduct);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Delete a product from the database
     * @param code the product code
     * @return HTTP status NO_CONTENT if successful
     */
    public ResponseEntity<?> deleteProduct(Integer code) {
        var product = productRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Product does not exist!"));
        productRepository.delete(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add an image to a product
     * @param image the image file
     * @param code the product code
     * @return HTTP status CREATED if successful
     * @throws IOException if an I/O error occurs
     */
    public ResponseEntity<?> addImageToProduct(MultipartFile image, Integer code) throws IOException {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("Image is empty");
        }
        var product = productRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
        product.setImage(image.getBytes());
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
