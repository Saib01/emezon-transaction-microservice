package com.emazon.transaction.domain.usecase;

import com.emazon.transaction.domain.api.IPurchaseServicePort;
import com.emazon.transaction.domain.exeption.InsufficientStockException;
import com.emazon.transaction.domain.exeption.ProductNotFoundInCart;
import com.emazon.transaction.domain.exeption.ReportRegistrationException;
import com.emazon.transaction.domain.exeption.ShoppingCartDeletionException;
import com.emazon.transaction.domain.model.Product;
import com.emazon.transaction.domain.model.Purchase;
import com.emazon.transaction.domain.model.ShoppingCart;
import com.emazon.transaction.domain.spi.*;
import com.emazon.transaction.domain.utils.TransactionStatus;

import java.util.List;

import static com.emazon.transaction.domain.utils.ExceptionResponseDomain.*;

public class PurchaseUseCase implements IPurchaseServicePort {
    private final IAuthenticationPersistencePort authenticationPersistencePort;
    private final IStockPersistencePort stockPersistencePort;
    private final IShoppingCartPersistencePort shoppingCartPersistencePort;
    private final IReportPersistencePort reportPersistencePort;

    public PurchaseUseCase(
            IAuthenticationPersistencePort authenticationPersistencePort,
            IStockPersistencePort stockPersistencePort,
            IShoppingCartPersistencePort shoppingCartPersistencePort,
            IReportPersistencePort reportPersistencePort
    ) {

        this.authenticationPersistencePort = authenticationPersistencePort;
        this.stockPersistencePort = stockPersistencePort;
        this.shoppingCartPersistencePort = shoppingCartPersistencePort;
        this.reportPersistencePort=reportPersistencePort;
    }

    @Override
    public Purchase createPurchase(List<Long> productIdList) {

        Purchase purchase=Purchase.from(this.authenticationPersistencePort.getEmail());

        List<ShoppingCart> shoppingCartList = getShoppingCartList(productIdList);
        List<Product> productListShoppingCart = getProductListFromCart(shoppingCartList);
        purchase.setPurchaseInformation(getStockInfoForPurchasedProducts(productListShoppingCart));
        removeProductsFromShoppingCart(productIdList, productListShoppingCart);
        saveReport(purchase, productListShoppingCart, shoppingCartList);
        return purchase;

    }

    private void saveReport(Purchase purchase, List<Product> productListShoppingCart, List<ShoppingCart> shoppingCartList) {
        if(!this.reportPersistencePort.save(purchase)){
            this.fallbackInReport(productListShoppingCart, shoppingCartList);
            throw new ReportRegistrationException(REPORT_REGISTRATION);
        }
        purchase.setStatus(TransactionStatus.SUCCESS);
    }

    private void removeProductsFromShoppingCart(List<Long> productIdList, List<Product> productListShoppingCart) {
        if (!this.shoppingCartPersistencePort.removeProductListFromShoppingCart(productIdList)) {
            this.fallbackInReduceShoppingCart(productListShoppingCart);
            throw new ShoppingCartDeletionException(SHOPPING_CART_DELETION);
        }
    }

    private List<Product> getStockInfoForPurchasedProducts(List<Product> productListShoppingCart) {
        List<Product> products=this.stockPersistencePort.reduceStockAfterPurchase(productListShoppingCart);
        if (products.isEmpty()) {
            throw new InsufficientStockException(INSUFFICIENT_STOCK);
        }
        return products;
    }

    private static List<Product> getProductListFromCart(List<ShoppingCart> shoppingCartList) {
        return shoppingCartList.stream()
                .map(shoppingCart -> Product.from(shoppingCart.getIdProduct(), shoppingCart.getAmount())
                ).toList();
    }

    private List<ShoppingCart> getShoppingCartList(List<Long> productIdList) {
        List<ShoppingCart> shoppingCartList=this.shoppingCartPersistencePort.getListShoppingCartInListIdProduct(productIdList);
        if (shoppingCartList.isEmpty()) {
            throw new ProductNotFoundInCart(PRODUCT_NOT_FOUND);
        }
        return shoppingCartList;
    }


    private void fallbackInReduceShoppingCart(List<Product> productListShoppingCart) {
       this.stockPersistencePort.restoreStockToPreviousStateFallback(productListShoppingCart);

    }
    private void fallbackInReport(List<Product> productListShoppingCart,List<ShoppingCart> shoppingCartList) {
        this.shoppingCartPersistencePort.restoreShoppingCartFromShoppingCartList(shoppingCartList);
        this.fallbackInReduceShoppingCart(productListShoppingCart);
    }

}

