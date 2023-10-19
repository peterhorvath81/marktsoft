package com.marktsoft.practice.payment.mapper;

import com.marktsoft.practice.payment.domain.Payment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PaymentMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Payment.builder()
                .paymentId(rs.getInt("payment_id"))
                .customerId(rs.getInt("customer_id"))
                .staffId(rs.getInt("staff_id"))
                .rentalId(rs.getInt("rental_id"))
                .amount(rs.getLong("amount"))
                .payment_date(rs.getDate("payment_date").toLocalDate())
                .build();
    }
}
