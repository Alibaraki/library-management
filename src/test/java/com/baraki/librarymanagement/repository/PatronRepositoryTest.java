package com.baraki.librarymanagement.repository;

import com.baraki.librarymanagement.model.Patron;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PatronRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PatronRepository patronRepository;

    private Patron patron;

    @BeforeEach
    public void setUp() {
        // Create and persist new Patron
        patron = new Patron();
        patron.setName("John Doe");
        patron.setContactInformation("john.doe@example.com");
        entityManager.persist(patron);
        entityManager.flush();
    }

    @Test
    public void whenFindByName_thenReturnPatronList() {
        // Execute the query method
        List<Patron> foundPatrons = patronRepository.findByName(patron.getName());
        assertThat(foundPatrons)
                .hasSize(1)
                .extracting(Patron::getName)
                .containsOnly(patron.getName());
    }

    @Test
    public void whenFindByContactInformation_thenReturnPatron() {
        // Execute the query method
        Optional<Patron> foundPatron = patronRepository.findByContactInformation(patron.getContactInformation());
        assertThat(foundPatron)
                .isPresent()
                .contains(patron);
    }
}