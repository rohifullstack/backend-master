package com.general.loan.service;

import com.general.loan.dto.CustomerDto;
import com.general.loan.entity.CustomerEntity;
import com.general.loan.entity.UserEntity;
import com.general.loan.exception.UserDoesNotExist;
import com.general.loan.repository.CustomerRepository;
import com.general.loan.repository.UserRepository;
import com.general.loan.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    public static final String ACTIVE = "ACTIVE";


    public String saveCustomer(CustomerDto customerDto) {

        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerId(customerDto.getFirstName() + customerDto.getMobileNumber());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBirthDate(customerDto.getBirthDate());
        customer.setUserId(customerDto.getUserId());
//        customer.setMobileNumber(customerDto.getMobileNumber());
//        customer.setEmailId(customerDto.getEmailId());
//        customer.setCity(customerDto.getCity());
//        customer.setState(customerDto.getState());
//        customer.setZipCode(customerDto.getZipCode());
//        customer.setAdharCardNumber(customerDto.getAdharCardNumber());
//        customer.setMonthlyIncome(customerDto.getMonthlyIncome());
//        customer.setLoanPurpose(customerDto.getLoanPurpose());
//        customer.setLoanAmount(customerDto.getLoanAmount());
//        customer.setLoanPeriod(customerDto.getLoanPeriod());
        //   byte[] bytes = ImageUtils.compressImage(customerDto.getAdharFile());
        //   customer.setAdharFile(bytes);
        // customer.setPanCard(customerDto.getPanCard());
        // customer.setPaymentSlip(customerDto.getPaymentSlip());
        try {
            CustomerEntity save = customerRepository.save(customer);
            if (save != null) {
                return save.getCustomerId();
            }
        } catch (Exception e) {
            //will create new Customer error
        }
        return null;
    }

    public String updateCustomer(String customerId, MultipartFile adharCard) throws IOException {
        Optional<CustomerEntity> byCustomerId = customerRepository.findById(customerId);
        if (byCustomerId.isPresent()) {
            byte[] bytes = ImageUtils.compressImage(
                    adharCard.getBytes());
            byCustomerId.get().setAdharFile(bytes);
            customerRepository.save(byCustomerId.get());
        }
        return "image uploaded successfully..";
    }


    public List<CustomerDto> getAllCustomer() {
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        return constructCustomerDtoList(customerEntityList);
    }

    private List<CustomerDto> constructCustomerDtoList(List<CustomerEntity> customerEntityList) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        customerEntityList.forEach(customer -> {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomerId(customer.getCustomerId());
            customerDto.setFirstName(customer.getFirstName());
            customerDto.setLastName(customer.getLastName());
            customerDto.setBirthDate(customer.getBirthDate());
            customerDto.setMobileNumber(customer.getMobileNumber());
            customerDto.setEmailId(customer.getEmailId());
            customerDto.setCity(customer.getCity());
            customerDto.setState(customer.getState());
            customerDto.setZipCode(customer.getZipCode());
            customerDto.setAdharCardNumber(customer.getAdharCardNumber());
            customerDto.setMonthlyIncome(customer.getMonthlyIncome());
            customerDto.setLoanPurpose(customer.getLoanPurpose());
            customerDto.setLoanAmount(customer.getLoanAmount());
            customerDto.setLoanPeriod(customer.getLoanPeriod());
            if(customer.getAdharFile() != null) {
                customerDto.setAdharFile(ImageUtils.decompressImage(customer.getAdharFile()));
            }
            customerDto.setUserId(customer.getUserId());
            customerDtos.add(customerDto);

        });
        return customerDtos;
    }

    public List<CustomerDto> getCustomerByAgentId(String userId) {
        Optional<UserEntity> byUserIdAndStatus = userRepository.findByUserIdAndStatus(userId, ACTIVE);
        if(!byUserIdAndStatus.isPresent()){
            throw new UserDoesNotExist("invalid agent");
        }
        List<CustomerEntity> byUserId = customerRepository.findByUserId(userId);
        return constructCustomerDtoList(byUserId);
    }



}
