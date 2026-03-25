package com.app.ecommerce.service;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

import com.app.ecommerce.entity.*;
import com.app.ecommerce.repository.*;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       ProductRepository productRepository,
                       UserRepository userRepository,
                    UserService userService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // 🛒 Get or create cart
    public Cart getCartByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(new Cart(user)));
    }

    // ➕ Add product to cart
    public Cart addToCart(Long productId, int quantity) {

        User user = userService.getCurrentUser();

        Cart cart = getCartByUser(user.getId());
        Product product = productRepository.findById(productId).orElseThrow();

        Optional<CartItem> existingItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem(cart, product, quantity);
            cartItemRepository.save(newItem);
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    // 📄 View cart
    public List<CartItem> viewCart() {
        User user = userService.getCurrentUser();
        return getCartByUser(user.getId()).getItems();
    }

    // ❌ Remove item
    public void removeItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}