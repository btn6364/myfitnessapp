package com.baonguyen.fitnesstrackingapp.service;

import com.baonguyen.fitnesstrackingapp.dto.ProductResponse;
import com.baonguyen.fitnesstrackingapp.dto.UserCredentials;
import com.baonguyen.fitnesstrackingapp.dto.UserResponse;
import com.baonguyen.fitnesstrackingapp.entity.User;
import com.baonguyen.fitnesstrackingapp.repository.ProductRepository;
import com.baonguyen.fitnesstrackingapp.repository.UserRepository;
import com.baonguyen.fitnesstrackingapp.util.Convertor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final Convertor convertor;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    /**
     * Get user information
     * @param authentication the authentication object
     * @return user information
     */
    public UserResponse getUserInfo(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        var retrievedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return convertor.userToResponseDto(retrievedUser);
    }

    /**
     * Get user information
     * @param authentication the authentication object
     * @return user information
     */
    public UserCredentials getUserCredentialsOnly(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        var retrievedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return convertor.userCredentialsToDto(retrievedUser);
    }

    /**
     * Get products associated with the user
     * @param authentication the authentication object
     * @return a list of products
     */
    public List<ProductResponse> getUsersProducts(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        var retrievedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return convertor.productMapper(retrievedUser.getProducts());
    }

    /**
     * Add a product to the user's list
     * @param authentication the authentication object
     * @param code the product code
     * @return HTTP status CREATED if successful
     */
    public ResponseEntity<?> addProduct(Authentication authentication, Integer code) {
        var user = (User) authentication.getPrincipal();
        var retrievedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var product = productRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Product does not exist"));
        retrievedUser.setProducts(List.of(product));
        userRepository.save(retrievedUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
