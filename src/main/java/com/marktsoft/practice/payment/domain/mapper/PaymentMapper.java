package com.marktsoft.practice.payment.domain.mapper;

import com.marktsoft.practice.payment.domain.Payment;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class PaymentMapper {


    public static Payment mapRow(ResultSet rs) throws SQLException {

        Integer paymentId = rs.getInt("payment_id");
        Payment payment = new Payment();

        if (paymentId == null) {
            payment.setPaymentId(null);
            payment.setAmount(0L);
            payment.setPayment_date(LocalDate.of(00, 00, 00));
        } else {
            payment.setPaymentId(paymentId);
            payment.setAmount(rs.getLong("amount"));
            payment.setPayment_date(rs.getDate("payment_date").toLocalDate());
        }
        return payment;
    }
}
