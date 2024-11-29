package com.emazon.transaction.domain.spi;

import com.emazon.transaction.domain.model.ShoppingCart;

import java.util.List;

public interface IShoppingCartPersistencePort {
    boolean removeProductListFromShoppingCart(List<Long> productIdList);

    boolean restoreShoppingCartFromShoppingCartList(List<ShoppingCart> shoppingCartList);

    List<ShoppingCart> getListShoppingCartInListIdProduct(List<Long> productIdList);
}
