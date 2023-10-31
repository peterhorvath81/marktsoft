package com.marktsoft.practice.payment.domain.mapper;

import com.marktsoft.practice.payment.domain.Payment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PaymentMapper {

    public static Payment mapRow(ResultSet rs) throws SQLException {
        return Payment.builder()
                .paymentId(rs.getInt("payment_id"))
                .amount(rs.getLong("amount"))
                .payment_date(rs.getDate("payment_date").toLocalDate())
                .build();
    }
}
