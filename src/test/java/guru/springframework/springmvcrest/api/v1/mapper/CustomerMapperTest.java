package guru.springframework.springmvcrest.api.v1.mapper;

import guru.springframework.springmvcrest.api.v1.model.CustomerDTO;
import guru.springframework.springmvcrest.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    public static final String FIRSTNAME = "Khamzat";
    public static final String LASTNAME = "Chimaev";

    CustomerMapper customerMapper;

    @BeforeEach
    void setUp(){
        customerMapper = CustomerMapper.INSTANCE;
    }

    @Test
    void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(FIRSTNAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());
    }

    @Test
    void customerDTOToCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRSTNAME);
        customerDTO.setLastName(LASTNAME);
        customerDTO.setId(1L);

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        assertEquals(FIRSTNAME, customer.getFirstName());
        assertEquals(LASTNAME, customer.getLastName());
        assertEquals(1L, customer.getId());
    }
}