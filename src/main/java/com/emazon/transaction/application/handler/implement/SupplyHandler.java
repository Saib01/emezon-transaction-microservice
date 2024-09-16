package com.emazon.transaction.application.handler.implement;

import com.emazon.transaction.application.dtos.SupplyRequest;
import com.emazon.transaction.application.handler.ISupplyHandler;
import com.emazon.transaction.application.mapper.SupplyRequestMapper;
import com.emazon.transaction.domain.api.ISupplyServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class SupplyHandler implements ISupplyHandler {
    private final ISupplyServicePort supplyServicePort;
    private final SupplyRequestMapper supplyRequestMapper;

    @Override
    public void addSupply(SupplyRequest supplyRequest) {
        this.supplyServicePort.addSupply(
                this.supplyRequestMapper.toSupply(supplyRequest)
        );
    }
}
