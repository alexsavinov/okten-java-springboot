package okten.javaspringboot.controllers;

import okten.javaspringboot.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class MainController {

    ArrayList<Customer> customers = new ArrayList<>();

    public MainController() {
        customers.add(new Customer(1, "Leanne Graham", "Sincere@april.biz"));
        customers.add(new Customer(2, "Ervin Howell", "Shanna@melissa.tv"));
        customers.add(new Customer(3, "Clementine Bauch", "Nathan@yesenia.net"));
        customers.add(new Customer(4, "Patricia Lebsack", "Julianne.OConner@kory.org"));
        customers.add(new Customer(5, "Chelsey Dietrich", "Lucio_Hettinger@annie.ca"));
        customers.add(new Customer(6, "Kurtis Weissnat", "Karley_Dach@jasper.info"));
    }

    /* Get all customers */
    @GetMapping({"/customers"})
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(this.customers, HttpStatus.OK);
    }

    /* Get customer by id */
    @GetMapping({"/customers/{id}"})
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        return new ResponseEntity<>(
                this.customers.stream()
                        .filter(customer -> customer.getId() == id)
                        .findFirst()
                        .get(),
                HttpStatusCode.valueOf(200)
        );
    }

    /* Add new customer */
    @PostMapping("/customers")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        this.customers.add(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    /* Delete customer by id */
    @DeleteMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable int id) {
        this.customers.removeIf(customer -> Objects.equals(customer.getId(), id));
    }

    /* Replace customer by id and data */
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> replaceCustomer(@PathVariable int id, @RequestBody Customer customer) {
        int index = this.customers.indexOf(findCustomerById(id));
        this.customers.set(index, customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    /* Update customer by id and data */
    @PatchMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        Customer customerFound = findCustomerById(id);
        customerFound.setName(customer.getName());
        customerFound.setEmail(customer.getEmail());
        return new ResponseEntity<>(customerFound, HttpStatus.CREATED);
    }

    private Customer findCustomerById(int id) {
        return this.customers.stream()
                .filter(c -> Objects.equals(c.getId(), id))
                .findFirst()
                .get();
    }

}
