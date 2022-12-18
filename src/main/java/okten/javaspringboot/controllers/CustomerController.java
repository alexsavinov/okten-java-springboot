package okten.javaspringboot.controllers;

import lombok.AllArgsConstructor;
import okten.javaspringboot.dao.CustomerDAO;
import okten.javaspringboot.models.Customer;
import okten.javaspringboot.models.dto.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerDAO customerDAO;

    /* Get all Customers */
    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(customerDAO.findAll(), HttpStatus.OK);
    }

    /* Get Customer by id */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        return new ResponseEntity<>(customerDAO.findById(id).get(), HttpStatus.OK);
    }

    /* Add new Customer */
    @PostMapping("")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = Customer.builder()
                .name(customerDTO.getName())
                .surname(customerDTO.getSurname())
                .email(customerDTO.getEmailAddress())
                .build();
        return new ResponseEntity<>(customerDAO.save(customer), HttpStatus.CREATED);
    }

    /* Delete Customer by id */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCustomerById(@PathVariable int id) {
        customerDAO.deleteById(id);
    }

    /* Update customer by id and data */
    @PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        customerDAO.updateNameAndSurnameAndEmailById(
                customerDTO.getName(),
                customerDTO.getSurname(),
                customerDTO.getEmailAddress(),
                id);
        return new ResponseEntity<>(customerDAO.findById(id).get(), HttpStatus.CREATED);
    }

}
