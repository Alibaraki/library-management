package com.baraki.librarymanagement.repository;

import com.baraki.librarymanagement.model.BorrowingRecord;
import com.baraki.librarymanagement.model.Book;
import com.baraki.librarymanagement.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the BorrowingRecord entity.
 *
 */
@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    /**
     * Find borrowing records by book.
     *
     * @return A list of borrowing records for the specified book.
     */
    List<BorrowingRecord> findByBook(Book book);

    /**
     * Find borrowing records by patron.
     *
     * @return A list of borrowing records for the specified patron.
     */
    List<BorrowingRecord> findByPatron(Patron patron);

    /**
     * Find borrowing records by the borrow date.
     *
     * @return A list of borrowing records for the specified borrow date.
     */
    List<BorrowingRecord> findByBorrowDate(Date borrowDate);
}