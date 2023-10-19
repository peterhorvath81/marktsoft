package com.marktsoft.practice.customer.controller.dto.mapper;

import com.marktsoft.practice.customer.controller.dto.request.CustomerResultRow;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRequestDTOMapper implements RowMapper<CustomerResultRow> {

    @Override
    public CustomerResultRow mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CustomerResultRow.builder()
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .paymentId(rs.getInt("payment_id"))
                .amount(rs.getLong("amount"))
                .paymentDate(rs.getDate("payment_date").toLocalDate())
                .build();
    }
}
