package com.emazon.transaction.infraestructure.output.jpa.adapters;

import com.emazon.transaction.domain.model.ShoppingCart;
import com.emazon.transaction.domain.spi.IShoppingCartPersistencePort;
import com.emazon.transaction.infraestructure.exceptionhandler.ConnectionRefused;
import com.emazon.transaction.infraestructure.output.jpa.feign.ShoppingCartFeignClient;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class ShoppingCartFeignAdapter implements IShoppingCartPersistencePort {
    private final ShoppingCartFeignClient shoppingCartFeignClient;

    @Override
    public boolean removeProductListFromShoppingCart(List<Long> productIdList) {
        try {
            this.shoppingCartFeignClient.removeProductListFromShoppingCart(productIdList);
            return true;
        }catch (RuntimeException e){
            ConnectionRefused.throwIfConnectionRefused(e.getMessage());
            return false;
        }
    }
    @Override
    public boolean restoreShoppingCartFromShoppingCartList(List<ShoppingCart> shoppingCartList) {
        try {
            this.shoppingCartFeignClient.restoreShoppingCartFromShoppingCartList(shoppingCartList);
            return true;
        }catch (RuntimeException e){
            ConnectionRefused.throwIfConnectionRefused(e.getMessage());
            return false;
        }
    }
    @Override
    public List<ShoppingCart> getListShoppingCartInListIdProduct(List<Long> listProductIds){
        try{
            return this.shoppingCartFeignClient.getListShoppingCartInListIdProduct(listProductIds);

        }catch (RuntimeException e){
            ConnectionRefused.throwIfConnectionRefused(e.getMessage());
            return List.of();
        }
    }

}
