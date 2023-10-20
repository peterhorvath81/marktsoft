package com.marktsoft.practice.customer.CustomerResultExtractor;

import com.marktsoft.practice.customer.domain.Customer;
import com.marktsoft.practice.payment.domain.Payment;
import com.marktsoft.practice.customer.domain.mapper.CustomerMapper;
import com.marktsoft.practice.payment.mapper.PaymentMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@AllArgsConstructor
public class CustomerResultSetExtractor implements ResultSetExtractor<List<Customer>> {

    private CustomerMapper customerMapper;

    private PaymentMapper paymentMapper;
    @Override
    public List<Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Customer> customerMap = new HashMap<>();

        int counter = 1;
        Customer customer;

        while(rs.next()) {
            Integer customerId = rs.getInt("customer_id");
            customer = customerMap.get(customerId);
            if (customer == null) {
                customer = customerMapper.mapRow(rs,counter);
                customerMap.put(customerId, customer);
            }
            List<Payment> paymentList = customer.getPayment();
            if(paymentList == null) {
                paymentList = new ArrayList<>();
                customer.setPayment(paymentList);
            }
                Payment payment = paymentMapper.mapRow(rs, counter);
                paymentList.add(payment);

            counter++;
        }
        return customerMap.values().stream().toList();
    }
}
