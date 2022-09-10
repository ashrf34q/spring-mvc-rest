package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getAllCustomers();

    public CustomerDTO getCustomerById(Long id);

    public CustomerDTO createCustomer(CustomerDTO customerDTO);
}
