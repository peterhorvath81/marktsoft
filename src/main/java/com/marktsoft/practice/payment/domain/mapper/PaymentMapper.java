package com.marktsoft.practice.payment.domain.mapper;

import com.marktsoft.practice.payment.domain.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface PaymentMapper {

    public Payment mapRow() throws SQLException;
}
