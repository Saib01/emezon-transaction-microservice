package com.emazon.transaction.domain.utils;

import java.io.Serializable;

public enum TransactionStatus implements Serializable {
        SUCCESS,
        FAILURE,
        PENDING;

        @Override
        public String toString() {
                return name();
        }
}