package com.emazon.transaction.application.mapper;

import com.emazon.transaction.application.dtos.SupplyRequest;
import com.emazon.transaction.domain.model.Supply;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface SupplyRequestMapper {
    Supply toSupply(SupplyRequest supplyRequest);
}
