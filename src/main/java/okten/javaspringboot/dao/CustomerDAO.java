package okten.javaspringboot.dao;

import okten.javaspringboot.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {

}
