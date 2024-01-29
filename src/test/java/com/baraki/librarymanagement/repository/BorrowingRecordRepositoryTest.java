package com.baraki.librarymanagement.repository;

import com.baraki.librarymanagement.model.Book;
import com.baraki.librarymanagement.model.BorrowingRecord;
import com.baraki.librarymanagement.model.Patron;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BorrowingRecordRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    private BorrowingRecord record;
    private Book book;
    private Patron patron;

    @BeforeEach
    public void setUp() {
        // Create and persist associated Book
        book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPublicationYear(2020);
        book.setIsbn("1234567890");
        entityManager.persist(book);

        // Create and persist associated Patron
        patron = new Patron();
        patron.setName("Test Patron");
        patron.setContactInformation("Test Contact Info");
        entityManager.persist(patron);

        // Create and persist BorrowingRecord
        record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowDate(new Date());
        entityManager.persist(record);
        entityManager.flush();
    }

    @Test
    public void whenFindByBook_thenReturnBorrowingRecords() {
        // Execute the query method
        List<BorrowingRecord> foundRecords = borrowingRecordRepository.findByBook(book);
        assertThat(foundRecords).hasSize(1).extracting(BorrowingRecord::getBook).containsOnly(book);
    }

    @Test
    public void whenFindByPatron_thenReturnBorrowingRecords() {
        // Execute the query method
        List<BorrowingRecord> foundRecords = borrowingRecordRepository.findByPatron(patron);
        assertThat(foundRecords).hasSize(1).extracting(BorrowingRecord::getPatron).containsOnly(patron);
    }

    @Test
    public void whenFindByBorrowDate_thenReturnBorrowingRecords() {
        // Execute the query method
        List<BorrowingRecord> foundRecords = borrowingRecordRepository.findByBorrowDate(record.getBorrowDate());
        assertThat(foundRecords).hasSize(1).extracting(BorrowingRecord::getBorrowDate).containsOnly(record.getBorrowDate());
    }
}