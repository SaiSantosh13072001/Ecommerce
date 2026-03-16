package com.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ecommerce.entity.Cart;
import com.app.ecommerce.entity.User;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}