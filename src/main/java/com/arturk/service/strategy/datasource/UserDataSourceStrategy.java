package com.arturk.service.strategy.datasource;

import com.arturk.config.DataSourceConfig;
import com.arturk.dto.UserDto;

import java.util.List;

public interface UserDataSourceStrategy {

    List<UserDto> getUsers(DataSourceConfig config);

    boolean supports(String strategy);
}
