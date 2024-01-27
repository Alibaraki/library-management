package com.baraki.librarymanagement.controllers;

import com.baraki.librarymanagement.model.BorrowingRecord;
import com.baraki.librarymanagement.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * REST controller for managing borrowing records.
 */
@RestController
@RequestMapping("/api/borrowing-records")
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;

    @Autowired
    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    /**
     * GET  /api/borrowing-records : Get all borrowing records.
     * @return the ResponseEntity with status 200 (OK) and the list of borrowing records in body.
     */
    @GetMapping
    public ResponseEntity<List<BorrowingRecord>> getAllBorrowingRecords() {
        List<BorrowingRecord> records = borrowingRecordService.findAllBorrowingRecords();
        return ResponseEntity.ok().body(records);
    }

    /**
     * GET  /api/borrowing-records/{id} : Get the "id" borrowing record.
     * @param id the id of the borrowing record to retrieve.
     * @return the ResponseEntity with status 200 (OK) and with body the borrowing record, or with status 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecord> getBorrowingRecordById(@PathVariable Long id) {
        return borrowingRecordService.findBorrowingRecordById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /api/borrowing-records : Create a new borrowing record.
     * @param borrowingRecord the borrowing record to create.
     * @return the ResponseEntity with status 201 (Created) and with body the new borrowing record, or with status 400 (Bad Request) if the borrowing record has already an ID.
     */
    @PostMapping
    public ResponseEntity<BorrowingRecord> createBorrowingRecord(@Valid @RequestBody BorrowingRecord borrowingRecord) {
        if (borrowingRecord.getId() != null) {
            return ResponseEntity.badRequest().body(null); // ID should not be present for a new creation
        }
        BorrowingRecord result = borrowingRecordService.saveBorrowingRecord(borrowingRecord);
        return ResponseEntity.created(URI.create("/api/borrowing-records/" + result.getId())).body(result);
    }

    /**
     * PUT  /api/borrowing-records/{id} : Updates an existing borrowing record.
     * @param id the id of the borrowing record to update.
     * @param borrowingRecordDetails the borrowing record details to update.
     * @return the ResponseEntity with status 200 (OK) and with body the updated borrowing record,
     * or with status 400 (Bad Request) if the borrowing record is not valid,
     * or with status 500 (Internal Server Error) if the borrowing record couldn't be updated.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BorrowingRecord> updateBorrowingRecord(@PathVariable Long id,@Valid @RequestBody BorrowingRecord borrowingRecordDetails) {
        if (borrowingRecordDetails.getId() == null) {
            return ResponseEntity.badRequest().body(null); // ID is required for update
        }
        BorrowingRecord result = borrowingRecordService.saveBorrowingRecord(borrowingRecordDetails);
        return ResponseEntity.ok().body(result);
    }

    /**
     * DELETE  /api/borrowing-records/{id} : delete the "id" borrowing record.
     * @param id the id of the borrowing record to delete.
     * @return the ResponseEntity with status 200 (OK).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowingRecord(@PathVariable Long id) {
        borrowingRecordService.deleteBorrowingRecord(id);
        return ResponseEntity.ok().build();
    }

    // Additional endpoints or utility methods can be added here as needed
}