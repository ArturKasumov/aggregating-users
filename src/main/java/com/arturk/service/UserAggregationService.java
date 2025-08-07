package com.arturk.service;

import com.arturk.config.AppConfig;
import com.arturk.config.DataSourceConfig;
import com.arturk.dto.UserDto;
import com.arturk.service.strategy.datasource.UserDataSourceStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAggregationService {

    private final List<DataSourceConfig> dataSourceConfigs;
    private final List<UserDataSourceStrategy> strategies;

    public UserAggregationService(AppConfig appConfig, List<UserDataSourceStrategy> strategies) {
        this.dataSourceConfigs = appConfig.getDataSources();
        this.strategies = strategies;
    }

    public List<UserDto> getAggregatedUsers() {
        List<UserDto> aggregatedUsers = new ArrayList<>();

        for (DataSourceConfig config : dataSourceConfigs) {
            UserDataSourceStrategy strategy = strategies.stream()
                    .filter(s -> s.supports(config.getStrategy()))
                    .findFirst()
                    .orElseThrow(() -> new UnsupportedOperationException("Unsupported strategy for " + config.getStrategy()));

            List<UserDto> users = strategy.getUsers(config);
            aggregatedUsers.addAll(users);
        }

        return aggregatedUsers;
    }

}
