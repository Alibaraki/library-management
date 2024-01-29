package com.baraki.librarymanagement.service;

import com.baraki.librarymanagement.model.BorrowingRecord;
import com.baraki.librarymanagement.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing borrowing records.
 */
@Service
public class BorrowingRecordService {

    private final BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
    }

    /**
     * Retrieves all borrowing records from the repository.
     * @return a list of all borrowing records.
     */
    public List<BorrowingRecord> findAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    /**
     * Retrieves a borrowing record by its ID.
     *
     * @return an Optional containing the borrowing record if found, or empty otherwise.
     */
    public Optional<BorrowingRecord> findBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id);
    }

    /**
     * Saves a borrowing record to the repository.
     *
     * @return the saved borrowing record.
     */

    @Transactional
    public BorrowingRecord saveBorrowingRecord(BorrowingRecord borrowingRecord) {
        return borrowingRecordRepository.save(borrowingRecord);
    }

    /**
     * Deletes a borrowing record from the repository.
     *
     */
    @Transactional
    public void deleteBorrowingRecord(Long id) {
        borrowingRecordRepository.deleteById(id);
    }
}