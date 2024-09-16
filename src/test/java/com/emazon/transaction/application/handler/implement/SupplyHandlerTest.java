package com.emazon.transaction.application.handler.implement;

import com.emazon.transaction.application.dtos.SupplyRequest;
import com.emazon.transaction.application.mapper.SupplyRequestMapper;
import com.emazon.transaction.domain.api.ISupplyServicePort;
import com.emazon.transaction.domain.model.Supply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static com.emazon.transaction.util.TestConstants.VALID_AMOUNT;
import static com.emazon.transaction.util.TestConstants.VALID_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class SupplyHandlerTest {

    @Mock
    private ISupplyServicePort supplyServicePort;

    @Mock
    private SupplyRequestMapper supplyRequestMapper;

    @InjectMocks
    private SupplyHandler supplyHandler;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should map SupplyRequest to Supply and call the service to add the supply")
    void shouldCallServicePortAndMapperWhenAddingSupply() {
        SupplyRequest supplyRequest = new SupplyRequest(VALID_ID,VALID_AMOUNT);
        Supply expectedSupply = new Supply(VALID_ID,VALID_AMOUNT);

        when(supplyRequestMapper.toSupply(supplyRequest)).thenReturn(expectedSupply);

        supplyHandler.addSupply(supplyRequest);

        ArgumentCaptor<Supply> supplyCaptor = ArgumentCaptor.forClass(Supply.class);

        verify(supplyServicePort, times(1)).addSupply(supplyCaptor.capture());
        assertEquals(expectedSupply, supplyCaptor.getValue());
    }
}