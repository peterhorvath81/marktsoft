package com.marktsoft.practice.customer.CustomerResultExtractor;

import com.marktsoft.practice.customer.domain.Customer;
import com.marktsoft.practice.payment.domain.Payment;
import com.marktsoft.practice.customer.domain.mapper.CustomerMapper;
import com.marktsoft.practice.payment.domain.mapper.PaymentMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Component
@AllArgsConstructor
public class CustomerResultSetExtractor implements ResultSetExtractor<List<Customer>> {

    //paymentmapper maprow-ban: usernek nincs paymentje, line 36-ban is

    @Override
    public List<Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Customer> customerMap = new HashMap<>();

        Customer currentCustomer;

        while(rs.next()) {
            Integer customerId = rs.getInt("customer_id");
            currentCustomer = customerMap.get(customerId);
            if (currentCustomer == null) {
                currentCustomer = CustomerMapper.mapRow(rs);
                customerMap.put(customerId, currentCustomer);
            }
            if (PaymentMapper.mapRow(rs).getPaymentId() != null) {
                currentCustomer.getPaymentList().add(PaymentMapper.mapRow(rs));
            }
        }
        return customerMap.values().stream().sorted(Comparator.comparing(Customer::getId)).toList();
    }
}
