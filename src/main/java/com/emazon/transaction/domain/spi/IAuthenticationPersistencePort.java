package com.emazon.transaction.domain.spi;


public interface IAuthenticationPersistencePort {
    Long getUserId();

    String getEmail();
}
