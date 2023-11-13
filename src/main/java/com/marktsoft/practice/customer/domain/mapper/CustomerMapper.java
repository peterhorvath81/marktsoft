package com.marktsoft.practice.customer.domain.mapper;

import com.marktsoft.practice.customer.domain.Customer;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;




@Component
public class CustomerMapper {

    public static Customer mapRow(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        return customer;
    }

}
