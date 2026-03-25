package com.app.ecommerce.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.app.ecommerce.entity.Cart;
import com.app.ecommerce.entity.CartItem;
import com.app.ecommerce.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // ➕ Add to cart
    @PostMapping
    public Cart addToCart(@RequestParam Long productId,
                          @RequestParam int quantity) {
        return cartService.addToCart(productId, quantity);
    }

    // 📄 View cart
    @GetMapping
    public List<CartItem> viewCart() {
        return cartService.viewCart();
    }

    // ❌ Remove item
    @DeleteMapping("/remove/{cartItemId}")
    public String removeItem(@PathVariable Long cartItemId) {
        cartService.removeItem(cartItemId);
        return "Item removed";
    }
}