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
public class CustomerResultSetExtractor implements ResultSetExtractor<List<Customer>> {

    @Override
    public List<Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Customer> customerMap = new HashMap<>();


        while(rs.next()) {
            Integer customerId = rs.getInt("customer_id");
            Customer currentCustomer = new Customer();

            if (customerMap.get(customerId) == null) {

                CustomerMapper c = () -> {
                    currentCustomer.setId(rs.getInt("customer_id"));
                    currentCustomer.setFirstName(rs.getString("first_name"));
                    currentCustomer.setLastName(rs.getString("last_name"));
                    currentCustomer.setEmail(rs.getString("email"));
                    
                    return currentCustomer;
                };
                PaymentMapper p = () -> {
                    Payment payment = new Payment();

                    payment.setPaymentId(rs.getInt("payment_id"));
                    payment.setAmount(rs.getLong("amount"));
                    payment.setPayment_date(rs.getDate("payment_date").toLocalDate());

                    return payment;
                };

                if (p.mapRow().getPaymentId() != null) {
                    currentCustomer.getPaymentList().add(p.mapRow());
                }

                c.mapRow();

                customerMap.put(customerId, currentCustomer);
            }



        }



        return customerMap.values().stream().sorted(Comparator.comparing(Customer::getId)).toList();
    }
}
