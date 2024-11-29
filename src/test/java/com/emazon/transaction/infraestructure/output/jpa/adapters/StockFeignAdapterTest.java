package com.emazon.transaction.infraestructure.output.jpa.adapters;

import com.emazon.transaction.domain.model.Product;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.infraestructure.output.jpa.feign.StockFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static com.emazon.transaction.util.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class StockFeignAdapterTest {

    @Mock
    private StockFeignClient stockFeignClient;

    @InjectMocks
    private StockFeignAdapter stockFeignAdapter;

    List<Product> productList=List.of(new Product(VALID_ID,VALID_NAME,VALID_AMOUNT,VALID_PRICE));

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should call StockFeignClient reduceStockAfterPurchase with correct parameters")
    void reduceStockAfterPurchase() {
        when(this.stockFeignClient.reduceStockAfterPurchase(productList))
                .thenReturn(productList);
        List<Product> result=stockFeignAdapter.reduceStockAfterPurchase(productList);
        assertEquals(productList,result);
    }

    @Test
    @DisplayName("Should call StockFeignClient addSupply with correct parameters")
    void shouldCallStockFeignClientAddSupply() {
        doNothing().when(stockFeignClient).addSupply(VALID_ID, VALID_AMOUNT);
        Supply supply = new Supply(VALID_ID,VALID_AMOUNT);

        stockFeignAdapter.addSupply(supply);

        verify(stockFeignClient, times(1)).addSupply(supply.getIdProduct(), supply.getAmount());
    }

    @Test
    void restoreStockToPreviousStateFallback() {
        doNothing().when(stockFeignClient).restoreStockToPreviousStateFallback(productList);
        stockFeignAdapter.restoreStockToPreviousStateFallback(productList);
        verify(stockFeignClient, times(1)).restoreStockToPreviousStateFallback(productList);
    }
}