package com.baraki.librarymanagement.service;

import com.baraki.librarymanagement.model.Patron;
import com.baraki.librarymanagement.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing patrons.
 */
@Service
public class PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    /**
     * Retrieves all patrons from the repository.
     *
     */
    public List<Patron> findAllPatrons() {
        return patronRepository.findAll();
    }

    /**
     * Retrieves a patron by their ID.
     *
     * @return an Optional containing the patron if found, or empty otherwise.
     */
    public Optional<Patron> findPatronById(Long id) {
        return patronRepository.findById(id);
    }

    /**
     * Saves a patron to the repository.
     *
     * @return the saved patron.
     */
    @Transactional
    public Patron savePatron(Patron patron) {
        return patronRepository.save(patron);
    }

    /**
     * Updates a patron with new details.
     *
     * @return the updated patron.
     */
    @Transactional
    public Optional<Patron> updatePatron(Long patronId, Patron newPatronDetails) {
        return patronRepository.findById(patronId).map(patron -> {
            patron.setName(newPatronDetails.getName());
            patron.setContactInformation(newPatronDetails.getContactInformation());
            return patronRepository.save(patron);
        });
    }

    /**
     * Deletes a patron from the repository.
     *
     */
    @Transactional
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}