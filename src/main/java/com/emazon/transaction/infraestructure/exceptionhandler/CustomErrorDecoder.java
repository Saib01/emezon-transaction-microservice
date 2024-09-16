package com.emazon.transaction.infraestructure.exceptionhandler;

import com.emazon.transaction.infraestructure.exception.FeignProductNotFoundException;
import com.emazon.transaction.infraestructure.exception.UnknownException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import static com.emazon.transaction.infraestructure.exceptionhandler.ExceptionResponse.STOCK_FEIGN_PRODUCT_NOT_FOUND;
import static com.emazon.transaction.infraestructure.exceptionhandler.ExceptionResponse.UNKNOWN_ERROR;
import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.ADD_SUPPLY;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (methodKey.toLowerCase().contains(ADD_SUPPLY.toLowerCase())) {
            throw new FeignProductNotFoundException(STOCK_FEIGN_PRODUCT_NOT_FOUND);
        }
        return new UnknownException(UNKNOWN_ERROR);
    }
}
