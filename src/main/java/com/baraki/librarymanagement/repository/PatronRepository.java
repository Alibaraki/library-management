package com.baraki.librarymanagement.repository;

import com.baraki.librarymanagement.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the Patron entity.
 * This interface extends JpaRepository, providing CRUD operations and more for Patron entities.
 */
@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

    /**
     * Find patrons by name.
     * @param name The name of the patron.
     * @return A list of patrons with the specified name.
     */
    List<Patron> findByName(String name);

    /**
     * Find a patron by their contact information.
     * @param contactInformation The contact information of the patron.
     * @return An Optional containing the patron if found, or empty otherwise.
     */
    Optional<Patron> findByContactInformation(String contactInformation);

    // Additional custom query methods can be defined here
}