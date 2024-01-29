package com.baraki.librarymanagement.controllers;
import com.baraki.librarymanagement.model.Patron;
import com.baraki.librarymanagement.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * REST controller for managing patrons.
 */
@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    /**
     * GET  /api/patrons : Get all the patrons.
     * @return the ResponseEntity with status 200 (OK) and the list of patrons in body.
     */
    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() {
        List<Patron> patrons = patronService.findAllPatrons();
        return ResponseEntity.ok().body(patrons);
    }

    /**
     * GET  /api/patrons/{id} : Get the "id" patron.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the patron, or with status 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        return patronService.findPatronById(id)
                .map(patron -> ResponseEntity.ok().body(patron))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /api/patrons : Create a new patron.
     *
     * @return the ResponseEntity with status 201 (Created) and with body the new patron, or with status 400 (Bad Request) if the patron has already an ID.
     */
    @PostMapping
    public ResponseEntity<Patron> createPatron(@Valid @RequestBody Patron patron) {
        if (patron.getId() != null) {
            return ResponseEntity.badRequest().body(null); // ID should not be present for a new creation
        }
        Patron result = patronService.savePatron(patron);
        return ResponseEntity.created(URI.create("/api/patrons/" + result.getId())).body(result);
    }

    /**
     * PUT  /api/patrons/{id} : Updates an existing patron.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the updated patron,
     * or with status 400 (Bad Request) if the patron is not valid,
     * or with status 500 (Internal Server Error) if the patron couldn't be updated.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @Valid @RequestBody Patron patronDetails) {
        if (patronDetails.getId() == null || !patronDetails.getId().equals(id)) {
            return ResponseEntity.badRequest().build(); // ID mismatch or missing ID in the request body
        }
        return patronService.updatePatron(id, patronDetails)
                .map(updatedPatron -> ResponseEntity.ok().body(updatedPatron))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * DELETE  /api/patrons/{id} : delete the "id" patron.
     *
     * @return the ResponseEntity with status 200 (OK).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.ok().build();
    }
}