package com.emazon.transaction.infraestructure.output.jpa.adapters;


import com.emazon.transaction.domain.model.Purchase;
import com.emazon.transaction.domain.spi.IReportPersistencePort;
import com.emazon.transaction.infraestructure.exceptionhandler.ConnectionRefused;
import com.emazon.transaction.infraestructure.output.jpa.feign.ReportFeignClient;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
public class ReportFeignAdapter implements IReportPersistencePort {
    private final ReportFeignClient reportFeignClient;

    @Override
    public boolean save(Purchase purchase) {
        try {
            this.reportFeignClient.saveReport(purchase);
            return true;
        } catch (RuntimeException e) {
            ConnectionRefused.throwIfConnectionRefused(e.getMessage());
            return false;
        }
    }
}
