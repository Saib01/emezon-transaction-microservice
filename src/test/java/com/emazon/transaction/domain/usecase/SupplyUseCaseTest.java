package com.emazon.transaction.domain.usecase;

import com.emazon.transaction.domain.exeption.InvalidSupplyException;
import com.emazon.transaction.domain.model.Supply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.emazon.transaction.util.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class SupplyUseCaseTest {

    @Mock
    private ISupplyPersistencePort supplyPersistencePort;

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
    @DisplayName("Should add the supply when the amount is equal or greater than the minimum")
    void shouldAddSupplyWhenAmountIsValid() {
        supplyUseCase.addSupply(supply);
        ArgumentCaptor<Supply> supplyCaptor = ArgumentCaptor.forClass(Supply.class);
        verify(supplyPersistencePort, times(1)).addSupply(supplyCaptor.capture());
        assertEquals(supplyCaptor.getValue(), supply);
    }
}
