package com.baraki.librarymanagement.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class BorrowingRecordTest {

    private BorrowingRecord borrowingRecord;
    private Book book;
    private Patron patron;
    private Date borrowDate;
    private Date returnDate;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        patron = new Patron();
        patron.setId(1L);
        borrowDate = new Date();
        returnDate = new Date();

        borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1L);
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(borrowDate);
        borrowingRecord.setReturnDate(returnDate);
    }

    @Test
    void testGetterAndSetter() {
        assertEquals(1L, borrowingRecord.getId());
        assertEquals(book, borrowingRecord.getBook());
        assertEquals(patron, borrowingRecord.getPatron());
        assertEquals(borrowDate, borrowingRecord.getBorrowDate());
        assertEquals(returnDate, borrowingRecord.getReturnDate());
    }

    @Test
    void testEquals() {
        BorrowingRecord record2 = new BorrowingRecord();
        record2.setId(1L);

        assertTrue(borrowingRecord.equals(record2) && record2.equals(borrowingRecord));
        assertEquals(borrowingRecord.hashCode(), record2.hashCode());
    }

    @Test
    void testNotEquals() {
        BorrowingRecord record2 = new BorrowingRecord();
        record2.setId(2L);

        assertFalse(borrowingRecord.equals(record2));
        assertNotEquals(borrowingRecord.hashCode(), record2.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = String.format("BorrowingRecord{id=1, book=%s, patron=%s, borrowDate=%s, returnDate=%s}",
                book.toString(), patron.toString(), borrowDate.toString(), returnDate.toString());
        assertEquals(expectedString, borrowingRecord.toString());
    }
}