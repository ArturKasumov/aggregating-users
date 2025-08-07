package com.arturk.enums;

import lombok.Getter;

@Getter
public enum DatabaseStrategyEnum {

    POSTGRES("postgres"),
    MYSQL("mysql");

    private final String databaseStrategy;

    DatabaseStrategyEnum(String databaseStrategy) {
        this.databaseStrategy = databaseStrategy;
    }
}
