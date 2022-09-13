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

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    public static final Long ID = 1L;
    public static final String URL = "/shop/customers/1";
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
        customer.setFirstName(NAME);

        when(customerRepository.findById(anyLong())).thenReturn(ofNullable(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        assertEquals(NAME, customerDTO.getFirstName());
        assertEquals(ID, customerDTO.getId());
    }

    @Test
    void createCustomerTest() {
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
        assertEquals(URL, savedDTO.getURL());
    }

    @Test
    void updateCustomerTest() {
    //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(NAME);
        customerDTO.setLastName(LAST_NAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(ID);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO savedDTO = customerService.updateCustomer(ID, customerDTO);

        assertEquals(NAME, savedDTO.getFirstName());
        assertEquals(LAST_NAME, savedDTO.getLastName());
        assertEquals(URL, savedDTO.getURL());
    }

    @Test
    void patchCustomerTest() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(NAME);
        customerDTO.setLastName(LAST_NAME);

        Customer customer = new Customer();
        customer.setFirstName("Bjorn");
        customer.setLastName("Ironside");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        customer.setId(ID);

        when(customerRepository.findById(anyLong())).thenReturn(ofNullable(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO savedDTO = customerService.patchCustomer(ID, customerDTO);

        assertEquals(NAME, savedDTO.getFirstName());
        assertEquals(LAST_NAME, savedDTO.getLastName());
        assertEquals(URL, savedDTO.getURL());
    }

    @Test
    void deleteCustomerById() {
        customerService.deleteCustomerById(ID);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }

}