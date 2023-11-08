package com.marktsoft.practice.customer.domain.mapper;

import com.marktsoft.practice.customer.domain.Customer;

import java.sql.SQLException;



@FunctionalInterface
public interface CustomerMapper {

    Customer mapRow() throws SQLException;

}
