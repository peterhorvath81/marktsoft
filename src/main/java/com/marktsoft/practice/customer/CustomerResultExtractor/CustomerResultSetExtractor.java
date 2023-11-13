package com.marktsoft.practice.customer.CustomerResultExtractor;

import com.marktsoft.practice.customer.domain.Customer;
import com.marktsoft.practice.customer.domain.mapper.CustomerMapper;
import com.marktsoft.practice.payment.domain.Payment;
import com.marktsoft.practice.payment.domain.mapper.PaymentMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Component
public class CustomerResultSetExtractor {

    public static List<Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Customer> customerMap = new HashMap<>();


        while(rs.next()) {
            Integer customerId = rs.getInt("customer_id");
            Customer currentCustomer = new Customer();

            if (customerMap.get(customerId) == null) {

                currentCustomer = CustomerMapper.mapRow(rs);
                if (PaymentMapper.mapRow(rs).getPaymentId() != null) {
                    currentCustomer.getPaymentList().add(PaymentMapper.mapRow(rs));
                }

                customerMap.put(customerId, currentCustomer);
            }
        }
        return customerMap.values().stream().sorted(Comparator.comparing(Customer::getId)).toList();
    }
}
