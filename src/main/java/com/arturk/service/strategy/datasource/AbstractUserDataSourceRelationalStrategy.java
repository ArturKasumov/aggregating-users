package com.arturk.service.strategy.datasource;

import com.arturk.config.DataSourceConfig;
import com.arturk.dto.UserDto;
import com.arturk.enums.DatabaseStrategyEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractUserDataSourceRelationalStrategy implements UserDataSourceStrategy {

    @Override
    public List<UserDto> getUsers(DataSourceConfig config) {
        List<UserDto> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword())) {
            Map<String, String> mapping = config.getMapping();

            String idColumn = mapping.get("id");
            String usernameColumn = mapping.get("username");
            String nameColumn = mapping.get("name");
            String surnameColumn = mapping.get("surname");

            String sql = String.format("SELECT %s, %s, %s, %s FROM %s",
                    idColumn, usernameColumn, nameColumn, surnameColumn, config.getTable());

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Integer id = rs.getInt(idColumn);
                    String username = rs.getString(usernameColumn);
                    String name = rs.getString(nameColumn);
                    String surname = rs.getString(surnameColumn);

                    users.add(new UserDto(id, username, name, surname));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error during operating with " + getDatabaseType().getDatabaseStrategy() + " DB: " + config.getName(), e);
        }

        return users;
    }

    protected abstract DatabaseStrategyEnum getDatabaseType();
}