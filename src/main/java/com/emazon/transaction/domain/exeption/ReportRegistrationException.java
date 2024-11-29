package com.emazon.transaction.domain.exeption;

import com.emazon.transaction.domain.utils.ExceptionResponseDomain;

public class ReportRegistrationException extends RuntimeException {
    public ReportRegistrationException(ExceptionResponseDomain message) {
        super(message.getMessage());
    }
}