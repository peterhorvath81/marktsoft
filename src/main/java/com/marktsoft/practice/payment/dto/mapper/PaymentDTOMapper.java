package com.marktsoft.practice.payment.dto.mapper;

import com.marktsoft.practice.payment.dto.PaymentDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PaymentDTOMapper implements RowMapper<PaymentDTO> {
    @Override
    public PaymentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PaymentDTO.builder()
                .paymentId(rs.getInt("payment_id"))
                .amount(rs.getLong("amount"))
                .payment_date(rs.getDate("payment_date").toLocalDate())
                .build();
    }
}
