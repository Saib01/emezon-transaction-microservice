package com.emazon.transaction.domain.usecase;

import com.emazon.transaction.domain.exeption.InvalidIdProductException;
import com.emazon.transaction.domain.exeption.InvalidSupplyException;
import com.emazon.transaction.domain.exeption.ProductNotFoundException;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.spi.IStockPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.emazon.transaction.util.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class SupplyUseCaseTest {

    @Mock
    private IStockPersistencePort stockPersistencePort;

    @InjectMocks
    private SupplyUseCase supplyUseCase;
    Supply supply;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        supply = new Supply(VALID_ID, VALID_AMOUNT);
    }

    @Test
    @DisplayName("Should not add the supply when the amount is less than the minimum")
    void shouldNotAddSupplyWhenAmountIsLessThanMinimum() {
        supply.setAmount(INVALID_AMOUNT);
        assertThrows(InvalidSupplyException.class, () -> supplyUseCase.addSupply(supply));
    }

    @Test
    @DisplayName("Should not add the supply when the id product is less than the minimum")
    void shouldNotAddSupplyWhenIdProductIsLessThanMinimum() {
        supply.setIdProduct(INVALID_ID);
        assertThrows( InvalidIdProductException.class, () -> supplyUseCase.addSupply(supply));
    }

    @Test
    @DisplayName("Should not add the supply when the id product does not exist")
    void shouldNotAddSupplyWhenIdProductDoestNotExist() {
        when(this.stockPersistencePort.addSupply(supply)).thenReturn(false);
        supply.setIdProduct(VALID_ID);
        assertThrows( ProductNotFoundException.class, () -> supplyUseCase.addSupply(supply));
    }

    @Test
    @DisplayName("Should add the supply when the amount is equal or greater than the minimum")
    void shouldAddSupplyWhenAmountIsValid() {
        when(this.stockPersistencePort.addSupply(supply)).thenReturn(true);
        supplyUseCase.addSupply(supply);
        ArgumentCaptor<Supply> supplyCaptor = ArgumentCaptor.forClass(Supply.class);
        verify(stockPersistencePort, times(1)).addSupply(supplyCaptor.capture());
        assertEquals(supplyCaptor.getValue(), supply);
    }
}
