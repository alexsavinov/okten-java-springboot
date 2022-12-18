package okten.javaspringboot.controllers;

import lombok.AllArgsConstructor;
import okten.javaspringboot.dao.CustomerDAO;
import okten.javaspringboot.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerDAO customerDAO;

    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(customerDAO.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerDAO.save(customer), HttpStatus.CREATED);
    }
}
