package com.emazon.transaction.infraestructure.output.jpa.adapters;

import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.infraestructure.output.jpa.feign.StockFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.emazon.transaction.util.TestConstants.VALID_AMOUNT;
import static com.emazon.transaction.util.TestConstants.VALID_ID;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class SupplyJpaAdapterTest {

    @Mock
    private StockFeignClient stockFeignClient;

    @InjectMocks
    private SupplyJpaAdapter supplyJpaAdapter;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should call StockFeignClient addSupply with correct parameters")
    void shouldCallStockFeignClientAddSupply() {
        Supply supply = new Supply(VALID_ID,VALID_AMOUNT);

        supplyJpaAdapter.addSupply(supply);

        verify(stockFeignClient, times(1)).addSupply(supply.getIdProduct(), supply.getAmount());
    }
}
