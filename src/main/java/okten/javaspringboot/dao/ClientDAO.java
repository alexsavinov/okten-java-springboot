package okten.javaspringboot.dao;

import okten.javaspringboot.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientDAO extends JpaRepository<Client, Integer> {

    @Transactional
    @Modifying
    @Query("update Client c set c.name = ?1, c.surname = ?2, c.email = ?3 where c.id = ?4")
    void updateNameAndSurnameAndEmailById(String name, String surname, String email, int id);

    @Query("select c from Client c where c.name = :name")
    List<Client> findByName(@Param("name") String name);

    @Query("select c from Client c where c.surname = :surname")
    List<Client> findBySurname(@Param("surname") String surname);
}
