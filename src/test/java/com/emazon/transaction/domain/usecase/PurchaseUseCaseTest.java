package com.emazon.transaction.domain.usecase;

import com.emazon.transaction.domain.exeption.InsufficientStockException;
import com.emazon.transaction.domain.exeption.ProductNotFoundInCart;
import com.emazon.transaction.domain.exeption.ReportRegistrationException;
import com.emazon.transaction.domain.exeption.ShoppingCartDeletionException;
import com.emazon.transaction.domain.model.Product;
import com.emazon.transaction.domain.model.Purchase;
import com.emazon.transaction.domain.model.ShoppingCart;
import com.emazon.transaction.domain.spi.IAuthenticationPersistencePort;
import com.emazon.transaction.domain.spi.IReportPersistencePort;
import com.emazon.transaction.domain.spi.IShoppingCartPersistencePort;
import com.emazon.transaction.domain.spi.IStockPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static com.emazon.transaction.util.TestConstants.*;
import static com.emazon.transaction.util.TestConstants.VALID_PRICE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.openMocks;

class PurchaseUseCaseTest {
    @Mock
    private IStockPersistencePort stockPersistencePort;
    @Mock
    private IAuthenticationPersistencePort authenticationPersistencePort;
    @Mock
    private IShoppingCartPersistencePort shoppingCartPersistencePort;
    @Mock
    private IReportPersistencePort reportPersistencePort;
    @InjectMocks
    private PurchaseUseCase purchaseUseCase;
    List<ShoppingCart> shoppingCartList=List.of(new ShoppingCart(VALID_ID,VALID_ID,VALID_ID,VALID_AMOUNT));
    List<Long> productIdList=List.of(VALID_ID);
    List<Product> productList=List.of(new Product(VALID_ID,VALID_NAME,VALID_AMOUNT,VALID_PRICE));

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should create a purchase")
    void shouldCreatePurchase(){
        when(this.authenticationPersistencePort.getEmail()).thenReturn(VALID_EMAIL);
        when(this.shoppingCartPersistencePort.getListShoppingCartInListIdProduct(productIdList)).thenReturn(shoppingCartList);
        when(this.stockPersistencePort.reduceStockAfterPurchase(any())).thenReturn(productList);
        when(this.shoppingCartPersistencePort.removeProductListFromShoppingCart(productIdList)).thenReturn(true);
        when(this.reportPersistencePort.save(any())).thenReturn(true);

        Purchase purchase=this.purchaseUseCase.createPurchase(productIdList);
        assertNotNull(purchase);
    }
    @Test
    @DisplayName("Should not create a purchase when shopping cart is empty")
    void shouldNotCreatePurchaseWhenShoppingCartIsEmpty(){
        when(this.authenticationPersistencePort.getEmail()).thenReturn(VALID_EMAIL);
        when(this.shoppingCartPersistencePort.getListShoppingCartInListIdProduct(productIdList)).thenReturn(List.of());

        assertThrows( ProductNotFoundInCart.class, () ->this.purchaseUseCase.createPurchase(productIdList));
    }

    @Test
    @DisplayName("Should not create a purchase when the stock is insufficient")
    void shouldNotCreatePurchaseWhenTheStockIsInsufficient(){
        when(this.authenticationPersistencePort.getEmail()).thenReturn(VALID_EMAIL);
        when(this.shoppingCartPersistencePort.getListShoppingCartInListIdProduct(productIdList)).thenReturn(shoppingCartList);
        when(this.stockPersistencePort.reduceStockAfterPurchase(any())).thenReturn(List.of());

        assertThrows( InsufficientStockException.class, () ->this.purchaseUseCase.createPurchase(productIdList));
    }
    @Test
    @DisplayName("Should not create a purchase When removing from the cart fails")
    void shouldNotCreatePurchaseWhenRemovingFromCartFails(){
        when(this.authenticationPersistencePort.getEmail()).thenReturn(VALID_EMAIL);
        when(this.shoppingCartPersistencePort.getListShoppingCartInListIdProduct(productIdList)).thenReturn(shoppingCartList);
        when(this.stockPersistencePort.reduceStockAfterPurchase(any())).thenReturn(productList);
        when(this.shoppingCartPersistencePort.removeProductListFromShoppingCart(productIdList)).thenReturn(false);
        when(this.stockPersistencePort.restoreStockToPreviousStateFallback(any())).thenReturn(true);

        assertThrows( ShoppingCartDeletionException.class, () ->this.purchaseUseCase.createPurchase(productIdList));
    }

    @Test
    @DisplayName("Should not create a purchase When saving report fails")
    void shouldNotCreatePurchaseWhenSavingReportFails(){
        when(this.authenticationPersistencePort.getEmail()).thenReturn(VALID_EMAIL);
        when(this.shoppingCartPersistencePort.getListShoppingCartInListIdProduct(productIdList)).thenReturn(shoppingCartList);
        when(this.stockPersistencePort.reduceStockAfterPurchase(any())).thenReturn(productList);
        when(this.shoppingCartPersistencePort.removeProductListFromShoppingCart(productIdList)).thenReturn(true);
        when(this.reportPersistencePort.save(any())).thenReturn(false);
        when(this.shoppingCartPersistencePort.restoreShoppingCartFromShoppingCartList(any())).thenReturn(true);
        when(this.stockPersistencePort.restoreStockToPreviousStateFallback(any())).thenReturn(true);

        assertThrows( ReportRegistrationException.class, () ->this.purchaseUseCase.createPurchase(productIdList));
        verify(this.shoppingCartPersistencePort, times(1)).restoreShoppingCartFromShoppingCartList(any());
    }
}