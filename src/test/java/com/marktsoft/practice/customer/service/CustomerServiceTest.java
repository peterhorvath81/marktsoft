package com.marktsoft.practice.customer.service;

public class CustomerServiceTest {

//    public static final Integer ID = 1;
//    public static final String FIRST_NAME = "John";
//    public static final String LAST_NAME = "Doe";
//    public static final String EMAIL = "johndoe@gmail.com";
//    public static final String PHONE_NUMBER = "1234";
//    public static final String SORT_BY = "NAME";
//    public static final String SORT_DIRECTION = "DESC";
//    public static final Integer PAGE_NUMBER = 0;
//    public static final Integer PAGE_COUNT = 1;
//
//
//    @Mock
//    private CustomerRepository customerRepository;
//
//    @InjectMocks
//    private CustomerServiceImpl customerService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
////    @Test
//    public void shouldGetAllCustomers() {
////        Customer customer = createCustomer();
//
////        when(customerRepository.findAll(sort)).thenReturn(List.of(customer));
//
////        CustomerDTO customerDTO = createCustomerDTO(customer);
////
////        List<CustomerDTO> result = customerService.getAll();
//
////        verify(customerService, times(1)).getAll();
//
////        assertEquals(result, List.of(customerDTO));
//    }
//
//    @Test
//    public void shouldGetAllCustomersPaginated() {
//        Customer customer = createCustomer();
//
//        Page<Customer> customerPage = mock(Page.class);
//
//        when(customerRepository.findAll(isA(Pageable.class))).thenReturn(customerPage);
//
//        CustomerDTO customerDTO = createCustomerDTO(customer);
//
//        List<CustomerDTO> result = customerService.getAllPaginated(SORT_BY, PAGE_NUMBER, PAGE_COUNT);
//
//        assertEquals(result, Collections.emptyList());
//    }
//
////    @Test
////    public void shouldFindById() {
////        Customer customer = createCustomer();
////        when(customerRepository.findById(ID)).thenReturn(Optional.ofNullable(customer));
////
////        Customer result = customerService.findById(ID);
////
////        assertEquals(result, customer);
////    }
//
//    @Test
//    public void shouldCreateCustomer() {
//        CustomerDTO customerDTO = createCustomerDTO();
//        Customer customer = createCustomer(customerDTO);
//        CustomerResponseDTO customerResponseDTO = createCustomerResponseDTO();
//        when(customerRepository.save(customer)).thenReturn(customer);
//
//        CustomerResponseDTO result = customerService.create(customerDTO);
//
//        assertEquals(result, customerResponseDTO);
//    }
//
//    @Test
//    public void shouldUpdateOwner() {
//        CustomerDTO customerDTO = createCustomerDTO();
//        Customer customer = createCustomer(customerDTO);
//        CustomerResponseDTO customerResponseDTO = createCustomerResponseDTOforUpdate();
//        when(customerRepository.findById(ID)).thenReturn(Optional.of(customer));
//        when(customerRepository.save(customer)).thenReturn(customer);
//
//        CustomerResponseDTO result = customerService.update(ID, customerDTO);
//
//        assertEquals(result, customerResponseDTO);
//    }
//
////    @Test
////    public void shouldUpdateOwnerWithPet() {
////        Owner owner = createOwner();
////        Pet pet = createPet();
////        List<Pet> petList = new ArrayList<>();
////        petList.add(pet);
////        owner.setPetList(petList);
////
////        when(customerRepository.save(owner)).thenReturn(owner);
////
////        customerService.updateWithPet(owner,pet);
////
////        verify(customerRepository, times(1)).save(owner);
////    }
////
////    @Test
////    public void shouldFindOwnerByName() {
////        Owner owner = createOwner();
////        when(customerRepository.findByName(NAME)).thenReturn(owner);
////
////        Owner result = customerService.findByName(NAME);
////
////        assertEquals(result, owner);
////    }
//
//    @Test
//    public void shouldDeleteCustomer() {
//        Customer customer = createCustomer();
//        when(customerRepository.findById(ID)).thenReturn(Optional.of(customer));
//        doNothing().when(customerRepository).delete(customer);
//
//        customerService.delete(ID);
//
//        verify(customerRepository,times(1)).findById(ID);
//        verify(customerRepository, times(1)).delete(customer);
//    }
//
//    private Customer createCustomer(CustomerDTO customerDTO) {
//        return Customer.builder()
//                .firstName(customerDTO.getFirstName())
//                .lastName(customerDTO.getLastName())
//                .id(ID)
//                .email(customerDTO.getEmail())
//                .lastUpdate(Instant.now())
//                .active(1)
//                .createDate(LocalDate.now())
//                .activebool(true)
//                .build();
//    }
//
//    private CustomerDTO createCustomerDTO() {
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setFirstName(FIRST_NAME);
//        customerDTO.setLastName(LAST_NAME);
//        customerDTO.setEmail(EMAIL);
//        return customerDTO;
//    }
//
//    private CustomerDTO createCustomerDTO(Customer customer) {
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setFirstName(customer.getFirstName());
//        customerDTO.setLastName(customer.getLastName());
//        customerDTO.setEmail(customer.getEmail());
//        return customerDTO;
//    }
//
//    private Customer createCustomer() {
//        return Customer
//                .builder()
//                .firstName(FIRST_NAME)
//                .lastName(LAST_NAME)
//                .id(ID)
//                .email(EMAIL)
//                .activebool(true)
//                .createDate(LocalDate.now())
//                .active(1)
//                .lastUpdate(Instant.now())
//                .build();
//    }
//    private CustomerResponseDTO createCustomerResponseDTO() {
//        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
////        customerResponseDTO.setId(ID);
//        customerResponseDTO.setFirstName(FIRST_NAME);
//        customerResponseDTO.setLastName(LAST_NAME);
//        customerResponseDTO.setEmail(EMAIL);
//        return customerResponseDTO;
//    }
//
//    private CustomerResponseDTO createCustomerResponseDTOforUpdate() {
//        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
//        customerResponseDTO.setId(ID);
//        customerResponseDTO.setFirstName(FIRST_NAME);
//        customerResponseDTO.setLastName(LAST_NAME);
//        customerResponseDTO.setEmail(EMAIL);
//        return customerResponseDTO;
//    }
}
