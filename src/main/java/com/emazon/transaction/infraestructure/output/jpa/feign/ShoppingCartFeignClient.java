package com.emazon.transaction.infraestructure.output.jpa.feign;

import com.emazon.transaction.domain.model.ShoppingCart;
import com.emazon.transaction.infraestructure.configuration.FeingClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.*;

@FeignClient(name = FEIGN_SHOPPING_CART_NAME, url = FEIGN_SHOPPING_CART_URL, configuration = FeingClientConfig.class)
public interface ShoppingCartFeignClient {

    @DeleteMapping("api/shopping-cart/remove-product")
    void removeProductListFromShoppingCart(@RequestParam(name="listId", defaultValue ="") List<Long> productIdList) ;
    @GetMapping("api/shopping-cart")
    List<ShoppingCart> getListShoppingCartInListIdProduct(@RequestParam(name="listId", defaultValue ="") List<Long> listProductIds);
    @PutMapping("api/shopping-cart/restore-products-fallback")
    void restoreShoppingCartFromShoppingCartList(@RequestBody List<ShoppingCart> shoppingCartList) ;
}
