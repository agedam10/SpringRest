package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    //get all the customers
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    //get a single customer by id
    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id) {

        Customer customer = customerService.getCustomer(id);
        if (id > getCustomers().size() || id <0) {
            throw new CustomerNotFoundException("Customer id not found - " + id);
        }
        return customer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setId(0);
        customerService.saveCustomer(customer);
        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return customer;
    }

    @DeleteMapping("/customers/{customer}")
    public String deleteCustomer(@PathVariable int id) {
        Customer tempCustomer = customerService.getCustomer(id);
        if (id > getCustomers().size() || id <0) {
            throw new CustomerNotFoundException("Customer id not found - " + id);
        }
        customerService.deleteCustomer(id);
        return "Deleted customer with id - " + id;
    }
}
