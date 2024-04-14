package com.general.loan.controller;

import com.general.loan.dto.CustomerDto;
import com.general.loan.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);


    @Autowired
    CustomerService customerService;


    @PostMapping("/customer")
    public String saveCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @PostMapping("/customer-update")
    public String saveCustomer(@RequestParam("customerId") String customerId, @RequestParam("adharCard") MultipartFile adharCard) throws IOException {
        return customerService.updateCustomer(customerId, adharCard);
    }

    @GetMapping("/customer")
    public List<CustomerDto> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/customerByAgentId/{userId}")
    public List<CustomerDto> getCustomerByAgentId(@PathVariable("userID") String userId) {
        return customerService.getCustomerByAgentId(userId);
    }

}
