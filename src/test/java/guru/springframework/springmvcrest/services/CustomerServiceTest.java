package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.api.v1.mapper.CustomerMapper;
import guru.springframework.springmvcrest.api.v1.model.CustomerDTO;
import guru.springframework.springmvcrest.domain.Customer;
import guru.springframework.springmvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    public static final long ID = 1L;
    public static final String URL = "/shop/customers/";
    public static final String NAME = "Saul";
    public static final String LAST_NAME = "Goodman";
    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    void getAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(3, customerDTOS.size());
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName("Gabby");
        when(customerRepository.findCustomerById(anyLong())).thenReturn(customer);

        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(ID, customerDTO.getId());
    }

    @Test
    void createCustomerTest(){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(NAME);
        customerDTO.setLastName(LAST_NAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(NAME);
        savedCustomer.setLastName(LAST_NAME);
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO savedDTO = customerService.createCustomer(customerDTO);

        assertEquals(NAME, savedDTO.getFirstName());
        assertEquals(LAST_NAME, savedDTO.getLastName());
        assertEquals("/shop/customers/1", savedDTO.getURL());

    }

}