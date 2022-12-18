package okten.javaspringboot.dao;

import okten.javaspringboot.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {
    @Transactional
    @Modifying
    @Query("update Customer c set c.name = ?1, c.surname = ?2, c.email = ?3 where c.id = ?4")
    void updateNameAndSurnameAndEmailById(String name, String surname, String email, int id);
}
