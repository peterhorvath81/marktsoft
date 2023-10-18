package com.marktsoft.practice.customer.mapper;

import com.marktsoft.practice.customer.repository.domain.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Customer.builder()
                .id(rs.getInt("customer_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .activebool(rs.getBoolean("activebool"))
                .createDate(rs.getDate("create_date").toLocalDate())
                .lastUpdate(rs.getDate("last_update").toLocalDate())
                .active(rs.getInt("active"))
                .build();
    }
}