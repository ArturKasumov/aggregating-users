package com.arturk.service.strategy.datasource;

import com.arturk.enums.DatabaseStrategyEnum;
import org.springframework.stereotype.Component;

@Component
public class PostgresUserDataSourceStrategy extends AbstractUserDataSourceRelationalStrategy {


    @Override
    protected DatabaseStrategyEnum getDatabaseType() {
        return DatabaseStrategyEnum.POSTGRES;
    }

    @Override
    public boolean supports(String strategy) {
        return DatabaseStrategyEnum.POSTGRES.getDatabaseStrategy().equalsIgnoreCase(strategy);
    }
}
