package com.emazon.transaction.infraestructure.output.jpa.feign;


import com.emazon.transaction.domain.model.Purchase;
import com.emazon.transaction.infraestructure.configuration.FeingClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.FEIGN_REPORT_NAME;
import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.FEIGN_REPORT_URL;


@FeignClient(name = FEIGN_REPORT_NAME, url = FEIGN_REPORT_URL, configuration = FeingClientConfig.class)
public interface ReportFeignClient {

    @PostMapping("/api/report")
    void saveReport(@RequestBody Purchase purchase) ;
}
