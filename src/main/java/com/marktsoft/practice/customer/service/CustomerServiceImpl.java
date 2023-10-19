package com.marktsoft.practice.customer.service;

import com.marktsoft.practice.customer.controller.dto.CustomerDTO;
import com.marktsoft.practice.customer.controller.dto.CustomerResponseDTO;
import com.marktsoft.practice.customer.controller.dto.PaymentDTO;
import com.marktsoft.practice.customer.controller.dto.mapper.CustomerRequestDTOMapper;
import com.marktsoft.practice.customer.controller.dto.request.CustomerResultRow;
import com.marktsoft.practice.customer.mapper.CustomerMapper;
import com.marktsoft.practice.customer.repository.CustomerRepository;
import com.marktsoft.practice.customer.repository.domain.Customer;
import com.marktsoft.practice.exception.NotFoundRequestException;
import com.marktsoft.practice.payment.mapper.PaymentMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Getter
    private ApplicationContext applicationContext;

    private JdbcTemplate jdbcTemplate;

    private CustomerMapper customerMapper;

    private PaymentMapper paymentMapper;

    private CustomerRequestDTOMapper customerRequestDTOMapper;


    @Override
    public List<CustomerDTO> getAll() {

        String SQL_SELECT = "Select * from customer";
        List<Customer> customers = jdbcTemplate.query(SQL_SELECT, (resultset, i) -> new Customer(
                resultset.getInt("customer_id"),
                resultset.getString("first_name"),
                resultset.getString("last_name"),
                resultset.getString("email"),
                resultset.getBoolean("activebool"),
                resultset.getDate("create_date").toLocalDate(),
                resultset.getDate("last_update").toLocalDate(),
                resultset.getInt("active")
        ));

        return customers.stream().map(customer -> CustomerDTO
                .builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build()).toList();
    }

    public List<CustomerDTO> getAllPaginated(String sortBy, Integer pageNumber, Integer pageCount) {
        Pageable pageable = PageRequest.of(pageNumber, pageCount, Sort.by(Sort.Direction.ASC, sortBy));
        List<Customer> customerList = customerRepository.findAll(pageable).get().toList();
        log.info("Fetching customers by pagination");
        return getCustomerDTOList(customerList);
    }

    @Override
    public CustomerDTO findById(Integer id) { //ResultSetExtractorral - 2 rowmapper

        CustomerDTO customerDTO = new CustomerDTO();

        String SQL_JOIN = "select first_name, last_name, email, payment_id, amount, payment_date from customer c " +
                "join payment p ON c.customer_id=p.customer_id " +
                "where c.customer_id=? " +
                "order by c.customer_id ";

        List<CustomerResultRow> customerResultRowList = jdbcTemplate.query(SQL_JOIN, ps -> ps.setInt(1,id), customerRequestDTOMapper);

        customerDTO.setFirstName(customerResultRowList.get(0).getFirstName());
        customerDTO.setLastName(customerResultRowList.get(0).getLastName());
        customerDTO.setEmail(customerResultRowList.get(0).getEmail());

        List<PaymentDTO> paymentDTOList = customerResultRowList.stream().map(
                customerResultRow -> {
                    PaymentDTO paymentDTO = new PaymentDTO();
                    paymentDTO.setPaymentId(customerResultRow.getPaymentId());
                    paymentDTO.setAmount(customerResultRow.getAmount());
                    paymentDTO.setPayment_date(customerResultRow.getPaymentDate());
                    return paymentDTO;
                }
        ).toList();

        customerDTO.setPayments(paymentDTOList);



//        String SQL_SELECT = "Select * from customer where customer_id=?";
//        List<Customer> customerList = jdbcTemplate.query(SQL_SELECT, ps -> ps.setInt(1, id), customerMapper);
//
//        String SQL_PAYMENT = "Select * from payment where customer_id=?";
//        List<Payment> paymentList = jdbcTemplate.query(SQL_PAYMENT, ps -> ps.setInt(1, id), paymentMapper);
//
//        customerDTO.setFirstName(customerList.get(0).getFirstName());
//        customerDTO.setLastName(customerList.get(0).getLastName());
//        customerDTO.setEmail(customerList.get(0).getEmail());
//        customerDTO.setPayments(paymentList.stream().map(payment -> PaymentDTO.builder()
//                .paymentId(payment.getPaymentId())
//                .amount(payment.getAmount())
//                .payment_date(payment.getPayment_date())
//                .build()).toList());

        return customerDTO;
    }

    @Override
    public CustomerResponseDTO create(CustomerDTO customerDTO) {
        Customer customer = getCustomer(customerDTO);
        log.info("Saving customer");
        customerRepository.save(customer);
        return getCustomerResponseDTO(customer);
    }

    @Override
    @Transactional
    public CustomerResponseDTO update(Integer id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("The customer with the id " +id+ " not found"));
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        log.info("Updating customer");
        return getCustomerResponseDTO(customer);
    }

    @Override
    public void delete(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("The customer with the id " +id+ " not found"));
        log.info("deleting customer");
        customerRepository.delete(customer);
    }

//    @Override
//    public void updateWithPet(Owner owner, Pet pet) {
//        owner.getPetList().add(pet);
//        customerRepository.save(owner);
//    }
//
//    @Override
//    public Owner findByName(String name) {
//        return customerRepository.findByName(name);
//    }

    private static List<CustomerDTO> getCustomerDTOList(List<Customer> customerList) {
        return customerList.stream().map(customer -> CustomerDTO.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
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

    private static Customer getCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .email(customerDTO.getEmail())
                .activebool(true)
                .createDate(LocalDate.now())
                .lastUpdate(LocalDate.now())
                .build();
    }
}
