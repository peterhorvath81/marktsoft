package com.marktsoft.practice.customer.service;

import com.marktsoft.practice.customer.CustomerResultExtractor.CustomerResultSetExtractor;
import com.marktsoft.practice.customer.controller.dto.*;
import com.marktsoft.practice.customer.controller.dto.mapper.CustomerRecordNumberMapper;
import com.marktsoft.practice.payment.dto.PaymentDTO;
import com.marktsoft.practice.customer.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    public static final String SQL_CUSTOMER_SELECT = "select c.customer_id, first_name," +
            "last_name, " +
            "email, " +
            "payment_id, " +
            "amount, " +
            "payment_date " +
            "from customer c " +
            "left join payment p " +
            "ON c.customer_id=p.customer_id";

    public static final String SQL_CUSTOMER_PAGINATED = "select c.customer_id," +
            "first_name," +
            "last_name," +
            "email," +
            "payment_id, " +
            "amount, " +
            "payment_date " +
            "from (select * from customer ORDER BY customer_id LIMIT ? OFFSET ?) " +
            "AS c " +
            "left join payment p " +
            "ON c.customer_id=p.customer_id " +
            "ORDER BY c.customer_id";
    public static final String SQL_FIND_BY_ID = "select c.customer_id, first_name, last_name, email, payment_id, amount, " +
                                                "payment_date from customer c " +
                                                "join payment p ON c.customer_id=p.customer_id " +
                                                "where c.customer_id=? ";

    public static final String SQL_TOTAL_RECORDS = "select count(*) from customer";
    public static final String SQL_FIND_CUSTOMER_BY_ID = "Select * from customer where customer_id=?";
    public static final String SQL_FIND_PAYMENT_BY_ID = "Select * from payment where customer_id=?";

    private JdbcTemplate jdbcTemplate;

    private CustomerResultSetExtractor customerResultSetExtractor;

    private CustomerRecordNumberMapper customerRecordNumberMapper;


    @Override
    public List<CustomerDTO> getAll() {

        List<Customer> customerList = jdbcTemplate.query(SQL_CUSTOMER_SELECT, customerResultSetExtractor);

        return getCustomerDTOList(customerList);
    }

    @Override
    public PaginatedResponseDTO<CustomerDTO> getAllPaginated(int pageNumber, int pageCount) {

        List<Customer> customerList = jdbcTemplate
                .query(SQL_CUSTOMER_PAGINATED, customerResultSetExtractor, pageCount, (pageNumber-1)*pageCount);

        int totalRecord = jdbcTemplate.queryForObject(SQL_TOTAL_RECORDS, Integer.class);

        int totalPages = totalRecord%pageCount==0 ? totalRecord/pageCount : (totalRecord/pageCount)+1;

        PaginatedResponseDTO<CustomerDTO> responseDTO = new PaginatedResponseDTO<>();
        responseDTO.setContent(getCustomerDTOList(customerList));
        responseDTO.setTotalPages(totalPages);
        responseDTO.setActualPage(pageNumber);
        responseDTO.setTotalRecord(totalRecord);

        return responseDTO;
    }


    @Override
    public CustomerDTO findByIdWithSingleQuery(Integer id) {

        List<Customer> customerList = jdbcTemplate.query(SQL_FIND_BY_ID, customerResultSetExtractor, id);

        return getCustomerDTOList(customerList).get(0);
    }

//    @Override
//    public CustomerDTO findByIdWithDoubleQuery(Integer id) {
//
//        CustomerDTO customerDTO = new CustomerDTO();
//
//        Customer customer = jdbcTemplate.queryForObject(SQL_FIND_CUSTOMER_BY_ID, customerMapper, id);
//
//        List<Payment> paymentList = jdbcTemplate.query(SQL_FIND_PAYMENT_BY_ID, ps -> ps.setInt(1, id), paymentMapper);
//
//        customerDTO.setFirstName(customer.getFirstName());
//        customerDTO.setLastName(customer.getLastName());
//        customerDTO.setEmail(customer.getEmail());
//        customerDTO.setPayments(paymentList.stream().map(payment -> PaymentDTO.builder()
//                .paymentId(payment.getPaymentId())
//                .amount(payment.getAmount())
//                .payment_date(payment.getPayment_date())
//                .build()).toList());
//        return customerDTO;
//    }

//    @Override
//    public CustomerResponseDTO create(CustomerDTO customerDTO) {
//        Customer customer = getCustomer(customerDTO);
//        log.info("Saving customer");
////        customerRepository.save(customer);
//        return getCustomerResponseDTO(customer);
//    }

    @Override
    @Transactional
    public CustomerResponseDTO update(Integer id, CustomerDTO customerDTO) {
//        Customer customer = customerRepository.findById(id)
//                .orElseThrow(() -> new NotFoundRequestException("The customer with the id " +id+ " not found"));
//        customer.setFirstName(customerDTO.getFirstName());
//        customer.setLastName(customerDTO.getLastName());
//        customer.setEmail(customerDTO.getEmail());
        log.info("Updating customer");
        return null;
    }

    @Override
    public void delete(Integer id) {
//        Customer customer = customerRepository.findById(id)
//                .orElseThrow(() -> new NotFoundRequestException("The customer with the id " +id+ " not found"));
//        log.info("deleting customer");
//        customerRepository.delete(customer);
    }

//
//    @Override
//    public Owner findByName(String name) {
//        return customerRepository.findByName(name);
//    }

//    private static List<CustomerDTO> getCustomerDTOList(List<Customer> customerList) {
//        return customerList.stream().map(customer -> CustomerDTO.builder()
//                .firstName(customer.getFirstName())
//                .lastName(customer.getLastName())
//                .email(customer.getEmail())
//                .build()).toList();
//    }

    private static List<CustomerDTO> getCustomerDTOList(List<Customer> customerList) {
        return customerList.stream().map(customer ->
                CustomerDTO.builder()
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .email(customer.getEmail())
                        .payments(customer.getPaymentList().stream().map(payment -> {
                            PaymentDTO paymentDTO = new PaymentDTO();
                            paymentDTO.setPaymentId(payment.getPaymentId());
                            paymentDTO.setAmount(payment.getAmount());
                            paymentDTO.setPayment_date(payment.getPayment_date());
                            return paymentDTO;
                        }).toList())
                        .build()).toList();
    }

    private static CustomerResponseDTO getCustomerResponseDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
    }

//    private static Customer getCustomer(CustomerDTO customerDTO) {
//        return Customer.builder()
//                .firstName(customerDTO.getFirstName())
//                .lastName(customerDTO.getLastName())
//                .email(customerDTO.getEmail())
//                .activebool(true)
//                .createDate(LocalDate.now())
//                .lastUpdate(LocalDate.now())
//                .build();
//    }
}
