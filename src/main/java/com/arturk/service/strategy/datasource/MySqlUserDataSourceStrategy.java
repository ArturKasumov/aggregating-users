package com.arturk.service.strategy.datasource;

import com.arturk.enums.DatabaseStrategyEnum;
import org.springframework.stereotype.Component;

@Component
public class MySqlUserDataSourceStrategy extends AbstractUserDataSourceRelationalStrategy {

    @Override
    protected DatabaseStrategyEnum getDatabaseType() {
        return DatabaseStrategyEnum.MYSQL;
    }

    @Override
    public boolean supports(String strategy) {
        return DatabaseStrategyEnum.MYSQL.getDatabaseStrategy().equalsIgnoreCase(strategy);
    }
}
