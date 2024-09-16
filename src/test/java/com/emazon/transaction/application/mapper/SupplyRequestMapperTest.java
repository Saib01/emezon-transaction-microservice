package com.emazon.transaction.application.mapper;

import com.emazon.transaction.application.dtos.SupplyRequest;
import com.emazon.transaction.domain.model.Supply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.emazon.transaction.util.TestConstants.VALID_AMOUNT;
import static com.emazon.transaction.util.TestConstants.VALID_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mapstruct.factory.Mappers.getMapper;

class SupplyRequestMapperTest {

    private final SupplyRequestMapper supplyRequestMapper = getMapper(SupplyRequestMapper.class);

    @Test
    @DisplayName("Should map SupplyRequest to Supply correctly")
    void shouldMapSupplyRequestToSupplyCorrectly() {
        SupplyRequest supplyRequest = new SupplyRequest(VALID_ID,VALID_AMOUNT);

        Supply supply = supplyRequestMapper.toSupply(supplyRequest);

        assertEquals(supplyRequest.getAmount(), supply.getAmount());
        assertEquals(supplyRequest.getIdProduct(), supply.getIdProduct());
    }
}
